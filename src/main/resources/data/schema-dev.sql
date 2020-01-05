CREATE SCHEMA `skud_log`;
CREATE TABLE public.security_user
(
    id integer NOT NULL,
    login character varying(25),
    passwd character varying(75),
    is_active boolean DEFAULT true,
    comment character varying(255),
    tabnum integer,
    date_create timestamp(0) without time zone,
    date_update timestamp(0) without time zone,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT uk_dui2p3jtnsa2021h98jiftv39 UNIQUE (login)

);
CREATE TABLE public.security_user_role_table
(
    login character varying(25) NOT NULL,
    role_name character varying(30) NOT NULL,
    filial_id integer,
    CONSTRAINT security_user_role_tbl_pkey UNIQUE (login, role_name, filial_id)

);
INSERT INTO public.security_user (id, login, passwd, is_active, tabnum)
VALUES(8957, 'user', '96e79218965eb72c92a549dd5a330112', true, 2533);
CREATE TABLE public.counterparties
(
    id integer NOT NULL auto_increment,
    name character varying(100) NOT NULL,
    active boolean NOT NULL DEFAULT true,
    version integer NOT NULL DEFAULT 0,
    CONSTRAINT counterparties_pkey PRIMARY KEY (id)
);
CREATE TABLE public.events
(
    id integer NOT NULL auto_increment,
    ident character varying(100) NOT NULL,
    ts timestamp,
    body text,
    CONSTRAINT events_pkey PRIMARY KEY (id)
);
CREATE TABLE public.shops
(
    id integer NOT NULL auto_increment,
    name character varying(100) NOT NULL,
    counterparty_id integer NOT NULL,
    active boolean NOT NULL DEFAULT true,
    version integer NOT NULL DEFAULT 0,
    CONSTRAINT shops_pkey PRIMARY KEY (id)
);
CREATE TABLE public.manufactures
(
    id integer NOT NULL auto_increment,
    name character varying(100) NOT NULL,
    active boolean NOT NULL DEFAULT true,
    version integer NOT NULL DEFAULT 0,
    CONSTRAINT manufactures_pkey PRIMARY KEY (id)
);
CREATE TABLE public.shop_types
(
    id integer NOT NULL auto_increment,
    name character varying(100) NOT NULL,
    active boolean NOT NULL DEFAULT true,
    manufactures_id integer NOT NULL,
    version integer NOT NULL DEFAULT 0,
    CONSTRAINT shop_types_pkey PRIMARY KEY (id)
);
CREATE TABLE public.shop_to_shop_types
(
    id integer NOT NULL auto_increment,
    shop_id integer NOT NULL,
    shop_types_id integer NOT NULL,
    CONSTRAINT shop_to_shop_types_pkey PRIMARY KEY (id),
    CONSTRAINT shop_id_shop_types_id_uidx UNIQUE (shop_id, shop_types_id)
);
