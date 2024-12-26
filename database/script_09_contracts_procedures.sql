CREATE OR REPLACE FUNCTION create_contract(p_id text, p_tour_id text, p_user_id text, p_price double precision)
RETURNS void AS
$$
BEGIN
    INSERT INTO contracts(id, tour_id, user_id, price)
    VALUES (p_id, p_tour_id, p_user_id, p_price);
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION delete_contract(p_id text)
RETURNS void AS
$$
BEGIN
    DELETE FROM contracts WHERE id = p_id;
END;
$$ LANGUAGE plpgsql;




CREATE OR REPLACE FUNCTION get_contract_by_id(p_id text)
RETURNS TABLE(id text, tour_id text, user_id text, price double precision) AS
$$
BEGIN
    RETURN QUERY
    SELECT c.id, c.tour_id, c.user_id, c.price
    FROM contracts as c
    WHERE c.id = p_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_all_contracts()
RETURNS TABLE(id text, tour_id text, user_id text, price double precision) AS
$$
BEGIN
    RETURN QUERY
    SELECT c.id, c.tour_id, c.user_id, c.price
    FROM contracts as c;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION get_contract_by_user(p_user_id text)
RETURNS TABLE(id text, tour_id text, user_id text, price double precision) AS
$$
BEGIN
    RETURN QUERY
    SELECT c.id, c.tour_id, c.user_id, c.price
    FROM contracts as c
    WHERE c.user_id = p_user_id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_contract_by_tour(p_tour_id text)
RETURNS TABLE(id text, tour_id text, user_id text, price double precision) AS
$$
BEGIN
    RETURN QUERY
    SELECT c.id, c.tour_id, c.user_id, c.price
    FROM contracts as c
    WHERE c.tour_id = p_tour_id;
END;
$$ LANGUAGE plpgsql;


