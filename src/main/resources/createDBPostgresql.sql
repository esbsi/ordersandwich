DROP TABLE SHOPS CASCADE;
DROP TABLE PERSONS CASCADE;
DROP TABLE SESSIONs CASCADE;
DROP TABLE sandwichorders CASCADE;
DROP TABLE SANDWICHTYPES CASCADE;
DROP TABLE orderHistory CASCADE;
DROP TABLE sandwichorderstoday CASCADE;
DROP TABLE sessionparticipants CASCADE;

drop sequence shop_seq;
drop sequence person_seq;
drop sequence session_seq;
drop sequence sandwichtype_seq;
drop sequence orderhistory_seq;
drop sequence sandwichorder_seq;



create sequence shop_seq start with 1;
create sequence person_seq start with 1;
create sequence session_seq start with 1;
create sequence sandwichtype_seq start with 1;
create sequence orderhistory_seq start with 1;
create sequence sandwichorder_seq start with 1;


CREATE TABLE SHOPS
(ID      INT  primary key default nextval('shop_seq'),
SHOP_NAME VARCHAR(45) NOT NULL
);

CREATE TABLE SESSIONS
(ID      INT  primary key default nextval('session_seq'),
 session_name VARCHAR(45) NOT NULL,
 startdate date not null,
 enddate date not null
);

CREATE TABLE PERSONS
(ID      INT  primary key default nextval('person_seq'),
 firstname VARCHAR(45) NOT NULL,
 lastname varchar(1000) NOT NULL
);

CREATE TABLE SANDWICHTYPES
(ID      INT  primary key default nextval('sandwichtype_seq'),
 sandwich_name VARCHAR(45) NOT NULL,
 shop_id INT ,
 CONSTRAINT FK_shop FOREIGN KEY (shop_id) REFERENCES shops,
 price double precision,
 sandwich_category varchar(45),
 vegetarian varchar(1),
 constraint boolean_veggie check ( vegetarian in ('T','F') ),
 description varchar(1000)
);

create table orderHistory
(ID      INT  primary key default nextval('orderhistory_seq'),
totalprice double precision,
 shop_id INT ,
 CONSTRAINT FK_shop2 FOREIGN KEY (shop_id) REFERENCES shops,
 senddate date,
 closingtime time
);

create table sandwichorders
(
    id              int primary key default nextval('sandwichorder_seq'),
    sandwichtype_id int,
    constraint FK_sandwichtype foreign key (sandwichtype_id) references SANDWICHTYPES,
    rauwkost        varchar(1),
    constraint boolean_rauwkost check ( rauwkost in ('T', 'F') ),
    grilledvegs     varchar(1),
    constraint boolean_grilledvegs check ( grilledvegs in ('T', 'F') ),
    white           varchar(1),
    constraint boolean_white check ( white in ('T', 'F') ),
    comment         varchar(1000),
    person_id       int,
    constraint FK_person foreign key (person_id) references PERSONS
);
     
create table sandwichorderstoday
(
    sandwichorder_id int,
    constraint FK_sandwichorder foreign key (sandwichorder_id) references sandwichorders,
    orderhistory_id int,
    constraint FK_orderHistory foreign key (orderhistory_id) references orderHistory
);

create table sessionparticipants
(
    session_id int,
    constraint FK_session foreign key (session_id) references SESSIONs,
    person_id int,
    constraint FK_person foreign key (person_id) references persons
);
     

 INSERT INTO SHOPS VALUES (nextval('shop_seq'), 'Pinkys');
INSERT INTO SHOPS VALUES (nextval('shop_seq'), 'Vleugels');


insert into PERSONS (firstname, lastname) values ('Joske', 'Demeuleneire') ;



