CREATE TABLE item
(
       item_id SERIAL PRIMARY KEY,
       description character varying(255) NOT NULL,
       name character varying(255) NOT NULL,
       price numeric(19,2) NOT NULL,
       type character varying(255) NOT NULL,
       quantity bigint NOT NULL,
       image_path character varying(255)
 );

CREATE TABLE item_type
(
       item_type_id SERIAL PRIMARY KEY,
       name character varying(64) NOT NULL
);


CREATE TABLE account
(
      account_id SERIAL PRIMARY KEY,
      account_name character varying(255) NOT NULL,
      email_address character varying(255) NOT NULL UNIQUE,
      password character varying(255) NOT NULL,
      phone_number character varying(32) NOT NULL,
      street_one character varying(255),
      street_two character varying(255),
      city character varying(255),
      state character varying(255),
      country character varying(255),
      zip character varying(255),
      enabled boolean NOT NULL
);

CREATE TABLE account_role
(
        role_id SERIAL PRIMARY KEY,
        account_name character varying(255) NOT NULL,
        role character varying(255) NOT NULL
);

CREATE TABLE reserve_order
(
        order_id SERIAL PRIMARY KEY,
        account_id bigint NOT NULL,
        item_id bigint NOT NULL,
        item_quantity bigint NOT NULL,
	      status character varying(255) NOT NULL,
	      note character varying(255) NOT NULL,
        reservation_timestamp timestamp without time zone NOT NULL,
		    transaction_id character varying(255) 

);

CREATE TABLE paid_order_info
(
        pay_id SERIAL PRIMARY KEY,
        account_id bigint NOT NULL,
        net_total bigint NOT NULL,
        gross_total bigint NOT NULL,
        vat bigint NOT NULL,
        duty_tax bigint NOT NULL
);

CREATE TABLE paid_order_items
(
        pay_id bigint NOT NULL references paid_order_info(pay_id),
        order_id bigint NOT NULL,
        account_id bigint NOT NULL,
        item_id bigint NOT NULL,
        item_quantity bigint NOT NULL,
	      status character varying(255) NOT NULL,
	      note character varying(255) NOT NULL,
        reservation_timestamp timestamp without time zone NOT NULL,
		    transaction_id character varying(255)
);



insert into account (email_address, account_name, password, phone_number, country, enabled)
       values ('admin@freewheelers.com', 'AdminCat','$2a$10$KBPVptlFp2tDo3w7xqLs6eRG/pXdIPFAo9e1aPNFXP/8jiv8kmm.i', '123456789', 'UK', true);
insert into account (email_address, account_name, password, phone_number, country, enabled)
       values ('user@freewheelers.com', 'UserCat','$2a$10$dMY734sgN6FVEPuIxD3TQuzYww18qWe4D4ZS0kCt9n026/xpHF4cO','123345567', null, true);

insert into account_role (account_name, role) values ('AdminCat', 'ROLE_ADMIN');
insert into account_role (account_name, role) values ('UserCat', 'ROLE_USER');

insert into item_type (name) values ('Frames');
insert into item_type (name) values ('Accessories');
