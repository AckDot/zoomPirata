
DROP DATABASE IF EXISTS zoomfake;

CREATE DATABASE zoomfake CHARACTER SET utf8mb4;
USE carservices_db;

CREATE TABLE users (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  name VARCHAR(50)NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  description VARCHAR(30) DEFAULT "Hey There! I am using FakeZoom",
  picture VARCHAR(100) DEFAULT "/Views/imagenes/blank-avatar.jpg",
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE meetings (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  code VARCHAR(20) NOT NULL,
  id_user INT NOT NULL UNSIGNED,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE meetings_users (
  id_user INT UNSIGNED NOT NULL,
  id_meeting INT UNSIGNED NOT NULL,
  rol VARCHAR(10),
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
)ENGINE=InnoDB DEFAULT CHARSET=utf8;