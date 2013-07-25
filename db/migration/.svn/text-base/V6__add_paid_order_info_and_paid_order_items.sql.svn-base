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
