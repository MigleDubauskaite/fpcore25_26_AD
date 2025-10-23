CREATE DATABASE mundo;
show databases;
USE mundo;
CREATE TABLE personas (nombre VARCHAR(30), apellido1 VARCHAR(30), apellido2 VARCHAR(30), nacimiento INT);
DESCRIBE personas;
INSERT INTO personas (nombre, apellido1, apellido2, nacimiento) VALUES 
('Noah', 'Kowalski', 'Rodríguez', 1960),
('Mateo', 'Andersson', 'Fernández', 1992),
('Noah', 'López', 'Martínez', 1986),
('Valentina', 'Kowalski', 'Rossi', 2010),
('Sofía', 'Gómez', 'Kowalski', 2018),
('Emma', 'Silva', 'Pérez', 2023),
('Noah', 'García', 'Rodríguez', 1954),
('Noah', 'Díaz', 'Novak', 1920),
('Lucía', 'Rodríguez', 'Brown', 1971),
('Mateo', 'Sánchez', 'Gómez', 1922);