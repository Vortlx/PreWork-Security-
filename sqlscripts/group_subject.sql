CREATE TABLE group_subject(
	id INT UNSIGNED AUTO_INCREMENT,
	id_group INT UNSIGNED,
	id_subject INT UNSIGNED,
	
	PRIMARY KEY(id),
	CONSTRAINT unique_group_subject UNIQUE(id_group, id_subject),
	FOREIGN KEY (id_group) REFERENCES groups(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_subject) REFERENCES subject(id) ON DELETE CASCADE ON UPDATE CASCADE

)  CHARACTER SET = utf8 COLLATE utf8_unicode_ci;