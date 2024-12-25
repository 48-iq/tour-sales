CREATE OR REPLACE FUNCTION create_country(p_name text)
RETURNS void AS
$$
BEGIN
    INSERT INTO countries(name) VALUES (p_name);
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_all_countries()
RETURNS TABLE(name text) AS
$$
BEGIN
    RETURN QUERY SELECT c.name FROM countries as c;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION delete_country(p_name text)
RETURNS void AS
$$
BEGIN
    DELETE FROM countries as c WHERE c.name = p_name;
END;
$$ LANGUAGE plpgsql;
