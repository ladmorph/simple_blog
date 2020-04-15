create sequence hibernate_sequence start 1 increment 1;


create table authors (
    id int8 not null,
    activation_code varchar(255),
    email varchar(255) not null,
    password varchar(255),
    reg_date varchar(255),
    username varchar(255) not null,
    verified boolean,
    primary key (id)
);


create table posts (
    id int8 not null,
    creation_date varchar(255),
    description varchar(255),
    title varchar(255),
    author_id int8,
    primary key (id)
);


create table user_role (
    author_id int8 not null,
    roles varchar(255)
);

alter table if exists authors add constraint authors_email_unique unique (email);
alter table if exists authors add constraint authors_username_unique unique (username);
alter table if exists posts add constraint posts_author_id foreign key (author_id) references authors;
alter table if exists user_role add constraint user_role_author_id foreign key (author_id) references authors;