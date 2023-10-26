drop table if exists review;
drop table if exists image;
drop table if exists tag_bar;
drop table if exists tag;
drop table if exists user_mbti;
drop table if exists mbti;
drop table if exists user_survey;
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

CREATE TABLE bar_category (
                              CATEGORY_ID   bigint(20)   NOT NULL primary key,
                              NAME   VARCHAR(255)   NULL
);
insert into bar_category(category_id, name) values (1,"일본식주점");
insert into bar_category(category_id, name) values (2,"실내포장마차");
insert into bar_category(category_id, name) values (3,"호프요리주점");
insert into bar_category(category_id, name) values (4,"칵테일바");
insert into bar_category(category_id, name) values (5,"와인바");

CREATE TABLE bar (
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
                     FOREIGN KEY(category_id) REFERENCES bar_category(category_id)
);

insert into bar(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (1,1,"g","서울 강남구 논현동 80-22",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","02-518-2078","37.51613021","127.0302467","매일",5,5);
insert into bar(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (2,2,"꼼주","서울 광진구 화양동 9-50 1층",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","010-5137-1675","37.54269611","127.069116","매일",5,5);
insert into bar(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (3,3,"빈대떡신사","서울 노원구 월계동 379-42",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","02-774-9595","37.6234669","127.060254","매일",5,10);
insert into bar(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (4,1,"키라키라윤 월계점","서울 노원구 월계동 942 신도브래뉴 104동 상가 1층",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","070-8848-1211","37.62944108","127.0589957","매일",5,8);
insert into bar(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (5,5,"달별아래","서울 노원구 중계동 154-33 1층 달별아래",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","0507-1369-3167","37.65972195","127.0739148","매일",5,10);
insert into bar(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (6,1,"오사카로","서울 구로구 신도림동 338 대우푸르지오 206호",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","02-2634-2223","37.51057904","126.8894732","매일",5,5);
insert into bar(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (7,2,"낙성대우동","서울 관악구 봉천동 1629-2",4.5,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","02-878-3345","37.47707805","126.961091","매일",5,5);
insert into bar(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (8,3,"술희야","서울 강북구 수유동 174-35",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","010-5811-3348","37.64068431","127.0274963","매일",5,5);
insert into bar(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (9,5,"몽희","서울 강북구 우이동 61-56 1층",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","010-6223-0414","37.65630903","127.0122664","매일",5,5);
insert into bar(bar_id,CATEGORY_ID,title,LOCATION,RATING,IMAGE,TEL,lat,log,OPEN_STATUS,group_seat,hit)
values (10,4,"단청","서울 관악구 봉천동 972-31 1층 101호",5.0,"https://ldb-phinf.pstatic.net/20180531_279/1527725073965wpnIX_JPEG/TAroOfA874YOsnnul2gWw0Az.jpg","070-4060-4061","37.49028899","126.9260004","매일",5,5);

CREATE TABLE menu_category (
                               menu_category_id   bigint(20)   NOT NULL auto_increment primary key,
                               NAME   VARCHAR(255)   NULL,
                               GRADE   VARCHAR(255)   NULL
);

insert into menu_category(menu_category_id, NAME, GRADE) values (1,"주류",1);
insert into menu_category(menu_category_id, NAME, GRADE) values (2,"안주",1);

CREATE TABLE menu (
                      MENU_ID   bigint(20)   NOT NULL primary key,
                      BAR_ID   bigint(20)   NOT NULL,
                      menu_category_id   bigint(20)   NOT NULL,
                      NAME   VARCHAR(255)   NOT NULL,
                      PRICE   bigint(20)   NULL
);
ALTER TABLE menu ADD CONSTRAINT FK_BAR_TO_MENU_1 FOREIGN KEY (BAR_ID) REFERENCES bar (BAR_ID);
ALTER TABLE menu ADD CONSTRAINT FK_MENU_CATEGORY_TO_MENU_1 FOREIGN KEY (menu_category_id) REFERENCES menu_category(menu_category_id);

insert into menu (menu_id,bar_id,menu_category_id,name,price) values(1,1,1,"하니비라",37000);
INSERT INTO menu (menu_id, bar_id,menu_category_id, name, price) VALUES (2,1,1,"간바레 오또상", 37000);
INSERT INTO menu (menu_id, bar_id,menu_category_id, name, price) VALUES (3,1,2,"데판야끼", 28000);
INSERT INTO menu (menu_id, bar_id,menu_category_id, name, price) VALUES (4,1,2,"나베", 34000);

CREATE TABLE user_bar (
                          `USER_BAR_ID`   bigint(20)   NOT NULL auto_increment primary key,
                          `BAR_ID`   bigint(20)   NOT NULL,
                          `USER_ID`   bigint(20)   NOT NULL,
                          FOREIGN KEY(bar_id) REFERENCES bar(bar_id),
                          FOREIGN KEY(user_id) REFERENCES user(user_id)
);
insert into user_bar(USER_BAR_ID, bar_id, user_id) VALUES (1,1,1);

CREATE TABLE `tag` (
                       `TAG_ID`   bigint(20)   NOT NULL auto_increment primary key,
                       `NAME`   varchar(255)   NULL
);


CREATE TABLE `tag_bar` (
                           `TAG_BAR_ID`   bigint(20)   NOT NULL auto_increment primary key,
                           `BAR_ID`   bigint(20)   NOT NULL,
                           `TAG_ID`   bigint(20)   NOT NULL,
                           FOREIGN KEY(bar_id) REFERENCES bar(bar_id),
                           FOREIGN KEY(tag_id) REFERENCES tag(tag_id)
);

insert into tag(tag_id, name) values (1,'조용한');
insert into tag(tag_id, name) values (2,'시끌벅적한');

insert into tag_bar(TAG_BAR_ID,BAR_ID,TAG_ID) values (1,1,1);
insert into tag_bar(TAG_BAR_ID,BAR_ID,TAG_ID) values (2,2,2);

CREATE TABLE mbti(
                     mbti_id bigint not null auto_increment primary key,
                     name varchar(30) null,
                     pattern varchar(30) null
);

create table user_mbti (
                           user_mbti_id bigint not null auto_increment primary key,
                           mbti_id bigint,
                           user_id bigint,
                           FOREIGN KEY (mbti_id) REFERENCES mbti(mbti_id),
                           FOREIGN KEY (user_id) REFERENCES user(user_id)
) engine=InnoDB;

CREATE TABLE survey(
                       survey_id bigint not null auto_increment primary key,
                       question_text varchar(255) null,
                       option1 varchar(50) null,
                       option2 varchar(50) null
);

create table user_survey  (
                              user_survey_id bigint not null auto_increment primary key,
                              survey_id bigint null,
                              user_id bigint null,
                              select_option int null,
                              FOREIGN KEY (survey_id) REFERENCES survey(survey_id),
                              FOREIGN KEY (user_id) REFERENCES user(user_id)
) engine=InnoDB;

insert into mbti
VALUES(1,'사교적 활동가','[1, 1, 1, 1, 1, 1, 1]');
insert into mbti
VALUES(2,'고요한 청년','[1,1,1,1,1,1,2]');
insert into mbti
VALUES(3,'탐험가','[1,1,1,1,1,2,1]');
insert into mbti
VALUES(4,'자연 속의 술주인','[1,1,1,1,1,2,2]');
insert into mbti
VALUES(5,'감성충만 예술감상가','[1,1,1,1,2,1,1]');
insert into mbti
VALUES(6,'매력적인 인플루언서','[1,1,1,1,2,1,2]');
insert into mbti
VALUES(7,'문화적 탐험가','[1,1,1,1,2,2,1]');
insert into mbti
VALUES(8,'휴식을 즐기는 로맨티스트','[1,1,1,1,2,2,2]');
insert into mbti
VALUES(9,'음식 중심의 미식가','[1,1,1,2,1,1,1]');
insert into mbti
VALUES(10,'자유로운 예술가','1,1,1,2,1,1,2');
insert into mbti
VALUES(11,'현실주의자','1,1,1,2,1,2,1');
insert into mbti
VALUES(12,'도전과 열정의 미식가','1,1,1,2,1,2,2');

INSERT into survey
values(1, '나의 술 모임 성향은?','4인 이상의 술자리가 좋아','4인은 좀.. 3인 이하가 좋아');
INSERT into survey
values(2, '술 자리에 참석했을 때','왁자 지껄한 분위기가 좋아','조용히 둘이서 토크하는게 좋아');
INSERT into survey
values(3, '편의점에 술을 사러 왔을 때','4캔에 12000원 무조건 사야지','비싸더라도 맛있는 술을 사는게 좋아');
INSERT into survey
values(4, '휴일에 술집을 찾는다 당신은?','힙하고 시끌 벅적한 술집','조용하고 분위기가 좋은 술집');
INSERT into survey
values(5, '안주를 고른다면?','양이 많고 값이 싼','양이 적고 비싸지만 이쁘고 맛있는');
INSERT into survey
values(6, '술집을 고른다면?','한잔한잔이 고급진 칵테일바','무한으로 즐겨요 칵테일 무한리필');
INSERT into survey
values(7, '당신이 좋아하는 주류?','소주','맥주');