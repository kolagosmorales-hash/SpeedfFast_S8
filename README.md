Sistema de Gestión de Entregas - SpeedFast (Java DAO)

Este es un sistema de gestión de entregas desarrollado en Java SE, que implementa el patrón de diseño DAO (Data Access Object) para interactuar con una base de datos MySQL.

El sistema permite administrar repartidores, pedidos y entregas, aplicando buenas prácticas como separación de capas y uso de ENUM en base de datos.

Características:

CRUD completo de Repartidores

CRUD completo de Pedidos

Registro de Entregas

Uso de ENUM en MySQL (tipo y estado del pedido)

Arquitectura en capas (Modelo, DAO, Vista)

Conexión JDBC con MySQL

Tecnologías Utilizadas:

Lenguaje: Java 11+

Base de Datos: MySQL

Librería: MySQL Connector (JDBC)

Arquitectura: Patrón DAO

Script de la Base de Datos:

Ejecuta este script en tu gestor de base de datos (MySQL Workbench, DBeaver, etc.):

CREATE DATABASE speedfast;
USE speedfast;

CREATE TABLE repartidores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    direccion VARCHAR(100) NOT NULL,
    tipo ENUM('COMIDA','ENCOMIENDA','EXPRESS'),
    estado ENUM('PENDIENTE','EN_REPARTO','ENTREGADO')
);

CREATE TABLE entregas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT,
    id_repartidor INT,
    fecha DATE,
    hora TIME,
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id),
    FOREIGN KEY (id_repartidor) REFERENCES repartidores(id)
);
