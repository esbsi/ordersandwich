create sequence shop_shno_seq start with 105;

CREATE TABLE SHOPS
(SHNO      INT  primary key default nextval('companies_cono_seq'),
SHNAME VARCHAR(45) NOT NULL

);
	 