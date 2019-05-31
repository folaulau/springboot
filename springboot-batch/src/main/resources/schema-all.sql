DROP TABLE IF EXISTS users;

CREATE TABLE users  (
    person_id BIGINT  NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20)
);