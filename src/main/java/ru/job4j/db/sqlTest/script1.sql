CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name) values (1, 'company1'), (2, 'company2'), (3, 'company3'), (4, 'company4'),
(5, 'company5');
insert into person (id, name, company_id) values(1, 'name1', 2), (2, 'name2', 2), (3, 'name3', 2), (4, 'name4', 1),
(5, 'name5', 4), (6, 'name6', 4), (7, 'name7', 4), (8, 'name8', 1), (9, 'name9', 1), (10, 'name10', 5);

SELECT  p.name, c.name
FROM  person p
JOIN company c ON p.company_id = c.id
WHERE c.id != 5;

 SELECT c.name, COUNT(p.company_id)
 FROM  company c FULL OUTER JOIN person p ON p.company_id = c.id
 GROUP BY c.name
HAVING COUNT(p.company_id) = ( SELECT MAX(count)
 								FROM  (SELECT count(company_id)
								        FROM person
								        GROUP BY company_id) company);