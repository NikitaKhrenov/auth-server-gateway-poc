CREATE SEQUENCE users_id_seq START 100000;

CREATE TABLE users
(
  id        INTEGER PRIMARY KEY DEFAULT nextval('users_id_seq'),
  email     VARCHAR(35) NOT NULL,
  password  VARCHAR(255) NOT NULL,
  origin    VARCHAR(255) NOT NULL,
  enabled   BOOL DEFAULT TRUE
);

CREATE UNIQUE INDEX users_unique_email_indx ON users (email);