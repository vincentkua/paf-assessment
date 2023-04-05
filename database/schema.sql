-- create database railway;
-- use railway;

create table user(
user_id varchar(8) not null unique,
username varchar(100) ,
name varchar(100),
constraint users_pk primary key (user_id),
constraint check_username check (username regexp '^[a-zA-Z0-9]+$')
);

create table task(
task_id integer not null auto_increment,
description varchar(255),
priority Integer,
due_date date,
user_id varchar(8),
constraint task_pk primary key (task_id),
constraint task_fk foreign key (user_id) references user(user_id)
);







