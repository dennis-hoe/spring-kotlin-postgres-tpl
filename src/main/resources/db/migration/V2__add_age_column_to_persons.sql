-- Example Flyway migration: add age column to persons
ALTER TABLE persons
ADD COLUMN age INTEGER;
