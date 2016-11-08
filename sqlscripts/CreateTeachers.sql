CREATE TABLE teachers(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255),
	family_name VARCHAR(255),
	login VARCHAR(255) UNIQUE,
	password VARCHAR(255),
	id_department INT UNSIGNED,
	
	PRIMARY KEY (id),
	CONSTRAINT unique_teacher UNIQUE(name, family_name),
	FOREIGN KEY (id_department) REFERENCES department(id) ON DELETE CASCADE ON UPDATE CASCADE
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;