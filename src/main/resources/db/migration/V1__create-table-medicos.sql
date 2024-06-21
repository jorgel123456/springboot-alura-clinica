CREATE TABLE medicos (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    documento VARCHAR(10) NOT NULL UNIQUE,
    especialidad VARCHAR(100) NOT NULL,
    calle VARCHAR(100) NOT NULL,
    numero VARCHAR(255),
    complemento VARCHAR(255),
    distrito VARCHAR(10) NOT NULL,
    ciudad VARCHAR(100) NOT NULL
);

