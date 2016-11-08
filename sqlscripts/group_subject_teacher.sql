CREATE TABLE group_subject_teacher(
	id INT UNSIGNED AUTO_INCREMENT,
	id_teacher INT UNSIGNED NOT NULL,
	id_group_subject INT UNSIGNED NOT NULL ,
	
	PRIMARY KEY(id),
	CONSTRAINT unique_teacher_subject UNIQUE(id_teacher, id_group_subject),
	FOREIGN KEY (id_teacher) REFERENCES teachers(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_group_subject) REFERENCES group_subject(id) ON DELETE CASCADE ON UPDATE CASCADE

)  CHARACTER SET = utf8 COLLATE utf8_unicode_ci;