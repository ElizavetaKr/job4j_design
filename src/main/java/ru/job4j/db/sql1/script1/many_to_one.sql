create table university(
	id serial primary key,
	name varchar(255)
	);

create table students(
	id serial primary key,
	name varchar(255),
	university_id int references university(id)
	);

insert into university(name) values ('MSU');
insert into students(name, university_id) values ('Ivan', 1);

select * from students;
select * from university where id in (select university_id from students);