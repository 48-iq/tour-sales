create procedure add_user_category(title text, description text)
language sql
begin atomic
    insert into user_categories(title, description)
        values (title, description)
end;

create procedure delete_user_category(title text)
language sql
begin atomic
    delete from user_categories as uc
        where uc.title = title
end;

create procedure get_all_user_categories()
language plpgsql
begin atomic
    select * from user_categories
end;

create procedure add_user_to_category(user_id text, user_category_title text)
language plpgsql
begin atomic
    insert into users_user_categories(user_category_title, user_id)
        values (user_id, user_category_title)
end;

create procedure delete_user_from_category(user_id text, user_category_title text)
language plpgsql
begin atomic
    delete from users_user_categories as uuc
        where uuc.user_id = user_id,
            and uuc.user_category_title = user_category_title
end;
