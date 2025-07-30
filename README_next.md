# 🧩 Online Event Management Platform (Microservices Architecture)

A fully functional, containerized **microservices-based backend platform** built with Spring Boot, Node.js, Python, Kafka, Docker, and AWS. This platform simulates a real-world event management system with core production-grade features like user authentication, event registration, notifications, analytics, observability, and CI/CD.

---

## 🌟 Key Features

- ✅ User Registration and Authentication (JWT)
- ✅ Event Creation and Management
- ✅ User Event Registration System
- ✅ Real-Time Notifications via Kafka
- ✅ Analytics for Admin Dashboard
- ✅ Centralized Logging with ELK Stack
- ✅ Monitoring via OpenTelemetry
- ✅ Fully Dockerized with Docker Compose
- ✅ CI/CD with GitHub Actions

---

## 🏗️ Architecture Diagram

```

\[Client]
|
\[API Gateway]
|
-

\|   |        |           |           |       |
Auth Event Registration Notification Analytics
Srv  Srv      Srv           Srv         Srv
\|     |        |             |           |
MySQL MongoDB Cassandra     Kafka      PostgreSQL
/   |  &#x20;
Email  SMS  Webhook

\[Logging Service] --> \[ELK Stack]

````

---

## 📁 Microservices Overview

| Service              | Tech Stack            | Purpose                                  |
|----------------------|------------------------|-------------------------------------------|
| **auth-service**      | Spring Boot, MySQL     | JWT-based user registration & login       |
| **event-service**     | Node.js, MongoDB       | Event creation, listing, and details      |
| **registration-service** | Spring Boot, Cassandra | Register users for events                  |
| **notification-service** | Python, Kafka         | Send email/SMS/webhook notifications      |
| **analytics-service** | Node.js, PostgreSQL    | Admin dashboard analytics                  |
| **logging-service**   | Beats + Logstash + Kibana | Centralized logging                      |

---

## 🐳 Dockerized Setup

### ✅ Prerequisites

- Docker + Docker Compose
- JDK 17+
- Maven (for Spring Boot services)
- Node.js + npm (for Node services)

### 🚀 Start the Application

```bash
git clone https://github.com/yourusername/event-management-platform.git
cd event-management-platform
docker-compose up --build
````

---

## 🧰 docker-compose.yml (Full)

```yaml
version: '3.8'

services:
  auth-service:
    build: ./auth-service
    ports:
      - "8081:8081"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://auth-db:3306/authdb
    depends_on:
      - auth-db

  event-service:
    build: ./event-service
    ports:
      - "8082:8082"
    environment:
      - MONGO_URL=mongodb://event-db:27017/events
    depends_on:
      - event-db

  registration-service:
    build: ./registration-service
    ports:
      - "8083:8083"
    environment:
      - CASSANDRA_CONTACT_POINTS=registration-db
    depends_on:
      - registration-db

  notification-service:
    build: ./notification-service
    ports:
      - "8084:8084"
    environment:
      - KAFKA_BOOTSTRAP_SERVERS=kafka:9092
    depends_on:
      - kafka

  analytics-service:
    build: ./analytics-service
    ports:
      - "8085:8085"
    environment:
      - DB_URL=jdbc:postgresql://analytics-db:5432/analytics
    depends_on:
      - analytics-db

  logging-service:
    build: ./logging-service
    ports:
      - "5044:5044"

  api-gateway:
    build: ./api-gateway
    ports:
      - "8080:8080"
    depends_on:
      - auth-service
      - event-service
      - registration-service
      - notification-service
      - analytics-service

  kafka:
    image: bitnami/kafka:latest
    ports:
      - "9092:9092"
    environment:
      - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181
      - ALLOW_PLAINTEXT_LISTENER=yes
    depends_on:
      - zookeeper

  zookeeper:
    image: bitnami/zookeeper:latest
    ports:
      - "2181:2181"

  auth-db:
    image: mysql:8
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: authdb
    ports:
      - "3306:3306"

  event-db:
    image: mongo:latest
    ports:
      - "27017:27017"

  registration-db:
    image: cassandra:latest
    ports:
      - "9042:9042"

  analytics-db:
    image: postgres:latest
    environment:
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: analytics
    ports:
      - "5432:5432"

  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:7.17.9
    environment:
      - discovery.type=single-node
    ports:
      - "9200:9200"

  kibana:
    image: docker.elastic.co/kibana/kibana:7.17.9
    ports:
      - "5601:5601"
    depends_on:
      - elasticsearch
```

---

## 🧪 API Examples

### 🔐 Auth Service

* `POST /api/auth/register` – Register new user
* `POST /api/auth/login` – Login & get JWT token

### 📅 Event Service

* `GET /api/events` – Get all events
* `POST /api/events` – Create new event

### 📥 Registration Service

* `POST /api/registrations` – Register for an event

### 📣 Notification Service

* Listens to Kafka topic `event.registered` and sends notifications

---

## 🧭 Monitoring & Observability

* **Logging**: Filebeat + Logstash + Kibana (accessible on `http://localhost:5601`)
* **Metrics & Tracing**: OpenTelemetry + Prometheus/Grafana setup (optional)
* **Docker Healthchecks** integrated

---

## 🔄 CI/CD Pipeline

* GitHub Actions CI pipeline runs:

  * Lint
  * Build
  * Unit tests
  * Docker build & push to DockerHub
* Can be extended to deploy on AWS/GCP via Terraform or GitHub Actions

---

## 🎯 Outcomes

✅ Built using production-grade patterns
✅ Showcases DevOps & Observability practices
✅ Scalable and easily extendable
✅ Great for portfolio & recruiter attention

---

## 🙌 Author

Yuvraj Singh Shaktawat
🎓 Software Engineer @ Persistent | AWS & Azure Certified | Spring Boot | DevOps | Microservices
📫 https://www.linkedin.com/in/yuvraj-singh-shaktawat

---

## ⭐ If you like this project

* Leave a ⭐ on this GitHub repo
* Share your thoughts or suggestions
* Feel free to fork and customize!

---

## 📜 License

MIT License — Free to use, modify, and distribute.

