/**
 * DDL
 *
 * Previo crear el usuario
 * CREATE USER 'melidbuser'@localhost IDENTIFIED BY 'm3l4ch4llep3r';
 * 
 */

CREATE SCHEMA meli_challenger;

/**
 * GRANT ALL PRIVILEGES ON meli_challenger.* TO 'melidbuser'@localhost IDENTIFIED BY 'm3l4ch4llep3r';
 * GRANT ALL PRIVILEGES ON meli_challenger.* TO 'melidbuser'@localhost;
 * FLUSH PRIVILEGES;
 */

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

DELIMITER $$
CREATE TRIGGER before_users_update
BEFORE UPDATE
ON users FOR EACH ROW
BEGIN                   
    IF new.status = 'deleted' THEN
      SET new.deleted_at = CURRENT_TIMESTAMP;
    ELSEIF new.status IN ('active','inactive') THEN
      SET new.deleted_at = null;
    END IF;
END;$$
DELIMITER;

/**
 * targets
 */
CREATE TABLE targets (
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(50) NOT NULL,
  amount_min bigint ,
  amount_max bigint ,
  cant_min int  default 0,
  cant_max int  default 1,
  rate float not null,
  status ENUM('active','inactive','deleted'),
  created_at datetime default current_timestamp,
  updated_at datetime default current_timestamp,
  deleted_at datetime default null
);

DELIMITER $$
CREATE TRIGGER before_targets_update
BEFORE UPDATE
ON targets FOR EACH ROW
BEGIN                   
    IF new.status = 'deleted' THEN
      SET new.deleted_at = CURRENT_TIMESTAMP;
    ELSEIF new.status IN ('active','inactive') THEN
      SET new.deleted_at = null;
    END IF;
END;$$
DELIMITER;

/**
 * Prestamos (loans)
 */
CREATE TABLE loans (
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  taget_id int UNSIGNED not null,
  user_id int UNSIGNED not null,
  amount_request double UNSIGNED not null, 
  term int UNSIGNED not null default 12,
  installment double not null,
  rate double not null,
  interest double not null,
  amount_total double UNSIGNED not null, 
  observation VARCHAR(500),
  status ENUM('active','inactive','deleted'),
  created_at datetime default current_timestamp,
  updated_at datetime default current_timestamp,
  deleted_at datetime default null,
  CONSTRAINT chk_term CHECK (term > 0), 
  CONSTRAINT fk_users_loans FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT fk_target_loans FOREIGN KEY (taget_id) REFERENCES targets(id) ON DELETE RESTRICT ON UPDATE RESTRICT

);

DELIMITER $$
CREATE TRIGGER before_loans_update
BEFORE UPDATE
ON loans FOR EACH ROW
BEGIN                   
    IF new.status = 'deleted' THEN
      SET new.deleted_at = CURRENT_TIMESTAMP;
    ELSEIF new.status IN ('active','inactive') THEN
      SET new.deleted_at = null;
    END IF;
END;$$
DELIMITER;

CREATE TABLE loans_payments (
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  loan_id int UNSIGNED not null,  
  amount double UNSIGNED not null,   
  paid_at datetime default null,
  reference VARCHAR(50),
  observation VARCHAR(500),
  status ENUM('pending','paid','deleted'),
  created_at datetime default current_timestamp,
  updated_at datetime default current_timestamp,
  deleted_at datetime default null,
  CONSTRAINT fk_loans_loans_payments FOREIGN KEY (loan_id) REFERENCES loans(id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

DELIMITER $$
CREATE TRIGGER before_loans_payments_update
BEFORE UPDATE
ON loans_payments FOR EACH ROW
BEGIN                   
    IF new.status = 'deleted' THEN
      SET new.deleted_at = CURRENT_TIMESTAMP;
    ELSEIF new.status IN ('pending','paid') THEN
      SET new.deleted_at = null;
    END IF;
END;$$
DELIMITER;