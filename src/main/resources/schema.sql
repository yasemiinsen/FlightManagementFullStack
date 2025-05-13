DROP TABLE IF EXISTS flights;
DROP TABLE IF EXISTS airlines;
DROP TABLE IF EXISTS airports;

CREATE TABLE airports (
                          airport_id INT AUTO_INCREMENT PRIMARY KEY,
                          airport_name VARCHAR(100) NOT NULL,
                          city VARCHAR(100),
                          country VARCHAR(100)
);

CREATE TABLE airlines (
                          airline_id INT AUTO_INCREMENT PRIMARY KEY,
                          airline_code VARCHAR(10) NOT NULL UNIQUE,
                          airline_name VARCHAR(100) NOT NULL,
                          country_of_origin VARCHAR(100)
);

CREATE TABLE flights (
                         flight_id INT AUTO_INCREMENT PRIMARY KEY,
                         flight_number VARCHAR(6) NOT NULL,
                         flight_date_time TIMESTAMP NOT NULL,
                         source_airport_id INT,
                         destination_airport_id INT,
                         flight_airline_id INT,
                         FOREIGN KEY (source_airport_id) REFERENCES airports(airport_id),
                         FOREIGN KEY (destination_airport_id) REFERENCES airports(airport_id),
                         FOREIGN KEY (flight_airline_id) REFERENCES airlines(airline_id)
);