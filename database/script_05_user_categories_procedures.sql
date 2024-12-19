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



CREATE OR REPLACE FUNCTION get_categories_by_user_id(p_user_id text)
RETURNS TABLE(title text, description text) AS
$$
BEGIN
    RETURN QUERY
    SELECT uc.title, uc.description FROM user_categories as uc
        join user_user_categories as uuc
            on uc.title = uuc.user_category_title
        where uuc.user_id = p_user_id;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION add_user_to_category(p_user_id text, p_user_category_name text)
RETURNS void AS
$$
BEGIN
    insert into user_user_categories(user_id, user_category_name)
        values (p_user_id, p_user_category_name);
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION remove_user_from_category(p_user_id text, p_user_category_name text)
RETURNS void AS
$$
BEGIN
    delete from user_user_categories as uuc
        where uuc.user_id = p_user_id and
            uuc.user_category_name = p_user_category_name;
END;
$$ LANGUAGE plpgsql;