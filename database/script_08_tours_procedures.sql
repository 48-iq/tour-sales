CREATE OR REPLACE FUNCTION create_tour(p_id text, p_title text, p_description text, p_price double precision, p_start_at date, p_finish_at date, p_available_count integer, p_company_id text)
RETURNS void AS
$$
BEGIN
    INSERT INTO tours(id, title, description, price, start_at, finish_at, available_count, company_id)
    VALUES (p_id, p_title, p_description, p_price, p_start_at, p_finish_at, p_available_count, p_company_id);
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION add_city_to_tour(p_city_name text, p_tour_id text)
RETURNS void AS
$$
BEGIN
    insert into tours_cities(city_name, tour_id) values
    (p_city_name, p_tour_id);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION remove_city_from_tour(p_city_name text, p_tour_id text)
RETURNS void AS
$$
BEGIN
    delete from tours_cities as tc
        where tc.tour_id = p_tour_id and
            tc.city_name = p_city_name;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_all_tours()
RETURNS TABLE(id text, title text, description text, price double precision, start_at date, finish_at date, available_count integer, company_id text) AS
$$
BEGIN
    RETURN QUERY
    SELECT t.id, t.title, t.description, t.price, t.start_at, t.finish_at, t.available_count, t.company_id
    FROM tours t;

END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_tours_by_city(city_name text)
RETURNS TABLE(id text, title text, description text, price double precision, start_at date, finish_at date, available_count integer, company_id text) AS
$$
BEGIN
    RETURN QUERY
    SELECT t.id, t.title, t.description, t.price, t.start_at, t.finish_at, t.available_count, t.company_id
    FROM tours t
    JOIN tours_cities tc ON tc.tour_id = t.id
    JOIN cities c ON c.name = tc.city_name
    WHERE c.name = city_name;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_tours_by_title(p_title text)
RETURNS TABLE(id text, title text, description text, price double precision, start_at date, finish_at date, available_count integer, company_id text) AS
$$
BEGIN
    RETURN QUERY
    SELECT t.id, t.title, t.description, t.price, t.start_at, t.finish_at, t.available_count, t.company_id
    FROM tours as t
    WHERE t.title like '%' || p_title || '%';
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_tour_by_id(p_id text)
RETURNS TABLE(id text, title text, description text, price double precision, start_at date, finish_at date, available_count integer, company_id text) AS
$$
BEGIN
    RETURN QUERY
    SELECT t.id, t.title, t.description, t.price, t.start_at, t.finish_at, t.available_count, t.company_id
    FROM tours as t
    WHERE t.id = p_id;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION minus_tour_available_count(p_id text)
RETURNS void AS
$$
BEGIN
    update tours
    set available_count = available_count - 1
    where id = p_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION update_tour_by_id(p_id text,
        p_title text,
        p_description text,
        p_price double precision,
        p_start_at date,
        p_finish_at date,
        p_available_count integer)
RETURNS void AS
$$
BEGIN
    UPDATE tours
    SET title = p_title,
        description = p_description,
        price = p_price,
        start_at = p_start_at,
        finish_at = p_finish_at,
        available_count = p_available_count
    WHERE id = p_id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_tours_by_title_and_company_id(p_title text, p_company_id text)
RETURNS TABLE(id text, title text, description text, price double precision, start_at date, finish_at date, available_count integer, company_id text) AS
$$
BEGIN
    RETURN QUERY
    SELECT t.id, t.title, t.description, t.price, t.start_at, t.finish_at, t.available_count, t.company_id
    FROM tours as t
    where t.title like '%' || p_title || '%' and t.company_id = p_company_id;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION delete_tour_by_id(p_id text)
RETURNS void AS
$$
BEGIN
    DELETE FROM tours as t WHERE t.id = p_id;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION get_tours_by_company(p_company_id text)
RETURNS TABLE(id text, title text, description text, price double precision, start_at date, finish_at date, available_count integer, company_id text) AS
$$
BEGIN
    RETURN QUERY
    SELECT t.id, t.title, t.description, t.price, t.start_at, t.finish_at, t.available_count, t.company_id
    FROM tours as t
    WHERE t.company_id = p_company_id;
END;
$$ LANGUAGE plpgsql;