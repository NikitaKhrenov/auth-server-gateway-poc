CREATE TABLE roles_privileges
(
  role_id        INTEGER NOT NULL,
  privilege_id   INTEGER NOT NULL,

  FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
  FOREIGN KEY (privilege_id) REFERENCES privileges (id) ON DELETE CASCADE
);

INSERT INTO roles_privileges (role_id, privilege_id) VALUES
  (100000, 100000),
  (100001, 100000),
  (100001, 100001);