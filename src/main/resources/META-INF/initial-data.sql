DROP SEQUENCE IF EXISTS fox_seq;
DROP TABLE IF EXISTS Fox;
CREATE TABLE Fox (id BIGINT NOT NULL PRIMARY KEY, name VARCHAR(255) NOT NULL, species VARCHAR(255) NOT NULL, gender VARCHAR(10) NOT NULL, image CLOB);
INSERT INTO Fox (id, name, species, gender, image) VALUES (1, 'Reddy', 'Red Fox', 'MALE', NULL);
INSERT INTO Fox (id, name, species, gender, image) VALUES (2, 'Sylva', 'Silver Fox', 'FEMALE', NULL);
INSERT INTO Fox (id, name, species, gender, image) VALUES (3, 'Artie', 'Arctic Fox', 'MALE', NULL);
CREATE SEQUENCE fox_seq START WITH 4 INCREMENT BY 1;
