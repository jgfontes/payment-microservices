# 💸 Payment Microservices Overview

A distributed trading system built on **microservices architecture** for scalable transaction handling.  
Every trade event triggers an automated **receipt generation pipeline**, ensuring transparency and traceability across the system.

---

## 🧭 Architecture Overview

This project demonstrates the migration of a **monolithic trading module** into a **microservices-based solution** composed of the following services:

### 🧩 Services

- **🟢 trade-service**
    - Exposes REST APIs to execute and retrieve trades
    - Persists trades in **PostgreSQL**
    - Publishes trade events to **RabbitMQ**

- **🧾 receipt-service**
    - Consumes trade events from **RabbitMQ**
    - Prints formatted trade receipts to the console

### 📦 Infrastructure

- **🐘 PostgreSQL**
    - Stores trade and transaction data

- **🐇 RabbitMQ**
    - Acts as the message broker for event-driven communication between services

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
- Java 21
- Maven
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
- Username: admin
- Password: admin

**Trade Service API Documentation**:
- Swagger/OpenAPI UI is available at:
- http://localhost:8082/swagger-ui.html

### Authentication

No authentication is currently configured. This may be added in the future.

## 🧑‍💻 Author

**Júlio Gurgel Fontes**
- GitHub: [github.com/juliogurgel]