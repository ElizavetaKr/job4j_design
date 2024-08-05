insert into roles(name) values('role_1');
insert into users(name, roles_id) values('user_1', 1);
insert into rules(name) values('rule_1');
insert into roles_rules(roles_id, rules_id) values(1, 1);
insert into states(name) values('state_1');
insert into categories(name) values('category_1');
insert into items(name, users_id, states_id, categories_id) values('item_1', 1, 1, 1);
insert into comments(name, items_id) values('comment_1', 1);
insert into attachs(name, items_id) values('attach_1', 1);