Demo Spring Boot application which represents a store management tool:

The application structure contains:
Two Entities Product and User.
Two Repositories ProductRepository and UserRepository. 
Two services, ProductService and AdminService.
Three RestControllers: ProductController, AdminController and AuthenticationController.

ProductController has Get, Post and Delete Requests which based on the role that the user has, may, or may not be accessible.
AdminController has Get, Post and Delete Requests that are only accessible to users with the Admin role.
AuthenticationController has only Post request for registering a new user and for authenticating existing users. 
The two endpoints are accessible to anyone.

I am using PostgresSQL for my database. Also, I am using CommandLineRunner to add an admin to the database when the application 
starts running (can be found in ApplicationConfig class).

Admin credentials: username: james@mail.com, password: password.

When we register or authenticate a user, a token is generated that may be used afterward
to access the endpoints that require roles to be accessible.







