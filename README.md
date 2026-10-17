# TaskTracker

A simple task management REST API built with Spring Boot, PostgreSQL, and Docker.

## Requirements

- Docker
- Docker Compose

## Configuration

Create a `.env` file in the project root:

```env
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

## Build and Run

Build the application JAR using Maven in IntelliJ IDEA (`Lifecycle → package`).

Build the Docker image and start the containers:

```bash
docker compose up --build -d
```

Stop the application:

```bash
docker compose down
```

PostgreSQL data is persisted in a Docker volume.

## API

Base URL: `http://localhost:8080/api/tasks`

- `GET /api/tasks` — Get all tasks
- `GET /api/tasks/{id}` — Get a task by ID
- `GET /api/tasks/status/{status}` — Get tasks by status
- `POST /api/tasks` — Create a task
- `PUT /api/tasks/{id}` — Update a task
- `DELETE /api/tasks/{id}` — Delete a task

## Environment Variables

- `DB_USERNAME` — PostgreSQL username
- `DB_PASSWORD` — PostgreSQL password
- `DB_URL` — Database connection URL, configured in `compose.yaml`
