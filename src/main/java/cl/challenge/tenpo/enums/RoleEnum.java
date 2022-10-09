package cl.challenge.tenpo.enums;

public enum RoleEnum {
    ADMINISTRATOR, USER;
}

/*
TODO: create sql scripts
INSERT INTO public.roles (id, role_name) VALUES (DEFAULT, 'ADMINISTRATOR'::varchar(255));
INSERT INTO public.roles (id, role_name) VALUES (DEFAULT, 'USER'::varchar(255));
commit;
 */