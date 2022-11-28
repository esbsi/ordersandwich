DROP TABLE SHOPS CASCADE;

drop sequence  shop_shno_seq;


create sequence shop_seq start with 1;
create sequence person_seq start with 1;
create sequence session_seq start with 1;
create sequence sandwichtype_seq start with 1;
create sequence ordertoday_seq start with 1;
create sequence sandwichorder_seq start with 1;


CREATE TABLE SHOPS
(ID      INT  primary key default nextval('shop_seq'),
SHOP_NAME VARCHAR(45) NOT NULL
);
CREATE TABLE SESSIONs
(ID      INT  primary key default nextval('session_seq'),
 session_name VARCHAR(45) NOT NULL,
 startdate date not null,
 enddate date not null
);

CREATE TABLE PERSONS
(ID      INT  primary key default nextval('person_seq'),
 firstname VARCHAR(45) NOT NULL,
 startdate date not null,
 enddate date not null
);

CREATE TABLE SANWICHTYPES
(ID      INT  primary key default nextval('sandwichtype_seq'),
 sandwichname VARCHAR(45) NOT NULL,
 shop_id INT ,
 CONSTRAINT FK_shop FOREIGN KEY (shop_id) REFERENCES shops,
 price double precision,
 typecategory varchar(45),
 vegetarian varchar(1),
 constraint boolean_veggie check ( vegetarian in ('T','F') ),
 discription varchar(1000)

);


create table orderHistory
(ID      INT  primary key default nextval('ordertoday_seq'),
totalprice double precision,
 shop_id INT ,
 CONSTRAINT FK_shop2 FOREIGN KEY (shop_id) REFERENCES shops,
 senddate date,
 closingtime time


);

INSERT INTO SHOPS VALUES (nextval('shop_shno_seq'), 'Pinkys');
INSERT INTO SHOPS VALUES (nextval('shop_shno_seq'), 'Vleugels');





