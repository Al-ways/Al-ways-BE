drop table if exists review;
drop table if exists image;
drop table if exists tag_bar;
drop table if exists tag;
drop table if exists survey;
drop table if exists menu;
drop table if exists menu_category;
drop table if exists user_bar;
drop table if exists bar;
drop table if exists bar_category;
drop table if exists user;

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

CREATE TABLE BAR_CATEGORY (
CATEGORY_ID	bigint(20)	NOT NULL primary key,
NAME	VARCHAR(255)	NULL
);
insert into BAR_CATEGORY(category_id, name) values (1,"일본식주점");
insert into BAR_CATEGORY(category_id, name) values (2,"실내포장마차");

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
    group_seat	bigint	NULL,
    hit	bigint	NULL,
    FOREIGN KEY(category_id) REFERENCES Bar_category(category_id)
);

insert into bar(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (1,1,"gubu","서울 강남구 논현동 80-22",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","02-518-2078","37.51613021","127.0302467","매일",5,5);
insert into bar(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (2,2,"ggom","서울 광진구 화양동 9-50 1층",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","010-5137-1675","37.54269611","127.069116","매일",5,5);

CREATE TABLE MENU_CATEGORY (
    menu_category_id   bigint(20)   NOT NULL auto_increment primary key,
    NAME   VARCHAR(255)   NULL,
    GRADE   VARCHAR(255)   NULL
);

insert into MENU_CATEGORY(menu_category_id, NAME, GRADE) values (1,"주류",1);
insert into MENU_CATEGORY(menu_category_id, NAME, GRADE) values (2,"안주",1);

CREATE TABLE MENU (
                      MENU_ID   bigint(20)   NOT NULL primary key,
                      BAR_ID   bigint(20)   NOT NULL,
                      menu_category_id   bigint(20)   NOT NULL,
                      NAME   VARCHAR(255)   NOT NULL,
                      PRICE   bigint(20)   NULL
);
ALTER TABLE MENU ADD CONSTRAINT FK_BAR_TO_MENU_1 FOREIGN KEY (BAR_ID) REFERENCES BAR (BAR_ID);
ALTER TABLE MENU ADD CONSTRAINT FK_MENU_CATEGORY_TO_MENU_1 FOREIGN KEY (menu_category_id) REFERENCES MENU_CATEGORY(menu_category_id);

insert into menu(menu_id,bar_id,menu_category_id,name,price) values(1,1,1,"하니비라",37000);
INSERT INTO menu (menu_id, bar_id,menu_category_id, name, price) VALUES (2,1,1,"간바레 오또상", 37000);
INSERT INTO menu (menu_id, bar_id,menu_category_id, name, price) VALUES (3,1,2,"데판야끼", 28000);
INSERT INTO menu (menu_id, bar_id,menu_category_id, name, price) VALUES (4,1,2,"나베", 34000);

CREATE TABLE USER_BAR (
`USER_BAR_ID`	bigint(20)	NOT NULL auto_increment primary key,
`BAR_ID`	bigint(20)	NOT NULL,
`USER_ID`	bigint(20)	NOT NULL
);
insert into USER_BAR(USER_BAR_ID, bar_id, member_id) VALUES (1,1,1);

CREATE TABLE `TAG` (
   `TAG_ID`   bigint(20)   NOT NULL auto_increment primary key,
   `NAME`   varchar(255)   NULL
);


CREATE TABLE `TAG_BAR` (
   `TAG_BAR_ID`   bigint(20)   NOT NULL auto_increment primary key,
   `BAR_ID`   bigint(20)   NOT NULL,
   `TAG_ID`   bigint(20)   NOT NULL,
    FOREIGN KEY(bar_id) REFERENCES bar(bar_id),
    FOREIGN KEY(tag_id) REFERENCES tag(tag_id)
);

insert into TAG(tag_id, name) values (1,'조용한');
insert into TAG(tag_id, name) values (2,'시끌벅적한');

insert into TAG_BAR(TAG_BAR_ID,BAR_ID,TAG_ID) values (1,1,1);
insert into TAG_BAR(TAG_BAR_ID,BAR_ID,TAG_ID) values (2,2,2);
