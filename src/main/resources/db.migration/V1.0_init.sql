
DROP TABLE IF EXISTS `user_info`;
-- nuomidb.user_info definition
CREATE TABLE `user_info`
(
    `id`       int(11)                                                       NOT NULL AUTO_INCREMENT,
    `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `password` varchar(100) DEFAULT NULL,
    `age`      varchar(100) DEFAULT NULL,
    `phone`    varchar(100) DEFAULT NULL,
    `role`     varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_info_un` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;