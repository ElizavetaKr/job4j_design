create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values ('phone', 10000), ('tv', 50000), ('oven', 2000), ('printer', 4500);

insert into people(name) values ('Anna'), ('Lena'), ('Ivan'), ('Anton'), ('Ira'), ('Masha');

insert into devices_people(device_id, people_id) values (1, 2), (1, 4);
insert into devices_people(device_id, people_id) values (2, 3), (2, 5), (2, 6);
insert into devices_people(device_id, people_id)  values (3, 1);
insert into devices_people(device_id, people_id) values (4, 1);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people
join devices d on devices_people.device_id = d.id
join people p on devices_people.people_id = p.id
group by p.name;

select p.name, avg(d.price)
from devices_people
join devices d on devices_people.device_id = d.id
join people p on devices_people.people_id = p.id
group by p.name
having avg(d.price) > 5000;

select name, price
from product
where price = (select max(price) from product);

