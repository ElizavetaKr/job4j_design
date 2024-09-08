begin transaction;
select * from products;
delete from products where name = 'product_2';
select * from products;

/*Зафиксировали изменения в первой транзакции*/
select * from products;

rollback;
select * from products;

/*repeatable read*/
begin transaction isolation level repeatable read;

update products set price = 1 where count = 10;
commit;

/*serializable*/
begin transaction isolation level serializable;
delete from products where name = 'product_2';
commit;