create table users (
    id int AUTO_INCREMENT PRIMARY KEY,
    first_name nvarchar(128),
    last_name nvarchar(128)
);

insert into users values (1, 'foo', 'bar');
insert into users values (2, 'hello', 'world');

