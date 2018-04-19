DROP TABLE IF EXISTS submissions;
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
   is_active BOOLEAN DEFAULT FALSE,
   CONSTRAINT name_not_empty CHECK (name <> ''),
   CONSTRAINT description_not_empty CHECK(description <> '')

);

CREATE TABLE assignments ( 
	id SERIAL PRIMARY KEY,
	course_id INTEGER,
	maxpoints INTEGER NOT NULL,
	FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE submissions (
	user_id INTEGER,
	course_id INTEGER,
	assign_id INTEGER,
	submission TEXT NOT NULL,
	pointsgot INTEGER NOT NuLL,
	PRIMARY KEY (user_id),
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (course_id) REFERENCES courses(id),
	FOREIGN KEY (assign_id) REFERENCES assignments(id)
);

INSERT INTO users (name, email, password, permission) VALUES
    ('admin', 'user1@user1', 'admin','true'), -- 1
    ('student1', 'user2@user2', 'student', 'false'), -- 2
    ('student2', 'user2@user3', 'student', 'false'); -- 3

INSERT INTO courses (name, description, is_active) VALUES
    ('Welcome Lesson', 'This is your first lesson','true'),   -- 1
    ('LGet started','This is your second lesson','false' ),  -- 2
	('Welcome assignment','This is your first assignment','true' ), --3
	('Let us dive into','This is your second assignment','false' );  -- 4

INSERT INTO assignments (course_id, maxpoints) VALUES
    (3,5),  -- id: 1, course_id: 3
    (4,10);  -- id: 2, course_id: 4



INSERT INTO submissions (user_id, course_id,assign_id,submission, pointsgot) VALUES

   (2, 3,1,'Hello there children!',0), -- user_id 2 course_id 3
   (3, 3,1,'pleasure to be here',0); -- user_id 3 course_id 3