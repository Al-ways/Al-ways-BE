DROP TABLE IF EXISTS user;

create table user
(
    user_id           bigint      not null auto_increment primary key,
    email        varchar(50) not null,
    password     varchar(100) null,
    name varchar(20) null,
    auth_provider varchar(100) null,
    oauth2Id varchar(100) null,
    role varchar(20) null
);

insert into user (user_id, email, password, name, auth_provider, oauth2Id,role)
values (1, "test@naver.com", "test1234", "testId","KAKAO","oauth2IdTest2","ROLE_GUEST");
