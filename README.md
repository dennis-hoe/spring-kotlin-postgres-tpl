# Spring Kotlin PostgreSQL Template

A production-ready Spring Boot template using Kotlin and PostgreSQL with a REST API example.

## Features

- **Spring Boot 3.2.0** with Kotlin
- **PostgreSQL** database with Docker Compose
- **Flyway** database migrations
- **Spring Data JPA** for data persistence
- **REST API** with CRUD operations
- **Spring Boot Actuator** for health checks and monitoring
- **Unit and Integration tests** with JUnit 5 and Testcontainers
- **Gradle** with Kotlin DSL

## Prerequisites

- Java 17 or higher
- Docker and Docker Compose (for PostgreSQL)

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/dennis-hoe/spring-kotlin-postgres-tpl.git
cd spring-kotlin-postgres-tpl
```

### 2. Start PostgreSQL

```bash
docker-compose up -d
```

This will start a PostgreSQL instance on port 5432 with:
- Database: `demo`
- Username: `postgres`
- Password: `postgres`

### 3. Build the project

```bash
./gradlew build
```

### 4. Run the application

```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`

## API Endpoints

### User Management

- **GET** `/api/users` - Get all users
- **GET** `/api/users/{id}` - Get user by ID
- **POST** `/api/users` - Create a new user
  ```json
  {
    "email": "user@example.com",
    "name": "John Doe"
  }
  ```
- **PUT** `/api/users/{id}` - Update an existing user
  ```json
  {
    "email": "newemail@example.com",
    "name": "Jane Doe"
  }
  ```
- **DELETE** `/api/users/{id}` - Delete a user

### Health Check

- **GET** `/actuator/health` - Application health status

## Testing

Run all tests:

```bash
./gradlew test
```

The tests use Testcontainers to spin up a PostgreSQL container automatically.

## Database Migrations

Database migrations are managed by Flyway and located in `src/main/resources/db/migration/`.

- `V1__create_users_table.sql` - Creates the users table

Migrations run automatically on application startup.

## Project Structure

```
src/
├── main/
│   ├── kotlin/
│   │   └── com/example/demo/
│   │       ├── DemoApplication.kt          # Main application class
│   │       ├── controller/                  # REST controllers
│   │       │   └── UserController.kt
│   │       ├── entity/                      # JPA entities
│   │       │   └── User.kt
│   │       ├── repository/                  # Spring Data repositories
│   │       │   └── UserRepository.kt
│   │       └── service/                     # Business logic
│   │           └── UserService.kt
│   └── resources/
│       ├── application.yml                  # Application configuration
│       └── db/migration/                    # Flyway migrations
│           └── V1__create_users_table.sql
└── test/
    ├── kotlin/
    │   └── com/example/demo/
    │       ├── DemoApplicationTests.kt      # Integration test
    │       └── service/
    │           └── UserServiceTest.kt       # Unit tests
    └── resources/
        └── application.yml                  # Test configuration
```

## Configuration

Application configuration is in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/demo
    username: postgres
    password: postgres
```

For production, override these values using environment variables or application profiles.

## Docker Support

Start the PostgreSQL database:

```bash
docker-compose up -d
```

Stop the database:

```bash
docker-compose down
```

Remove database volumes:

```bash
docker-compose down -v
```

## Development

### Adding a new entity

1. Create the entity class in `src/main/kotlin/com/example/demo/entity/`
2. Create a repository interface in `src/main/kotlin/com/example/demo/repository/`
3. Create a service class in `src/main/kotlin/com/example/demo/service/`
4. Create a controller in `src/main/kotlin/com/example/demo/controller/`
5. Add a Flyway migration in `src/main/resources/db/migration/`

### Running in development mode

The application supports Spring Boot DevTools for hot reload during development.

## License

This is a template project and can be used freely for any purpose.
