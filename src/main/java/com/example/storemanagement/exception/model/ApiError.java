package com.example.storemanagement.exception.model;

import com.example.storemanagement.exception.ErrorTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {

    private LocalDateTime dateTime;

    private ErrorTypeEnum errorType;

    private String message;

    private HttpStatus status;
}
