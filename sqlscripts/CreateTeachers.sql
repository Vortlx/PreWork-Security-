﻿CREATE TABLE teachers(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	family_name  VARCHAR(255) NOT NULL,
	login  VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	enabled TINYINT NOT NULL DEFAULT 1,
	id_subject INT  UNSIGNED NOT NULL,
	id_department INT UNSIGNED NOT NULL,
	
	PRIMARY KEY (id),
	CONSTRAINT unique_teacher UNIQUE(name, family_name),
	FOREIGN KEY (id_subject) REFERENCES subject (id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_department) REFERENCES department (id) ON DELETE CASCADE ON UPDATE CASCADE
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;