# Blog Application API

A Spring Boot REST API for a blog platform (Users, Posts, Categories, Comments) using Spring Data JPA and PostgreSQL.

## Tech Stack
- Java 17, Spring Boot 3.4.5
- Spring Data JPA + PostgreSQL
- Spring Boot Actuator (health checks)
- Lombok, ModelMapper

## Running Locally

1. Create a local Postgres database named `blog`.
2. Set environment variables (or rely on the defaults in `application.properties`, which point at a local Postgres on `localhost:5432` with user/pass `postgres`/`postgres`):
   ```
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/blog
   SPRING_DATASOURCE_USERNAME=postgres
   SPRING_DATASOURCE_PASSWORD=postgres
   ```
3. Run:
   ```
   ./mvnw spring-boot:run
   ```
4. Health check: `GET http://localhost:8080/actuator/health`

## Deploying to Render

### 1. Create the Postgres database
- Render Dashboard → New → PostgreSQL → pick the free plan.
- Once created, open the database and copy the **Internal Database URL** (use the internal one, not external, since the web service and DB will live in the same Render network — it's faster and doesn't count against external bandwidth).
- Render gives you a single connection string like `postgres://user:pass@host/dbname`. Spring's JDBC driver needs the `jdbc:` prefix and separate user/pass, so either:
  - Split it into `SPRING_DATASOURCE_URL=jdbc:postgresql://<host>/<dbname>`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`, or
  - Use Render's individual "Host/Port/Database/Username/Password" fields shown on the DB info page to build the three env vars above.

### 2. Create the Web Service
- Render Dashboard → New → Web Service → connect this GitHub repo.
- **Environment**: Docker not required — pick "Java" runtime, or leave as native/Docker off since Render auto-detects Maven projects. If it doesn't auto-detect, set:
  - **Build Command**: `./mvnw clean package -DskipTests`
  - **Start Command**: `java -jar target/blog-0.0.1-SNAPSHOT.jar`
- **Instance type**: Free tier is fine for a demo/portfolio project.

### 3. Set Environment Variables
In the Web Service → Environment tab, add:

| Key | Example value |
|---|---|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://<render-db-host>/blog` |
| `SPRING_DATASOURCE_USERNAME` | `<from Render DB info page>` |
| `SPRING_DATASOURCE_PASSWORD` | `<from Render DB info page>` |
| `JPA_DDL_AUTO` | `update` (or `validate` once schema is stable) |
| `CORS_ALLOWED_ORIGINS` | `https://your-frontend.vercel.app` |
| `JWT_SECRET` | `<a long random string>` (only needed once JWT auth is added) |
| `JWT_EXPIRATION_MS` | `3600000` |

Render sets `PORT` automatically — the app already reads it via `server.port=${PORT:8080}`, so nothing to configure there.

### 4. Deploy
- Push to the connected branch, or click "Manual Deploy" → "Deploy latest commit".
- Watch the build logs; once live, check `https://<your-service>.onrender.com/actuator/health` — it should return `{"status":"UP"}`.

### 5. Notes for interviews
- **Why Postgres over MySQL**: Render's free managed database tier is Postgres-only, and since this app doesn't rely on any MySQL-specific SQL, switching the JDBC driver + Hibernate dialect was a low-risk swap.
- **Why env vars instead of hardcoded config**: 12-factor app principle — config (especially secrets) should live in the environment, not in source control. It also lets the same JAR run unmodified in dev, staging, and prod.
- **Why `${PORT:8080}`**: Render assigns a dynamic port to each service via the `PORT` env var; the app must bind to whatever Render gives it, while still defaulting to 8080 for local runs.
- **Free-tier cold starts**: Render's free web services spin down after inactivity and take ~30-60s to wake on the next request — worth mentioning if asked about production trade-offs.
