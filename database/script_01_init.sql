create table users(
    id text not null primary key,
    username text not null unique,
    password text not null,
    name text,
    surname text,
    email text,
    birth_date date,
    phone text
);

create table roles(
    name text not null primary key
);

insert into roles(name)
    values ('ROLE_USER'), ('ROLE_ADMIN');

create table users_roles(
    role_name text not null references roles(name),
    user_id text not null references users(id),
    primary key (role_name, user_id)
);

create table companies(
    id text not null primary key,
    name text not null,
    description text,
    email text
);

create table tours (
    id text not null primary key,
    title text not null,
    description text,
    price double precision not null,
    start_at date not null,
    finish_at date not null,
    available_count integer not null,
    company_id text not null references companies(id)  on delete cascade
);

create table contracts(
    id text not null primary key,
    tour_id text not null references tours(id) on delete cascade,
    user_id text not null references users(id) on delete cascade,
    price double precision not null
);


create table user_categories (
    title text not null primary key,
    description text not null
);

create table users_user_categories(
    user_category_title text not null references user_categories(title)  on delete cascade,
    user_id text not null references users(id)  on delete cascade,
    primary key (user_category_title, user_id)
);

create table discounts (
    category_name text not null references user_categories(title) on delete cascade,
    tour_id text not null references tours(id) on delete cascade,
    discount double precision not null
);

create table countries (
    name text not null primary key
);

create table cities (
    name text not null primary key,
    country_name text not null references countries(name)
);

create table tours_cities(
    tour_id text not null references tours(id) on delete cascade,
    city_name text not null references cities(name) on delete cascade,
    primary key (tour_id, city_name)
);





