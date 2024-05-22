DROP DATABASE IF EXISTS gestorincidencias;
CREATE DATABASE IF NOT EXISTS gestorincidencias
  CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE gestorincidencias;


CREATE TABLE incidencias (
  codigo varchar(100) NOT NULL,
  estado varchar(20) NOT NULL,
  puesto INT NOT NULL,
  descripcion varchar(255) NOT NULL,
  segundo_codigo varchar(100) DEFAULT NULL,
  segunda_descripcion varchar(255) DEFAULT NULL
);



GRANT ALL PRIVILEGES ON gestorincidencias.*
TO marcos@localhost IDENTIFIED BY '12345';


