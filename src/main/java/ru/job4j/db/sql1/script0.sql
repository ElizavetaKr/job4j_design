create table animals(
	id serial primary key,
	name varchar(255),
	legs integer,
	fly boolean
);
insert into animals(name, legs, fly) values('cat', 4, false);
insert into animals(name, legs, fly) values('bird', 2, true);
insert into animals(name, legs, fly) values('spider', 8, false);
select * from animals;
update animals set name = 'dog';
select * from animals;
delete from animals;
select * from animals;