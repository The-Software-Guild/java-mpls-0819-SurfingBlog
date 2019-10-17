drop database if exists surfblogdb;
create database surfblogdb;

use surfblogdb;

create table `user`(
`id` int primary key auto_increment,
`username` varchar(30) not null unique,
`password` varchar(100) not null,
`enabled` boolean not null);

create table `role`(
`id` int primary key auto_increment,
`role` varchar(30) not null
);

create table `user_role`(
`user_id` int not null,
`role_id` int not null,
primary key(`user_id`,`role_id`),
foreign key (`user_id`) references `user`(`id`),
foreign key (`role_id`) references `role`(`id`));
    
create table home_news_link (
`id` int primary key auto_increment,
news_url varchar(300) not null, 
picture_url varchar(300) not null, 
isActive boolean not null);

create table break(
`id` int primary key auto_increment, 
`name` varchar(50) not null, 
`beachId` int not null, 
latitude decimal(9,6), 
longitude decimal(9,6),
foreign key fk_break_beach(beachId)
references beach(`id`)
);

create table beach (
`id`        int             primary key auto_increment,
`name`      varchar(30)     not null,
`zipcode`   int(5)          not null
);


create table comment (
`id` 			int 			primary key auto_increment,
`breakid`		int, 			
`beachid`		int				not null,
isbreakcomment 	boolean 		not null default false,
comment     	varchar(1000)   not null,

foreign key (`breakid`) references break(`id`),
foreign key (`beachid`) references beach(`id`)
);

-- create table break_comment(
-- `id` int primary key auto_increment,
-- userId int not null,

-- Foreign Key fk_break_comment_user(userId)
-- references `user`(`id`),

-- breakId int not null,

-- Foreign Key fk_break_comment_break(breakId)
-- references break(`id`),

-- `comment` varchar(1000) not null);

-- create table beach_comments (
-- `id`        int             primary key auto_increment,
-- userId      int             not null,
-- beachid     int             not null,
-- comment     varchar(1000)   not null,
-- foreign key (userId) references `user`(`id`),
-- foreign key (beachId) references beach(`id`)
-- );

insert into `user`(`id`,`username`,`password`,`enabled`)
    values(1,"admin", "$2a$10$Pwyf0jn0glXZ36Nvo0GBtuUdl0Y5OoXV7izp2/Mi6YGvx3YY4Zcmi", true),
        (2,"user","$2a$10$Pwyf0jn0glXZ36Nvo0GBtuUdl0Y5OoXV7izp2/Mi6YGvx3YY4Zcmi",true);

insert into `role`(`id`,`role`)
    values(1,"ROLE_ADMIN"), (2,"ROLE_USER");
    
insert into `user_role`(`user_id`,`role_id`)
    values(1,1),(1,2),(2,2);
    