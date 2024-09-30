CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers
VALUES (1, 'Джон', 'Доу', 25, 'Мюнхен'),
       (2, 'Ганс', 'Грубер', 18, 'Нью-Йорк'),
       (3, 'Сара', 'Смит', 26, 'Москва'),
       (4, 'Иван', 'Иванов', 18, 'Мюнхен'),
       (5, 'Грета', 'Купер', 38, 'Москва');

SELECT * FROM customers
WHERE age = (SELECT MIN(age) FROM customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders
VALUES (1, 2, 1),
       (2, 0, 2),
       (3, 5, 3),
       (4, 7, 4),
       (5, 0, 5);

select * from customers
WHERE id NOT IN (select customer_id
					from orders
					WHERE amount > 0);