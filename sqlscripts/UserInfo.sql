CREATE TABLE user_info(
	id INT UNSIGNED AUTO_INCREMENT,
	login  VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	enabled TINYINT NOT NULL DEFAULT 1,
	id_role INT UNSIGNED NOT NULL,

	PRIMARY KEY(id),
	FOREIGN KEY (id_role) REFERENCES roles(id) ON DELETE CASCADE ON UPDATE CASCADE
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;