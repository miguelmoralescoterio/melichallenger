/**
 * DDL
 */

CREATE SCHEMA meli_challenger;

USE meli_challenger;

CREATE TABLE users (
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  lastname VARCHAR(100) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  address VARCHAR(500) NOT NULL,
  status ENUM('active','inactive','deleted'),
  created_at datetime default current_timestamp,
  updated_at datetime default current_timestamp on update current_timestamp,
  deleted_at datetime default current_timestamp
);