CREATE TABLE users_roles
(
  user_id   INTEGER NOT NULL,
  role_id   INTEGER NOT NULL,
  
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);