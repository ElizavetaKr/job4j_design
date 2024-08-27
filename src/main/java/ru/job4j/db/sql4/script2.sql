create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

/*триггерная функции для расчета скидки
(если количество товара меньше или равно 5 – изменять поле price и уменьшать его на 20%)*/
create
or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5
        AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

/*триггер, который будет срабатывать после каждой вставки новой строки*/
create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

drop trigger discount_trigger on products;

/*триггер, который будет также менять цену на 20%, если позиций меньше 5, теперь уже на statement уровне*/
create
or replace function discount_st()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted)
        and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger discount_st_trigger
    after insert
    on products
    referencing new table as inserted
    for each statement
    execute procedure discount_st();

drop trigger discount_st_trigger on products;

/*срабатывает после вставки данных, для любого товара и насчитывает налог*/

create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert
    on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

/*срабатывает до вставки данных и насчитывает налог на товар - row уровень.*/

create
or replace function tax_row()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';


create trigger tax_row_trigger
    before insert
    on products
    for each row
    execute procedure tax_row();

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

/*триггер на row уровне, который после вставки в таблицу products, будет заносит инф-ю в таблицу history_of_price*/

create
or replace function paste()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date)
        values (new.name, new.price, '27.08.2024');
        return NEW; /*игнорируется*/
    END;
$$
LANGUAGE 'plpgsql';


create trigger paste_trigger
    after insert
    on products
    for each row
    execute procedure paste();

