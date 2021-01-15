create table `teacher`(
    `id`   int(10) primary key not null,
    `name` varchar(20) default null
) engine=innodb default charset=utf8;

insert into `teacher` values (1,'jack');

create table `student`(
    `id`   int(10) primary key not null,
    `name` varchar(20) default null,
    `tid`   int(10) not null,
    key `fktid` (`tid`),
    constraint `fktid` foreign key (`tid`) references `teacher` (`id`)
) engine=innodb default charset=utf8;

insert into `student` values (1,'tom',1);
insert into `student` values (2,'mike',1);
insert into `student` values (3,'tony',1);
insert into `student` values (4,'rose',1);