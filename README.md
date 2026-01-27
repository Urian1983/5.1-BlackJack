# S05T01 - Blackjack API amb Spring Boot WebFlux

API REST reactiva per a un joc de Blackjack desenvolupada amb Spring Boot WebFlux. Utilitza arquitectura reactiva completa amb MySQL (R2DBC) per a la gestió de jugadors i MongoDB per a la gestió de partides.

## 📝 Descripció del Projecte

Aquest projecte implementa una API completa per jugar al Blackjack amb les següents característiques:

- **Arquitectura Reactiva**: Implementació amb Spring WebFlux per a operacions no bloquejants
- **Dual Database**: Utilització de MySQL (reactiu amb R2DBC) i MongoDB
- **Gestió d'Excepcions Global**: GlobalExceptionHandler per a un control centralitzat d'errors
- **Seguretat**: Autenticació bàsica amb Spring Security
- **Documentació**: Swagger/OpenAPI per a documentació interactiva
- **Testing**: Proves unitàries amb JUnit i Mockito
- **Dockerització**: Aplicació completament containeritzada

## 🚀 Tecnologies

- **Java 21**
- **Spring Boot 3.5.9**
- **Spring WebFlux** (programació reactiva)
- **Spring Data R2DBC** (MySQL reactiu)
- **Spring Data MongoDB Reactive**
- **Spring Security** (autenticació bàsica)
- **MySQL 8.4**
- **MongoDB**
- **Docker** i **Docker Compose**
- **Maven**
- **JUnit 5** i **Mockito** (testing)
- **Swagger/OpenAPI** (documentació)

## 📋 Prerequisits

Per executar aquest projecte necessites:

- **Docker Desktop** instal·lat i en execució
- Ports lliures: `3306` (MySQL), `27017` (MongoDB), `8080` (aplicació)

## 🐳 Executar amb Docker (Recomanat)

### Opció 1: Usar la imatge de Docker Hub (més ràpid)

#### Per a Linux / Mac / Git Bash:

```bash
# 1. Crear la xarxa
docker network create s05t01_network

# 2. Aixecar MySQL
docker run -d \
  --name mysql_S05T01 \
  --network s05t01_network \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=s05t01_db \
  mysql:8.4

# 3. Aixecar MongoDB
docker run -d \
  --name mongo_S05T01 \
  --network s05t01_network \
  -p 27017:27017 \
  -e MONGO_INITDB_DATABASE=s05t01_mongo \
  mongo:latest

# 4. Executar l'aplicació
docker run -d \
  --name spring-app \
  --network s05t01_network \
  -p 8080:8080 \
  -e SPRING_R2DBC_URL=r2dbc:mysql://mysql_S05T01:3306/s05t01_db \
  -e SPRING_DATA_MONGODB_URI=mongodb://mongo_S05T01:27017/s05t01_mongo \
  urian1983/s05t01-app:latest
```

#### Per a Windows PowerShell:

```powershell
# 1. Crear la xarxa
docker network create s05t01_network

# 2. Aixecar MySQL
docker run -d `
  --name mysql_S05T01 `
  --network s05t01_network `
  -p 3306:3306 `
  -e MYSQL_ROOT_PASSWORD=root `
  -e MYSQL_DATABASE=s05t01_db `
  mysql:8.4

# 3. Aixecar MongoDB
docker run -d `
  --name mongo_S05T01 `
  --network s05t01_network `
  -p 27017:27017 `
  -e MONGO_INITDB_DATABASE=s05t01_mongo `
  mongo:latest

# 4. Executar l'aplicació
docker run -d `
  --name spring-app `
  --network s05t01_network `
  -p 8080:8080 `
  -e SPRING_R2DBC_URL=r2dbc:mysql://mysql_S05T01:3306/s05t01_db `
  -e SPRING_DATA_MONGODB_URI=mongodb://mongo_S05T01:27017/s05t01_mongo `
  urian1983/s05t01-app:latest
```

### Opció 2: Construir des del codi font

```bash
# 1. Clonar el repositori
git clone https://github.com/el-teu-usuari/S05T01.git
cd S05T01

