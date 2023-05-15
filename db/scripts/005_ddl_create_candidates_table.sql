create table candidates (
    id serial primary key,
    name varchar not null,
    description varchar not null,
    created timestamp,
    file_id int references files(id)
)