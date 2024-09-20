create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

begin transaction;

savepoint first_savepoint;

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 17, 45);

savepoint second_savepoint;

update products set price = 75 where name = 'product_1';

savepoint third_savepoint;

delete from products where price = 115;

select * from products;

release savepoint second_savepoint;
select * from products;

rollback to savepoint second_savepoint;

rollback to savepoint third_savepoint;

rollback to savepoint first_savepoint;
select * from products;

rollback to savepoint third_savepoint;

rollback;

select * from products;
begin;

savepoint first_savepoint;

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 17, 45);

savepoint second_savepoint;

update products set price = 75 where name = 'product_1';

savepoint third_savepoint;

delete from products where price = 115;

select * from products;

rollback to savepoint second_savepoint;
select * from products;

rollback to savepoint third_savepoint;

commit;
select * from products;