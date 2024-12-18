create procedure register_user(id text, username text, password text)
language sql
begin atomic
    insert into users(id, username, password) values
    (id, username, password) returning *
end;

create procedure find_user_by_id (id text)
language sql
begin atomic
    select * from users as u where u.id = id
end;

create procedure delete_user_by_id (id text)
language sql
begin atomic
    delete from users as u where u.id = id
end;

create procedure update_user (id text, name text, surname text, email text, phone text)
language sql
begin atomic
    update users as u
        set u.name = name,
        set u.surname = surname,
        set u.email = email,
        set u.phone = phone
        where u.id = id
end;