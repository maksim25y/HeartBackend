CREATE table USERS(
                      id bigserial primary key,
                      login varchar,
                      display_name varchar,
                      role varchar,
                      hashed_password varchar
);

create table images
(
    id  bigint generated always as identity,
    image text not null,
    title  text not null,
    creation_date   timestamp with time zone not null,
    description  text  not null,
    primary key (id)
)