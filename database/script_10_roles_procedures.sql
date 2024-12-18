CREATE OR REPLACE FUNCTION get_roles_by_user_id(p_id text)
RETURNS TABLE(name text) AS
$$
BEGIN
    RETURN QUERY
    SELECT role_name AS name FROM
    users_roles
        where user_id = p_id
END;
$$ LANGUAGE plpgsql;