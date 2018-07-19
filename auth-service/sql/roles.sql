CREATE SEQUENCE roles_id_seq START 100000;

CREATE TABLE roles
(
  id        INTEGER PRIMARY KEY DEFAULT nextval('roles_id_seq'),
  name      VARCHAR(35) NOT NULL
);

CREATE UNIQUE INDEX roles_unique_name_indx ON roles (name);

INSERT INTO roles (name) VALUES
  ('ROLE_USER'),
  ('ROLE_ADMIN');