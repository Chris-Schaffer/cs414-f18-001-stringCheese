CREATE DATABASE if not exists rollerball;
USE rollerball;

drop table if exists user;
CREATE TABLE user ( 
	user_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
	name VARCHAR(20) UNIQUE NOT NULL,
	email VARCHAR(30) UNIQUE NOT NULL,
	password VARBINARY(160),
	salt VARBINARY(64),
	is_active BOOLEAN DEFAULT 1,  /* alias of tinyInt(1); 0 for false, nonzero for true */
	PRIMARY KEY (user_id)
);

drop table if exists game;
CREATE TABLE game ( 
	game_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
	/*host_id SMALLINT UNSIGNED,*/
	host VARCHAR(20) NOT NULL,
    /*invitee_id SMALLINT UNSIGNED DEFAULT 0,*/
    invitee VARCHAR(20) DEFAULT NULL,
    start_time TIMESTAMP,
    end_time TIMESTAMP  DEFAULT NULL,
    result VARCHAR(20) DEFAULT 'UNFINISHED',/*WINNER NAME*/
    abandon BOOLEAN DEFAULT FALSE,
    next_turn VARCHAR(20),
	PRIMARY KEY (game_id)
	/*FOREIGN KEY (host_id) REFERENCES user(user_id),
	FOREIGN KEY (invitee_id) REFERENCES user(user_id)*/
);

drop table if exists gameserialized;
Create table gameserialized (
    game_id SMALLINT UNSIGNED NOT NULL,
    game_object BLOB,
    FOREIGN KEY (game_id) references game(game_id)
);

 INSERT INTO user (name, email, password, salt ) VALUES
 ( 'chris', 'chris@yahoo.com', null, null),
 ( 'zaira', 'zaira@google.com', null, null),
 ('john', 'john@bing.com', null, null),
 ('jane', 'jane@aol.com', null, null);


INSERT INTO game (host, invitee, start_time, next_turn) VALUES
('chris','john',current_timestamp,'chris'),
('john','chris',current_timestamp,'john'),
('zaira','jane',current_timestamp,'jane');




