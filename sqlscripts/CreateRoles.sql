Create TABLE roles(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255) UNIQUE NOT NULL,
	
	PRIMARY KEY (id)
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;