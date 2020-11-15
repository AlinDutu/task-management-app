create schema taskDB;
use taskDB;

CREATE TABLE `usr`
(
    `id`        bigint                 NOT NULL AUTO_INCREMENT,
    `age`       int                    NOT NULL,
    `email`     varchar(255) DEFAULT NULL,
    `firstName` varchar(255) DEFAULT NULL,
    `lastName`  varchar(255) DEFAULT NULL,
    `gender`    enum ('MALE','FEMALE') NOT NULL,
    `created`   datetime     DEFAULT NULL,
    `modified`  datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `task`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) DEFAULT NULL,
    `deadline`    datetime     DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `user_id`     bigint       DEFAULT NULL,
    `created`     datetime     DEFAULT NULL,
    `modified`    datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
);

alter table `task`
    add constraint foreign key (`user_id`) references `usr` (`id`);


# select u.id,
#        u.age,
#        u.created,
#        u.email,
#        u.firstName,
#        u.gender,
#        u.lastName,
#        u.modified,
# from usr u
# where u.lastName = ?
#   and u.age > ?
#   and u.firstName = ?;
