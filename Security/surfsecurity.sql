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
news_link_text varchar(100) not null, 
picture_url varchar(300) not null, 
isActive boolean not null);

create table beach (
`id`        int             primary key auto_increment,
`name`      varchar(30)     not null,
`zipcode`   int(5)          not null
);

create table break(
`id` int primary key auto_increment, 
`name` varchar(50) not null, 
`beachId` int not null, 
latitude decimal(9,6), 
longitude decimal(9,6),
foreign key fk_break_beach(beachId)
references beach(`id`)
);




create table comment (
`id` 			int 			primary key auto_increment,
userId			int             not null,
`breakid`		int, 			
`beachid`		int				not null,
isbreakcomment 	boolean 		not null default false,
comment     	varchar(1000)   not null,

foreign key(userId) references `user`(`id`),
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
    
insert into home_news_link (id, news_url, news_link_text, picture_url, isActive)
values
('1','https://www.surfline.com/surf-news/2019-red-bull-queen-bay-forecast-outlook/66513','Disappointing surf forecast for Waimea Bay','https://d14fqx6aetz9ka.cloudfront.net/wp-content/uploads/2019/10/15172128/Screen-Shot-2019-10-15-at-7.56.53-PM.png',TRUE),
('2','https://www.surfertoday.com/surfing/aritz-aranburu-inducted-into-the-spanish-surfing-walk-of-fame','Congrats to Aritz Aranburu -- the newest member of the Spanish Surfing Walk of Fame','https://www.surfertoday.com/images/stories/aritzaranburu8.jpg',TRUE),
('3','https://www.surfertoday.com/surfing/jeremy-flores-wins-2019-quiksilver-pro-france','Flores and Moore win the 2019 Quiksilver and Roxy Pro France','https://www.surfertoday.com/images/stories/jeremyflores18.jpg',TRUE),
('4','https://www.surfertoday.com/surfing/kathmandu-buys-rip-curl','Ripcurl purchased by Kathmandu','https://www.surfertoday.com/images/stories/mickfanning67.jpg',FALSE);

insert into beach (id, `name`, zipcode)
values
('301','Beach A','96701'),
('302','Beach B','96712'),
('303','Beach C','96707');

insert into break (id, `name`, beachId, latitude, longitude)
values
('801','Break A - Break 1','301','20.716179','-158.214676'),
('802','Break A - Break 2','301','20.716185','-158.214684'),
('803','Break A - Break 3','301','20.716634','-158.214965'),
('804','Break B - Break 1','302','20.715711','-158.211234'),
('805','Break B - Break 2','302','20.725869','-158.214674'),
('806','Break B - Break 3','302','20.735678','-158.214583'),
('807','Break C - Break 1','303','20.723456','-158.214637'),
('808','Break C - Break 2','303','20.725689','-158.214351'),
('809','Break C - Break 3','303','20.725657','-158.214225');

insert into comment(id, userId, breakid, beachid, isbreakcomment, comment)
values
('1001','2','801','301',TRUE, 'perspiciatis unde omnis iste natus error sit volupt'),
('1002','2',NULL,'301',FALSE, 'dolor sit amet, consectetu'),
('1003','2','803','301',TRUE, 'commodi consequatur? Quis autem vel eum iure'),
('1004','2','804','302',TRUE, 'tempora incidunt ut labore'),
('1005','2','805','302',TRUE, 'Duis aute irure dolor in'),
('1006','2','806','302',TRUE, 'exercitation ullamco laboris'),
('1007','2','807','303',TRUE, 'minim veniam, quis nostrud'),
('1008','2',NULL,'303',FALSE, 'aspernatur aut odit aut fugit, sed quia consequuntur'),
('1009','2','809','303',TRUE, 'consequatur, vel illum qui'),
('1010','2','801','301',TRUE, 'Ut enim ad minim veniam'),
('1011','2','802','301',TRUE, 'quis nostrud exercitation ullamco laboris nisi ut aliquip'),
('1012','2','803','301',TRUE, 'ea commodo consequat. Duis aute irure dolor in'),
('1013','2','804','302',TRUE, 'vel illum qui'),
('1014','2','805','302',TRUE, 'aute irure dolor in'),
('1015','2','806','302',TRUE, 'Quis autem vel eum iure'),
('1016','2',NULL,'303',FALSE, 'unde omnis iste natus error sit volupt'),
('1017','2','808','303',TRUE, 'laboris nisi ut aliquip'),
('1018','2','809','303',TRUE, 'quis nostrud exercitation ullamco laboris nisi'),
('1019','2','801','301',TRUE, 'tempora incidunt'),
('1020','2',NULL,'301',FALSE, 'quis nostrud ut aliquip');






    