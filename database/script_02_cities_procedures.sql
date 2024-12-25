CREATE OR REPLACE FUNCTION create_city(p_name text, p_country_name text)
RETURNS void AS
$$
BEGIN
    INSERT INTO cities(name, country_name) VALUES (p_name, p_country_name);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_all_cities()
RETURNS TABLE(name text, country_name text) AS
$$
BEGIN
    RETURN QUERY SELECT c.name, c.country_name FROM cities as c;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_city(p_name text)
RETURNS void AS
$$
BEGIN
    DELETE FROM cities WHERE name = p_name;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_cities_by_tour(p_tour_id text)
RETURNS TABLE(name text, country_name text) AS
$$
BEGIN
    RETURN QUERY SELECT c.name, c.country_name FROM cities c
        join tours_cities as tc on tc.city_name = c.name
        where tc.tour_id = p_tour_id;
END;
$$ LANGUAGE plpgsql;