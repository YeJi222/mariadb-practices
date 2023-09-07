-- delete all data from tables
delete from order_book;
delete from orders;
delete from cart;
delete from book;
delete from category;
delete from member;

-- auto_increment 초기화
alter table book auto_increment=1;
alter table cart auto_increment=1;
alter table category auto_increment=1;
alter table member auto_increment=1;
alter table orders auto_increment=1;