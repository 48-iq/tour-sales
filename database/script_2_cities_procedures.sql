create procedure add_city(name text)
language sql
begin atomic
    insert into cities(name) values (name)
end;

create procedure delete_city(name text)
language sql
begin atomic
    delete from cities as c
        where c.name = name
end;

create procedure get_all_cities()
language sql
begin atomic
    select * from cities
end;

