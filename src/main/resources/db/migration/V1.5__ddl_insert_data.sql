INSERT INTO users
(id, user_name, password, enabled, photo_link)
VALUES
(1, 'admin', '$2a$10$5nKQ7OXDMJMqZbr6VW/B2.gh7KxZ3RkLQOAgBOrcl/ukGUY7wxSWe', true, 'myfoto.png'),
(2, 'user', '$2a$10$9.M6EUrH4woxaxhUrRgbSuDcKtwlxhzwiWYZdLTaiZQIGsi7S2jgK', true, 'katya.png');

INSERT INTO authorities
(authority)
VALUES
('ROLE_ADMIN'),
('ROLE_USER');

INSERT INTO user_authorities
(user_id, authority_id)
VALUES
(1, 1),
(1, 2),
(2, 2);

insert into event_participants
(event_id, user_id)
values
(1 , 1), (1, 2), (2, 1), (2, 2);