# Fox Garden

A Jakarta EE 8 sample application for managing foxes.

The template is originally created imported from [Jakarta EE 8 Starter Boilerplate](https://github.com/hantsy/jakartaee8-starter-boilerplate) GitHub Project providing a solid base for building Java EE 8 applications. It has been customized to focus on a simple use case of managing foxes, demonstrating the core features of Jakarta EE 8 while maintaining a clean and modular architecture.

## Technology Stack

* Java 11
* Jakarta EE 8
* JSF 2.3
* PrimeFaces 12
* JPA (Hibernate)
* H2 Database
* EJB
* JAX-RS
* Maven
* Docker & Docker Compose

## Getting Started

### Build

Build the application using Maven:

```bash
mvn clean package
```

### Run with Docker

Start the alternative Application Servers using Docker with provided Docker Compose configuration:

```bash
docker compose up --build
```

Run a specific application server:

| Application Server | Command                                     |
|--------------------|---------------------------------------------|
| Wildfly            | ```docker compose up --build wildfly```     |
| TomEE WebProfile   | ```docker compose up --build tomee```       |
| TomEE MicroProfile | ```docker compose up --build tomee_micro``` |

### Application URLs

| Service  | URL                                                  |
| -------- | ---------------------------------------------------- |
| Web UI   | http://localhost:8080/jakartaee8-starter/index.jsf   |
| REST API | http://localhost:8080/jakartaee8-starter/api         |
| OpenAPI  | http://localhost:8080/jakartaee8-starter/api/openapi |

## API Examples

The project includes ready‑to‑use Postman collections for exploring the REST API.

### Postman Collections

| Description                  | File Path                                           |
|------------------------------|-----------------------------------------------------|
| API Endpoints Collection     | docs/postman_collection/fox-garden.json             |
| Environment Variables (port) | docs/postman_collection/fox-garden-environment.json |

Both files can be directly imported into Postman or any compatible REST client.

### Collection Contents

The collection includes example requests for:

* ```GET /api/foxes``` – list all foxes
* ```GET /api/foxes/{id}``` – retrieve a fox by ID
* ```POST /api/foxes``` – create a new fox
* ```DELETE /api/foxes/{id}``` – remove a fox
* ```GET /api/openapi``` – retrieves the OpenAPI specification for the REST API

## Testing

Run all Unit Tests:

```bash
mvn clean test
```

## Contributing

Contributions are welcome by hiring me as a coworker.