# 2. Construir i executar amb Docker Compose
docker-compose up -d --build
```

## 🔍 Verificar que funciona

### Comprovar els logs:
```bash
docker logs spring-app
```

### Accedir a l'aplicació:
- **Health Check:** http://localhost:8080/actuator/health
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **API Docs:** http://localhost:8080/v3/api-docs

## 🔐 Credencials d'accés

L'aplicació utilitza Spring Security amb autenticació bàsica:

- **Administrador:**
  - Username: `admin`
  - Password: `admin1234`
  - Rols: `ADMIN`, `PLAYER`

- **Jugador:**
  - Username: `player`
  - Password: `player1234`
  - Rols: `PLAYER`

## 🎮 Endpoints de l'API

### 1. Crear Partida
- **Mètode:** `POST`
- **Endpoint:** `/game/new/{name}`
- **Descripció:** Crea una nova partida de Blackjack amb un nom de jugador
- **Autenticació:** No requerida
- **Paràmetres:** 
  - `name` (path): Nom del jugador
- **Resposta:** `201 Created` amb informació de la partida creada

**Exemple:**
```bash
curl -X POST http://localhost:8080/game/new/Joan
```

### 2. Obtenir Detalls de Partida
- **Mètode:** `GET`
- **Endpoint:** `/game/{id}`
- **Descripció:** Obté els detalls d'una partida específica
- **Autenticació:** Requerida (PLAYER o ADMIN)
- **Paràmetres:** 
  - `id` (path): Identificador únic de la partida
- **Resposta:** `200 OK` amb informació detallada de la partida

### 3. Realitzar Jugada
- **Mètode:** `POST`
- **Endpoint:** `/game/{playerId}`
- **Descripció:** Realitza una jugada en una partida de Blackjack
- **Autenticació:** Requerida (PLAYER o ADMIN)
- **Paràmetres:** 
  - `playerId` (path): Identificador del jugador
- **Resposta:** `200 OK` amb el resultat de la jugada

### 4. Eliminar Partida
- **Mètode:** `DELETE`
- **Endpoint:** `/game/{id}/delete`
- **Descripció:** Elimina una partida de Blackjack
- **Autenticació:** Requerida (PLAYER o ADMIN)
- **Paràmetres:** 
  - `id` (path): Identificador únic de la partida
- **Resposta:** `204 No Content`

### 5. Obtenir Rànquing de Jugadors
- **Mètode:** `GET`
- **Endpoint:** `/ranking`
- **Descripció:** Obté el rànquing dels jugadors ordenat per rendiment
- **Autenticació:** No requerida
- **Resposta:** `200 OK` amb la llista de jugadors ordenada

**Exemple:**
```bash
curl http://localhost:8080/ranking
```

### 6. Canviar Nom del Jugador
- **Mètode:** `PUT`
- **Endpoint:** `/player/{playerId}`
- **Descripció:** Canvia el nom d'un jugador
- **Autenticació:** Requerida (PLAYER o ADMIN)
- **Paràmetres:** 
  - `playerId` (path): Identificador del jugador
  - Body: Nou nom del jugador
- **Resposta:** `200 OK` amb informació actualitzada

### 7. Consultar Jugador (ADMIN)
- **Mètode:** `GET`
- **Endpoint:** `/ranking/player/{id}`
- **Descripció:** Consulta informació detallada d'un jugador específic
- **Autenticació:** Requerida (només ADMIN)
- **Paràmetres:** 
  - `id` (path): Identificador del jugador
- **Resposta:** `200 OK` amb informació del jugador

## 📊 Resum d'Endpoints

| Mètode | Endpoint | Descripció | Auth | Rol |
|--------|----------|------------|------|-----|
| POST | `/game/new/{name}` | Crear partida | No | - |
| GET | `/game/{id}` | Detalls partida | Sí | PLAYER/ADMIN |
| POST | `/game/{playerId}` | Realitzar jugada | Sí | PLAYER/ADMIN |
| DELETE | `/game/{id}/delete` | Eliminar partida | Sí | PLAYER/ADMIN |
| GET | `/ranking` | Rànquing jugadors | No | - |
| PUT | `/player/{playerId}` | Canviar nom | Sí | PLAYER/ADMIN |
| GET | `/ranking/player/{id}` | Consultar jugador | Sí | ADMIN |

## 🛑 Aturar l'aplicació

### Amb Docker:
```bash
docker stop spring-app mongo_S05T01 mysql_S05T01
docker rm spring-app mongo_S05T01 mysql_S05T01
docker network rm s05t01_network
```

### Amb Docker Compose:
```bash
docker-compose down
```

## 💻 Desenvolupament local

### Prerequisits locals:
- Java 21
- Maven 3.9+
- MySQL 8.4 (executant-se al port 3306)
- MongoDB (executant-se al port 27017)

### Executar en mode desenvolupament:

```bash
# 1. Aixecar només les bases de dades
docker-compose up -d mysql-db mongo-db

