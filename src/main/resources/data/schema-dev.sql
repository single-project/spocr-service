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
    name character varying(300) NOT NULL,
    legal_type_id integer,
    short_name  character varying(300),
    full_name character varying(1000),
    inn character varying(10),
    kpp character varying(9),
    ogrn character varying(15),
    ogrn_date date,
    ogrn_authority character varying(1000),
    okpo character varying(40),
    okonh character varying(100),
    counterparty_payment_details_id integer,
    counterparty_person_id integer,
    suggestion text,
    parent_id integer,
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
    gln character varying(40),
    signboard character varying(50),
    area double,
    comment character varying(1000),
    counterparty_id integer NOT NULL,
    address_id integer,
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
CREATE TABLE public.addresses
(
    id integer NOT NULL auto_increment,
    address character varying(100) NOT NULL,
    latitude double,
    longitude double,
    comment character varying(500),
    suggestion text,
    CONSTRAINT addresses_pkey PRIMARY KEY (id)
);
CREATE TABLE public.counterparty_payment_details
(
    id integer NOT NULL auto_increment,
    payment_account character varying(20) NOT NULL,
    corresponding_account character varying(20),
    bic character varying(9) NOT NULL,
    bank character varying(300) NOT NULL,
    active boolean NOT NULL DEFAULT true,
    version integer NOT NULL DEFAULT 0,
    CONSTRAINT counterparty_payment_details_pkey PRIMARY KEY (id)
);
CREATE TABLE public.legal_types
(
    id integer NOT NULL auto_increment,
    name character varying(50) NOT NULL,
    okpf_short_name character varying(50),
    okpf_full_name character varying(100),
    okpf_id character varying(15),
    okpf_type character varying(25),
    active boolean NOT NULL DEFAULT true,
    version integer NOT NULL DEFAULT 0,
    CONSTRAINT legal_types_pkey PRIMARY KEY (id),
    CONSTRAINT legal_types_uidx UNIQUE (name)
);
CREATE TABLE public.settings
(
    id integer NOT NULL auto_increment,
    data text,
    CONSTRAINT settings_pkey PRIMARY KEY (id),
);
CREATE TABLE public.sales_channels
(
    id integer NOT NULL auto_increment,
    name character varying(100) NOT NULL,
    active boolean NOT NULL DEFAULT true,
    manufactures_id integer NOT NULL,
    version integer NOT NULL DEFAULT 0,
    CONSTRAINT sales_channels_pkey PRIMARY KEY (id)
);
CREATE TABLE public.shop_to_sales_channels
(
    id integer NOT NULL auto_increment,
    shop_id integer NOT NULL,
    sales_channels_id integer NOT NULL,
    CONSTRAINT shop_to_sales_channel_pkey PRIMARY KEY (id),
    CONSTRAINT shop_to_sales_channel_uidx UNIQUE (shop_id, sales_channels_id)
);
CREATE TABLE public.shop_departs
(
    id integer NOT NULL auto_increment,
    name character varying(100) NOT NULL,
    active boolean NOT NULL DEFAULT true,
    manufactures_id integer NOT NULL,
    version integer NOT NULL DEFAULT 0,
    CONSTRAINT shop_departs_pkey PRIMARY KEY (id)
);
CREATE TABLE public.shop_to_shop_departs
(
    id integer NOT NULL auto_increment,
    shop_id integer NOT NULL,
    shop_departs_id integer NOT NULL,
    CONSTRAINT shop_to_shop_departs_pkey PRIMARY KEY (id),
    CONSTRAINT shop_to_shop_departs_uidx UNIQUE (shop_id, shop_departs_id)
);
CREATE TABLE public.shop_specializations
(
    id integer NOT NULL auto_increment,
    name character varying(100) NOT NULL,
    active boolean NOT NULL DEFAULT true,
    manufactures_id integer NOT NULL,
    version integer NOT NULL DEFAULT 0,
    CONSTRAINT shop_specializations_pkey PRIMARY KEY (id)
);
CREATE TABLE public.shop_to_shop_specializations
(
    id integer NOT NULL auto_increment,
    shop_id integer NOT NULL,
    shop_specializations_id integer NOT NULL,
    CONSTRAINT shop_to_shop_specializations_pkey PRIMARY KEY (id),
    CONSTRAINT shop_to_shop_specializations_uidx UNIQUE (shop_id, shop_specializations_id)
);
CREATE TABLE public.counterparty_statuses
(
    id integer NOT NULL auto_increment,
    name character varying(100) NOT NULL,
    CONSTRAINT counterparty_statuses_pkey PRIMARY KEY (id),
    CONSTRAINT counterparty_statuses_uidx UNIQUE (name)
);
CREATE TABLE public.counterparty_to_counterparty_statuses
(
    id integer NOT NULL auto_increment,
    counterparty_id integer NOT NULL,
    counterparty_statuses_id integer NOT NULL,
    CONSTRAINT counterparty_to_counterparty_statuses_pkey PRIMARY KEY (id),
    CONSTRAINT counterparty_to_counterparty_statuses_uidx UNIQUE (counterparty_id, counterparty_statuses_id)
);
CREATE TABLE public.contracts
(
    id integer NOT NULL auto_increment,
    counterparty_1_id integer NOT NULL,
    counterparty_2_id integer NOT NULL,
    contract_number character varying(25) NOT NULL,
    name character varying(300) NOT NULL,
    comment character varying(1000),
    start_date date NOT NULL,
    end_date date NOT NULL,
    active boolean NOT NULL DEFAULT true,
    version integer NOT NULL DEFAULT 0,
    CONSTRAINT ccontracts_pkey PRIMARY KEY (id)
);
CREATE TABLE public.sub_contracts
(
    id integer NOT NULL auto_increment,
    contract_id integer NOT NULL,
    sub_contract_number integer NOT NULL,
    name character varying(300) NOT NULL,
    comment character varying(1000),
    sub_contract_date date NOT NULL,
    active boolean NOT NULL DEFAULT true,
    version integer NOT NULL DEFAULT 0,
    CONSTRAINT sub_contracts_pkey PRIMARY KEY (id)
);
CREATE TABLE public.persons
(
    id integer NOT NULL auto_increment,
    last_name character varying(75),
    first_name character varying(75),
    patronymic character varying(75),
    birth_date date,
    birth_place character varying(200),
    doc_type_id integer,
    doc_series_number character varying(75),
    inn character varying(25),
    citizenship_id integer,
    gender_id integer,
    email character varying(75),
    phones text,
    version integer NOT NULL DEFAULT 0,
    active boolean NOT NULL DEFAULT true,
    CONSTRAINT persons_pkey PRIMARY KEY (id)
);
CREATE TABLE public.enumerations
(
    id integer NOT NULL auto_increment,
    ident character varying(75) NOT NULL,
    value character varying(75) NOT NULL,
    description_key character varying(100) NOT NULL,
    properties text,
    CONSTRAINT enumerations_pkey PRIMARY KEY (id)
);