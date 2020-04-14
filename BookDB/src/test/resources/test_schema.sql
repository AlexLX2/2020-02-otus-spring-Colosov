create schema if not exists PUBLIC;

create table if not exists AUTHORS
(
	ID INT auto_increment
		primary key,
	NAME VARCHAR
);

create table if not exists GENRES
(
	ID INT auto_increment
		primary key,
	NAME VARCHAR
);

create table if not exists BOOKS
(
	ID INT auto_increment
		primary key,
	TITLE VARCHAR,
	AUTHOR_ID INT,
	GENRE_ID INT,
	constraint BOOKS_AUTHORS_ID_FK
		foreign key (AUTHOR_ID) references AUTHORS (ID),
	constraint BOOKS_GENRES_ID_FK
		foreign key (GENRE_ID) references GENRES (ID)
);

