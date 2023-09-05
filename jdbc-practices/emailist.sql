-- emaillist

-- insert
insert into emaillist values(null, '홍', '예지', 'aa@gmail.com');

-- findAll
select no, first_name, last_name, email from emaillist order by no desc;

-- deleteByEmail
delete from emaillist where email='aa@gmail.com';

-- findAll
select no, first_name, last_name, email from emaillist order by no;
