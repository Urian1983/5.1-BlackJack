# S05T01 - Blackjack API with Spring Boot WebFlux

Reactive REST API for a Blackjack game developed with Spring Boot WebFlux. It utilizes a full reactive architecture with MySQL (R2DBC) for player management and MongoDB for game management.

## 📝 Project Description

This project implements a complete API to play Blackjack with the following features:

- **Reactive Architecture**: Implementation with Spring WebFlux for non-blocking operations.
- **Dual Database**: Utilization of MySQL (reactive with R2DBC) and MongoDB.
- **Global Exception Handling**: GlobalExceptionHandler for centralized error control.
- **Security**: Basic authentication with Spring Security.
- **Documentation**: Swagger/OpenAPI for interactive documentation.
- **Testing**: Unit tests with JUnit and Mockito.
- **Dockerization**: Fully containerized application.

## 🚀 Technologies

- **Java 21**
- **Spring Boot 3.5.9**
- **Spring WebFlux** (reactive programming)
- **Spring Data R2DBC** (reactive MySQL)
- **Spring Data MongoDB Reactive**
- **Spring Security** (basic authentication)
- **MySQL 8.4**
- **MongoDB**
- **Docker** and **Docker Compose**
- **Maven**
- **JUnit 5** and **Mockito** (testing)
- **Swagger/OpenAPI** (documentation)

## 📋 Prerequisites

To run this project, you need:

- **Docker Desktop** installed and running.
- Free ports: `3306` (MySQL), `27017` (MongoDB), `8080` (application).

## 🐳 Run with Docker (Recommended)

### Option 1: Use the Docker Hub image (fastest)

#### For Linux / Mac / Git Bash:

```bash
# 1. Create the network
docker network create s05t01_network

# 2. Start MySQL
docker run -d \
  --name mysql_S05T01 \
  --network s05t01_network \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=s05t01_db \
  mysql:8.4

# 3. Start MongoDB
docker run -d \
  --name mongo_S05T01 \
  --network s05t01_network \
  -p 27017:27017 \
  -e MONGO_INITDB_DATABASE=s05t01_mongo \
  mongo:latest

# 4. Run the application
docker run -d \
  --name spring-app \
  --network s05t01_network \
  -p 8080:8080 \
  -e SPRING_R2DBC_URL=r2dbc:mysql://mysql_S05T01:3306/s05t01_db \
  -e SPRING_DATA_MONGODB_URI=mongodb://mongo_S05T01:27017/s05t01_mongo \
  urian1983/s05t01-app:latest
