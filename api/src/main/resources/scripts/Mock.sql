-- Inserting mock data into 'Country'
INSERT INTO Country (name) VALUES 
('United States'),
('Spain'),
('Japan');

-- Inserting mock data into 'City'
INSERT INTO City (name, country_id) VALUES 
('New York', 1),
('Madrid', 2),
('Tokyo', 3);

-- Inserting mock data into 'Service'
INSERT INTO Service (country_id, city_id, date, service_type, price, rate, quantity, description) VALUES 
(1, 1, '2024-06-01', 'TOURS INDIVIDUALES', 120.50, 4.5, 10, 'Personalized tour around Manhattan.'),
(2, 2, '2024-06-05', 'TOURS GRUPALES', 75.00, 4.3, 20, 'Group tour visiting historical sites in Madrid.'),
(3, 3, '2024-06-10', 'TRADUCCIONES', 200.00, 4.9, 5, 'Professional translation services in Tokyo.');

-- Inserting mock data into 'Guide'
INSERT INTO Guide (dni, phone, user, profile_photo, first_name, last_name, gender, email, password, active, country_id, cities, credential_photo, language, score) VALUES 
(123456789, 5550123, 'john_doe', 'john_photo.jpg', 'John', 'Doe', 'Male', 'john.doe@example.com', 'password123', TRUE, 1, 'New York', 'credential.jpg', 'English', 4.8),
(987654321, 5550456, 'jane_smith', 'jane_photo.jpg', 'Jane', 'Smith', 'Female', 'jane.smith@example.com', 'password321', TRUE, 2, 'Madrid', 'credential2.jpg', 'Spanish, English', 4.7);

-- Inserting mock data into 'Tourist'
INSERT INTO Tourist (dni, phone, user, profile_photo, first_name, last_name, gender, email, password, active) VALUES 
(234567890, 5550789, 'alice_wonder', 'alice_photo.jpg', 'Alice', 'Wonder', 'Female', 'alice.wonder@example.com', 'securepass', TRUE),
(876543210, 5550321, 'bob_builder', 'bob_photo.jpg', 'Bob', 'Builder', 'Male', 'bob.builder@example.com', 'mypassword', TRUE);

-- Inserting mock data into 'GuideService'
INSERT INTO GuideService (guide_id, service_id) VALUES 
(1, 1),
(2, 2);

-- Inserting mock data into 'TuristTrips'
INSERT INTO TuristTrips (tourist_id, service_id) VALUES 
(1, 1),
(2, 2);

-- Inserting mock data into 'Trophy'
INSERT INTO Trophy (condition_string, date) VALUES 
('Completed 100 Tours', '2024-05-01'),
('Highest Rated Guide of the Month', '2024-05-15');

-- Inserting mock data into 'Reviews'
INSERT INTO Reviews (score, from_tourist, to_guide, date, description) VALUES 
(5.0, 1, 1, '2024-06-03', 'Fantastic guide, very knowledgeable and friendly.'),
(4.0, 2, 2, '2024-06-04', 'Good tour, but started late.');

-- Inserting mock data into 'UserTrophy'
INSERT INTO UserTrophy (guide_id, tourist_id, trophy_id) VALUES 
(1, NULL, 1),
(NULL, 1, 2);
