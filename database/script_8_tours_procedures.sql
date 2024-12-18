CREATE OR REPLACE FUNCTION create_tour(p_id text, p_title text, p_description text, p_created_at timestamp, p_price double precision, p_start_at timestamp, p_finish_at timestamp, p_available_count integer, p_company_id text)
RETURNS void AS
$$
BEGIN
    INSERT INTO tours(id, title, description, created_at, price, start_at, finish_at, available_count, company_id)
    VALUES (p_id, p_title, p_description, p_created_at, p_price, p_start_at, p_finish_at, p_available_count, p_company_id);
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_tours_by_city(city_name text)
RETURNS TABLE(id text, title text, description text, created_at timestamp, price double precision, start_at timestamp, finish_at timestamp, available_count integer, company_id text) AS
$$
BEGIN
    RETURN QUERY
    SELECT t.id, t.title, t.description, t.created_at, t.price, t.start_at, t.finish_at, t.available_count
    FROM tours t
    JOIN tours_cities tc ON tc.tour_id = t.id
    JOIN cities c ON c.name = tc.city_name
    WHERE c.name = city_name;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_tours_by_title(p_title text)
RETURNS TABLE(id text, title text, description text, created_at timestamp, price double precision, start_at timestamp, finish_at timestamp, available_count integer, company_id text) AS
$$
BEGIN
    RETURN QUERY
    SELECT id, title, description, created_at, price, start_at, finish_at, available_count
    FROM tours
    WHERE title like '%' || p_title || '%';
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_tour_by_id(p_id text)
RETURNS TABLE(id text, title text, description text, created_at timestamp, price double precision, start_at timestamp, finish_at timestamp, available_count integer, company_id text) AS
$$
BEGIN
    RETURN QUERY
    SELECT id, title, description, created_at, price, start_at, finish_at, available_count
    FROM tours
    WHERE id = p_id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION update_tour_by_id(p_id text, p_title text, p_description text, p_created_at timestamp, p_price double precision, p_start_at timestamp, p_finish_at timestamp, p_available_count integer)
RETURNS void AS
$$
BEGIN
    UPDATE tours
    SET title = p_title, description = p_description, created_at = p_created_at, price = p_price, start_at = p_start_at, finish_at = p_finish_at, available_count = p_available_count
    WHERE id = p_id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_tours_by_title_and_city(p_title text, p_city_name text)
RETURNS TABLE(id text, title text, description text, created_at timestamp, price double precision, start_at timestamp, finish_at timestamp, available_count integer, company_id text) AS
$$
BEGIN
    RETURN QUERY
    SELECT t.id, t.title, t.description, t.created_at, t.price, t.start_at, t.finish_at, t.available_count
    FROM tours t
    JOIN tours_cities tc ON tc.tour_id = t.id
    JOIN cities c ON c.name = tc.city_name
    WHERE t.title LIKE '%' || p_title || '%' AND c.name = p_city_name;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION delete_tour_by_id(p_id text)
RETURNS void AS
$$
BEGIN
    DELETE FROM tours WHERE id = p_id;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION get_tours_by_company(p_company_id text)
RETURNS TABLE(id text, title text, description text, created_at timestamp, price double precision, start_at timestamp, finish_at timestamp, available_count integer, company_id text) AS
$$
BEGIN
    RETURN QUERY
    SELECT id, title, description, created_at, price, start_at, finish_at, available_count
    FROM tours
    WHERE company_id = p_company_id;
END;
$$ LANGUAGE plpgsql;