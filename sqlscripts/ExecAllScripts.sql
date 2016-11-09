DROP TABLE group_subject;
DROP TABLE teachers;
DROP TABLE subject;
DROP TABLE students;
DROP TABLE groups;
DROP TABLE department;

CREATE TABLE department(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255) UNIQUE NOT NULL ,
	login VARCHAR(255) UNIQUE NOT NULL ,
	password VARCHAR(255) NOT NULL,
	
	PRIMARY KEY(id)
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;

CREATE TABLE groups(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255) UNIQUE NOT NULL ,
	id_department INT UNSIGNED NOT NULL ,
	
	PRIMARY KEY (id),
	FOREIGN KEY (id_department) REFERENCES department(id) ON DELETE CASCADE ON UPDATE CASCADE
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;

CREATE TABLE subject(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	subject_type VARCHAR(8) NOT NULL,
	
	PRIMARY KEY(id),
	CONSTRAINT unique_subject UNIQUE(name, subject_type)
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;

CREATE TABLE teachers(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	family_name  VARCHAR(255) NOT NULL,
	login  VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	id_subject INT  UNSIGNED NOT NULL,
	id_department INT UNSIGNED NOT NULL,
	
	PRIMARY KEY (id),
	CONSTRAINT unique_teacher UNIQUE(name, family_name),
	FOREIGN KEY (id_subject) REFERENCES subject (id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_department) REFERENCES department (id) ON DELETE CASCADE ON UPDATE CASCADE
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;

CREATE TABLE students(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	family_name  VARCHAR(255) NOT NULL,
	login VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	id_group INT UNSIGNED NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY (id_group) REFERENCES groups(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT unique_student UNIQUE(name, family_name)
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;

CREATE TABLE group_subject(
	id INT UNSIGNED AUTO_INCREMENT,
	id_group INT UNSIGNED NOT NULL,
	id_subject INT UNSIGNED NOT NULL,
	
	PRIMARY KEY(id),
	CONSTRAINT unique_group_subject UNIQUE(id_group, id_subject),
	FOREIGN KEY (id_group) REFERENCES groups(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_subject) REFERENCES subject(id) ON DELETE CASCADE ON UPDATE CASCADE

)  CHARACTER SET = utf8 COLLATE utf8_unicode_ci;

INSERT INTO department (name, login, password) VALUES ('SuperDepartment', 'SuperDepartment', 'SuperDepartment');

INSERT INTO groups (name, id_department) VALUES('111', 1);
INSERT INTO groups (name, id_department) VALUES('112', 1);
INSERT INTO groups (name, id_department) VALUES('113', 1);
INSERT INTO groups (name, id_department) VALUES('121', 1);
INSERT INTO groups (name, id_department) VALUES('122', 1);
INSERT INTO groups (name, id_department) VALUES('131', 1);
INSERT INTO groups (name, id_department) VALUES('141', 1);

INSERT INTO subject (name, subject_type) VALUES('Mathematical analysis', 'LECTURE');
INSERT INTO subject (name, subject_type) VALUES('Mathematical analysis', 'PRACTICE');
INSERT INTO subject (name, subject_type) VALUES('Information Technology', 'LECTURE');
INSERT INTO subject (name, subject_type) VALUES('Information Technology', 'PRACTICE');
INSERT INTO subject (name, subject_type) VALUES('Geometry', 'LECTURE');
INSERT INTO subject (name, subject_type) VALUES('Geometry', 'PRACTICE');
INSERT INTO subject (name, subject_type) VALUES('A culture of speech', 'LECTURE');
INSERT INTO subject (name, subject_type) VALUES('Foreign language', 'PRACTICE');
INSERT INTO subject (name, subject_type) VALUES('Foreign language', 'LECTURE');
INSERT INTO subject (name, subject_type) VALUES('Physical Culture', 'PRACTICE');
INSERT INTO subject (name, subject_type) VALUES('Mathematical logic', 'LECTURE');
INSERT INTO subject (name, subject_type) VALUES('Mathematical logic', 'PRACTICE');
INSERT INTO subject (name, subject_type) VALUES('Differential equations', 'LECTURE');
INSERT INTO subject (name, subject_type) VALUES('Differential equations', 'PRACTICE');
INSERT INTO subject (name, subject_type) VALUES('Discrete Math', 'LECTURE');
INSERT INTO subject (name, subject_type) VALUES('Discrete Math', 'PRACTICE');
INSERT INTO subject (name, subject_type) VALUES('Economics', 'LECTURE');
INSERT INTO subject (name, subject_type) VALUES('Economics', 'PRACTICE');
INSERT INTO subject (name, subject_type) VALUES('Social science', 'LECTURE');
INSERT INTO subject (name, subject_type) VALUES('Math modeling', 'LECTURE');
INSERT INTO subject (name, subject_type) VALUES('Math modeling', 'PRACTICE');

INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Jeannie', 'Reynolds', 'ReynoldsJeannie', 'test', 1, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Julian', 'Bowers', 'BowersJulian', 'test', 2, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Maria', 'Graham', 'GrahamMaria', 'test', 3, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Frank', 'Bryan', 'BryanFrank', 'test', 4, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Constance', 'Bryant', 'BryantConstance', 'test', 5, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Cecilia', 'Fleming', 'FlemingCecilia', 'test', 6, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Lauren', 'Simmons', 'SimmonsLauren', 'test', 7, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Jackie', 'Doyle', 'DoyleJackie', 'test', 8, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Leonard', 'Miller', 'MillerLeonard', 'test', 9, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Lynda', 'Gray', 'GrayLynda', 'test', 10, 1);

INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Natasha', 'White', 'WhiteNatasha', 'test', 11, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Eugene', 'Silva', 'SilvaEugene', 'test', 12, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Lucille', 'Hoffman', 'HoffmanLucille', 'test', 13, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Darryl', 'Gray', 'GrayDarryl', 'test', 14, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Tamara', 'Fisher', 'FisherTamara', 'test', 15, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Kara', 'Rivera', 'RiveraKara', 'test', 16, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Glenda', 'Walters', 'WaltersGlenda', 'test', 17, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Meghan', 'James', 'JamesMeghan', 'test', 18, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Bobby', 'Riley', 'RileyBobby', 'test', 19, 1);
INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Shannon', 'Barton', 'BartonShannon', 'test', 20, 1);

INSERT INTO teachers (name, family_name, login, password, id_subject, id_department) VALUES('Donald', 'Hanson', 'HansonDonald', 'test', 21, 1);


INSERT INTO students (name, family_name, login, password, id_group) VALUES('Norman', 'Hernandez', 'HernandezNorman', 'test', 1);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Roger', 'Evans', 'EvansRoger', 'test', 1);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Diane', 'Newton', 'NewtonDiane', 'test', 1);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Katrina', 'Mcdonald', 'McdonaldKatrina', 'test', 1);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Regina', 'Banks', 'BanksRegina', 'test', 1);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Betsy', 'Walters', 'WaltersBetsy', 'test', 1);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Crystal', 'Parks', 'ParksCrystal', 'test', 1);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Yolanda', 'Rodriquez', 'RodriquezYolanda', 'test', 1);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Luther', 'Knight', 'KnightLuther', 'test', 1);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Fred', 'Anderson', 'AndersonFred', 'test', 1);

