create table phone(
    id serial primary key,
    number int
);

create table people(
    id serial primary key,
    name varchar(255),
    phone_id int references phone(id) unique
);

insert into phone(number) values (12345);
insert into phone(number) values (67890);
insert into phone(number) values (14789);
insert into phone(number) values (25639);

insert into people(name, phone_id) values ('Anna',1);
insert into people(name, phone_id) values ('Lena',2);
insert into people(name, phone_id) values ('Ivan',4);
insert into people(name, phone_id) values ('Anton',3);
insert into people(name) values ('Ira');
insert into people(name) values ('Masha');

select ph.number, p.name, p.phone_id
from people as p join phone as ph on p.phone_id = ph.id;

select ph.number as "Номер телефона", p.name, p.phone_id
from people as p join phone as ph on p.phone_id = ph.id;

select ph.number Номер, p.name Имя, p.phone_id
from people as p join phone as ph on p.phone_id = ph.id;