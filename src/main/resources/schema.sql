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

DROP TABLE IF EXISTS bar;
CREATE TABLE BAR (
    BAR_ID	bigint(20)	NOT NULL auto_increment primary key,
    CATEGORY_ID	bigint(20)	NOT NULL,
    TITLE	VARCHAR(255)	NULL,
    LOCATION	VARCHAR(255)	NULL,
    RATING	DOUBLE	NULL,
    IMAGE	VARCHAR(600)	NULL,
    TEL	VARCHAR(255)	NULL,
    lat	VARCHAR(255)	NULL,
    LOG	VARCHAR(255)	NULL,
    OPEN_STATUS	VARCHAR(255)	NULL,
    group	bigint	NULL,
    hit	bigint	NULL
);
insert into bar(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group,hit)
values (1,1,"고부시","서울 강남구 논현동 80-22",5.0,"no image","02-518-2078","37.51613021","127.0302467","매일",5,5);

