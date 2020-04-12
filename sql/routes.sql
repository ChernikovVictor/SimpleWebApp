CREATE TABLE `routes` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `departure_id` int NOT NULL,
                          `destination_id` int NOT NULL,
                          `departure_time` varchar(45) DEFAULT NULL,
                          `arrival_time` varchar(45) DEFAULT NULL,
                          `transport_id` int NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `departure_id_idx` (`departure_id`),
                          KEY `destination_id_idx` (`destination_id`),
                          KEY `transport_id_idx` (`transport_id`),
                          CONSTRAINT `departure_id` FOREIGN KEY (`departure_id`) REFERENCES `cities` (`id`),
                          CONSTRAINT `destination_id` FOREIGN KEY (`destination_id`) REFERENCES `cities` (`id`),
                          CONSTRAINT `transport_id` FOREIGN KEY (`transport_id`) REFERENCES `transports` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8