CREATE OR REPLACE FUNCTION get_roles_by_user_id(p_id text)
RETURNS TABLE(name text) AS
$$
BEGIN
    RETURN QUERY
    SELECT role_name AS name FROM
    users_roles
        where user_id = p_id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION set_role_admin_to_user(p_id text)
RETURNS void AS
$$
BEGIN
    INSERT INTO users_roles(role_name, user_id)
    VALUES ('ROLE_ADMIN', p_id);
END;
$$ LANGUAGE plpgsql;