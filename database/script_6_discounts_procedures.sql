CREATE OR REPLACE FUNCTION create_discount(p_category_name text, p_tour_id text, p_discount double precision)
RETURNS void AS
$$
BEGIN
    INSERT INTO discounts(category_name, tour_id, discount)
    VALUES (p_category_name, p_tour_id, p_discount);
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION delete_discount(p_category_name text, p_tour_id text)
RETURNS void AS
$$
BEGIN
    DELETE FROM discounts WHERE category_name = p_category_name AND tour_id = p_tour_id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_discounts_by_category(p_category_name text)
RETURNS TABLE(tour_id text, discount double precision) AS
$$
BEGIN
    RETURN QUERY
    SELECT tour_id, discount
    FROM discounts
    WHERE category_name = p_category_name;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_discounts_by_tour(p_tour_id text)
RETURNS TABLE(category_name text, discount double precision) AS
$$
BEGIN
    RETURN QUERY
    SELECT category_name, discount
    FROM discounts
    WHERE tour_id = p_tour_id;
END;
$$ LANGUAGE plpgsql;
