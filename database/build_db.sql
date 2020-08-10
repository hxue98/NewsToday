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

CREATE TABLE `news_category`(
    `id` int PRIMARY KEY,
    `category` VARCHAR(30) NOT NULL
);

-- populate news_category
INSERT INTO `news_category` (`id`, `category`)
VALUES (1, 'Tech'),
       (2, 'Entertainment'),
       (3, 'Gaming'),
       (4, 'Sports'),
       (5, 'Finance'),
       (6, 'Military'),
       (7, 'International'),
       (8, 'Other');

-- create post table
CREATE TABLE `post`(
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `account_id` int NOT NULL,
    `text` VARCHAR(2000) NOT NULL,
    FOREIGN KEY (`account_id`) REFERENCES `account`(`id`)
);

-- dummy data for post
INSERT INTO `post` (`id`, `account_id`, `text`)
VALUES (1, 1, 'text post');

-- create post_image table
CREATE TABLE `post_image`(
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `post_id` int NOT NULL,
    `image_path` varchar(255) NOT NULL,
    FOREIGN KEY (`post_id`) REFERENCES `post`(`id`)
);

-- dummy data for post_image
INSERT INTO `post_image` (`id`, `post_id`, `image_path`)
VALUES (1, 1, '/sample/image 1.jpg'),
       (2, 1, '/sample/image 2.jpg');

-- create news table
CREATE TABLE `news`(
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(255) NOT NULL,
    `category_id` int NOT NULL,
    `summary` varchar(1023) NOT NULL,
    `url` varchar(255) NOT NULL,
    `date` datetime NOT NULL,
    `source` varchar(30) NOT NULL,
    `image_path` varchar(255) NOT NULL,
    `num_clicks` int DEFAULT 0,
    FOREIGN KEY (`category_id`) REFERENCES `news_category`(`id`)
);

-- dummy data for news
INSERT INTO `news` (`id`, `title`, `category_id`, `summary`, `url`, `date`, `source`, `image_path`)
VALUES (1, 'test title 1', 1, 'test summary 1', 'https://www.google.com/', '2020-01-01 01:01:01', 'CNN', '/sample/image 1.jpg'),
       (2, 'test title 2', 2, 'test summary 2', 'https://www.amazon.com/', '2020-01-01 02:02:02', 'FOX', '/sample/image 2.jpg'),
       (3, 'test title 3', 3, 'test summary 3', 'https://www.facebook.com/', '2020-01-01 03:03:03', 'NEW YORK TIME', '/sample/image 3.jpg'),
       (4, 'test title 4', 4, 'test summary 4', 'https://www.bing.com/', '2020-01-01 04:04:04', 'ABC', '/sample/image 4.jpg'),
       (5, 'test title 5', 5, 'test summary 5', 'https://www.youtube.com/', '2020-01-01 05:05:05', 'BBC', '/sample/image 5.jpg');

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