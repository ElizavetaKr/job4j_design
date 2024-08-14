create table type(
id    serial primary key,
name varchar(255)
);

create table product(
id    serial primary key,
name varchar(255),
type_id int references type(id),
expired_date date,
price float
);

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('МОРОЖЕНОЕ'), ('СОУС');

insert into product(name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2023-07-26', 200);
insert into product(name, type_id, expired_date, price) values ('Сыр моцарелла', 1, '2024-10-20', 300);
insert into product(name, type_id, expired_date, price) values ('Козий сыр', 1, '2024-12-28', 800);
insert into product(name, type_id, expired_date, price) values ('Миндальное молоко', 2, '2024-03-02', 250);
insert into product(name, type_id, expired_date, price) values ('Овсяное молоко', 2, '2025-02-25', 340);
insert into product(name, type_id, expired_date, price) values ('Кокосовое молоко', 2, '2024-07-19', 500);
insert into product(name, type_id, expired_date, price) values ('Миндальное молоко', 2, '2025-01-17', 350);
insert into product(name, type_id, expired_date, price) values ('Рисовое молоко', 2, '2024-08-16', 100);
insert into product(name, type_id, expired_date, price) values ('Маложирное молоко', 2, '2023-12-05', 50);
insert into product(name, type_id, expired_date, price) values ('Козье молоко', 2, '2025-07-01', 200);
insert into product(name, type_id, expired_date, price) values ('Отборное молоко', 2, '2024-09-29', 210);
insert into product(name, type_id, expired_date, price) values ('Верблюжье молоко', 2, '2024-07-18', 800);
insert into product(name, type_id, expired_date, price) values ('Топленое молоко', 2, '2025-12-06', 290);
insert into product(name, type_id, expired_date, price) values ('Ягодное мороженое', 3, '2023-11-19', 40);
insert into product(name, type_id, expired_date, price) values ('Карамельное мороженое', 3, '2024-07-19', 70);
insert into product(name, type_id, expired_date, price) values ('Пломбир', 3, '2024-10-30', 50);
insert into product(name, type_id, expired_date, price) values ('Майонез', 4, '2024-11-15', 260);
insert into product(name, type_id, expired_date, price) values ('Кетчуп', 4, '2024-08-24', 80);
insert into product(name, type_id, expired_date, price) values ('Горчица', 4, '2024-09-03', 95);
insert into product(name, type_id, expired_date, price) values ('Песто', 4, '2024-12-01', 800);

select *
from product join type on product.type_id = type.id
where type.name = 'СЫР';

select *
from product
where name like '%мороженое%';

select *
from product
where expired_date < '2024-08-14';

select type.name, count(product.type_id)
from type join product on type.id = product.type_id
group by type.name;

select type.name, product.name
from type join product on type.id = product.type_id
where type.name = 'СЫР' or type.name = 'МОЛОКО';

select type.name
from type join product on type.id = product.type_id
group by type.name
having count(type.name) < 10;

select type.name as "Тип продукта", product.name as "Имя продукта"
from type join product on type.id = product.type_id;