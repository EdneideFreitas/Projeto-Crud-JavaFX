create database crud;

-----------------------------------------------------
-- TABELA SIMPLES
-----------------------------------------------------
create table Pessoa(

	codigo int not null auto_increment,
	nome varchar(255) not null,
	sobreNome varchar(255) not null,
	email varchar(255) not null,
	constraint pk_pessoa primary key(codigo)
);

drop table Pessoa;
truncate Pessoa;

select * from Pessoa;

insert into Pessoa
values
(1, 'Ednei'		, 'Freitas'		,'fretas@gmail.com');


-----------------------------------------------------
-- TABELA NORMALIZADA
-----------------------------------------------------
create table Pessoa(

	codigo int not null auto_increment,
	nome varchar(255) not null,
	email varchar(255) not null,
	sexo char(2) not null,
	idade int not null,
	cod_cidade int not null,
	constraint pk_pessoa primary key(codigo),
	constraint check_sexo check(sexo='F' or sexo='M'),
	constraint check_idade check(idade <=1 and idade>=110),
	constraint fk_pessoa foreign key(cod_cidade)
	references cidade (codigo)
);

create table cidade(
	codigo int not null auto_increment,
	uf char(2) not null,
	constraint pk_cidade primary key(codigo)
);
DELETE FROM Pessoa WHERE codigo=1;
SELECT * FROM  Pessoa;
SELECT * FROM  cidade;

--EXCLUIOR TABELA
DROP table Pessoa;

insert into Pessoa
values
(1,'Ednei Freitas'	,'fretas@gmail.com'		,'M'	,26	,1),
(2,'Ana Maria'		,'Ana@gmail.com'		,'F'	,16	,1),
(3,'Jose Augusto'	,'Jose@gmail.com'		,'M'	,41	,2),
(4,'Roberto Carlos'	,'Roberto@gmail.com'	,'M'	,21	,3),
(5,'Viviany Gomes'	,'Viviany@gmail.com'	,'F'	,29 ,4);


insert into cidade
values
(1, 'RJ'),
(2, 'SP'),
(3, 'BH'),
(4, 'MG'),
(5, 'RC');

select * from Pessoa;