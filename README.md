# 💸 Payment Microservices Overview

A distributed trading system built on **microservices architecture** for scalable transaction handling.  
Every trade event triggers an automated **receipt generation pipeline**, ensuring transparency and traceability across the system.

---

## 🧭 Architecture Overview

This project demonstrates the migration of a **monolithic trading module** into a **microservices-based solution** composed of the following services:

### 🧩 Services and Environment Variables

- **🟢 trade-service**
    - Exposes REST APIs to execute and retrieve trades
    - Persists trades in **PostgreSQL**
    - Publishes trade events to **RabbitMQ**
    - Environment Variables:
        - `SPRING_DATASOURCE_URL`: JDBC URL for connecting to PostgreSQL (default: `jdbc:postgresql://localhost:5432/payments_db`)
        - `SPRING_DATASOURCE_USERNAME`: Username for PostgreSQL connection (default: `admin`)
        - `SPRING_DATASOURCE_PASSWORD`: Password for PostgreSQL connection (default: `admin`)
        - `SPRING_RABBITMQ_HOST`: Hostname for RabbitMQ connection (default: `localhost`)
        - `SPRING_RABBITMQ_PORT`: Port for RabbitMQ connection (default: `5672`)
        - `SPRING_RABBITMQ_USERNAME`: Username for RabbitMQ connection (default: `admin`)
        - `SPRING_RABBITMQ_PASSWORD`: Password for RabbitMQ connection (default: `admin`)

- **🧾 receipt-service**
    - Consumes trade events from **RabbitMQ**
    - Prints formatted trade receipts to the console
    - Environment Variables:
        - `SPRING_RABBITMQ_HOST`: Hostname for RabbitMQ connection (default: `localhost`)
        - `SPRING_RABBITMQ_PORT`: Port for RabbitMQ connection (default: `5672`)
        - `SPRING_RABBITMQ_USERNAME`: Username for RabbitMQ connection (default: `admin`)
        - `SPRING_RABBITMQ_PASSWORD`: Password for RabbitMQ connection (default: `admin`)

### 📦 Infrastructure and Environment Variables

- **🐘 PostgreSQL**
    - Stores trade and transaction data
    - Environment Variables:
        - `POSTGRES_DB`: Defines the username used to connect to the database (default: `admin`)
        - `POSTGRES_USER`: Defines the username used to connect to the database (default: `admin`)
        - `POSTGRES_PASSWORD`: Defines the password for the database user (default: `admin`)

- **🐇 RabbitMQ**
    - Acts as the message broker for event-driven communication between services
    - Environment Variables:
        - `RABBITMQ_DEFAULT_USER`: Defines the username for RabbitMQ management (default: `admin`)
        - `RABBITMQ_DEFAULT_PASS`: Defines the password for RabbitMQ management (default: `admin`)

---

### ⚙️ Tech Stack

| Component        | Technology              |
|------------------|-------------------------|
| Language         | Java 21                 |
| Framework        | Spring Boot 3           |
| Messaging        | RabbitMQ                |
| Database         | PostgreSQL              |
| Build            | Maven                   |
| Containerization | Docker & Docker Compose |

---

## 🚀 Running the Project

### 🧰 Prerequisites

Make sure you have installed:

- Docker & Docker Compose

---

### ▶️ Start the Infrastructure

From the project root, run:

```bash
docker-compose up -d
```

This will start:
- PostgreSQL (port 5432)
- RabbitMQ (ports 5672, 15672)
- trade-service (port 8082)
- receipt-service (port 8083)

## 🧩 Management Accesses

**RabbitMQ Management UI**:
- URL: http://localhost:15672

**Trade Service API Documentation**:
- Swagger/OpenAPI UI is available at:
- http://localhost:8082/swagger-ui.html


## 📬 Postman Collection

You can test all endpoints easily using the included Postman collection.

### 🔗 Import the Collection

1. Open Postman
2. Click **Import** → **File**
3. Select the file: [`Payment Microservices API.json`](./postman/payment-api-requests))

The collection includes:
- `POST /trades` — Creates a trade and publishes an event
- `GET /trades/{id}` — Retrieves trade details
- `GET /trades/findAll` — Retrieves all trade with pagination
- `GET /trades/find-by-client-id/{clientId}"` — Retrieves trade details by client ID

### Authentication

The Trade Service API is protected with HTTP Basic authentication.

- **Username:** `${SPRING_SECURITY_USER_NAME}` (default: `admin`)
- **Password:** `${SPRING_SECURITY_USER_PASSWORD}` (default: `password`)

You can override these credentials using environment variables in your `docker-compose.yml` or at runtime.