INSERT INTO students (name, family_name, login, password, id_group) VALUES('Joanna', 'Tyler', 'TylerJoanna', 'test', 2);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Walter', 'Wong', 'WongWalter', 'test', 2);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Willard', 'Cole', 'ColeWillard', 'test', 2);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Lola', 'Schneider', 'SchneiderLola', 'test', 2);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Lucas', 'Farmer', 'FarmerLucas', 'test', 2);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Shari', 'Alexander', 'AlexanderShari', 'test', 2);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Mandy', 'Rogers', 'RogersMandy', 'test', 2);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Anthony', 'Bowers', 'BowersAnthony', 'test', 2);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Gertrude', 'Shaw', 'ShawGertrude', 'test', 2);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Hector', 'Dixon', 'DixonHector', 'test', 2);

INSERT INTO students (name, family_name, login, password, id_group) VALUES('Stephanie', 'Mann', 'MannStephanie', 'test', 3);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Brenda', 'Mccoy', 'MccoyBrenda', 'test', 3);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Elizabeth', 'Mclaughlin', 'MclaughlinElizabeth', 'test', 3);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Caleb', 'Lopez', 'LopezCaleb', 'test', 3);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Vicky', 'Soto', 'SotoVicky', 'test', 3);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Geraldine', 'Kim', 'KimGeraldine', 'test', 3);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Steven', 'Mills', 'MillsSteven', 'test', 3);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Annie', 'Brewer', 'BrewerAnnie', 'test', 3);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Ann', 'Payne', 'PayneAnn', 'test', 3);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Marvin', 'Hodges', 'HodgesMarvin', 'test', 3);

