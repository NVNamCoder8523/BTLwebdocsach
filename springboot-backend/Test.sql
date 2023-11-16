create table book (
        id integer not null auto_increment,
        release_date date,
        upload_at datetime(6) not null,
        cover_image varchar(200) not null,
        author varchar(255),
        description varchar(255),
        title varchar(255) not null,
        primary key (id),
        unique (cover_image),
        unique (title)
    ) engine=InnoDB;

create table book_category (
        book_id integer not null,
        category_id integer not null,
        primary key (book_id, category_id),
        foreign key (book_id) references book (id),
        foreign key (category_id) references category (id)
    ) engine=InnoDB;

create table category (
        id integer not null auto_increment,
        description varchar(255),
        name varchar(255) not null,
        primary key (id),
        unique (name)
    ) engine=InnoDB;

create table chapter (
        book_id integer not null,
        id integer not null auto_increment,
        upload_at datetime(6),
        file_name varchar(255) not null,
        title varchar(255),
        primary key (id),
        unique (file_name),
        foreign key (book_id) references book (id)
    ) engine=InnoDB;

create table child_comment (
        child_comment_id integer,
        id integer not null,
        is_edited bit,
        parent_comment_id integer not null,
        user_id integer not null,
        comment_at datetime(6),
        content tinytext,
        primary key (id),
        foreign key (child_comment_id) references child_comment (id),
        foreign key (parent_comment_id) references comment (id),
        foreign key (user_id) references user (id)
    ) engine=InnoDB;

create table comment (
        book_id integer not null,
        chapter_id integer not null,
        id integer not null,
        is_edited bit,
        user_id integer not null,
        comment_at datetime(6),
        content tinytext,
        primary key (id),
        foreign key (book_id) references book (id),
        foreign key (chapter_id) references chapter (id),
        foreign key (user_id) references user (id)
    ) engine=InnoDB;

create table like_comment (
        comment_id integer not null,
        user_id integer not null,
        like_at datetime(6),
        primary key (comment_id, user_id),
        foreign key (comment_id) references comment (id),
        foreign key (user_id) references user (id)
    ) engine=InnoDB;

create table rating (
        book_id integer not null,
        id integer not null auto_increment,
        rate_star tinyint,
        user_id integer not null,
        primary key (id),
        foreign key (book_id) references book (id),
        foreign key (user_id) references user (id)
    ) engine=InnoDB;

create table reading (
        chapter_id integer,
        id integer not null auto_increment,
        user_id integer not null,
        read_at datetime(6),
        book_name varchar(255),
        primary key (id),
        foreign key (chapter_id) references chapter (id),
        foreign key (user_id) references user (id)
    ) engine=InnoDB;

create table user (
        id integer not null auto_increment,
        role tinyint,
        avatar varchar(255) not null,
        email varchar(255) not null,
        full_name varchar(255),
        password varchar(255) not null,
        user_name varchar(255) not null,
        primary key (id),
        unique (email),
        unique (user_name)
    ) engine=InnoDB;
