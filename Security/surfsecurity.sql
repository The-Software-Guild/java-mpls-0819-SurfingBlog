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

create table Beaches (
`id` 		int 			primary key auto_increment,
`name` 		varchar(30)		not null,
`zipcode` 	int(5) 			not null,
`breakid` 	int 			not null, 

foreign key (`breakid`) references Breaks(`id`)

);

create table BeachComments (
`id`		int 			primary key auto_increment,
userId		int 			not null,
beachid		int 			not null,
comment		varchar(1000)	not null,

foreign key (userId) references `user`(`id`),
foreign key (beachId) references Beaches(`id`)

);

create table Breaks (
`id`		int 			primary key
);

insert into `user`(`id`,`username`,`password`,`enabled`)
    values(1,"admin", "$2a$10$Pwyf0jn0glXZ36Nvo0GBtuUdl0Y5OoXV7izp2/Mi6YGvx3YY4Zcmi", true),
        (2,"user","$2a$10$Pwyf0jn0glXZ36Nvo0GBtuUdl0Y5OoXV7izp2/Mi6YGvx3YY4Zcmi",true);

insert into `role`(`id`,`role`)
    values(1,"ROLE_ADMIN"), (2,"ROLE_USER");
    
insert into `user_role`(`user_id`,`role_id`)
    values(1,1),(1,2),(2,2);