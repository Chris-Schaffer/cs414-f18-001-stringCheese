CREATE DATABASE if not exists rollerball;
USE rollerball;

drop table if exists user;
CREATE TABLE user ( 
	user_id smallint unsigned not null auto_increment, 
	name varchar(20) unique not null, 
	email varchar(30) unique not null, 
	password varbinary(160),
	salt varbinary(64),
	primary key (user_id) 
);

drop table if exists game;
CREATE TABLE game ( 
	game_id smallint unsigned not null auto_increment, 
	name varchar(20) not null, 
	primary key (game_id) 
);

/* INSERT INTO user (name, email, password, salt ) VALUES
( 'chris', 'chris@yahoo.com', null, null),
( 'zaira', 'zaira@google.com', null, null); */