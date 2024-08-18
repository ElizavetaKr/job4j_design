/*1. Создать таблицы и заполнить их начальными данными*/
create table departments(
id serial primary key,
name varchar(255)
);

 create table employees(
id serial primary key,
name varchar(255),
departments_id int references departments(id)
);

insert into departments(name) values ('programmer'), ('project manager'), ('director'), ('accountant');
insert into employees(name, departments_id) values('Boris', 1), ('Ivan', 3), ('Kiril', 4);
insert into employees(name) values ('Marina'), ('Petya');

/*3. Используя left join найти департаменты, у которых нет работников*/
select * from departments
left join employees on departments.id = employees.departments_id
where employees.departments_id is null;

/*4. Используя left и right join написать запросы, которые давали бы одинаковый результат*/
select * from departments
left join employees on departments.id = employees.departments_id
where employees.departments_id is not null;

select * from departments
right join employees on departments.id = employees.departments_id
where employees.departments_id is not null;

/*2.Выполнить запросы с full, cross соединениями*/
select * from departments
full join employees on departments.id = employees.departments_id;

select * from departments
cross join employees;

/*5.Используя cross join составить все возможные разнополые пары. Исключите дублирование*/
create table teens(
id serial primary key,
name varchar(255),
gender bool
);

insert into teens(name, gender) values ('Marina', '0'), ('Inna', '0'), ('Anna', '0'), ('Rita', '0'), ('Karina', '0'),
 ('Boris', '1'), ('Ivan', '1'), ('Kiril', '1'), ('Petya', '1');

select t1.name, t2.name from teens t1 cross join teens t2
	where t1.gender != t2.gender and t2.gender = '0';





