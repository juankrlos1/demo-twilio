# Twilio Messaging API
This project implements a RESTful API for sending and managing messages using Twilio's messaging services. It allows users to send messages to one or multiple recipients, view a history of sent messages, and delete messages by ID. The API is built with Spring Boot and uses SQLite as its database for storing message details and logs.

# Features
* Send Messages: Send text messages to a single recipient or multiple recipients at once.
* View Message History: Retrieve a list of messages that have been sent, including details like recipient, message content, and timestamp.
* Delete Messages: Remove sent messages from the history by their ID.
# Configuration
Before running the application, ensure you have set up the following properties in your application.properties file:

```
# DataSource Configuration
pring.datasource.url=jdbc:sqlite:twilio.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update

# Twilio Configuration
twilio.account_sid=AC5d788d5edd14b555238c8fb0e7666fcc
twilio.auth_token=2ed708a4ae3b2b35dae718951117abc6
twilio.from_phone=+12293982925

# Springdoc OpenAPI Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

# Running the Application
To run the application, use the following command in the terminal at the root of the project:

```bash
./mvnw spring-boot:run
```
API Documentation
Once the application is running, you can access the Swagger UI to view the API documentation, try out the endpoints, and see the available operations:

```bash
http://localhost:8080/swagger-ui.html
```
# Development
This project is developed with:

* Spring Boot version 2.6.x
* Java JDK 17
* SQLite for the database
* Hibernate for ORM
* Twilio Java SDK for messaging integration
Contributing
Contributions to the Twilio Messaging API are welcome. Please follow the standard fork and pull request workflow. If you have any suggestions or encounter issues, feel free to open an issue or send a pull request.