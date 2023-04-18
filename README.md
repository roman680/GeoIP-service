# 🌍 GeoIP RESTful Web Service 🌍

This project is a RESTful GeoIP Web Service 🗺️ implemented using Spring Boot 🌱, Java 17 ☕, and Maven 🏗️. The service allows users to get geographic information of an IP address 🏙️ by querying a database (PostgreSQL or MySQL) 📁 that contains data imported from IP2Location Lite database 🗄️. The service provides information like city name, country code, country name, latitude, and longitude based on a given IP address.

## 📚 Table of Contents

1. ✨ [Features](#features)
2. 📋 [Requirements](#requirements)
3. 🔧 [Installation](#installation)
4. 🚀 [Usage](#usage)
5. 🧪 [Running Tests](#running-tests)

## ✨ Features

- RESTful API 🛠️ to fetch geolocation data based on IP addresses 🌐.
- Built with Spring Boot 🌱, Java 17 ☕, and Maven 🏗️.
- Data imported from IP2Location Lite database 🗄️.
- Supports MySQL databases 📁.
- Optimized database queries for fast response times ⚡.
- Unit tests and integration tests provided 🧪.

## 📋 Requirements

- Java 17 ☕
- Maven 🏗️
- MySQL 📁

## 🔧 Installation

1. Clone this repository using your preferred Git client (GitHub, GitLab, or Bitbucket) 🖥️.

```bash
git clone https:/git@github.com:roman680/GeoIP-service.git
```

2. Navigate to the project directory and install the required dependencies using Maven 🔨.

```bash
cd geoip-service
mvn clean install
```
3. Download IP2Location data [here](https://lite.ip2location.com/database/db5-ip-country-region-city-latitude-longitude).

4. Specify the path to the file in src/main/java/geoip/config/DataImportConfiguration.java:
```java
 public void onApplicationEvent(ContextRefreshedEvent event) {
        String csvFilePath = "PATH_TO_CSV_FILE";
        csvImporter.importCsv(csvFilePath);
    }
```

5. Import the provided CSV data into your chosen database (MySQL) using the provided script or code 📝. This can be found in the \`src/main/resources/import-data\` directory.

6. Configure the database connection in the `src/main/resources/application.properties` file. Update the following properties with your database credentials 🔑:

```
spring.datasource.url=YOUR_DB_URL
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

7. Run the SQL script located in the `src/main/resources/init_db.sql` directory to set up the required database schema 📚.

## 🚀 Usage

1. Run the Spring Boot application using the following command 🌱:

```bash
mvn spring-boot:run
```

2. Make a GET request to the `/geoip/{ip}` endpoint to fetch the geolocation data for a specific IP address 📥. For example:

```
GET http://localhost/geoip/8.8.8.8
```

The response will be in JSON format 📄:

```json
{
  "canonicalIPv4Representation": "8.8.8.8",
  "cityName": "Mountain View",
  "countryCode": "US",
  "countryName": "United States",
  "IPv4": "134744072",
  "latitude": "37.405992",
  "longitude": "-122.078515",
  "regionName": "California"
}
```

## 🧪 Running Tests

1. Run the provided tests
``` 
mvn test
```
