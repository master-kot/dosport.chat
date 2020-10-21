INSERT INTO users
(id, user_name, password, enabled, first_name)
VALUES
(1, 'admin', '$2a$10$ArsakpPHT5jPbPEAeVc/lup1V4tJS9hqaa1PfRNIUy459JkPjK5xS', true, 'Иван'),
(2, 'user', '$2a$10$7v8.w1xVYIu6TKY3a58CX.xcmSXPpW6mVLTqB11kAn10jezppGdE2', true, 'Катя');

INSERT INTO authorities
(authority)
VALUES
('ROLE_ADMIN'),
('ROLE_USER');

INSERT INTO users_authorities
(user_id, authority_id)
VALUES
(1, 1),
(2, 2);
