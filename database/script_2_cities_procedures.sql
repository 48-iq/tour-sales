create procedure select_city_by_name(name text)
language sql
begin
    select * from cities where name = text
end;

create procedure insert_city(name text)
language sql
begin
    insert into cities(name) values (name)
end;

create procedure delete_city(name text)
language sql
begin
    delete from cities where name = name
end;

create procedure select_all_cities()
language sql
begin
    select * from cities
end;

