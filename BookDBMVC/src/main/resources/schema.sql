create schema if not exists public;

comment on schema public is 'default public schema';

alter schema public owner to postgres;

create table if not exists authors
(
    id serial not null
        constraint authors_pk
            primary key,
    name varchar not null
);

alter table authors owner to postgres;

create unique index if not exists authors_id_uindex
    on authors (id);

create table if not exists genres
(
    id serial not null
        constraint genres_pk
            primary key,
    name varchar not null
);

alter table genres owner to postgres;

create unique index if not exists genres_id_uindex
    on genres (id);

create table if not exists books
(
    id serial not null
        constraint books_pk
            primary key,
    title varchar not null,
    author_id integer not null
        constraint books_authors_id_fk
            references authors,
    genre_id integer not null
        constraint books_genres_id_fk
            references genres
);

alter table books owner to postgres;

create unique index if not exists books_id_uindex
    on books (id);

create table if not exists comments
(
    id serial not null
        constraint comments_pk
            primary key,
    text varchar not null,
    book_id integer not null
        constraint comments_books_id_fk
            references books
            on delete cascade
);

alter table comments owner to postgres;

create unique index if not exists comments_id_uindex
    on comments (id);

