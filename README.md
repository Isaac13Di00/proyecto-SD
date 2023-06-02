# Proyecto Sistemas Distribuidos

Con la ayuda de sockets en Java crearemos un sistema de actualización de precios, con un inicio de sesión, apartado visual de revisión de precios y un log para guardar los cambios.
.
## Creación de base de datos

Usando [MySQL Workbench](https://dev.mysql.com/downloads/workbench) para crear el query y [XAMPP](https://www.apachefriends.org/es/index.html) para crear localmente la base de datos.

```SQL
DROP DATABASE IF EXISTS db_sd;
-- Crear la base de datos
CREATE DATABASE db_sd;

-- Usar la base de datos creada
USE db_sd;

-- Crear la tabla "user"
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

-- Crear la tabla "product"
CREATE TABLE product (
  id INT(5) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  price FLOAT NOT NULL,
  description VARCHAR(255) NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Crear la tabla "log"
CREATE TABLE log (
  id INT AUTO_INCREMENT PRIMARY KEY,
  product_id INT,
  user_id INT,
  new_price FLOAT,
  old_price FLOAT,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (product_id) REFERENCES product(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);
