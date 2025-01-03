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


CREATE OR REPLACE FUNCTION get_price_with_discount(p_tour_id text, p_user_id text)
RETURNS double precision AS
$$
BEGIN
    select t.price * (1 - d.discount / 100) as price_with_discount
        from tours as t
        join discounts as d on d.tour_id = t.id
        join users_user_categories as uuc on uuc.user_category_name = d.user_category_name
        where t.id = tour_id and uuc.user_id = p_user_id
            and d.discount = (select max(discount) from discounts as i_d join
                users_user_categories as i_uuc on i_d.user_category_name = i_uuc.user_category_name
                where i_d.tour_id = p_tour_id
                    and i_d.user_id = p_user_id);
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION get_discounts_by_tour(p_tour_id text)
RETURNS TABLE(tour_id text, category_name text, discount double precision, tour_title text) AS
$$
BEGIN
    RETURN QUERY
    SELECT d.tour_id, d.category_name, d.discount, t.title as tour_title
    FROM discounts as d
       join tours as t on t.id = d.tour_id
    WHERE d.tour_id = p_tour_id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_all_discounts()
RETURNS TABLE(tour_id text, category_name text, discount double precision, tour_title text) AS
$$
BEGIN
    RETURN QUERY
    SELECT d.tour_id, d.category_name, d.discount, t.title as tour_title
    FROM discounts as d
        join tours as t on t.id = d.tour_id;
END;
$$ LANGUAGE plpgsql;
