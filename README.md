# Spring Boot + Kotlin + PostgreSQL Beispiel

Anleitung:

1. PostgreSQL starten:
   - lokal: `docker compose up -d`
   - oder lokal installiertes Postgres verwenden und Umgebungsvariablen setzen

2. Umgebungsvariablen (optional):
   - DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD
   Beispiel:
   DB_HOST=localhost DB_PORT=5432 DB_NAME=demo DB_USER=postgres DB_PASSWORD=postgres

3. App starten:
   - mit Gradle: `./gradlew bootRun`
   - oder in IDE (IntelliJ) die Main-Funktion `DemoApplication.kt` ausführen

4. Test-Requests:
   - GET alle Personen: GET http://localhost:8080/api/persons
   - POST Person (JSON): POST http://localhost:8080/api/persons
     Body: `{ "name": "Alice" }`

Hinweise:
- Für Entwicklung ist `spring.jpa.hibernate.ddl-auto=update` praktisch, in Produktion aber riskant.
- Für migrationsverwaltete DBs empfehle ich Flyway (schon vorbereitet im Beispiel).
