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

Update users Set username ='Test123' Where username = 'Test3'

INSERT INTO users VALUES ('Tom', '{noop}Tompw', 'Chan Kin Kong', 56178406, 'Wan Chai Central Plaza 245');
INSERT INTO user_roles(username, role) VALUES ('Tom', 'ROLE_USER');
INSERT INTO user_roles(username, role) VALUES ('Tom', 'ROLE_ADMIN');

INSERT INTO users VALUES ('john', '{noop}johnpw', 'Wong tai ming', 54710456, 'Central And Western District Bank Of America Tower 1234');
INSERT INTO user_roles(username, role) VALUES ('john', 'ROLE_ADMIN');

INSERT INTO users VALUES ('mary', '{noop}marypw', 'Wong xiao', 84410454, 'North District Unit I3/F Good Harvest Ctr33 Onchuen St Fanling');
INSERT INTO user_roles(username, role) VALUES ('mary', 'ROLE_USER');

CREATE TABLE lectures (
    lectures_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    title VARCHAR(50) NOT NULL,
    PRIMARY KEY (lectures_id)
);

INSERT INTO lectures (title) VALUES ('Lecture 1');


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
    lectures_id INTEGER DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (lectures_id) REFERENCES lectures(lectures_id)
);