INSERT INTO students (name, family_name, login, password, id_group) VALUES('Orville', 'Carr', 'CarrOrville', 'test', 4);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Wilson', 'Moss', 'MossWilson', 'test', 4);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Audrey', 'Moody', 'MoodyAudrey', 'test', 4);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Tom', 'Webb', 'WebbTom', 'test', 4);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Antonio', 'Long', 'LongAntonio', 'test', 4);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Nora', 'Henderson', 'HendersonNora', 'test', 4);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Marion', 'Mathis', 'MathisMarion', 'test', 4);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Crystal', 'Blair', 'BlairCrystal', 'test', 4);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Sonia', 'Holmes', 'HolmesSonia', 'test', 4);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Nicole', 'Ferguson', 'FergusonNicole', 'test', 4);

INSERT INTO students (name, family_name, login, password, id_group) VALUES('Elisa', 'Thomas', 'ThomasElisa', 'test', 5);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Orlando', 'Sharp', 'SharpOrlando', 'test', 5);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Holly', 'Alvarado', 'AlvaradoHolly', 'test', 5);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Roberta', 'Vasquez', 'VasquezRoberta', 'test', 5);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Brittany', 'Ross', 'RossBrittany', 'test', 5);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Rachel', 'Beck', 'BeckRachel', 'test', 5);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Joe', 'Rowe', 'RoweJoe', 'test', 5);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Caroline', 'Frank', 'FrankCaroline', 'test', 5);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Melanie', 'Huff', 'HuffMelanie', 'test', 5);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Raymond', 'Hansen', 'HansenRaymond', 'test', 5);

INSERT INTO students (name, family_name, login, password, id_group) VALUES('Gene', 'Fox', 'FoxGene', 'test', 6);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Leo', 'Erickson', 'EricksonLeo', 'test', 6);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Lorena', 'Bridges', 'BridgesLorena', 'test', 6);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Jerry', 'Harrison', 'HarrisonJerry', 'test', 6);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Lindsey', 'Walker', 'WalkerLindsey', 'test', 6);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Kari', 'Torres', 'TorresKari', 'test', 6);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Carlos', 'Walsh', 'WalshCarlos', 'test', 6);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Emily', 'Morgan', 'MorganEmily', 'test', 6);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Maria', 'Mckenzie', 'MckenzieMaria', 'test', 6);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Grady', 'Hill', 'HillGrady', 'test', 6);

INSERT INTO students (name, family_name, login, password, id_group) VALUES('Ollie', 'Ortiz', 'OrtizOllie', 'test', 7);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Lynn', 'Curtis', 'CurtisLynn', 'test', 7);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Jeffrey', 'Buchanan', 'BuchananJeffrey', 'test', 7);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Alvin', 'Matthews', 'MatthewsAlvin', 'test', 7);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Chester', 'Briggs', 'BriggsChester', 'test', 7);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Billie', 'Williams', 'WilliamsBillie', 'test', 7);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Tomas', 'Rogers', 'RogersTomas', 'test', 7);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Darrel', 'Hicks', 'HicksDarrel', 'test', 7);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Ed', 'Thompson', 'ThompsonEd', 'test', 7);
INSERT INTO students (name, family_name, login, password, id_group) VALUES('Marlon', 'Mckenzie', 'MckenzieMarlon', 'test', 7);



