CREATE TABLE `transports` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `kind` varchar(45) NOT NULL,
                              `name` varchar(45) DEFAULT NULL,
                              `capacity` int DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8