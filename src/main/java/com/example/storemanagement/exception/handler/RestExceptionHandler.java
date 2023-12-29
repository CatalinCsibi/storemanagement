package com.example.storemanagement.exception.handler;

import com.example.storemanagement.exception.ErrorTypeEnum;
import com.example.storemanagement.exception.ProductAlreadyRegisteredException;
import com.example.storemanagement.exception.ProductNotRegisteredException;
import com.example.storemanagement.exception.UserAlreadyRegisteredException;
import com.example.storemanagement.exception.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ProductAlreadyRegisteredException.class)
    public ResponseEntity<ApiError> handle(final ProductAlreadyRegisteredException exception) {
        log.info("The following exception occurred: " + exception);

        ApiError apiError = buildApiErrorResponse(ErrorTypeEnum.PRODUCT_ALREADY_REGISTERED,
                exception.getMessage(), BAD_REQUEST);

        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotRegisteredException.class)
    public ResponseEntity<ApiError> handle(final ProductNotRegisteredException exception) {
        log.info("The following exception occurred: " + exception);

        ApiError apiError = buildApiErrorResponse(ErrorTypeEnum.PRODUCT_NOT_REGISTERED,
                exception.getMessage(), BAD_REQUEST);

        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ApiError> handle(final UserAlreadyRegisteredException exception) {
        log.info("The following exception occurred: " + exception);

        ApiError apiError = buildApiErrorResponse(ErrorTypeEnum.USER_ALREADY_REGISTERED,
                exception.getMessage(), BAD_REQUEST);

        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    private ApiError buildApiErrorResponse(ErrorTypeEnum errorType, String message, HttpStatus status) {
        return ApiError.builder()
                .dateTime(LocalDateTime.now())
                .errorType(errorType)
                .message(message)
                .status(status)
                .build();
    }
}
