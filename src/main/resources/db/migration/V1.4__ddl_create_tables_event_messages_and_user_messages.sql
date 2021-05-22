
DROP TABLE IF EXISTS event_messages;

create table event_messages
(
	id              bigserial NOT NULL UNIQUE,
	sender_id       bigint,
	event_id        bigint NOT NULL,
	content         varchar(500),
	creation_date   timestamp with time zone not null,
	PRIMARY KEY (id),
	FOREIGN KEY (sender_id) REFERENCES users (id) ON DELETE SET NULL
);

DROP TABLE IF EXISTS user_messages;

create table user_messages
(
	id              bigserial NOT NULL UNIQUE,
	sender_id       bigint,
	recipient_id    bigint,
	content         varchar(500),
	creation_date   timestamp with time zone not null,
	status          smallint,
	PRIMARY KEY (id),
	FOREIGN KEY (sender_id) REFERENCES users (id) ON DELETE SET NULL,
	FOREIGN KEY (recipient_id) REFERENCES users (id) ON DELETE SET NULL
);

DROP TABLE IF EXISTS event_participants;

create table event_participants
(
	event_id        bigint not null,
	user_id         bigint not null,
	PRIMARY KEY (event_id, user_id),
	FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);