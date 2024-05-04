create schema porygon;

create user 'porygon'@'localhost' identified by 'pesquisador';

grant update, delete, select, insert on porygon.* to 'porygon'@'localhost';

use porygon;

create table atr_configuracao (
	id bigint auto_increment, 
    nome varchar(100) not null unique,
    valor double not null,
    primary key(id)
);

create table arquivo (
	id bigint auto_increment primary key, 
    nome varchar(100) not null, 
    cidade varchar(10) not null, 
    estacao varchar(20) not null
);

create table registro(
	id bigint auto_increment primary key,
    data_hora timestamp not null
);

create table reg_informacao(
	registro bigint,
	nome varchar(100) not null unique, 
    valor double not null,
    foreign key(registro) references registro(id)
);