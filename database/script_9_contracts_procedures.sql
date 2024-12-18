CREATE OR REPLACE FUNCTION create_contract(p_id text, p_tour_id text, p_user_id text, p_was_paid boolean)
RETURNS void AS
$$
BEGIN
    INSERT INTO contracts(id, tour_id, user_id, was_paid)
    VALUES (p_id, p_tour_id, p_user_id, p_was_paid);
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION update_contract_by_id(p_id text, p_was_paid boolean)
RETURNS void AS
$$
BEGIN
    UPDATE contracts
    SET  was_paid = p_was_paid
    WHERE id = p_id;
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
RETURNS TABLE(id text, tour_id text, user_id text, was_paid boolean) AS
$$
BEGIN
    RETURN QUERY
    SELECT id, tour_id, user_id, was_paid
    FROM contracts
    WHERE id = p_id
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION get_contract_by_user(p_user_id text)
RETURNS TABLE(id text, tour_id text, user_id text, was_paid boolean) AS
$$
BEGIN
    RETURN QUERY
    SELECT id, tour_id, user_id, was_paid
    FROM contracts
    WHERE user_id = p_user_id
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_contract_by_tour(p_tour_id text)
RETURNS TABLE(id text, tour_id text, user_id text, was_paid boolean) AS
$$
BEGIN
    RETURN QUERY
    SELECT id, tour_id, user_id, was_paid
    FROM contracts
    WHERE tour_id = p_tour_id
END;
$$ LANGUAGE plpgsql;


