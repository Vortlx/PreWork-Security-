CREATE TABLE students(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255),
	family_name VARCHAR(255),
	id_group INT UNSIGNED,
	
	PRIMARY KEY (id),
	FOREIGN KEY (id_group) REFERENCES groups(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT unique_student UNIQUE(name, family_name)
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;