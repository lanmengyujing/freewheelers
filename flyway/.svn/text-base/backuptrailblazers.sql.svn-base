--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'LATIN1';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE account (
    account_id integer NOT NULL,
    account_name character varying(255) NOT NULL,
    email_address character varying(255) NOT NULL,
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


ALTER TABLE public.account OWNER TO postgres;

--
-- Name: account_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE account_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_account_id_seq OWNER TO postgres;

--
-- Name: account_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE account_account_id_seq OWNED BY account.account_id;


--
-- Name: account_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('account_account_id_seq', 40, true);


--
-- Name: account_role; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE account_role (
    role_id integer NOT NULL,
    account_name character varying(255) NOT NULL,
    role character varying(255) NOT NULL
);


ALTER TABLE public.account_role OWNER TO postgres;

--
-- Name: account_role_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE account_role_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_role_role_id_seq OWNER TO postgres;

--
-- Name: account_role_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE account_role_role_id_seq OWNED BY account_role.role_id;


--
-- Name: account_role_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('account_role_role_id_seq', 30, true);


--
-- Name: item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item (
    item_id integer NOT NULL,
    description character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    price numeric(19,2) NOT NULL,
    type character varying(255) NOT NULL,
    quantity bigint NOT NULL
);


ALTER TABLE public.item OWNER TO postgres;

--
-- Name: item_item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE item_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_item_id_seq OWNER TO postgres;

--
-- Name: item_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE item_item_id_seq OWNED BY item.item_id;


--
-- Name: item_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('item_item_id_seq', 26, true);


--
-- Name: item_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item_type (
    item_type_id integer NOT NULL,
    name character varying(64) NOT NULL
);


ALTER TABLE public.item_type OWNER TO postgres;

--
-- Name: item_type_item_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE item_type_item_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_type_item_type_id_seq OWNER TO postgres;

--
-- Name: item_type_item_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE item_type_item_type_id_seq OWNED BY item_type.item_type_id;


--
-- Name: item_type_item_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('item_type_item_type_id_seq', 2, true);


--
-- Name: reserve_order; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE reserve_order (
    order_id integer NOT NULL,
    account_id bigint NOT NULL,
    item_id bigint NOT NULL,
    status character varying(255) NOT NULL,
    note character varying(255) NOT NULL,
    reservation_timestamp timestamp without time zone NOT NULL
);


ALTER TABLE public.reserve_order OWNER TO postgres;

--
-- Name: reserve_order_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE reserve_order_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reserve_order_order_id_seq OWNER TO postgres;

--
-- Name: reserve_order_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE reserve_order_order_id_seq OWNED BY reserve_order.order_id;


--
-- Name: reserve_order_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('reserve_order_order_id_seq', 19, true);


--
-- Name: account_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account ALTER COLUMN account_id SET DEFAULT nextval('account_account_id_seq'::regclass);


--
-- Name: role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account_role ALTER COLUMN role_id SET DEFAULT nextval('account_role_role_id_seq'::regclass);


--
-- Name: item_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item ALTER COLUMN item_id SET DEFAULT nextval('item_item_id_seq'::regclass);


--
-- Name: item_type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_type ALTER COLUMN item_type_id SET DEFAULT nextval('item_type_item_type_id_seq'::regclass);


--
-- Name: order_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reserve_order ALTER COLUMN order_id SET DEFAULT nextval('reserve_order_order_id_seq'::regclass);


--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY account (account_id, account_name, email_address, password, phone_number, street_one, street_two, city, state, country, zip, enabled) FROM stdin;
1	AdminCat	admin@freewheelers.com	admin		\N	\N	\N	\N	UK	\N	t
2	UserCat	user@freewheelers.com	user	12312412	\N	\N	\N	\N	UK	\N	t
28	Hugo Huser	Hugo-Huser@random-email.com	secret	555-123456	\N	\N	\N	\N	UK	\N	t
35	Arno Admin	Arno-Admin@random-email.com	secret	555-123456	\N	\N	\N	\N	UK	\N	t
36	Bob Buyer	Bob-Buyer@random-email.com	secret	555-123456	\N	\N	\N	\N	UK	\N	t
38	Mircea	Mircea@random-email.com	1234	555-123456	\N	\N	\N	\N	UK	\N	t
40	jing	jingliu@thoughtworks.com	123	111-111	one	2	city	state	UK	234	t
\.


--
-- Data for Name: account_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY account_role (role_id, account_name, role) FROM stdin;
1	AdminCat	ROLE_ADMIN
2	UserCat	ROLE_USER
18	Hugo Huser	ROLE_USER
25	Arno Admin	ROLE_ADMIN
26	Bob Buyer	ROLE_USER
28	Mircea	ROLE_USER
30	jing	ROLE_USER
7	Bill Rieflin	ROLE_USER
\.


--
-- Data for Name: item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY item (item_id, description, name, price, type, quantity) FROM stdin;
25	A very nice item.	Simplon Pavo 3 Ultra	1.00	FRAME	0
13	4 x red, curved Arrow shape, screw fastening	NEW - Spoke - Reflectors Arrow red	2899.00	ACCESSORIES	999
6	Fuji' s Altamira CX 1.0 cyclocross frameset is the perfect platform for building up the cross bike of your dreams.	Fuji Altamira CX 1.0 Cyclocross	2599.99	FRAME	422
\.


--
-- Data for Name: item_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY item_type (item_type_id, name) FROM stdin;
1	Frames
2	Accessories
\.


--
-- Data for Name: reserve_order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY reserve_order (order_id, account_id, item_id, status, note, reservation_timestamp) FROM stdin;
17	2	25	NEW		2013-06-25 12:17:51.194
18	2	26	PAID		2013-06-25 12:21:50.865
19	2	13	NEW		2013-06-25 16:40:22.843
11	8	7	NEW		2013-03-28 12:19:10.021
\.


--
-- Name: account_email_address_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_email_address_key UNIQUE (email_address);


--
-- Name: account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_pkey PRIMARY KEY (account_id);


--
-- Name: account_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY account_role
    ADD CONSTRAINT account_role_pkey PRIMARY KEY (role_id);


--
-- Name: item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item
    ADD CONSTRAINT item_pkey PRIMARY KEY (item_id);


--
-- Name: item_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item_type
    ADD CONSTRAINT item_type_pkey PRIMARY KEY (item_type_id);


--
-- Name: reserve_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reserve_order
    ADD CONSTRAINT reserve_order_pkey PRIMARY KEY (order_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

