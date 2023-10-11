DROP TABLE IF EXISTS user;

create table user
(
    user_id           bigint      not null auto_increment primary key,
    email        varchar(50) not null,
    password     varchar(100) null,
    name varchar(20) null,
    auth_provider varchar(100) null,
    oauth2Id varchar(100) null,
    role varchar(20) null,
    profile_image varchar(512) null
);

insert into user (user_id, email, password, name, auth_provider, oauth2Id,role, profile_image)
values (1, "test1@naver.com", "test1", "testId1","KAKAO","oauth2IdTest1","ROLE_GUEST","https://test_profile_image");

--insert into user (user_id, email, password, name, auth_provider, oauth2Id,role, profile_image)
--values (2, "test2@naver.com", "test2", "testId2","GOOGLE","oauth2IdTest2","ROLE_GUEST","https://test_profile_image");
--
--insert into user (user_id, email, password, name, auth_provider, oauth2Id,role, profile_image)
--values (3, "test3@naver.com", "test3", "testId3","NAVER","oauth2IdTest3","ROLE_GUEST","https://test_profile_image");