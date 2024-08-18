/*кузова*/
create table car_bodies(
id serial primary key,
name varchar(255)
);

/*двигатели*/
create table car_engines(
id serial primary key,
name varchar(255)
);

/*коробки передач*/
create table car_transmissions(
id serial primary key,
name varchar(255)
);

create table cars(
id serial primary key,
name varchar(255),
body_id int references car_bodies(id),
engine_id int references car_engines(id),
transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('Седан'), ('Хэтчбек'), ('Пикап'), ('Универсал'), ('Кабриолет'), ('Купе');
insert into car_engines(name) values ('Бензиновый'), ('Дизельный'), ('Газовый');
insert into car_transmissions(name) values ('Механическая'),('Автоматическая'),('Роботизированная'),('Вариативная');
insert into cars(name, body_id) values ('Hyundai', 1), ('Lexus', 5), ('BMW', 2);
insert into cars(name, body_id, engine_id) values ('Audi', 2, 1), ('Chery', 1, 1), ('Nissan', 3, 1), ('Mitsubishi', 1, 3);
insert into cars(name, body_id, transmission_id) values ('Subaru', 2, 1), ('Volvo', 5, 1), ('Jeep', 3, 2), ('Kia', 3, 4);
insert into cars(name, body_id, engine_id, transmission_id) values ('Volkswagen', 5, 1, 2),
('Datsun', 5, 3, 4), ('Mazda', 5, 3, 1), ('Infiniti', 5, 1, 1);
insert into cars(name, engine_id) values ('Citroen', 1), ('Opel', 3), ('Bentley', 3);
insert into cars(name, engine_id, transmission_id) values ('Hummer', 3, 1), ('Dodge', 3, 2), ('Haval', 1, 4),
('Suzuki', 3, 4);
insert into cars(name, transmission_id) values ('Ford', 1), ('Jaguar', 2), ('Honda', 4);

/* Вывести список всех машин и все привязанные к ним детали. */
select cars.name, b.name, e.name, t.name
from cars
full outer join car_transmissions t on cars.transmission_id = t.id
full outer join car_engines e on cars.engine_id = e.id
full outer join car_bodies b on cars.body_id = b.id;

/* Вывести кузова, которые не используются НИ в одной машине.*/
select cars.name, car_bodies.name
from car_bodies
full outer join cars on car_bodies.id = cars.body_id
where cars.name is null;

/*Вывести двигатели, которые не используются НИ в одной машине*/
select cars.name, car_engines.name
from car_engines
full outer join cars on car_engines.id = cars.engine_id
where cars.name is null;

/*Вывести коробки передач, которые не используются НИ в одной машине*/

select cars.name, car_transmissions.name
from car_transmissions
full outer join cars on car_transmissions.id = cars.transmission_id
where cars.name is null;






