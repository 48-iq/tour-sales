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
    values ('ROLE_USER'), ('ROLE_ADMIN')

create table roles_users(
    role_name text not null references roles(name),
    user_id text not null references users(id),
    primary key (role_name, user_id)
);

create table companies(
    id text not null primary key,
    name text not null,
    description text,
    created_at timestamp,
    email text
);

create table tours (
    id text not null primary key,
    title text not null,
    description text,
    created_at timestamp not null,
    price double precision not null,
    start_at timestamp not null,
    finish_at timestamp not null,
    available_count integer not null,
    company_id text not null references companies(id)
);

create table contracts(
    id text not null primary key,
    tour_id text not null references tours(id),
    user_id text not null references users(id),
    was_paid boolean not null
);


create table user_categories (
    title text not null primary key,
    description text not null primary key
);

create table users_user_categories(
    user_category_title text not null references user_categories(title),
    user_id text not null references users(id)
    primary key (user_category_title, user_id)
);

create table discounts (
    category_name text not null,
    tour_id text not null references tours(id),
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
    tour_id not null references tours(id),
    city_name not null references cities(name),
    arrive_at not null timestamp,
    depart_at not null timestamp
);





