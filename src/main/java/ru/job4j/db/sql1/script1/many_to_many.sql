create table people(
    id serial primary key,
    name varchar(255)
);

create table games(
    id serial primary key,
    name varchar(255)
);

create table people_games(
    id serial primary key,
    people_id int references people(id),
    games_id int references games(id)
);

insert into people(name) values ('Ivan');
insert into people(name) values ('Kirill');
insert into people(name) values ('Roman');

insert into games(name) values ('Mario');
insert into games(name) values ('Skyrim');
insert into games(name) values ('Portal');

insert into people_games(people_id, games_id) values (1, 1);
insert into people_games(people_id, games_id) values (1, 3);
insert into people_games(people_id, games_id) values (2, 1);
insert into people_games(people_id, games_id) values (2, 2);
insert into people_games(people_id, games_id) values (2, 3);
insert into people_games(people_id, games_id) values (3, 2);