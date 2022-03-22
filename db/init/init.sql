create schema if not exists user_info;

create table if not exists user_info.user (
    id bigserial primary key,
    username text not null unique,
    password text not null
);

create table if not exists user_info.role (
    id bigserial primary key,
    name text not null unique,
    description text not null
);

create table user_info.user_role (
    id bigserial primary key,
    user_id bigint not null,
    role_id bigint not null,
    foreign key (user_id) references user_info.user(id),
    foreign key (role_id) references user_info.role
);