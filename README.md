# 🃏 Blackjack Game - Backend API

[![Docker Hub](https://img.shields.io/badge/Docker%20Hub-urian1983%2Fblackjack--game-blue?logo=docker)](https://hub.docker.com/repository/docker/urian1983/blackjack-game/general)
[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.10-brightgreen?logo=spring)](https://spring.io/projects/spring-boot)

A reactive Blackjack game backend built with Spring Boot WebFlux, featuring dual database persistence (MongoDB for game data and MySQL for player rankings) and comprehensive REST API endpoints.

**🐳 Docker Image**: [urian1983/blackjack-game](https://hub.docker.com/repository/docker/urian1983/blackjack-game/general)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Security](#security)
- [Database Schema](#database-schema)
- [Game Rules](#game-rules)
- [Development](#development)

## 🎯 Overview

This project implements a complete Blackjack game backend with reactive programming principles. Players can create games, make moves (hit/stand), and compete in a global ranking system. The application uses a dual-database approach to optimize different data access patterns.

**Sprint 5 - Task 1** by Josep Julià Roca Blanco

## ✨ Features

- 🎮 **Complete Blackjack Gameplay**
  - Deal initial cards
  - Hit (draw additional cards)
  - Stand (pass turn to dealer)
  - Automatic dealer logic (hits until 17+)
  - Win/lose/draw detection

- 🏆 **Player Ranking System**
  - Track wins per player
  - Global leaderboard
  - Player name management (admin only)

- 🔐 **Security**
  - Role-based access control (ADMIN, PLAYER)
  - Basic HTTP authentication
  - Protected endpoints

- 📊 **Dual Database Architecture**
  - MongoDB for game sessions (flexible document storage)
  - MySQL for player rankings (relational integrity)

- 🚀 **Reactive Programming**
  - Non-blocking I/O with Project Reactor
  - WebFlux for reactive REST endpoints
  - R2DBC for reactive database access

## 🛠 Tech Stack

### Core Framework
- **Java 21**
- **Spring Boot 3.5.10**
- **Spring WebFlux** - Reactive web framework
- **Project Reactor** - Reactive streams implementation

### Databases
- **MongoDB** - Game session storage
- **MySQL 8.0** - Player ranking persistence
- **Spring Data R2DBC** - Reactive relational database access
- **Spring Data MongoDB Reactive** - Reactive MongoDB access

### Tools & Libraries
- **Lombok** - Reduce boilerplate code
- **MapStruct** - Object mapping
- **SpringDoc OpenAPI** - API documentation (Swagger)
- **Spring Security** - Authentication and authorization
- **Docker & Docker Compose** - Containerization

### Build Tool
- **Maven 3.9.12**

## 🏗 Architecture

### Project Structure

```
src/main/java/it/academy/sprint5/task1/blackjack/
├── config/                 # Configuration classes
│   ├── MongoConfig.java
│   ├── R2dbcConfig.java
│   ├── SecurityConfig.java
│   └── OpenAPIConfig.java
├── controller/             # REST endpoints
│   ├── GameController.java
│   └── RankingController.java
├── domain/                 # Domain models & business logic
│   ├── Game.java
│   ├── enums/
│   │   ├── GameState.java
│   │   ├── Rank.java
│   │   └── Suit.java
│   └── model/
│       ├── Card.java
│       ├── Dealer.java
│       ├── Deck.java
│       ├── Hand.java
│       ├── Player.java
│       └── PlayerRanking.java
├── dto/                    # Data transfer objects
│   ├── CardDTO.java
│   ├── GameResponseDTO.java
│   └── PlayerRankingDTO.java
├── exceptions/             # Custom exceptions
│   ├── GameNotFoundException.java
│   ├── IlegalMoveException.java
│   └── GlobalExceptionHandler.java
├── mapper/                 # MapStruct mappers
│   └── GameMapper.java
├── repository/             # Data access layer
│   ├── RankingRepository.java
│   └── mongodb/
│       └── GameRepository.java
└── service/                # Business logic
    ├── GameService.java
    └── RankingService.java
```

### Domain Model

The game follows classic Blackjack rules with the following key entities:

- **Game**: Main aggregate managing game state, player, dealer, and deck
- **Player**: Represents a game participant with a hand of cards
- **Dealer**: Specialized player with automated drawing logic (must hit until 17+)
- **Deck**: Standard 52-card deck with shuffle and draw operations
- **Hand**: Collection of cards with value calculation (including Ace logic)
- **Card**: Individual playing card with rank and suit

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Docker** and **Docker Compose** (for containerized deployment)
- **Maven 3.9+** (if running locally without Docker)

### Quick Start with Docker Hub 🐳

The easiest way to run the application is using the pre-built Docker image from Docker Hub:

1. **Pull the image**
   ```bash
   docker pull urian1983/blackjack-game:latest
   ```

2. **Create a docker-compose.yml file**
   ```yaml
   services:
     app:
       image: urian1983/blackjack-game:latest
       container_name: blackjack-app
       ports:
         - "8080:8080"
       environment:
         - SPRING_R2DBC_URL=r2dbc:mysql://mysql-db:3306/s05t01_db
         - SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/s05t01_mongo
       depends_on:
         - mysql-db
         - mongodb

     mysql-db:
       image: mysql:8.0
       container_name: blackjack-mysql
       environment:
         MYSQL_DATABASE: s05t01_db
         MYSQL_ROOT_PASSWORD: root
       ports:
         - "3306:3306"
       volumes:
         - mysql_data:/var/lib/mysql

     mongodb:
       image: mongo:latest
       container_name: blackjack-mongo
       ports:
         - "27017:27017"
       volumes:
         - mongo_data:/data/db

   volumes:
     mysql_data:
     mongo_data:
   ```

3. **Start the services**
   ```bash
   docker-compose up -d
   ```

4. **Access the application**
   - API: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

### Option 1: Docker Compose (Recommended)

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd blackjack
   ```

2. **Start all services**
   ```bash
   docker-compose up -d
   ```

   This will start:
   - Application (port 8080)
   - MySQL database (port 3306)
   - MongoDB (port 27017)

3. **Access the application**
   - API: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

### Option 2: Local Development

1. **Start databases manually**
   ```bash
   # MySQL
   docker run -d --name blackjack-mysql \
     -e MYSQL_DATABASE=s05t01_db \
     -e MYSQL_ROOT_PASSWORD=root \
     -p 3306:3306 \
     mysql:8.0

   # MongoDB
   docker run -d --name blackjack-mongo \
     -p 27017:27017 \
     mongo:latest
   ```

2. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

### Stopping the Application

```bash
docker-compose down
```

To remove volumes (database data):
```bash
docker-compose down -v
```

### 🔄 Alternative Installation Methods

#### Using Docker Run (without compose)

```bash
# Start MySQL
docker run -d --name blackjack-mysql \
  -e MYSQL_DATABASE=s05t01_db \
  -e MYSQL_ROOT_PASSWORD=root \
  -p 3306:3306 \
  mysql:8.0

# Start MongoDB
docker run -d --name blackjack-mongo \
  -p 27017:27017 \
  mongo:latest

# Start the application
docker run -d --name blackjack-app \
  -p 8080:8080 \
  -e SPRING_R2DBC_URL=r2dbc:mysql://host.docker.internal:3306/s05t01_db \
  -e SPRING_DATA_MONGODB_URI=mongodb://host.docker.internal:27017/s05t01_mongo \
  urian1983/blackjack-game:latest
```

#### Using Docker Network (recommended for docker run)

```bash
# Create network
docker network create blackjack-network

# Start MySQL
docker run -d --name blackjack-mysql \
  --network blackjack-network \
  -e MYSQL_DATABASE=s05t01_db \
  -e MYSQL_ROOT_PASSWORD=root \
  -p 3306:3306 \
  mysql:8.0

# Start MongoDB
docker run -d --name blackjack-mongo \
  --network blackjack-network \
  -p 27017:27017 \
  mongo:latest

# Start the application
docker run -d --name blackjack-app \
  --network blackjack-network \
  -p 8080:8080 \
  -e SPRING_R2DBC_URL=r2dbc:mysql://blackjack-mysql:3306/s05t01_db \
  -e SPRING_DATA_MONGODB_URI=mongodb://blackjack-mongo:27017/s05t01_mongo \
  urian1983/blackjack-game:latest
```

## 📚 API Documentation

Once the application is running, access the interactive API documentation:

**Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Authentication

Use Basic HTTP Authentication:

| Username | Password    | Role         |
|----------|-------------|--------------|
| admin    | admin1234   | ADMIN, PLAYER|
| player   | player1234  | PLAYER       |

### Key Endpoints

#### Game Management

```http
POST /game/new
Content-Type: application/json
Body: "John Doe"

Response:
{
  "id": "507f1f77bcf86cd799439011",
  "playerName": "John Doe",
  "playerHand": [
    {"rank": "ACE", "suit": "HEARTS"},
    {"rank": "KING", "suit": "SPADES"}
  ],
  "playerValue": 21,
  "dealerHand": [...],
  "dealerValue": 15,
  "status": "IN_PROGRESS"
}
```

```http
POST /game/{id}/hit
Authentication: Required (PLAYER role)
```

```http
POST /game/{id}/stand
Authentication: Required (PLAYER role)
```

```http
DELETE /game/{id}/delete
Authentication: Required (PLAYER role)
```

#### Player Ranking

```http
GET /player_ranking
Public endpoint

Response:
[
  {
    "playerName": "John Doe",
    "victories": 15
  },
  ...
]
```

```http
PUT /player_ranking/player/{playerId}
Content-Type: application/json
Body: "NewName"
Authentication: Required (ADMIN role)
```

## 🔐 Security

The application implements role-based access control:

### Public Endpoints
- `GET /player_ranking` - View global ranking
- `POST /game/new` - Create new game
- Swagger documentation endpoints

### Player Endpoints (Requires Authentication)
- `POST /game/{id}/hit` - Request card
- `POST /game/{id}/stand` - End turn
- `DELETE /game/{id}/delete` - Delete game

### Admin Endpoints (Requires ADMIN Role)
- `PUT /player_ranking/player/{playerId}` - Update player name

### Configuration

Security is configured in `SecurityConfig.java`:
- CSRF disabled (suitable for stateless API)
- Basic HTTP authentication
- In-memory user store (for demo purposes)

## 💾 Database Schema

### MongoDB - Games Collection

```javascript
{
  "_id": "507f1f77bcf86cd799439011",
  "player": {
    "name": "John Doe",
    "hand": {
      "cardsInHand": [
        {"rank": "ACE", "suit": "HEARTS"},
        {"rank": "KING", "suit": "SPADES"}
      ]
    }
  },
  "dealer": {
    "name": "Dealer",
    "hand": {
      "cardsInHand": [...]
    }
  },
  "deck": {
    "deck": [...]  // Remaining cards
  },
  "state": "PLAYER_WIN"
}
```

### MySQL - Player Ranking Table

```sql
CREATE TABLE player_ranking (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  games_won INT DEFAULT 0
);
```

## 🎲 Game Rules

### Blackjack Basics

1. **Objective**: Get closer to 21 than the dealer without going over (busting)

2. **Card Values**:
   - Number cards (2-10): Face value
   - Face cards (J, Q, K): 10 points
   - Ace: 1 or 11 (whichever is better)

3. **Gameplay Flow**:
   - Both player and dealer receive 2 cards initially
   - Player can **hit** (draw card) or **stand** (end turn)
   - If player busts (>21), player loses immediately
   - When player stands, dealer automatically plays
   - Dealer must hit until reaching 17 or higher
   - Compare final values to determine winner

4. **Win Conditions**:
   - `PLAYER_WIN`: Player's hand > Dealer's hand (both ≤21)
   - `DEALER_WIN`: Dealer's hand > Player's hand (both ≤21)
   - `PLAYER_BUST`: Player's hand > 21
   - `DEALER_BUST`: Dealer's hand > 21 (Player wins)
   - `DRAW`: Both hands have equal value

### Game States

- `IN_PROGRESS`: Game is active, player can make moves
- `PLAYER_BUST`: Player exceeded 21
- `DEALER_BUST`: Dealer exceeded 21 (player wins)
- `PLAYER_WIN`: Player has higher value than dealer
- `DEALER_WIN`: Dealer has higher value than player
- `DRAW`: Both have same value

## 🔧 Development

### Build the Project

```bash
./mvnw clean package
```

### Run Tests

```bash
./mvnw test
```

### Build Docker Image

```bash
docker build -t blackjack-game .
```

### Push to Docker Hub

```bash
# Tag the image
docker tag blackjack-game:latest urian1983/blackjack-game:latest

# Login to Docker Hub
docker login

# Push the image
docker push urian1983/blackjack-game:latest
```

### Pull from Docker Hub

```bash
docker pull urian1983/blackjack-game:latest
```

### Environment Variables

Configure via environment variables or `application.yml`:

```yaml
SPRING_R2DBC_URL: r2dbc:mysql://localhost:3306/s05t01_db
SPRING_R2DBC_USERNAME: root
SPRING_R2DBC_PASSWORD: root
SPRING_DATA_MONGODB_URI: mongodb://localhost:27017/s05t01_mongo
```

### Logging

Debug logging is enabled for:
- R2DBC queries: `org.springframework.data.r2dbc`
- MongoDB operations: `org.springframework.data.mongodb.core.ReactiveMongoTemplate`

## 📝 License

This project is part of IT Academy Sprint 5 - Task 1.

## 👤 Author

**Josep Julià Roca Blanco**
- Email: urianr@gmail.com

---
