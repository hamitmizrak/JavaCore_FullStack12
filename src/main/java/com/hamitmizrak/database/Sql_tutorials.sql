-- DELETE DATABASE
DROP DATABASE `cars`;

-- Create Database
CREATE SCHEMA `cars` DEFAULT CHARACTER SET utf8 COLLATE utf8_turkish_ci ;

-- Set
use cars;

-- Table
CREATE TABLE register (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nick_name` VARCHAR(255) NULL DEFAULT 'takma adınıız yazmadınız.',
  `email_address` VARCHAR(255) NULL DEFAULT 'email adres yazmadınız',
  `password` VARCHAR(255) NULL DEFAULT 'şifrenizi girmediniz',
  `roles` VARCHAR(10) NULL DEFAULT 'user',
  `remaining_number` INT(2) NULL DEFAULT '4',
  `is_passive` VARCHAR(2) NULL DEFAULT '1',
  `system_created_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;

-- tabloyayeni kolon eklemek
ALTER TABLE register
ADD COLUMN `system_created_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `is_passive`;

-- DELETE TABLE
DROP TABLE register;

-- TRUNCATE Table
TRUNCATE register;

-- INSERT
-- insert into database.table () values ()
INSERT INTO register (`nick_name`, `email_address`, `password`, `roles`, `remaining_number`, `is_passive`) VALUES ('computer', 'hamitmizrak@gmail.com', '123456','admin','5','1');

-- FIND
SELECT * FROM cars.register where id=1;

-- UPDATE
-- update database.table  set attributes="içerik"
UPDATE register SET `nick_name`="nick44", `email_address`="email44@gmail.com", `password`="12345644", `roles`="user", `remaining_number`=44, `is_passive`=1 WHERE (`id` = '1');

-- DELETE
DELETE FROM register WHERE (`id` = '1');

-- SELECT
SELECT * FROM cars.register;
SELECT * FROM cars.register where email_address="hamitmizrak";
select count(*) from register;

-- LIKE --
select * from register;
select * from register where id=2;
select * from register where nick_name like 'c%'; -- c harfiyle başladı
select * from register where nick_name like 'b%' or email_address like 'da%';
select * from register where nick_name like '%4'; -- 4 ile bitti
select * from register where nick_name like '_o%'; -- ikinci karakter o olanlar
