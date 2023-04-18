CREATE DATABASE `geoip` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE geoip.'iplocation' (
                              `ip_address_from` BIGINT NOT NULL,
                              `ip_address_to` BIGINT NOT NULL,
                              `country_code` VARCHAR(255) NOT NULL,
                              `country_name` VARCHAR(255) NOT NULL,
                              `region_name` VARCHAR(255) NOT NULL,
                              `city_name` VARCHAR(225) NOT NULL,
                              `latitude` DOUBLE NOT NULL,
                              `longitude` DOUBLE NOT NULL,
                              PRIMARY KEY (`ip_address_from`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

