
create table pet(
	name varchar(255) primary key,
	owner varchar(255),
    animal varchar(255),
    birth varchar(255),
    go varchar(255)
);

-- table 삭제: DDL
show tables;
describe pet;
desc pet;

-- table 삭제: DDL
drop table pet;
show tables;

-- insert: DML(C)
insert into pet values('성탄이', '홍예지', 'dog', 'm', '2019-12-25', null);

-- select: DML(R)
select * from pet;

-- update: DML(U)
update pet set name='성타니' where name='성탄이';

-- delete: DML(D)
delete from pet where name='성타니';

-- load data
load data local infile '/Users/yeji/desktop/pet.txt' into table pet;