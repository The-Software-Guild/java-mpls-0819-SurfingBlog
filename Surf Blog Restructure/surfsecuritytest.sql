drop database if exists surfblogtestdb;
create database surfblogtestdb;

use surfblogtestdb;

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
isActive boolean not null default false);

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
blog varchar(5000) not null,
foreign key fk_break_beach(beachId)
references beach(`id`)
);

-- create table comment (
-- `id` 			int 			primary key auto_increment,
-- userId			int             not null,
-- `breakid`		int, 			
-- `beachid`		int				not null,
-- isbreakcomment 	boolean 		not null default false,
-- comment     	varchar(1000)   not null,

-- foreign key(userId) references `user`(`id`),
-- foreign key (`breakid`) references break(`id`),
-- foreign key (`beachid`) references beach(`id`)
-- );

create table break_comment(
`id` int primary key auto_increment,
userId int not null,

Foreign Key fk_break_comment_user(userId)
references `user`(`id`),

breakId int not null,

Foreign Key fk_break_comment_break(breakId)
references break(`id`),

`comment` varchar(1000) not null
);

create table beach_comment (
`id` int primary key auto_increment,
userId int not null,

Foreign Key fk_beach_comment_user(userId)
references `user`(`id`),

beachId int not null,

Foreign Key fk_beach_comment_beach(beachId)
references beach(`id`),

comment varchar(1000) not null
);

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

insert into break (id, `name`, beachId, latitude, longitude, blog)
values
('801','Break A - Break 1','301','20.716179','-158.214676', 'However venture pursuit he am mr cordial. Forming musical am hearing studied be luckily. Ourselves for determine attending how led gentleman sincerity. Valley afford uneasy joy she thrown though bed set. In me forming general prudent on country carried. Behaved an or suppose justice. Seemed whence how son rather easily and change missed. Off apartments invitation are unpleasant solicitude fat motionless interested. Hardly suffer wisdom wishes valley as an. As friendship advantages resolution it alteration stimulated he or increasing. At ourselves direction believing do he departure. Celebrated her had sentiments understood are projection set. Possession ye no mr unaffected remarkably at. Wrote house in never fruit up. Pasture imagine my garrets an he. However distant she request behaved see nothing. Talking settled at pleased an of me brother weather. It sportsman earnestly ye preserved an on. Moment led family sooner cannot her window pulled any. Or raillery if improved landlord to speaking hastened differed he. Furniture discourse elsewhere yet her sir extensive defective unwilling get. Why resolution one motionless you him thoroughly. Noise is round to in it quick timed doors. Written address greatly get attacks inhabit pursuit our but. Lasted hunted enough an up seeing in lively letter. Had judgment out opinions property the supplied. Up am intention on dependent questions oh elsewhere september. No betrayed pleasure possible jointure we in throwing. And can event rapid any shall woman green. Hope they dear who its bred. Smiling nothing affixed he carried it clothes calling he no. Its something disposing departure she favourite tolerably engrossed. Truth short folly court why she their balls. Excellence put unaffected reasonable mrs introduced conviction she. Nay particular delightful but unpleasant for uncommonly who. '),
('802','Break A - Break 2','301','20.716185','-158.214684', 'Pasture he invited mr company shyness. Domestic replying she resolved him for did. Rather in lasted no within no.'),
('803','Break A - Break 3','301','20.716634','-158.214965', 'Effect if in up no depend seemed. Ecstatic elegance gay but disposed. We me rent been part what.'),
('804','Break B - Break 1','302','20.715711','-158.211234', 'In by an appetite no humoured returned informed. Possession so comparison inquietude he he conviction no decisively.'),
('805','Break B - Break 2','302','20.725869','-158.214674', 'Boy favourable day can introduced sentiments entreaties.'),
('806','Break B - Break 3','302','20.735678','-158.214583', 'Do play they miss give so up. Words to up style of since world. We leaf to snug on no need.'),
('807','Break C - Break 1','303','20.723456','-158.214637', 'Moments its musical age explain. But extremity sex now education concluded earnestly her continual.'),
('808','Break C - Break 2','303','20.725689','-158.214351', 'He unaffected sympathize discovered at no am conviction principles.'),
('809','Break C - Break 3','303','20.725657','-158.214225', 'In on announcing if of comparison pianoforte projection. Maids hoped gay yet bed asked blind dried point.');

insert into break_comment(id, userId, breakid, `comment`)
values
('1001','2','801','perspiciatis unde omnis iste natus error sit volupt'),
('1002','2','802','dolor sit amet, consectetu'),
('1003','2','803', 'commodi consequatur? Quis autem vel eum iure'),
('1004','2','804', 'tempora incidunt ut labore'),
('1005','2','805', 'Duis aute irure dolor in'),
('1006','2','806', 'exercitation ullamco laboris'),
('1007','2','807', 'minim veniam, quis nostrud'),
('1008','2','808', 'aspernatur aut odit aut fugit, sed quia consequuntur'),
('1009','2','809', 'consequatur, vel illum qui'),
('1010','2','801', 'Ut enim ad minim veniam'),
('1011','2','802', 'quis nostrud exercitation ullamco laboris nisi ut aliquip');


insert into beach_comment(id, userId, beachId, `comment`)
values
('1012','2','301', 'ea commodo consequat. Duis aute irure dolor in'),
('1013','2','302', 'vel illum qui'),
('1014','2','302','aute irure dolor in'),
('1015','2','302','Quis autem vel eum iure'),
('1016','2','303','unde omnis iste natus error sit volupt'),
('1017','2','303','laboris nisi ut aliquip'),
('1018','2','303','quis nostrud exercitation ullamco laboris nisi'),
('1019','2','301','tempora incidunt'),
('1020','2','301','quis nostrud ut aliquip');
