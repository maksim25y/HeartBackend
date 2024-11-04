CREATE table USERS
(
    id              bigserial primary key,
    email           varchar,
    firstname       varchar,
    lastname        varchar,
    patronymic      varchar,
    role            varchar,
    hashed_password varchar
);

create table images
(
    id            bigint generated always as identity,
    image         text                     not null,
    title         text                     not null,
    creation_date timestamp with time zone not null,
    description   text                     not null,
    primary key (id)
);

CREATE TABLE refresh_token
(
    id    bigint generated always as identity,
    email VARCHAR(255) NOT NULL,
    token VARCHAR(255) NOT NULL
);