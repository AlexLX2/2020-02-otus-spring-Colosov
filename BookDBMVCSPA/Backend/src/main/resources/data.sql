truncate table books cascade;
truncate table authors cascade;
truncate table genres cascade;

alter sequence authors_id_seq restart ;
alter sequence books_id_seq restart ;
alter sequence genres_id_seq restart ;



INSERT INTO PUBLIC.AUTHORS ( NAME) VALUES ( 'Mark Twain');
INSERT INTO PUBLIC.AUTHORS ( NAME) VALUES ( 'Andjey Sapkovski');
INSERT INTO PUBLIC.AUTHORS ( NAME) VALUES ( 'George Martin');
INSERT INTO PUBLIC.AUTHORS ( NAME) VALUES ( 'James Orwell');

INSERT INTO PUBLIC.GENRES ( NAME) VALUES ( 'Drama');
INSERT INTO PUBLIC.GENRES ( NAME) VALUES ( 'Fantasy');

INSERT INTO PUBLIC.BOOKS ( TITLE, AUTHOR_ID, GENRE_ID) VALUES ( 'Tom Soyer', 1, 1);
INSERT INTO PUBLIC.BOOKS ( TITLE, AUTHOR_ID, GENRE_ID) VALUES ( 'Witcher', 2, 2);
INSERT INTO PUBLIC.BOOKS ( TITLE, AUTHOR_ID, GENRE_ID) VALUES ( 'Game of Thrones', 3, 2);
INSERT INTO PUBLIC.BOOKS ( TITLE, AUTHOR_ID, GENRE_ID) VALUES ( '1984', 4, 1);

insert into COMMENTS (BOOK_ID,TEXT  ) VALUES (1,'Comment1');
insert into COMMENTS (BOOK_ID,TEXT  ) VALUES (1,'Comment2');
insert into COMMENTS (BOOK_ID,TEXT  ) VALUES (2,'Comment3');
insert into COMMENTS (BOOK_ID,TEXT  ) VALUES (1,'Comment4');
insert into COMMENTS (BOOK_ID,TEXT  ) VALUES (2,'Comment5');
insert into COMMENTS (BOOK_ID,TEXT  ) VALUES (3,'Comment6');
insert into COMMENTS (BOOK_ID,TEXT  ) VALUES (4,'Comment7');
insert into COMMENTS (BOOK_ID,TEXT  ) VALUES (1,'Comment8');
