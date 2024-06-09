-- Insertando datos en 'Country'
INSERT INTO Country (name) VALUES 
('France'),
('Italy'),
('Germany');

-- Insertando datos en 'City'
INSERT INTO City (name, country_id) VALUES 
('Paris', 1),
('Rome', 2),
('Berlin', 3);

-- Insertando datos en 'Service'
INSERT INTO Service (country_id, city_id, date, service_type, price, rate, quantity, description) VALUES 
(1, 1, '2024-07-10', 'TOURS INDIVIDUALES', 150.00, 4.8, 5, 'Tour individual por los principales monumentos de París.'),
(2, 2, '2024-07-15', 'TOURS GRUPALES', 90.00, 4.2, 15, 'Tour grupal por la historia antigua de Roma.'),
(3, 3, '2024-07-20', 'TRADUCCIONES', 85.00, 4.6, 8, 'Servicios de traducción en conferencias en Berlín.');

-- Insertando datos en 'Guide'
INSERT INTO Guide (dni, phone, user, profile_photo, first_name, last_name, gender, email, password, active, country_id, cities, credential_photo, language, score) VALUES 
(444555666, 555111222, 'claire_rouge', 'claire_photo.jpg', 'Claire', 'Rouge', 'Female', 'claire.rouge@example.com', 'clairepass', TRUE, 1, 'Alemania', 'credential_claire.jpg', 'GER', 4.9),
(111222333, 555666777, 'luca_verdi', 'luca_photo.jpg', 'Luca', 'Verdi', 'Male', 'luca.verdi@example.com', 'lucapass', TRUE, 2, 'Rusia', 'credential_luca.jpg', 'RU', 4.6);

-- Insertando datos en 'Tourist'
INSERT INTO Tourist (dni, phone, user, profile_photo, first_name, last_name, gender, email, password, active) VALUES 
(777888999, 555888999, 'sophia_bauer', 'sophia_photo.jpg', 'Sophia', 'Bauer', 'Female', 'sophia.bauer@example.com', 'sophiapass', TRUE),
(333444555, 555999111, 'miguel_santos', 'miguel_photo.jpg', 'Miguel', 'Santos', 'Male', 'miguel.santos@example.com', 'miguelpass', TRUE);

-- Insertando datos en 'GuideService'
INSERT INTO GuideService (guide_id, service_id) VALUES 
(1, 1),
(2, 2);

-- Insertando datos en 'TuristTrips'
INSERT INTO TuristTrips (tourist_id, service_id) VALUES 
(1, 1),
(2, 2);

-- Insertando datos en 'Trophy'
INSERT INTO Trophy (condition_string, date) VALUES 
('Completed 50 Tours', '2024-05-01'),
('Top Rated Guide of the Year', '2024-06-01');

-- Insertando datos en 'Reviews'
INSERT INTO Reviews (score, from_tourist, to_guide, date, description) VALUES 
(5.0, 1, 1, '2024-07-11', 'Claire fue excepcional, conocía todos los rincones de París y su pasión por la historia es contagiosa.'),
(4.5, 2, 1, '2024-07-12', 'Muy buena guía, aunque el tour se extendió un poco más de lo previsto.'),
(4.0, 1, 2, '2024-07-16', 'Luca mostró gran conocimiento sobre Roma, pero el grupo era algo grande.'),
(3.5, NULL, 2, '2024-07-17', 'El tour fue informativo pero empezó tarde y fue algo apresurado.');

-- Insertando datos en 'UserTrophy'
INSERT INTO UserTrophy (guide_id, trophy_id) VALUES 
(1, 1),
(2, 2);
