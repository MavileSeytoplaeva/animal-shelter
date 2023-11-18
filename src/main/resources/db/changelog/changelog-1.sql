--liquibase formatted sql

--changeset roman:2023-11-12-std-ind
create table volunteers
(
    telegram_id bigint not null primary key,
    first_name  varchar(255),
    last_name   varchar(255)
);
create table cat
(
    cat_id         bigserial not null primary key,
    name       varchar(255),
    age        integer
        constraint check_age check (age > 0),
    is_healthy boolean,
    vaccinated boolean


);

create table cat_shelter
(
    id            bigserial not null primary key,
    about_me      varchar(255),
    location      varchar(255),
    name          varchar(255),
    safety_advice varchar(255),
    security      varchar(255),
    timetable     varchar(255),
    cat_id bigint
            constraint cat_cat_cat_id_fk
                references cat
);

create table dog
(
    dog_id         bigserial not null primary key,
    name       varchar(255),
    age        integer
        constraint check_age
            check (age > 0),
    is_healthy boolean,
    vaccinated boolean
);

create table dog_shelter
(
    id            bigserial not null primary key,
    about_me      varchar(255),
    location      varchar(255),
    name          varchar(255),
    safety_advice varchar(255),
    security      varchar(255),
    timetable     varchar(255),
    dog_id bigint
                constraint dog_dog_dog_id_fk
                    references dog
);


create table users
(
    telegram_id bigint not null primary key,
    first_name  varchar(255)

);

