--liquibase formatted sql

--changeset roman:1
CREATE TABLE users (

    id bigserial primary key,
    chat_id bigint,
    user_Name varchar,
    first_Name varchar,
    last_Name varchar,
    Registered_At timestamp
    )
CREATE TABLE cat (

    id bigserial primary key,
    name varchar,
    breed varchar,
    age bigint,
    info varchar
    )
CREATE TABLE dog (

    id bigserial primary key,
    name varchar,
    breed varchar,
    age bigint,
    info varchar
    )