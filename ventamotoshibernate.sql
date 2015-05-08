CREATE DATABASE IF NOT EXISTS ventamotoshibernate;

USE ventamotoshibernate;

CREATE TABLE IF NOT EXISTS vendedor
(
    	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    	nombre VARCHAR (50),
    	apellido VARCHAR (50),
    	dni VARCHAR (50),
    	idcliente VARCHAR (50),
	provincia VARCHAR (50),
	salario INT
);
CREATE TABLE IF NOT EXISTS accesorio
(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(50),
	refencia VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS moto
(
    	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    	marca VARCHAR (50),
	modelo VARCHAR (50),
	numerochasis VARCHAR (50),
	matricula VARCHAR (50),
	preciomoto FLOAT,
	fecha DATE,
	id_vendedor INT UNSIGNED,
    	INDEX (id_vendedor),
    	FOREIGN KEY (id_vendedor) REFERENCES Vendedor (id)
	ON DELETE NO ACTION ON UPDATE NO ACTION,
	id_accesorio INT UNSIGNED,
	INDEX (id_accesorio),
	FOREIGN KEY (id_accesorio) REFERENCES Accesorio (id)
	ON DELETE NO ACTION ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS cliente
(
    	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    	nombre VARCHAR (50),
	apellido VARCHAR (50),
	dni VARCHAR(50),
	direccion VARCHAR(50),
	provincia VARCHAR(50),
	telefono INT,
	id_moto INT UNSIGNED,
    	INDEX (id_moto),
    	FOREIGN KEY (id_moto) REFERENCES Moto (id)
	ON DELETE NO ACTION ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS moto_accesorio
(
	id_moto INT UNSIGNED,
    	INDEX (id_moto),
    	FOREIGN KEY (id_moto) REFERENCES Moto (id)
	ON DELETE NO ACTION ON UPDATE NO ACTION,
	id_accesorio INT UNSIGNED,
	INDEX (id_accesorio),
	FOREIGN KEY (id_accesorio) REFERENCES Accesorio (id)
	ON DELETE NO ACTION ON UPDATE NO ACTION
);