/**
 * DDL
 */

CREATE SCHEMA meli_challenger;

USE meli_challenger;

/**
 * Usuarios
 */
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

CREATE TRIGGER before_users_update
BEFORE UPDATE
ON users FOR EACH ROW
BEGIN                   
    IF new.status = 'deleted' THEN
      SET new.deleted_at = CURRENT_TIMESTAMP;
    ELSEIF new.status IN ('active','inactive') THEN
      SET new.deleted_at = null;
    END IF;
END;

/**
 * targets
 */
CREATE TABLE targets (
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(50) NOT NULL,
  amount_min bigint NOT NULL,
  amount_max bigint NOT NULL,
  cant_min int not null default 0,
  cant_max int not null default 1,
  rate float not null,
  status ENUM('active','inactive','deleted'),
  created_at datetime default current_timestamp,
  updated_at datetime default current_timestamp,
  deleted_at datetime default null,
  CONSTRAINT chk_amounts CHECK (amount_min < amount_max)
);

/**
 * Prestamos (loans)
 */
CREATE TABLE loans (
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  taget_id int UNSIGNED not null,
  user_id int UNSIGNED not null,
  amount_total bigint UNSIGNED not null, 
  term int UNSIGNED not null default 12,
  installment double not null,
  observation VARCHAR(500),
  status ENUM('active','inactive','deleted'),
  created_at datetime default current_timestamp,
  updated_at datetime default current_timestamp,
  deleted_at datetime default null,
  CONSTRAINT chk_term CHECK (term > 0), 
  CONSTRAINT fk_users_loans FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT fk_target_loans FOREIGN KEY (taget_id) REFERENCES targets(id) ON DELETE RESTRICT ON UPDATE RESTRICT

);