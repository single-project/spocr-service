INSERT INTO public.security_user (id, login, passwd, is_active, tabnum)
VALUES(1, 'admin', '96e79218965eb72c92a549dd5a330112', true, 3);
INSERT INTO public.system_roles (name, active)
VALUES('ROLE_ADMIN', true);
INSERT INTO public.system_roles (name, active)
VALUES('ROLE_MANAGER', true);
INSERT INTO public.system_roles (name, active)
VALUES('ROLE_READER', true);
INSERT INTO public.system_rules (name, active)
VALUES('CREATE_PRIVILEGE', true);
INSERT INTO public.system_rules (name, active)
VALUES('UPDATE_PRIVILEGE', true);
INSERT INTO public.system_rules (name, active)
VALUES('READ_PRIVILEGE', true);



