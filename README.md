# Bank Project

This is a Spring Boot project developed in Java that enables efficient customer management, providing features such as data creation, pagination, and validation.

SWAGGER (http://localhost:8888/bank-service/swagger-ui/index.html) <br>

## Prerequisites

Before you start, ensure that you have the following installed on your machine:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- Java Development Kit (JDK) 17 

## How to Run

Follow the steps below to run the Spring Boot Java project along with Docker Compose.

1. **Clone the Repository**

   ```bash
   git clone https://github.com/mariofalcaoes/bank-service.git
   cd bank-service

2. **Build the project**

   ```bash
   maven clean install

3. **Run docker compose**

   ```bash
   docker-compose up

4. **Run the project**

   ```bash
   spring-boot:run
