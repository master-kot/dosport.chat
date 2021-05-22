DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id            BIGINT NOT NULL UNIQUE,
    user_name     VARCHAR(128) NOT NULL UNIQUE,
    password      VARCHAR(128),
    enabled       BOOLEAN NOT NULL,
    photo_link      VARCHAR(255),
    PRIMARY KEY (id)
);