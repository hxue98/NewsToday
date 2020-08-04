CREATE DATABASE newstoday;
USE newstoday;

-- create account table
CREATE TABLE `account`(
    `id` int primary key AUTO_INCREMENT,
    `username` varchar(30) NOT NULL,
    `password` varchar(30) NOT NULL
);

-- dummy data for account
INSERT INTO `account` (`id`, `username`, `password`)
VALUES (1, 'admin', '123');