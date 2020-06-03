CREATE TABLE `tasks_list`
(
    `id`      BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `version` BIGINT(20)   NOT NULL,
    `name`    VARCHAR(512) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `task`
(
    `id`           BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `version`      BIGINT(20)   NOT NULL,
    `list_id`      BIGINT(20)   NOT NULL,
    `description`  LONGTEXT,
    `name`         VARCHAR(512) NOT NULL,
    `is_completed` BIT(1) DEFAULT FALSE,
    PRIMARY KEY (`id`),
    KEY `FK_dadsndwhvruixdsaer3r` (`list_id`),
    CONSTRAINT `FK_dadsndwhvruixdsaer3r` FOREIGN KEY (`list_id`) REFERENCES `tasks_list` (`id`)
);
