create table students
(
    id   serial primary key,
    name varchar(50)
);

insert into students (name) values ('Иван Иванов'), ('Петр Петров'), ('Сергей Сергеев');

create table authors
(
    id   serial primary key,
    name varchar(50)
);

insert into authors (name) values ('Александр Пушкин'), ('Николай Гоголь');

create table books
(
    id serial primary key,
    name varchar(200),
    author_id integer references authors (id)
);

insert into books (name, author_id) values ('Евгений Онегин', 1), ('Капитанская дочка', 1), ('Дубровский', 1),
('Мертвые души', 2), ('Вий', 2);

create table orders
(
    id serial primary key,
    active boolean default true,
    book_id integer references books (id),
    student_id integer references students (id)
);

insert into orders (book_id, student_id) values (1, 1), (3, 1), (5, 2), (4, 1), (2, 2);
insert into orders (student_id) values (3);

create view show_students
as
select s.name, count(b.name) as "Количество книг"
from students s
         full join orders o on s.id = o.student_id
         full join books b on o.book_id = b.id
         full join authors a on b.author_id = a.id
WHERE b.name != 'Вий' or b.name is null
group by s.name
order by "Количество книг" desc;

select * from show_students;
