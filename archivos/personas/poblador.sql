CREATE DATABASE mundo;
show databases;
USE mundo;
CREATE TABLE personas (nombre VARCHAR(30), apellido1 VARCHAR(30), apellido2 VARCHAR(30), nacimiento INT);
DESCRIBE personas;
INSERT INTO personas (nombre, apellido1, apellido2, nacimiento) VALUES 
('Liam', 'Johnson', 'López', 2017),
('Hugo', 'Johnson', 'Novak', 1932),
('Aitana', 'Gómez', 'Rodríguez', 1960),
('Hugo', 'Rossi', 'López', 1967),
('Liam', 'Silva', 'Johnson', 1956),
('Hugo', 'Andersson', 'Díaz', 1936),
('Valentina', 'Johnson', 'López', 1932),
('Mateo', 'Dubois', 'Dubois', 1959),
('Valentina', 'Díaz', 'Müller', 1965),
('Liam', 'Dubois', 'Andersson', 2003);