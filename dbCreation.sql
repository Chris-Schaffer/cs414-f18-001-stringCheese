CREATE DATABASE rollerball;
USE rollerball;
CREATE TABLE tablename ( 
	id smallint unsigned not null auto_increment, 
	name varchar(20) not null, 
	constraint pk_example primary key (id) 
);



INSERT INTO tablename ( id, name ) VALUES ( null, 'Sample data' );