# 2. Executar l'aplicació amb Maven
mvn spring-boot:run
```

### Executar tests:
```bash
mvn test
```

## 📦 Estructura del projecte

```
S05T01/
├── src/
│   ├── main/
│   │   ├── java/cat/itacademy/s05/t01/
│   │   │   ├── config/          # Configuració (Security, WebFlux)
│   │   │   ├── controller/      # Controllers REST reactius
│   │   │   ├── exception/       # GlobalExceptionHandler
│   │   │   ├── model/           # Entitats (Player, Game)
│   │   │   ├── repository/      # Repositoris R2DBC i MongoDB
│   │   │   └── service/         # Lògica de negoci reactiva
│   │   └── resources/
│   │       └── application.yml  # Configuració
│   └── test/
│       └── java/                # Tests unitàries (JUnit + Mockito)
├── Dockerfile                   # Imatge Docker
├── .dockerignore                # Exclusions Docker
├── docker-compose.yml           # Orquestració
├── pom.xml                      # Dependències Maven
└── README.md                    # Documentació
```

## 🧪 Testing

El projecte inclou proves unitàries per a controladors i serveis utilitzant:
- **JUnit 5**: Framework de testing
- **Mockito**: Mocking de dependències
- **WebTestClient**: Testing de endpoints reactius

Executar tests:
```bash
mvn test
```

## 🐋 Imatge Docker

La imatge Docker està disponible públicament:

**Docker Hub:** [urian1983/s05t01-app](https://hub.docker.com/r/urian1983/s05t01-app)

```bash
docker pull urian1983/s05t01-app:latest
```

### Versions disponibles:
- `latest` - Última versió estable
- `0.2` - Versió 0.2

## 🔧 Variables d'entorn

| Variable | Descripció | Valor per defecte |
|----------|------------|-------------------|
| `SPRING_R2DBC_URL` | URL connexió MySQL | `r2dbc:mysql://localhost:3306/s05t01_db` |
| `SPRING_R2DBC_USERNAME` | Usuari MySQL | `root` |
| `SPRING_R2DBC_PASSWORD` | Contrasenya MySQL | `root` |
| `SPRING_DATA_MONGODB_URI` | URI connexió MongoDB | `mongodb://localhost:27017/s05t01_mongo` |
| `JAVA_OPTS` | Opcions JVM | `-Xmx512m -Xms256m` |

## ✅ Requisits del Projecte Complerts

### Nivell 1:
- ✅ Implementació reactiva amb Spring WebFlux
- ✅ Gestió d'excepcions global amb GlobalExceptionHandler
- ✅ Configuració dual de bases de dades (MySQL + MongoDB)
- ✅ Proves unitàries amb JUnit i Mockito
- ✅ Documentació amb Swagger/OpenAPI
- ✅ Tots els endpoints del joc de Blackjack implementats

### Nivell 2:
- ✅ Dockerització completa de l'aplicació
- ✅ Dockerfile optimitzat multi-stage
- ✅ .dockerignore configurat
- ✅ Imatge pujada a Docker Hub
- ✅ Docker Compose per orquestració
- ✅ Documentació per a usuaris finals

## 📄 Llicència

Aquest projecte ha estat desenvolupat com a part del curs d'IT Academy - Sprint 5, Tasca 1.

## 👤 Autor

**Josep J. Roca Blanco** - [urian1983](https://hub.docker.com/u/urian1983)

---

## 🆘 Resolució de problemes

### L'aplicació no es connecta a les bases de dades
- Assegura't que MySQL i MongoDB estan en execució
- Verifica que tots els contenidors estan a la mateixa xarxa Docker
- Comprova els logs: `docker logs spring-app`

### Port 8080 ja està en ús
```bash
# Trobar el procés que usa el port
netstat -ano | findstr :8080  # Windows
lsof -i :8080                 # Linux/Mac

# Canviar el port de l'aplicació
docker run ... -p 8081:8080 ...
```

### Errors d'autenticació
- Verifica que estàs utilitzant les credencials correctes
- Recorda que alguns endpoints requereixen rol ADMIN

### Problemes amb Docker Desktop
- Assegura't que Docker Desktop està en execució
- Reinicia Docker Desktop si cal
- Comprova que tens prou espai en disc
