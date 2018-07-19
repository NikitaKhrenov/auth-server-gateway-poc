CREATE SEQUENCE privileges_id_seq START 100000;

CREATE TABLE privileges
(
  id        INTEGER PRIMARY KEY DEFAULT nextval('privileges_id_seq'),
  name      VARCHAR(35) NOT NULL
);

CREATE UNIQUE INDEX privileges_unique_name_indx ON privileges (name);

INSERT INTO privileges (name) VALUES
  ('READ_PRIVILEGE'),
  ('WRITE_PRIVILEGE');