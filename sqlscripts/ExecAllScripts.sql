DROP TABLE group_subject;
DROP TABLE teachers;
DROP TABLE subject;
DROP TABLE students;
DROP TABLE groups;
DROP TABLE department;
DROP TABLE user_info;
DROP TABLE roles;

Create TABLE roles(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255) UNIQUE NOT NULL,
	
	PRIMARY KEY (id)
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;

CREATE TABLE user_info(
	id INT UNSIGNED AUTO_INCREMENT,
	username VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	enabled TINYINT NOT NULL DEFAULT 1,
	id_role INT UNSIGNED NOT NULL,

	PRIMARY KEY(id),
	FOREIGN KEY (id_role) REFERENCES roles(id) ON DELETE CASCADE ON UPDATE CASCADE
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;

CREATE TABLE department(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255) UNIQUE NOT NULL,
	id_user_info INT UNSIGNED NOT NULL,
	
	PRIMARY KEY(id),
	FOREIGN KEY (id_user_info) REFERENCES user_info (id) ON DELETE CASCADE ON UPDATE CASCADE
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
	id_subject INT  UNSIGNED NOT NULL,
	id_department INT UNSIGNED NOT NULL,
	id_user_info INT UNSIGNED NOT NULL,
	
	PRIMARY KEY (id),
	CONSTRAINT unique_teacher UNIQUE(name, family_name),
	FOREIGN KEY (id_subject) REFERENCES subject (id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_department) REFERENCES department (id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_user_info) REFERENCES user_info (id) ON DELETE CASCADE ON UPDATE CASCADE
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;

CREATE TABLE students(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	family_name  VARCHAR(255) NOT NULL,
	id_group INT UNSIGNED NOT NULL,
	id_user_info INT UNSIGNED NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY (id_group) REFERENCES groups(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_user_info) REFERENCES user_info (id) ON DELETE CASCADE ON UPDATE CASCADE,
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

INSERT INTO roles VALUES (1, 'ROLE_STUDENT');
INSERT INTO roles VALUES (2, 'ROLE_TEACHER');
INSERT INTO roles VALUES (3, 'ROLE_DEPARTMENT');

INSERT INTO user_info (username, password, id_role) VALUES('SuperDepartment', 'SuperDepartment', 3);

INSERT INTO user_info (username, password, id_role) VALUES('ReynoldsJeannie', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('BowersJulian', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('GrahamMaria', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('BryanFrank', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('BryantConstance', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('FlemingCecilia', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('SimmonsLauren', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('DoyleJackie', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('MillerLeonard', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('GrayLynda', 'test', 2);

INSERT INTO user_info (username, password, id_role) VALUES('WhiteNatasha', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('SilvaEugene', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('HoffmanLucille', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('GrayDarryl', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('FisherTamara', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('RiveraKara', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('WaltersGlenda', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('JamesMeghan', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('RileyBobby', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('BartonShannon', 'test', 2);
INSERT INTO user_info (username, password, id_role) VALUES('HansonDonald', 'test', 2);

INSERT INTO user_info (username, password, id_role) VALUES('HernandezNorman', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('EvansRoger', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('NewtonDiane', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('McdonaldKatrina', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('BanksRegina', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('WaltersBetsy', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('ParksCrystal', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('RodriquezYolanda', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('KnightLuther', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('AndersonFred', 'test', 1);

INSERT INTO user_info (username, password, id_role) VALUES('TylerJoanna', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('WongWalter', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('ColeWillard', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('SchneiderLola', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('FarmerLucas', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('AlexanderShari', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('RogersMandy', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('BowersAnthony', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('ShawGertrude', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('DixonHector', 'test', 1);

INSERT INTO user_info (username, password, id_role) VALUES('MannStephanie', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('MccoyBrenda', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('MclaughlinElizabeth', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('LopezCaleb', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('SotoVicky', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('KimGeraldine', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('MillsSteven', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('BrewerAnnie', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('PayneAnn', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('HodgesMarvin', 'test', 1);

INSERT INTO user_info (username, password, id_role) VALUES('CarrOrville', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('MossWilson', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('MoodyAudrey', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('WebbTom', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('LongAntonio', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('HendersonNora', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('MathisMarion', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('BlairCrystal', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('HolmesSonia', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('FergusonNicole', 'test', 1);

INSERT INTO user_info (username, password, id_role) VALUES('ThomasElisa', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('SharpOrlando', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('AlvaradoHolly', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('VasquezRoberta', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('RossBrittany', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('BeckRachel', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('RoweJoe', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('FrankCaroline', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('HuffMelanie', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('HansenRaymond', 'test', 1);

INSERT INTO user_info (username, password, id_role) VALUES('FoxGene', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('EricksonLeo', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('BridgesLorena', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('HarrisonJerry', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('WalkerLindsey', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('TorresKari', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('WalshCarlos', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('MorganEmily', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('MckenzieMaria', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('HillGrady', 'test', 1);

INSERT INTO user_info (username, password, id_role) VALUES('OrtizOllie', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('CurtisLynn', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('BuchananJeffrey', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('MatthewsAlvin', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('BriggsChester', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('WilliamsBillie', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('RogersTomas', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('HicksDarrel', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('ThompsonEd', 'test', 1);
INSERT INTO user_info (username, password, id_role) VALUES('MckenzieMarlon', 'test', 1);

INSERT INTO department (name, id_user_info) VALUES ('SuperDepartment', 1);

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

INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Jeannie', 'Reynolds', 1, 1, 2);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Julian', 'Bowers', 2, 1, 3);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Maria', 'Graham', 3, 1, 4);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Frank', 'Bryan', 4, 1, 5);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Constance', 'Bryant', 5, 1, 6);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Cecilia', 'Fleming', 6, 1, 7);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Lauren', 'Simmons', 7, 1, 8);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Jackie', 'Doyle', 8, 1, 9);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Leonard', 'Miller', 9, 1, 10);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Lynda', 'Gray', 10, 1, 11);

INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Natasha', 'White', 11, 1, 12);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Eugene', 'Silva', 12, 1, 13);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Lucille', 'Hoffman', 13, 1, 14);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Darryl', 'Gray', 14, 1, 15);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Tamara', 'Fisher', 15, 1, 16);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Kara', 'Rivera', 16, 1, 17);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Glenda', 'Walters', 17, 1, 18);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Meghan', 'James', 18, 1, 19);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Bobby', 'Riley', 19, 1, 20);
INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Shannon', 'Barton', 20, 1, 21);

INSERT INTO teachers (name, family_name, id_subject, id_department, id_user_info) VALUES('Donald', 'Hanson', 21, 1, 22);

INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Norman', 'Hernandez', 1, 23);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Roger', 'Evans', 1, 24);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Diane', 'Newton', 1, 25);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Katrina', 'Mcdonald', 1, 26);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Regina', 'Banks', 1, 27);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Betsy', 'Walters', 1, 28);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Crystal', 'Parks', 1, 29);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Yolanda', 'Rodriquez', 1, 30);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Luther', 'Knight', 1, 31);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Fred', 'Anderson', 1, 32);

INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Joanna', 'Tyler', 2, 33);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Walter', 'Wong', 2, 34);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Willard', 'Cole', 2, 35);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Lola', 'Schneider', 2, 36);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Lucas', 'Farmer', 2, 37);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Shari', 'Alexander', 2, 38);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Mandy', 'Rogers', 2, 39);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Anthony', 'Bowers', 2, 40);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Gertrude', 'Shaw', 2, 41);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Hector', 'Dixon', 2, 42);

INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Stephanie', 'Mann', 3, 43);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Brenda', 'Mccoy', 3, 44);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Elizabeth', 'Mclaughlin', 3, 45);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Caleb', 'Lopez', 3, 46);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Vicky', 'Soto', 3, 47);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Geraldine', 'Kim', 3, 48);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Steven', 'Mills', 3, 49);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Annie', 'Brewer', 3, 50);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Ann', 'Payne', 3, 51);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Marvin', 'Hodges', 3, 52);

INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Orville', 'Carr', 4, 53);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Wilson', 'Moss', 4, 54);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Audrey', 'Moody', 4, 55);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Tom', 'Webb', 4, 56);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Antonio', 'Long', 4, 57);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Nora', 'Henderson', 4, 58);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Marion', 'Mathis', 4, 59);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Crystal', 'Blair', 4, 60);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Sonia', 'Holmes', 4, 61);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Nicole', 'Ferguson', 4, 62);

INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Elisa', 'Thomas', 5, 63);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Orlando', 'Sharp', 5, 64);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Holly', 'Alvarado', 5, 65);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Roberta', 'Vasquez', 5, 66);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Brittany', 'Ross', 5, 67);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Rachel', 'Beck', 5, 68);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Joe', 'Rowe', 5, 69);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Caroline', 'Frank', 5, 70);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Melanie', 'Huff', 5, 71);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Raymond', 'Hansen', 5, 72);

INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Gene', 'Fox', 6, 73);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Leo', 'Erickson', 6, 74);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Lorena', 'Bridges', 6, 75);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Jerry', 'Harrison', 6, 76);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Lindsey', 'Walker', 6, 77);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Kari', 'Torres', 6, 78);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Carlos', 'Walsh', 6, 79);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Emily', 'Morgan', 6, 80);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Maria', 'Mckenzie', 6, 81);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Grady', 'Hill', 6, 82);

INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Ollie', 'Ortiz', 7, 83);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Lynn', 'Curtis', 7, 84);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Jeffrey', 'Buchanan', 7, 85);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Alvin', 'Matthews', 7, 86);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Chester', 'Briggs', 7, 87);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Billie', 'Williams', 7, 88);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Tomas', 'Rogers', 7, 89);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Darrel', 'Hicks', 7, 90);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Ed', 'Thompson', 7, 91);
INSERT INTO students (name, family_name, id_group, id_user_info) VALUES('Marlon', 'Mckenzie', 7, 92);

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