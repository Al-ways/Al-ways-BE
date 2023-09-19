DROP TABLE IF EXISTS member;

create table member
(
    member_id           bigint      not null auto_increment primary key,
    email        varchar(50) not null,
    password     varchar(20) not null,
    name varchar(20) null

);

insert into member (member_id, email, password, name)
values (1, "test@naver.com", "test1234", "testId");