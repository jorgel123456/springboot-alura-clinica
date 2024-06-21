CREATE TABLE pacientes (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono varchar(20) not null,
    documento VARCHAR(10) NOT NULL UNIQUE,
    calle VARCHAR(100) NOT NULL,
    numero VARCHAR(255),
    complemento VARCHAR(255),
    distrito VARCHAR(10) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    activo tinyint
);

