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
VALUES(1, 'user', '96e79218965eb72c92a549dd5a330112', true, 100);
INSERT INTO public.security_user (id, login, passwd, is_active, tabnum)
VALUES(2, 'reader', '96e79218965eb72c92a549dd5a330112', true, 200);
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
    entity character varying(100) NOT NULL,
    ts timestamp,
    body text,
    username character varying(100) NOT NULL,
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
CREATE TABLE public.external_ids
(
    id integer NOT NULL auto_increment,
    entity_id integer NOT NULL,
    entity_ext_id integer NOT NULL,
    entity_type character varying(25) NOT NULL,
    ext_prog_id integer NOT NULL,
    CONSTRAINT external_ids_pkey PRIMARY KEY (id),
    CONSTRAINT external_ids_uidx UNIQUE (entity_ext_id, entity_type, ext_prog_id)
);
CREATE TABLE public.system_roles
(
    id integer NOT NULL auto_increment,
    name character varying(100) NOT NULL,
    active boolean NOT NULL DEFAULT true,
    CONSTRAINT system_roles_pkey PRIMARY KEY (id),
    CONSTRAINT system_roles_uidx UNIQUE (name)
);
CREATE TABLE public.user_to_system_roles
(
    id integer NOT NULL auto_increment,
    user_id integer NOT NULL,
    system_roles_id integer NOT NULL,
    CONSTRAINT user_to_system_roles_pkey PRIMARY KEY (id),
    CONSTRAINT user_to_system_roles_uidx UNIQUE (user_id, system_roles_id)
);
CREATE TABLE public.system_rules
(
    id integer NOT NULL auto_increment,
    name character varying(100) NOT NULL,
    active boolean NOT NULL DEFAULT true,
    CONSTRAINT system_rules_pkey PRIMARY KEY (id),
    CONSTRAINT system_rules_uidx UNIQUE (name)
);
CREATE TABLE public.role_to_rules
(
    id integer NOT NULL auto_increment,
    system_role_id integer NOT NULL,
    system_rules_id integer NOT NULL,
    CONSTRAINT role_to_rules_pkey PRIMARY KEY (id),
    CONSTRAINT role_to_rules_uidx UNIQUE (system_role_id, system_rules_id)
);