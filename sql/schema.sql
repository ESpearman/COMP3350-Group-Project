CREATE TABLE Term
(
	id VARCHAR(100) PRIMARY KEY NOT NULL,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE Building
(
	id VARCHAR(100) PRIMARY KEY NOT NULL,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE Student
(
	id VARCHAR(100) PRIMARY KEY NOT NULL,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	student_number INTEGER NOT NULL,
	science_student BOOLEAN NOT NULL,
	term VARCHAR(100) FOREIGN KEY REFERENCES Term(id) NOT NULL
);

CREATE TABLE Locker
(
	id VARCHAR(100) PRIMARY KEY NOT NULL,
	term VARCHAR(100) FOREIGN KEY REFERENCES Term(id) NOT NULL,
	num INTEGER NOT NULL,
	building VARCHAR(100) FOREIGN KEY REFERENCES Building(id) NOT NULL,
	locker_size VARCHAR(100) NOT NULL
);

CREATE TABLE Rental
(
	id VARCHAR(100) PRIMARY KEY NOT NULL,
	term VARCHAR(100) FOREIGN KEY REFERENCES Term(id) NOT NULL,
	student VARCHAR(100) FOREIGN KEY REFERENCES Student(id) NOT NULL,
	locker VARCHAR(100) FOREIGN KEY REFERENCES Locker(id) NOT NULL,
	price_paid FLOAT NOT NULL,
	signed_agreement BOOLEAN NOT NULL
);

