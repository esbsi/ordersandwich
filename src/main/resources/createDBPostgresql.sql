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
    note         varchar(1000),
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
insert into PERSONS (firstname, lastname) values ('Sim', 'Haas') ;
insert into PERSONS (firstname, lastname) values ('Jef', 'Jeweet') ;

insert into SESSIONS (session_name, startdate, enddate) values ('Intro SQL', '2022-12-01', '2022-12-04');
insert into SESSIONS (session_name, startdate, enddate) values ('Intro UML', '2022-12-12', '2022-12-14');
insert into SESSIONS (session_name, startdate, enddate) values ('Intro python', '2022-12-01', '2022-12-14');



insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Americain',1,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Boulette',1,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Pastrami',1,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Hesp + kaas',1,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Martino',1,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Vleessalade',1,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Hesp',1,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Parmaham',1,'vlees',5.00,null, 'F');

insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Kipfilet', 1, 'kip', 5.00, null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Spicy kip curry', 1, 'kip', 5.00, null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Kip Hawaii', 1, 'kip', 5.00, null, 'F');

insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Tonijnsalade', 1, 'vis', 5.00, null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Tonijnsalade pikant', 1, 'vis', 5.00, null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Zalm(gemarineerd) + philadelphia', 1, 'vis', 5.00, null, 'F');

insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Kaas', 1, 'veggie', 5.00, null, 'T');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Tomaat + mozzarella', 1, 'veggie', 5.00, null, 'T');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Eiersalade', 1, 'veggie', 5.00, null, 'T');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Brie', 1, 'veggie', 5.00, null, 'T');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Feta', 1, 'veggie', 5.00, null, 'T');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Geitenkaas', 1, 'veggie', 5.00, null, 'T');

insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Wortelspread + sesam + tuinkers', 1, 'vegan', 5.00, null, 'T');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Hummus', 1, 'vegan', 5.00, null, 'T');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Vegan mayo', 1, 'vegan', 5.00, null, 'T');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Avocadospread', 1, 'vegan', 5.00, null, 'T');

insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Carolina', 1, 'specials', 5.00, 'Eiersalade, spek, tuinkers', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Parmigiano', 1, 'specials', 5.00, 'Americain, zongedroogde tomaat, rucola, pijnboompitten', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Kip cocktail', 1, 'specials', 5.00, 'Kipfilet, cocktailsaus, perzik, tuinkers', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('BBQ chicken', 1, 'specials', 5.00, 'Kip, barbecuesaus, spek, tomaat, sla', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Flying brie', 1, 'specials', 5.00, 'Brie, honing, noot, rucola', 'T');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Provence', 1, 'specials', 5.00, 'Feta, zongedroogde tomaat, rucola', 'T');


insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Hesp',2,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Rosbief',2,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Gebraad',2,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Gerookte Nootham',2,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Parma ham',2,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Salami',2,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Americain Preparé',2,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Gemend gehakt',2,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Martino',2,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Kip curry',2,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Kip zigeuner',2,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Kipsla',2,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Weense sla',2,'vlees',5.00,null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Vleessla',2,'vlees',5.00,null, 'F');

insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Hollandse kaas', 2, 'kaas', 5.00, null, 'T');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Brie', 2, 'kaas', 5.00, null, 'T');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Kaassla', 2, 'kaas', 5.00, null, 'T');

insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Tomaat garnaal', 2, 'vis', 5.00, null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Garnaalsla', 2, 'vis', 5.00, null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Tonijnsla', 2, 'vis', 5.00, null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Pikante tonijnsla', 2, 'vis', 5.00, null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Zalmsla', 2, 'vis', 5.00, null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Gerookte zalmsla', 2, 'vis', 5.00, null, 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Krabsla', 2, 'vis', 5.00, null, 'F');

insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Eiersla', 2, 'ei', 5.00, null, 'T');

insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Smos', 2, 'specials', 5.00, 'Club met kaas en hesp', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Pinky', 2, 'specials', 5.00, 'Sla, tomaat, ei, haringfilet, tartaar', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Mozzarella', 2, 'specials', 5.00, 'Olijfolie, zout, peper, basilicum, tomaat', 'T');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Special', 2, 'specials', 5.00, 'Sla, tomaat, ei, kipfilet, saus', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Florida', 2, 'specials', 5.00, 'Sla, ananas, kipfilet, cocktailsaus', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Primus', 2, 'specials', 5.00, 'Sla, perzik, tonijnsla', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Apollo', 2, 'specials', 5.00, 'Rucola, ananas, gebraad, cocktailsaus', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Milano', 2, 'specials', 5.00, 'Sla, tomaat, augurk, ei, salami, tartaar', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Ardenne', 2, 'specials', 5.00, 'Rucola, tomaat, nootham, cocktailsaus', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Gerookte zalm', 2, 'specials', 5.00, 'Sla, ui, gerookte zalm, cocktailsaus', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Tweetybroodje', 2, 'specials', 5.00, 'Eiersla, salami', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Lentebroodje', 2, 'specials', 5.00, 'Seldersla, geraspte wortels, ananas', 'T');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Hawaiibroodje', 2, 'specials', 5.00, 'Ananas, hesp, kaas, cocktailsaus', 'F');
insert into SANDWICHTYPES (sandwich_name, shop_id, sandwich_category, price, description, vegetarian) values('Gezond', 2, 'specials', 5.00, 'Sla, tomaat, seldersla, geraspte wortelen, komkommer, ei, mayonaise', 'T');



insert into sessionparticipants (session_id, person_id) values (1, 1);
insert into sessionparticipants (session_id, person_id) values (1, 2);
insert into sessionparticipants (session_id, person_id) values (2, 3);
insert into sessionparticipants (session_id, person_id) values (2, 1);

insert into orderHistory (shop_id, senddate, closingtime,totalprice) VALUES (1, '2022-11-27', '12:00:00',30);
insert into orderHistory (shop_id, senddate, closingtime,totalprice) VALUES (1, '2022-11-27', '12:00:00',30);

insert into sandwichorders (sandwichtype_id, rauwkost, grilledvegs, white, note, person_id) values (1, 'T', 'F', 'T', 'geen mayo aub', 1);
insert into sandwichorders (sandwichtype_id, rauwkost, grilledvegs, white, note, person_id) values (3, 'T', 'F', 'T', 'geen boter aub', 2);

insert into sandwichorderstoday (sandwichorder_id, orderhistory_id) VALUES (1, 1);
insert into sandwichorderstoday (sandwichorder_id, orderhistory_id) VALUES (2, 1);
