create table products
(
    id       serial primary key,
    name     varchar(50),
    count    integer default 0,
    price    integer
);

insert into products (name, count, price)
VALUES ('product_1', 5, 50);
insert into products (name, count, price)
VALUES ('product_2', 10, 100);
insert into products (name, count, price)
VALUES ('product_3', 15, 150);

begin transaction;

insert into products (name, count, price) VALUES ('product_4', 7, 70);
update products set price = 75 where count < 10;
delete from products where name = 'product_3';

select * from products;
/*Изменения в0 второй транзакции*/
select * from products;

commit;


delete from products;
insert into products (name, count, price)
VALUES ('product_1', 5, 50);
insert into products (name, count, price)
VALUES ('product_2', 10, 100);
insert into products (name, count, price)
VALUES ('product_3', 15, 150);

/*repeatable read*/

begin transaction isolation level repeatable read;

insert into products (name, count, price) VALUES ('product_5', 8, 80);
update products set price = 0 where count = 10;
delete from products where name = 'product_4';

rollback;

/*serializable*/
begin transaction isolation level serializable;
delete from products where name = 'product_1';
commit;
