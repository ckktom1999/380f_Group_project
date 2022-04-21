CREATE TABLE users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    full_name VARCHAR(50) NOT NULL,
    phone_number INT NOT NULL,
    address VARCHAR(200) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE user_roles (
    user_role_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_role_id),
    FOREIGN KEY (username) REFERENCES users(username)
);


INSERT INTO users VALUES ('Tom', '{noop}Tompw', 'Chan Kin Kong', 56178406, 'Wan Chai Central Plaza 245');
INSERT INTO user_roles(username, role) VALUES ('Tom', 'ROLE_USER');
INSERT INTO user_roles(username, role) VALUES ('Tom', 'ROLE_ADMIN');

INSERT INTO users VALUES ('john', '{noop}johnpw', 'Wong tai ming', 54710456, 'Central And Western District Bank Of America Tower 1234');
INSERT INTO user_roles(username, role) VALUES ('john', 'ROLE_ADMIN');

INSERT INTO users VALUES ('mary', '{noop}marypw', 'Wong xiao', 84410454, 'North District Unit I3/F Good Harvest Ctr33 Onchuen St Fanling');
INSERT INTO user_roles(username, role) VALUES ('mary', 'ROLE_USER');

INSERT INTO users VALUES ('Tommy', '{noop}Tommypw', 'Tang kwok bang', 86784562, 'Wont Tai Sin Nort house 8 Flat 20 Room');
INSERT INTO user_roles(username, role) VALUES ('Tommy', 'ROLE_USER');

CREATE TABLE lectures (
    lectures_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    title VARCHAR(200) NOT NULL,
    PRIMARY KEY (lectures_id)
);

INSERT INTO lectures (title) VALUES ('Lecture 1: Overview of Web Applications');
INSERT INTO lectures (title) VALUES ('Lecture 2: Servlet');

CREATE TABLE lecture_notes_attachment (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    filename VARCHAR(255) DEFAULT NULL,
    content_type VARCHAR(255) DEFAULT NULL,
    content BLOB DEFAULT NULL,
    lectures_id INTEGER DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (lectures_id) REFERENCES lectures(lectures_id)
);

CREATE TABLE tutorial_notes_attachment (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    filename VARCHAR(255) DEFAULT NULL,
    content_type VARCHAR(255) DEFAULT NULL,
    content BLOB DEFAULT NULL,
    lectures_id INTEGER DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (lectures_id) REFERENCES lectures(lectures_id)
);

CREATE TABLE lecture_comments (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name VARCHAR(255) NOT NULL,
    body VARCHAR(255) NOT NULL,
    date_time TIMESTAMP NOT NULL,
    lectures_id INTEGER DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (lectures_id) REFERENCES lectures(lectures_id)
);

INSERT INTO lecture_comments (name, body, date_time, lectures_id) VALUES ('Tom', 'Welcome to Course 380F! Please read the lecture note before the lecture.', '2022-04-11 15:52:25', 1);
INSERT INTO lecture_comments (name, body, date_time, lectures_id) VALUES ('Tom', 'This week Lecture will show how to use Servlet!', '2022-04-18 13:24:20', 2);
INSERT INTO lecture_comments (name, body, date_time, lectures_id) VALUES ('mary', 'Sir, you forgot to upload the lab answer!', '2022-04-18 14:02:30', 2);
INSERT INTO lecture_comments (name, body, date_time, lectures_id) VALUES ('Tom', 'Thank you for your reminder! I will upload the answer soon.', '2022-04-18 14:30:10', 2);

CREATE TABLE pollQuestion (
    pollId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    question VARCHAR(255) NOT NULL,
    optionA VARCHAR(255),
    optionB VARCHAR(255),
    optionC VARCHAR(255),
    optionD VARCHAR(255),
    username VARCHAR(255) NOT NULL,
    pqDate TIMESTAMP,
    PRIMARY KEY (pollId)
);

CREATE TABLE pollAnswer (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(255) NOT NULL,
    answer VARCHAR(1) NOT NULL,
    pollId INTEGER NOT NULL,
    paDate TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (pollId) REFERENCES pollQuestion(pollId)
);


INSERT INTO pollQuestion(question,optionA,optionB,optionC,optionD,username,pqDate) VALUES ('Which date do you prefer for exam?', '1/11','2/11','3/11','4/11','Tom', '2022-01-19 03:14:07');
INSERT INTO pollQuestion(question,optionA,optionB,optionC,optionD,username,pqDate) VALUES ('Which date do you prefer for exam?', '1/12','2/12','3/12','4/12','Mary', '2022-01-20 07:24:08');

