create table phone(
    id serial primary key,
    number int
);

create table people(
    id serial primary key,
    name varchar(255),
    phone_id int references phone(id) unique
);