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

create table unidade_configuracao(
    id bigint auto_increment,
    nome varchar(100) not null unique,
    valor varchar(100),
    primary key (id)
);

insert into unidade_configuracao (nome, valor) VALUES ('temperatura', 'Temperatura');
insert into unidade_configuracao (nome, valor) VALUES ('pressao', 'Pressão');
insert into unidade_configuracao (nome, valor) VALUES ('velVento', 'Vel. Vento');
insert into unidade_configuracao (nome, valor) VALUES ('chuva', 'Chuva');
insert into unidade_configuracao (nome, valor) VALUES ('ptoOrvalho', 'Pto. Orvalho');
insert into unidade_configuracao (nome, valor) VALUES ('umiIns', 'Umidade');
insert into unidade_configuracao (nome, valor) VALUES ('nebulosidade', 'Nebulosidade');
insert into unidade_configuracao (nome, valor) VALUES ('radiacao', 'Radiação');
insert into unidade_configuracao (nome, valor) VALUES ('dirVento', 'Dir. Vento');
insert into unidade_configuracao (nome, valor) VALUES ('insolacao', 'Insolação');
insert into unidade_configuracao (nome, valor) VALUES ('rajVento', 'Raj. Vento');

create table cidade (
    sigla varchar(10) primary key,
    nome varchar(20)
);

create table estacao (
    codigo varchar(10) primary key,
    nome varchar(100)
);

create table arquivo (
    id bigint auto_increment primary key,
    cidade varchar(10),
    estacao varchar(10),
    foreign key (estacao) references estacao(codigo),
    foreign key (cidade) references cidade(sigla),
    unique (cidade, estacao)
);

create table registro(
    arquivo bigint,
    id bigint auto_increment primary key,
    data_hora timestamp not null,
    tipo_arquivo varchar(20) not null,
    foreign key(arquivo) references arquivo(id),
    unique (arquivo, data_hora, tipo_arquivo)
);

create table reg_informacao(
   registro bigint,
   nome varchar(100),
   dado_suspeito tinyint(1) default 0 null,
   valor double not null,
   foreign key(registro) references registro(id),
   primary key(registro, nome)
);
