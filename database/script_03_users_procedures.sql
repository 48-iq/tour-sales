CREATE OR REPLACE FUNCTION get_user_by_id(user_id text)
RETURNS TABLE(id text, username text, password text, name text, surname text, email text, birth_date date, phone text) AS
$$
BEGIN
    RETURN QUERY
    SELECT u.id, u.username, u.password text, u.name, u.surname, u.email, u.birth_date, u.phone
    FROM users u
    WHERE u.id = user_id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_all_users()
RETURNS TABLE(id text, username text, password text, name text, surname text, email text, birth_date date, phone text) AS
$$
BEGIN
    RETURN QUERY
    SELECT u.id, u.username, u.password text, u.name, u.surname, u.email, u.birth_date, u.phone
    FROM users u;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION create_user(p_id text, p_username text, p_password text, p_name text, p_surname text, p_email text, p_birth_date date, p_phone text)
RETURNS void AS
$$
BEGIN
    INSERT INTO users(id, username, password, name, surname, email, birth_date, phone)
    VALUES (p_id, p_username, p_password, p_name, p_surname, p_email, p_birth_date, p_phone);
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION update_user_by_id(p_id text, p_name text, p_surname text, p_email text, p_birth_date date, p_phone text)
RETURNS void AS
$$
BEGIN
    UPDATE users
    SET name = p_name, surname = p_surname, email = p_email, birth_date = p_birth_date, phone = p_phone
    WHERE id = p_id;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION delete_user_by_id(p_id text)
RETURNS void AS
$$
BEGIN
    DELETE FROM users WHERE id = p_id;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION get_users_by_tour(tour_id text)
RETURNS TABLE(id text, username text, password text, name text, surname text, email text, birth_date date, phone text) AS
$$
BEGIN
    RETURN QUERY
    SELECT u.id, u.username, u.password text, u.name, u.surname, u.email, u.birth_date, u.phone
    FROM users u
    JOIN contracts c ON c.user_id = u.id
    WHERE c.tour_id = tour_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_user_by_username(p_username text)
RETURNS TABLE(id text, username text, password text, name text, surname text, email text, birth_date date, phone text) AS
$$
BEGIN
    RETURN QUERY
    SELECT u.id, u.username, u.password text, u.name, u.surname, u.email, u.birth_date, u.phone
    FROM users u
    WHERE u.username = p_username;
END;
$$ LANGUAGE plpgsql;