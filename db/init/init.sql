create schema if not exists user_info;

create table if not exists user_info.user (
    id          bigserial primary key,
    username    text not null unique,
    password    text not null,
    email       text,
    address     text,
    private_key text
);

create table if not exists user_info.role
(
    id          bigserial primary key,
    name        text not null unique,
    description text not null
);

create table if not exists user_info.user_role
(
    id      bigserial primary key,
    user_id bigint not null,
    role_id bigint not null,
    foreign key (user_id) references user_info.user (id),
    foreign key (role_id) references user_info.role
);

create schema if not exists admin_requests;

create table if not exists admin_requests.issuer_request
(
    id                    bigserial primary key,
    user_who_sent_id      bigint not null,
    date_created          timestamp with time zone not null,
    status                text not null,
    admin_who_answered_id bigint,
    date_answered         timestamp with time zone,
    foreign key (user_who_sent_id) references user_info.user (id),
    foreign key (admin_who_answered_id) references user_info.user (id)
);

insert into user_info.role (name, description)
values ('ROLE_ADMIN',
        'Администратор. Позволяет управлять аккаунтами пользователей и ЦФА, ' ||
        'дает доступ к admin панели.'),
       ('ROLE_ISSUER',
        'Эмитент. Позволяет выпускать свои ЦФА.'),
       ('ROLE_TRADER',
        'Торговец. Позволяет покупать\продавать ЦФА.')