INSERT INTO group_subject (id_group, id_subject) VALUES(1, 1);
INSERT INTO group_subject (id_group, id_subject) VALUES(1, 2);
INSERT INTO group_subject (id_group, id_subject) VALUES(1, 3);
INSERT INTO group_subject (id_group, id_subject) VALUES(1, 4);
INSERT INTO group_subject (id_group, id_subject) VALUES(1, 5);
INSERT INTO group_subject (id_group, id_subject) VALUES(1, 6);
INSERT INTO group_subject (id_group, id_subject) VALUES(1, 7);

INSERT INTO group_subject (id_group, id_subject) VALUES(2, 1);
INSERT INTO group_subject (id_group, id_subject) VALUES(2, 2);
INSERT INTO group_subject (id_group, id_subject) VALUES(2, 3);
INSERT INTO group_subject (id_group, id_subject) VALUES(2, 4);
INSERT INTO group_subject (id_group, id_subject) VALUES(2, 5);
INSERT INTO group_subject (id_group, id_subject) VALUES(2, 6);
INSERT INTO group_subject (id_group, id_subject) VALUES(2, 7);

INSERT INTO group_subject (id_group, id_subject) VALUES(3, 1);
INSERT INTO group_subject (id_group, id_subject) VALUES(3, 2);
INSERT INTO group_subject (id_group, id_subject) VALUES(3, 3);
INSERT INTO group_subject (id_group, id_subject) VALUES(3, 4);
INSERT INTO group_subject (id_group, id_subject) VALUES(3, 5);
INSERT INTO group_subject (id_group, id_subject) VALUES(3, 6);
INSERT INTO group_subject (id_group, id_subject) VALUES(3, 7);

INSERT INTO group_subject (id_group, id_subject) VALUES(4, 3);
INSERT INTO group_subject (id_group, id_subject) VALUES(4, 4);
INSERT INTO group_subject (id_group, id_subject) VALUES(4, 8);
INSERT INTO group_subject (id_group, id_subject) VALUES(4, 11);
INSERT INTO group_subject (id_group, id_subject) VALUES(4, 12);
INSERT INTO group_subject (id_group, id_subject) VALUES(4, 20);
INSERT INTO group_subject (id_group, id_subject) VALUES(4, 21);

INSERT INTO group_subject (id_group, id_subject) VALUES(5, 3);
INSERT INTO group_subject (id_group, id_subject) VALUES(5, 4);
INSERT INTO group_subject (id_group, id_subject) VALUES(5, 8);
INSERT INTO group_subject (id_group, id_subject) VALUES(5, 11);
INSERT INTO group_subject (id_group, id_subject) VALUES(5, 12);
INSERT INTO group_subject (id_group, id_subject) VALUES(5, 20);
INSERT INTO group_subject (id_group, id_subject) VALUES(5, 21);

INSERT INTO group_subject (id_group, id_subject) VALUES(6, 1);
INSERT INTO group_subject (id_group, id_subject) VALUES(6, 2);
INSERT INTO group_subject (id_group, id_subject) VALUES(6, 3);
INSERT INTO group_subject (id_group, id_subject) VALUES(6, 4);
INSERT INTO group_subject (id_group, id_subject) VALUES(6, 10);
INSERT INTO group_subject (id_group, id_subject) VALUES(6, 17);
INSERT INTO group_subject (id_group, id_subject) VALUES(6, 18);

INSERT INTO group_subject (id_group, id_subject) VALUES(7, 1);
INSERT INTO group_subject (id_group, id_subject) VALUES(7, 2);
INSERT INTO group_subject (id_group, id_subject) VALUES(7, 3);
INSERT INTO group_subject (id_group, id_subject) VALUES(7, 4);
INSERT INTO group_subject (id_group, id_subject) VALUES(7, 8);
INSERT INTO group_subject (id_group, id_subject) VALUES(7, 10);
INSERT INTO group_subject (id_group, id_subject) VALUES(7, 19);