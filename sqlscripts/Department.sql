CREATE TABLE department(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255) UNIQUE NOT NULL,
	id_user_info INT UNSIGNED NOT NULL,
	
	PRIMARY KEY(id),
	FOREIGN KEY (id_user_info) REFERENCES user_info (id) ON DELETE CASCADE ON UPDATE CASCADE
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;