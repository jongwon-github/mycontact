insert into person(`name`, `age`, `blood_type`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`) values ('martin', 10, 'A', 1991, 8, 15);
insert into person(`name`, `age`, `blood_type`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`) values ('david', 9, 'B', 1992, 7, 21);
insert into person(`name`, `age`, `blood_type`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`) values ('dennis', 8, 'O', 1993, 10, 15);
insert into person(`name`, `age`, `blood_type`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`) values ('sophia', 7, 'AB', 1994, 8, 31);
insert into person(`name`, `age`, `blood_type`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`) values ('benny', 6, 'A', 1995, 12, 23);

insert into block(`name`) values ('dennis');
insert into block(`name`) values ('sophia');

update person set block_id = 1 where id = 3;
update person set block_id = 2 where id = 4;