CREATE OR REPLACE FUNCTION create_company(p_id text, p_name text, p_description text, p_created_at timestamp, p_email text)
RETURNS void AS
$$
BEGIN
    INSERT INTO companies(id, name, description, created_at, email)
    VALUES (p_id, p_name, p_description, p_created_at, p_email);
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION find_company_by_name_or_email(query text)
RETURNS TABLE(id text, name text, description text, created_at timestamp, email text) AS
$$
BEGIN
    RETURN QUERY
    SELECT id, name, description, created_at, email
    FROM companies
    WHERE name like '%' || query || '%' OR email like '%' || query || '%';
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_company_by_id(p_id text)
RETURNS TABLE(id text, name text, description text, created_at timestamp, email text) AS
$$
BEGIN
    RETURN QUERY
    SELECT id, name, description, created_at, email
    FROM companies
    WHERE id = p_id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION delete_company(p_id text)
RETURNS void AS
$$
BEGIN
    DELETE FROM companies WHERE id = p_id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION update_company_by_id(p_id text, p_name text, p_description text,  p_email text)
RETURNS void AS
$$
BEGIN
    UPDATE companies
    SET name = p_name, description = p_description, email = p_email
    WHERE id = p_id;
END;
$$ LANGUAGE plpgsql;
