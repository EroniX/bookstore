INSERT INTO permissions (id, version, value) VALUES (1, 0, 'BOOK_LIST');
INSERT INTO roles (id, version) VALUES (1, 2);

INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 1);

INSERT INTO users (id, version, username, email, password, role_id) VALUES (1, 0, 'admin', 'admin@gmail.com', 'admin', 1);
INSERT INTO users (id, version, username, email, password, role_id) VALUES (2, 0, 'user', 'user@gmail.com', 'user', 1);

INSERT INTO code_dictionary_groups (id, version, value) VALUES (1, 0, 'Writers');
INSERT INTO code_dictionary_groups (id, version, value) VALUES (2, 0, 'Publishers');
INSERT INTO code_dictionary_groups (id, version, value) VALUES (3, 0, 'Categories');

INSERT INTO code_dictionary_items (id, version, code_dictionary_group_id, value) VALUES (1, 0, 1, 'J. R. R. Tolkien');
INSERT INTO code_dictionary_items (id, version, code_dictionary_group_id, value) VALUES (2, 0, 1, 'George R. Martin');
INSERT INTO code_dictionary_items (id, version, code_dictionary_group_id, value) VALUES (3, 0, 1, 'Diana Nemeth');

INSERT INTO code_dictionary_items (id, version, code_dictionary_group_id, value) VALUES (4, 0, 2, 'Lira');
INSERT INTO code_dictionary_items (id, version, code_dictionary_group_id, value) VALUES (5, 0, 2, 'Good Book Publisher');
INSERT INTO code_dictionary_items (id, version, code_dictionary_group_id, value) VALUES (6, 0, 2, 'Alexandra');

INSERT INTO code_dictionary_items (id, version, code_dictionary_group_id, value) VALUES (7, 0, 3, 'Horror');
INSERT INTO code_dictionary_items (id, version, code_dictionary_group_id, value) VALUES (8, 0, 3, 'Sci-fi');
INSERT INTO code_dictionary_items (id, version, code_dictionary_group_id, value) VALUES (9, 0, 3, 'Thriller');

INSERT INTO books (id, version, publisher_id, category_id, title, content, max_pieces, preface)
  VALUES (1, 0, 4, 7, 'Harry Potter 1',
  'Tartalom tartalom tartalom tartalom', 10, 'Bevezetés bevezetés bevezetés bevezetés bevezetés');
INSERT INTO books (id, version, publisher_id, category_id, title, content, max_pieces, preface)
  VALUES (2, 0, 5, 8, 'Harry Potter 2',
  'Tartalom  tartalom tartalom tartalom', 0, 'Bevezetés bevezetés bevezetés bevezetés bevezetés');
INSERT INTO books (id, version, publisher_id, category_id, title, content, max_pieces, preface)
  VALUES (3, 0, 6, 9, 'Harry Potter 3',
  'Tartalom  tartalom tartalom tartalom', 10, 'Bevezetés bevezetés bevezetés bevezetés bevezetés');

INSERT INTO books_writers (book_id, code_dictionary_group_id) VALUES (1, 1);
INSERT INTO books_writers (book_id, code_dictionary_group_id) VALUES (1, 2);
INSERT INTO books_writers (book_id, code_dictionary_group_id) VALUES (2, 3);
INSERT INTO books_writers (book_id, code_dictionary_group_id) VALUES (3, 2);

INSERT INTO users_rents (user_id, rent_id) VALUES (1, 1);