create procedure add_discount(category_name text, tour_id text, discount  double precision)
language plpgsql
begin atomic
    insert into discounts (category_name, tour_id, discount)
        values (category_name, tour_id, discount)
end;

create procedure delete_discount(category_name text, tour_id text)
language plpgsql
begin atomic
    delete from discounts as d
        where d.category_name = category_name
            and d.tour_id = tour_id
end;

create procedure