SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
create database time_tracking;
use time_tracking;
CREATE TABLE IF NOT EXISTS `time_tracking`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(35) NOT NULL,
  `profession_id`  INT NOT NULL ,
  `role` VARCHAR(20) ,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_user_to_profession_id`
  FOREIGN KEY (`profession_id`)
  REFERENCES `time_tracking`.`profession` (`profession_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB CHARACTER SET=UTF8;
INSERT INTO user (user_id, username, email, password, profession_id, role)
VALUES (1 , 'AlexMostipan', 'a.mostipan@gmail.com', '123123123' , 1 , 'ADMIN'),
( 2 , 'AndrewRyabchenko', 'ryaba@gmail.com', '123123123' , 2 , 'USER'),
( 3 , 'KovalchukRoman', 'kovalchuk@gmail.com', '123123123' , 3 , 'USER');

CREATE TABLE IF NOT EXISTS `time_tracking`.`activity` (
    `activity_id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `dateStart` VARCHAR(50),
    `dateFinish` VARCHAR(50),
    `description` VARCHAR(1000) NOT NULL,
    `activity_type_id` INT NOT NULL,
    `status` VARCHAR(20),
    PRIMARY KEY (`activity_id`),
   FOREIGN KEY (user_id)  REFERENCES user (user_id) 
	ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (activity_type_id)  REFERENCES activity_type (activity_type_id) 
        ON DELETE CASCADE ON UPDATE CASCADE
)  ENGINE=INNODB CHARACTER SET=UTF8;

INSERT INTO activity (activity_id , user_id , description  , activity_type_id , status , dateStart  )
values (1 , 2 , 'Срочно разработать макет главной страницы сайты', 1 , 'DURING'  , '00:05 2019.09.18' ),
(2 , 2 , ' Нарисовать логотип', 1 , 'DURING' , '00:05 2019.09.18' ) ,
(3 , 3 , 'Сделать реализацию Dao паттерна ', 2 , 'DURING' , '00:05 2019.09.18' ) ,
(4 , 2 , ' Сделать Connection pool', 2 , 'DURING' ,'00:05 2019.09.18' ) ,
(5 , 2 , 'Срочно разработать макет страницы регистрации и логина страницы сайты', 1 , 'DURING' , '00:05 2019.09.18' ) ;
CREATE TABLE IF NOT EXISTS `time_tracking`.`activity_request` (
    `activity_request_id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `request_type` VARCHAR(20),
    `creationDate` VARCHAR(50),
    PRIMARY KEY (`activity_request_id`),
    CONSTRAINT `fk_activity_request_to_user` FOREIGN KEY (`user_id`)
        REFERENCES `time_tracking`.`user` (`user_id`)
        ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB CHARACTER SET=UTF8;
INSERT INTO activity_request (activity_request_id , user_id , request_type , creationDate )
values (1 , 2 , 'GETTING', '00:05 2019.09.18' );


CREATE TABLE IF NOT EXISTS `time_tracking`.`activity_type` (
    `activity_type_id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`activity_type_id`)
)ENGINE = InnoDB CHARACTER SET=UTF8;
INSERT INTO activity_type (activity_type_id , title  )
values (1 , 'Задача по дизайну' ),
	(2 ,'Задача по Dev');

CREATE TABLE IF NOT EXISTS `time_tracking`.`profession` (
  `profession_id` INT NOT NULL AUTO_INCREMENT,
  `skill`  varchar(20) not null ,
  `profession`  varchar(100) not null ,
  `experience` INT,
 PRIMARY KEY (`profession_id`))ENGINE = InnoDB CHARACTER SET=UTF8;

 SET FOREIGN_KEY_CHECKS = 1;