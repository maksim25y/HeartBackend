CREATE table USERS(
                      id bigserial primary key,
                      login varchar,
                      display_name varchar,
                      role varchar,
                      hashed_password varchar
)