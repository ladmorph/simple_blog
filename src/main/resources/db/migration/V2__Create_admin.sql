INSERT INTO authors(id, email, password, username) VALUES(1, 'ladmorph@gmail.com', '1234', 'admin');

INSERT INTO user_role(author_id, roles) VALUES (1, 'USER'), (1, 'ADMIN');