CREATE TABLE subject(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	subject_type VARCHAR(8) NOT NULL,
	
	PRIMARY KEY(id),
	CONSTRAINT unique_subject UNIQUE(name, subject_type)
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;