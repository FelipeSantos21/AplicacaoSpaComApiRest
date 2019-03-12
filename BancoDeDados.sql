SET GLOBAL time_zone = "-3:00";

CREATE DATABASE avaliacao;
USE avaliacao;
CREATE TABLE contato (
    id int NOT NULL AUTO_INCREMENT,
	nome varchar(255),
    sobrenome varchar(255),
    email varchar(255),
    telefone varchar(20),
    twitter varchar(255),
    skype varchar(255)
    PRIMARY KEY (id)
);

INSERT INTO contato (nome, sobrenome, email, telefone, twitter,
 skype) VALUE ("Felipe", "Santos", "felipesantos21@live.com", "+55 (14) 98800-2115",
 "", "felipesantos21@live.com", "dfdfdf");