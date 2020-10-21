DROP TABLE IF EXISTS users_authorities;

CREATE TABLE users_authorities
(
    user_id      BIGINT,
    authority_id SMALLINT,
    PRIMARY KEY (user_id, authority_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (authority_id) REFERENCES authorities (id)
);
