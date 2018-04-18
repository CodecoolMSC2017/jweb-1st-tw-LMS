/*
    Database initialization script that runs on every web-application redeployment.
*/
DROP TABLE IF EXISTS assignments;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name TEXT unique not null,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
	permission BOOLEAN,
	CONSTRAINT name_not_empty CHECK (name <> ''),
	CONSTRAINT email_not_empty CHECK (email <> ''),
	CONSTRAINT password_not_empty CHECK (password <> '')
);

CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
	description TEXT NOT NULL,
	is_active BOOLEAN,
	CONSTRAINT name_not_empty CHECK (name <> ''),
	CONSTRAINT description_not_empty CHECK(description <> '')

);


CREATE TABLE assignments (
    user_id INTEGER,
    course_id INTEGER,
    submission TEXT NOT NULL,
    maxpoints INTEGER NOT NULL,
    pointsgot INTEGER NOT NULL,
    PRIMARY KEY (user_id, course_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

INSERT INTO users (name, email, password, permission) VALUES
	('admin', 'user1@user1', 'admin','true'), -- 1
	('student1', 'user2@user2', 'student', 'false'), -- 2
	('student2', 'user2@user3', 'student', 'false'); -- 3

INSERT INTO courses (name, description, is_active) VALUES
	('Welcome Lesson', 'This is your first lesson','true'),   -- 1
	('LGet started','This is your second lesson','false' ),  -- 2
	('Welcome assignment','This is your first assignment','true' ),  -- 3
	('Let us dive into','This is your second assignment','false' );  -- 4



INSERT INTO assignments (user_id, course_id, submission, maxpoints, pointsgot) VALUES

    (2, 3,'Hello there children!',5,0), -- user_id 2 course_id 3
    (3, 3,'pleasure to be here',5,0); -- user_id 3 course_id 3
