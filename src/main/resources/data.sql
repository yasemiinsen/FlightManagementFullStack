INSERT INTO airports (airport_name, city, country) VALUES
                                                       ('Istanbul Airport', 'Istanbul', 'Turkey'),
                                                       ('Sabiha Gokcen Airport', 'Istanbul', 'Turkey'),
                                                       ('Heathrow Airport', 'London', 'United Kingdom'),
                                                       ('Charles de Gaulle Airport', 'Paris', 'France'),
                                                       ('Frankfurt Airport', 'Frankfurt', 'Germany');


INSERT INTO airlines (airline_code, airline_name, country_of_origin) VALUES
                                                                 ('THY', 'Turkish Airlines', 'Turkey'),
                                                                 ('PGT', 'Pegasus Airlines', 'Turkey'),
                                                                 ('BA', 'British Airways', 'United Kingdom'),
                                                                 ('AF', 'Air France', 'France'),
                                                                 ('LH', 'Lufthansa', 'Germany');

-- Flights (Günlük 3 uçuş kuralına uygun örnek veriler)
INSERT INTO flights (flight_number, flight_date_time, source_airport_id, destination_airport_id, flight_airline_id) VALUES
-- Turkish Airlines uçuşları
('TK1000', '2025-04-09 08:00:00', 1, 3, 1),  -- Istanbul -> London
('TK1001', '2025-04-09 12:00:00', 1, 3, 1),  -- Istanbul -> London
('TK1002', '2025-04-09 16:00:00', 1, 3, 1),  -- Istanbul -> London (günlük max 3)

-- Pegasus uçuşları
('PC2000', '2025-04-09 07:30:00', 2, 4, 2),  -- Sabiha -> Paris
('PC2001', '2025-04-09 13:30:00', 2, 4, 2),  -- Sabiha -> Paris

-- British Airways uçuşları
('BA3000', '2025-04-09 09:00:00', 3, 1, 3),  -- London -> Istanbul
('BA3001', '2025-04-09 15:00:00', 3, 1, 3),

-- Air France uçuşları
('AF4000', '2025-04-09 10:00:00', 4, 5, 4),  -- Paris -> Frankfurt
('AF4001', '2025-04-09 14:00:00', 4, 5, 4),

-- Lufthansa uçuşları
('LH5000', '2025-04-09 11:00:00', 5, 2, 5),  -- Frankfurt -> Sabiha
('LH5001', '2025-04-09 17:00:00', 5, 2, 5);