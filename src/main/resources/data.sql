DROP TABLE IF EXISTS category;

CREATE TABLE category (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

insert into category (name) values('Miscellaneous');
