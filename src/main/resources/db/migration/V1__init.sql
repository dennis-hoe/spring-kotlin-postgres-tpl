-- Optional: Flyway-Migration (nur wenn Flyway aktiviert ist)
CREATE TABLE IF NOT EXISTS persons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
