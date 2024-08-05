create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('cat1', 10, '2020-09-01');
insert into fauna(name, avg_age, discovery_date)
values ('dog1', 20001, '2000-09-01');
insert into fauna(name, avg_age, discovery_date)
values ('fish1', 3569, '1018-09-01');
insert into fauna(name, avg_age, discovery_date)
values ('bird1', 498, '1917-09-01');
insert into fauna(name, avg_age, discovery_date)
values ('bird2', 54444, '1816-09-01');

insert into fauna(name, avg_age)
values ('cat2', 1);
insert into fauna(name, avg_age)
values ('fish3', 29);
insert into fauna(name, avg_age)
values ('cat3', 3458);

select * from fauna where name like '%fish%';
select * from fauna where avg_age < 21000 and avg_age > 10000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';

