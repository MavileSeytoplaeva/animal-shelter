--liquibase formatted sql

--changeset roman:1
CREATE TABLE users (

    telegramId bigserial primary key,
    first_Name varchar

    )
CREATE TABLE cat (

    id bigserial primary key,
    name varchar,
    age bigint,
    isHealthy,
    vaccinated,
    shelter_Id varchar
    )
CREATE TABLE dog (

    id bigserial primary key,
        name varchar,
        age bigint,
        isHealthy Boolean,
        vaccinated Boolean,
        shelter_Id varchar
        )

       CREATE TABLE cat_shelter (

       id bigserial primary key,
       name varchar,
       location varchar,
       timetable bigint,
       aboutMe varchar,
       security varchar,
       safetyAdvice varchar
       )

       CREATE TABLE dog_shelter (

              id bigserial primary key,
              name varchar,
              location varchar,
              timetable bigint,
              aboutMe varchar,
              security varchar,
              safetyAdvice varchar)

              CREATE TABLE dog_shelter (

              id bigserial primary key,
              name varchar,
              location varchar,
              timetable bigint,
              aboutMe varchar,
              security varchar,
              safetyAdvice varchar)

              CREATE TABLE volunteers (

            telegramId bigserial primary key,
             firstName varchar,
             lastName varchar
             )