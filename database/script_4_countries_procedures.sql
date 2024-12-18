create procedure add_country(name text)
language sql
begin atomic
    insert into countries(name) values (name)
end;

create procedure delete_country(name text)
language sql
begin atomic
    delete from countries as c
        where c.name = name
end;

create procedure get_all_countries()
language sql
begin atomic
    select * from countries
end;
