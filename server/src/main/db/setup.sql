/*DROP DATABASE bookstore;*/
CREATE DATABASE bookstore;
USE bookstore;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
                            `category_id` int unsigned NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`category_id`)
);

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
                        `book_id` int unsigned NOT NULL AUTO_INCREMENT,
                        `title` varchar(60) DEFAULT NULL,
                        `author` varchar(60) DEFAULT NULL,
                        `description` varchar(1000) DEFAULT NULL,
                        `price` int unsigned DEFAULT NULL,
                        `rating` int unsigned DEFAULT NULL,
                        `is_public` tinyint(1) DEFAULT NULL,
                        `is_featured` tinyint(1) DEFAULT NULL,
                        `category_id` int unsigned DEFAULT NULL,
                        PRIMARY KEY (`book_id`),
                        KEY `category_id` (`category_id`),
                        CONSTRAINT `book_category_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
);

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
                            `customer_id` int unsigned NOT NULL AUTO_INCREMENT,
                            `name` varchar(45) DEFAULT NULL,
                            `address` varchar(45) DEFAULT NULL,
                            `phone` varchar(45) DEFAULT NULL,
                            `email` varchar(45) DEFAULT NULL,
                            `cc_number` varchar(19) DEFAULT NULL,
                            `cc_exp_date` date DEFAULT NULL,
                            PRIMARY KEY (`customer_id`)
);

DROP TABLE IF EXISTS `customer_order`;
CREATE TABLE `customer_order` (
                                  `customer_order_id` int unsigned NOT NULL AUTO_INCREMENT,
                                  `amount` int unsigned DEFAULT NULL,
                                  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  `confirmation_number` int unsigned DEFAULT NULL,
                                  `customer_id` int unsigned DEFAULT NULL,
                                  PRIMARY KEY (`customer_order_id`),
                                  KEY `customer_id` (`customer_id`),
                                  CONSTRAINT `customer_order_customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
);

DROP TABLE IF EXISTS `customer_order_line_item`;
CREATE TABLE `customer_order_line_item` (
                                            `customer_order_id` int unsigned NOT NULL,
                                            `book_id` int unsigned DEFAULT NULL,
                                            `quantity` smallint unsigned DEFAULT '1',
                                            PRIMARY KEY (`customer_order_id`),
                                            KEY `book_id` (`book_id`),
                                            CONSTRAINT `customer_order_line_item_book_fk` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
);

DELETE FROM book;
ALTER TABLE book AUTO_INCREMENT = 1001;

DELETE FROM category;
ALTER TABLE category AUTO_INCREMENT = 1001;

INSERT INTO `category` (`name`) VALUES
                                    ('Best Sellers'),
                                    ('Business & Finance'),
                                    ('Fiction'),
    /*('Mystery'),*/
                                    ('Sports'),
                                    ('Technology');

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Old Man and the Sea', 'Ernest Hemingway', '', 599, 0, TRUE, TRUE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Boys In the Boat', 'Daniel James Brown', '', 799, 0, FALSE, TRUE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Into the Abyss', 'Carol Shaben', '', 999, 0, TRUE, TRUE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Alchemist', 'Paulo Coelho', '', 699, 0, FALSE, TRUE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Kite Runner', 'Khaled Hosseini', '', 899, 0, TRUE, TRUE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Hatchet', 'Gary Paulsen', '', 399, 0, FALSE, TRUE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('1984', 'George Orwell', '', 1099, 0, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Lord of the Flies', 'William Golding', '', 499, 0, FALSE, FALSE, 1001);

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Intelligent Investor', 'Benjamin Graham', '', 1599, 0, FALSE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Leading Change', 'John P. Cotter', '', 699, 0, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Think and Grow Rich', 'Napoleon Hill', '', 799, 0, FALSE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Book on Rental Property Investing', 'Brandon Turner', '', 1099, 0, TRUE, FALSE, 1002);

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Things They Carried', 'Tim O''Brien', '', 499, 0, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('All the Light We Cannot See', 'Anthony Doerr', '', 699, 0, FALSE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Midnight Library', 'Matt Haig', '', 899, 0, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Wonder', 'RJ Palacio', '', 1099, 0, FALSE, FALSE, 1003);

/*
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Camino Island', 'John Grisham', '', 599, 0, TRUE, TRUE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Night Fire', 'Michael Connelly', '', 699, 0, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('A Minute To Midnight', 'David Balducci', '', 399, 0, FALSE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Lost Symbol', 'Dan Brown', '', 499, 0, TRUE, FALSE, 1004);
 */

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('It''s Better To Be Feared', 'Seth Wickersham', '', 999, 0, FALSE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Moneyball', 'Michael Lewis', '', 699, 0, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Five Lessons', 'Ben Hogan', '', 599, 0, FALSE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Baseball 100', 'Joe Posnanski', '', 799, 0, TRUE, FALSE, 1004);

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Weapons of Math Destruction', 'Cathy O''Neil', '', 1099, 0, TRUE, TRUE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Cracking the Coding Interview', 'Gayle Laakmann McDowell', '', 1399, 0, FALSE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Clean Code', 'Robert Martin', '', 1599, 0, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Alice and Bob Learn Application Security', 'Tanya Janca', '',1199, 0, FALSE, FALSE, 1005);
