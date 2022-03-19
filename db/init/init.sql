create schema if not exists user_info;

create table if not exists user_info.user (
    id serial primary key,
    nickname text not null,
    password text not null
);