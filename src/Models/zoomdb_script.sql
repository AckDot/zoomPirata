
DROP DATABASE IF EXISTS zoomfake;

CREATE DATABASE zoomfake CHARACTER SET utf8mb4;
USE carservices_db;

CREATE TABLE users (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  name VARCHAR(50)NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

