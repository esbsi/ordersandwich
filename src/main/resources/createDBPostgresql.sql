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
 shop_id INT ,
 CONSTRAINT FK_shop FOREIGN KEY (shop_id) REFERENCES shops,
 sandwich_name VARCHAR(45) NOT NULL,
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

INSERT INTO SHOPS VALUES (nextval('shop_seq'), 'Vleugels');
INSERT INTO SHOPS VALUES (nextval('shop_seq'), 'Pinkys');

insert into PERSONS (firstname, lastname) values ('Joske', 'Demeuleneire') ;
insert into PERSONS (firstname, lastname) values ('Jietse', 'Molenaers') ;

insert into SESSIONS (session_name, startdate, enddate) values ('Intro SQL', '2022-12-01', '2022-12-04');
insert into SESSIONS (session_name, startdate, enddate) values ('Intro UML', '2022-12-12', '2022-12-14');

insert into SANDWICHTYPES (shop_id, sandwich_name, price, sandwich_category, vegetarian) VALUES (1, 'Americain', '4.56', 'Vlees', 'F');
insert into SANDWICHTYPES (shop_id, sandwich_name, price, sandwich_category, vegetarian) VALUES (1, 'Boulette', '3.56', 'Vlees', 'F');
insert into SANDWICHTYPES (shop_id, sandwich_name, price, sandwich_category, vegetarian) VALUES (1, 'Pastrami', '5.56', 'Vlees', 'F');
insert into SANDWICHTYPES (shop_id, sandwich_name, price, sandwich_category, vegetarian) VALUES (1, 'Kaas', '3.45', 'Veggie', 'T');
insert into SANDWICHTYPES (shop_id, sandwich_name, price, sandwich_category, vegetarian) VALUES (1, 'Brie', '3.45', 'Veggie', 'T');
insert into SANDWICHTYPES (shop_id, sandwich_name, price, sandwich_category, vegetarian, description) VALUES (1, 'Carolina', '3.45', 'Specials', 'F', 'Eiersalade, spek, tuinkers');
insert into SANDWICHTYPES (shop_id, sandwich_name, price, sandwich_category, vegetarian, description) VALUES (1, 'Provence', '3.45', 'Specials', 'T', 'Feta, zongedroogde tomaat, rucola');

insert into SANDWICHTYPES (shop_id, sandwich_name, price, sandwich_category, vegetarian) VALUES (2, 'Hesp', '5.00', 'Vlees', 'F');
insert into SANDWICHTYPES (shop_id, sandwich_name, price, sandwich_category, vegetarian) VALUES (2, 'Rosbief', '5.50', 'Vlees', 'F');
insert into SANDWICHTYPES (shop_id, sandwich_name, price, sandwich_category, vegetarian) VALUES (2, 'Brie', '4.50', 'Kaas', 'T');
insert into SANDWICHTYPES (shop_id, sandwich_name, price, sandwich_category, vegetarian, description) VALUES (2, 'Smos', '4.60', 'Specialiteiten', 'F', 'Club met kaas en hesp');
insert into SANDWICHTYPES (shop_id, sandwich_name, price, sandwich_category, vegetarian, description) VALUES (2, 'Mozarella', '5.20', 'Specialiteiten', 'T', 'Olijfolie, zout, peper, basilicum, tomaat');

insert into sessionparticipants (session_id, person_id) values (1, 1);
insert into sessionparticipants (session_id, person_id) values (1, 2);
insert into sessionparticipants (session_id, person_id) values (2, 1);

insert into orderHistory (shop_id, senddate, closingtime) VALUES (1, '2022-11-27', '12:00:00');

insert into sandwichorders (sandwichtype_id, rauwkost, grilledvegs, white, comment, person_id) values (1, 'T', 'F', 'T', 'geen mayo aub', 1);
insert into sandwichorders (sandwichtype_id, rauwkost, grilledvegs, white, comment, person_id) values (3, 'T', 'F', 'T', 'geen boter aub', 2);

insert into sandwichorderstoday (sandwichorder_id, orderhistory_id) VALUES (1, 1);
insert into sandwichorderstoday (sandwichorder_id, orderhistory_id) VALUES (2, 1);
