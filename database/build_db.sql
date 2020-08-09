CREATE DATABASE newstoday;
USE newstoday;

-- create account table
CREATE TABLE `account`(
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `username` varchar(30) NOT NULL,
    `email` varchar(30) NOT NULL,
    `password` varchar(100) NOT NULL
);

-- dummy data for account
INSERT INTO `account` (`id`, `username`, `email`, `password`)
VALUES (1, 'admin', 'admin@admin.com', '123456');

-- create news table
CREATE TABLE `news`(
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(255) NOT NULL,
    `abstract` varchar(1023) NOT NULL,
    `url` varchar(255) NOT NULL,
    `date` datetime NOT NULL,
    `source` varchar(30) NOT NULL,
    `image_path` varchar(255) NOT NULL
);

-- dummy data for news
INSERT INTO `news` (`id`, `title`, `abstract`, `url`, `date`, `source`, `image_path`)
VALUES (1, 'test title', 'test abstract', 'https://www.google.com/', '2020-01-01 05:05:05', 'CNN', '/sample/image.jpg');

-- create comment table
CREATE TABLE `comment`(
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `account_id` int NOT NULL,
    `news_id` int NOT NULL,
    `comment_id` int,
    `comment` varchar(300) NOT NULL,
    `date` datetime NOT NULL,
    FOREIGN KEY (`account_id`) REFERENCES `account`(`id`),
    FOREIGN KEY (`news_id`) REFERENCES `news`(`id`),
    FOREIGN KEY (`comment_id`) REFERENCES `comment`(`id`)
);

-- dummy data for comment
INSERT INTO `comment` (`id`, `account_id`, `news_id`, `comment`, `date`)
VALUES(1, 1, 1, 'test comment', '2020-01-01 01:01:01');

-- create comment_image table
CREATE TABLE `comment_image`(
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `comment_id` int NOT NULL,
    `image_path` varchar(255) NOT NULL,
    FOREIGN KEY (`comment_id`) REFERENCES `comment`(`id`)
);

-- dummy data for comment_image
INSERT INTO `comment_image` (`id`, `comment_id`, `image_path`)
VALUES(1, 1, '/sample/image.jpg');

-- create rating table
CREATE TABLE `rating`(
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `account_id` int NOT NULL,
    `news_id` int NOT NULL,
    `like` boolean,
    `dislike` boolean,
    FOREIGN KEY (`account_id`) REFERENCES `account`(`id`),
    FOREIGN KEY (`news_id`) REFERENCES `news`(`id`)
);

-- dummy data for rating
INSERT INTO `rating` (`id`, `account_id`, `news_id`, `like`)
VALUES(1, 1, 1, true);

-- TODO: add profile attributes
-- create profile table
CREATE TABLE `profile`(
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `account_id` int NOT NULL,
    FOREIGN KEY (`account_id`) REFERENCES `account`(`id`)
);

-- TODO: populate profile table with dummy data