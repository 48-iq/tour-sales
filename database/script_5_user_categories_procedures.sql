CREATE OR REPLACE FUNCTION create_user_category(p_title text, p_description text)
RETURNS void AS
$$
BEGIN
    INSERT INTO user_categories(title, description) VALUES (p_title, p_description);
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION delete_user_category(p_title text)
RETURNS void AS
$$
BEGIN
    DELETE FROM user_categories WHERE title = p_title;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_all_user_categories()
RETURNS TABLE(title text, description text) AS
$$
BEGIN
    RETURN QUERY
    SELECT title, description FROM user_categories;
END;
$$ LANGUAGE plpgsql;
