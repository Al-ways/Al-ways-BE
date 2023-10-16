drop table if exists REVIEW;
drop table if exists IMAGE;
drop table if exists TAG_BAR;
drop table if exists TAG;
drop table if exists SURVEY;
drop table if exists MENU;
drop table if exists MENU_CATEGORY;
drop table if exists USER_BAR;
drop table if exists BAR;
drop table if exists BAR_CATEGORY;
drop table if exists USER;

create table USER
(
    user_id           bigint      not null auto_increment primary key,
    email        varchar(50) not null,
    password     varchar(20) null,
    name varchar(20) null,
    auth_provider varchar(100) null,
    oauth2Id varchar(100) null,
    role varchar(20) null,
    profile_image varchar(512) null
);

insert into USER (user_id, email, password, name, auth_provider, oauth2Id,role, profile_image)
values (1, "test1@naver.com", "test1", "testId1","KAKAO","oauth2IdTest1","ROLE_GUEST","https://test_profile_image");

CREATE TABLE BAR_CATEGORY (
                              CATEGORY_ID   bigint(20)   NOT NULL primary key,
                              NAME   VARCHAR(255)   NULL
);
insert into BAR_CATEGORY(category_id, name) values (1,"일본식주점");
insert into BAR_CATEGORY(category_id, name) values (2,"실내포장마차");
insert into BAR_CATEGORY(category_id, name) values (3,"호프요리주점");
insert into BAR_CATEGORY(category_id, name) values (4,"칵테일바");
insert into BAR_CATEGORY(category_id, name) values (5,"와인바");

CREATE TABLE BAR (
                     BAR_ID   bigint(20)   NOT NULL auto_increment primary key,
                     CATEGORY_ID   bigint(20)   NOT NULL,
                     TITLE   VARCHAR(255)   NULL,
                     LOCATION   VARCHAR(255)   NULL,
                     RATING   DOUBLE   NULL,
                     IMAGE   VARCHAR(600)   NULL,
                     TEL   VARCHAR(255)   NULL,
                     lat   VARCHAR(255)   NULL,
                     LOG   VARCHAR(255)   NULL,
                     OPEN_STATUS   VARCHAR(255)   NULL,
                     group_seat   bigint   NULL,
                     hit   bigint   NULL,
                     FOREIGN KEY(category_id) REFERENCES BAR_CATEGORY(category_id)
);

insert into BAR(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (1,1,"고부시","서울 강남구 논현동 80-22",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","02-518-2078","37.51613021","127.0302467","매일",5,5);
insert into BAR(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (2,2,"꼼주","서울 광진구 화양동 9-50 1층",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","010-5137-1675","37.54269611","127.069116","매일",5,56);
insert into BAR(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (3,3,"빈대떡신사","서울 노원구 월계동 379-42",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","02-774-9595","37.6234669","127.060254","매일",5,10);
insert into BAR(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (4,1,"키라키라윤 월계점","서울 노원구 월계동 942 신도브래뉴 104동 상가 1층",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","070-8848-1211","37.62944108","127.0589957","매일",5,8);
insert into BAR(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (5,5,"달별아래","서울 노원구 중계동 154-33 1층 달별아래",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","0507-1369-3167","37.65972195","127.0739148","매일",5,10);
insert into BAR(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (6,1,"오사카로","서울 구로구 신도림동 338 대우푸르지오 206호",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","02-518-2078","37.51613021","127.0302467","매일",5,5);
insert into BAR(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (7,2,"낙성대우동","서울 관악구 봉천동 1629-2",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","02-518-2078","37.51613021","127.0302467","매일",5,5);
insert into BAR(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (8,3,"술희야","서울 강북구 수유동 174-35",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","02-518-2078","37.51613021","127.0302467","매일",5,5);
insert into BAR(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (9,5,"몽희","서울 강북구 우이동 61-56 1층",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","02-518-2078","37.51613021","127.0302467","매일",5,5);
insert into BAR(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (10,4,"단청","서울 관악구 봉천동 972-31 1층 101호",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","02-518-2078","37.51613021","127.0302467","매일",5,5);


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

insert into MENU (menu_id,bar_id,menu_category_id,name,price) values(1,1,1,"하니비라",37000);
INSERT INTO MENU (menu_id, bar_id,menu_category_id, name, price) VALUES (2,1,1,"간바레 오또상", 37000);
INSERT INTO MENU (menu_id, bar_id,menu_category_id, name, price) VALUES (3,1,2,"데판야끼", 28000);
INSERT INTO MENU (menu_id, bar_id,menu_category_id, name, price) VALUES (4,1,2,"나베", 34000);

CREATE TABLE USER_BAR (
                          `USER_BAR_ID`   bigint(20)   NOT NULL auto_increment primary key,
                          `BAR_ID`   bigint(20)   NOT NULL,
                          `USER_ID`   bigint(20)   NOT NULL,
                          FOREIGN KEY(bar_id) REFERENCES BAR(bar_id),
                          FOREIGN KEY(user_id) REFERENCES USER(user_id)
);
insert into USER_BAR(USER_BAR_ID, bar_id, user_id) VALUES (1,1,1);

CREATE TABLE `TAG` (
                       `TAG_ID`   bigint(20)   NOT NULL auto_increment primary key,
                       `NAME`   varchar(255)   NULL
);


CREATE TABLE `TAG_BAR` (
                           `TAG_BAR_ID`   bigint(20)   NOT NULL auto_increment primary key,
                           `BAR_ID`   bigint(20)   NOT NULL,
                           `TAG_ID`   bigint(20)   NOT NULL,
                           FOREIGN KEY(bar_id) REFERENCES BAR(bar_id),
                           FOREIGN KEY(tag_id) REFERENCES TAG(tag_id)
);

insert into TAG(tag_id, name) values (1,'조용한');
insert into TAG(tag_id, name) values (2,'시끌벅적한');

insert into TAG_BAR(TAG_BAR_ID,BAR_ID,TAG_ID) values (1,1,1);
insert into TAG_BAR(TAG_BAR_ID,BAR_ID,TAG_ID) values (2,2,2);