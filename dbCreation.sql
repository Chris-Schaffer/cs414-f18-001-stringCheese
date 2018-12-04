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
/* FIXME need to check all classes for anywhere that used result='UNFINISHED' and change it to result=null.  */
drop table if exists game;
CREATE TABLE game ( 
	  game_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
	  /*host_id SMALLINT UNSIGNED,*/
	  host VARCHAR(20) NOT NULL,
    /*invitee_id SMALLINT UNSIGNED DEFAULT 0,*/
    invitee VARCHAR(20) DEFAULT NULL,
    start_time TIMESTAMP,
    end_time TIMESTAMP  DEFAULT NULL,
    result VARCHAR(20) DEFAULT NULL,/*WINNER NAME*/
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
    last_updated TIMESTAMP,
    FOREIGN KEY (game_id) references game(game_id)
);

 INSERT INTO user (name, email, password, salt ) VALUES
 ( 'chris', 'chris@yahoo.com', null, null),
 ( 'zaira', 'zaira@google.com', null, null),
 ('john', 'john@bing.com', null, null),
 ('jane', 'jane@aol.com', null, null);

/* Dummy Finished games  */
INSERT INTO game (host, invitee, start_time, end_time, result) VALUES
('chris','john',current_timestamp, current_timestamp, 'chris'),
('john','chris',current_timestamp, current_timestamp, 'john'),
('john','jane',current_timestamp, current_timestamp, 'jane'),
('chris','john',current_timestamp, current_timestamp, 'chris'),
('chris','john',current_timestamp, current_timestamp, 'john'),
('chris','john',current_timestamp, current_timestamp, 'chris'),
('chris','john',current_timestamp, current_timestamp, 'chris'),
('chris','jane',current_timestamp, current_timestamp, 'jane'),
('john','chris',current_timestamp, current_timestamp, 'john'),
('john','jane',current_timestamp, current_timestamp, 'jane'),
('chris','john',current_timestamp, current_timestamp, 'chris'),
('chris','john',current_timestamp, current_timestamp, 'john'),
('chris','john',current_timestamp, current_timestamp, 'chris'),
('chris','john',current_timestamp, current_timestamp, 'chris');




