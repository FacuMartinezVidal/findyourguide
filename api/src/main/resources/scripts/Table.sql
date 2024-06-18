CREATE DATABASE IF NOT EXISTS findyourguide;

USE findyourguide;

CREATE TABLE Country (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE City (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country_id INT NOT NULL,
    FOREIGN KEY (country_id) REFERENCES Country (id) ON DELETE CASCADE
);

CREATE TABLE Service (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country_id INT NOT NULL,
    city_id INT NOT NULL,
    date DATE NOT NULL,
    service_type VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    rate FLOAT NOT NULL,
    quantity INT NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (country_id) REFERENCES Country (id) ON DELETE CASCADE,
    FOREIGN KEY (city_id) REFERENCES City (id) ON DELETE CASCADE,
    CONSTRAINT chk_service_type CHECK (
        service_type IN (
            'TOURS INDIVIDUALES',
            'TOURS GRUPALES',
            'TRADUCCIONES'
        )
    )
);

CREATE TABLE Guide (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni INT UNIQUE,
    phone INT,
    user VARCHAR(255),
    profile_photo VARCHAR(255),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    gender VARCHAR(10),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    status VARCHAR(255) NOT NULL DEFAULT "OFFLINE",
    country_id INT NOT NULL,
    cities TEXT,
    -- Usar tabla intermedia
    credential_photo VARCHAR(255),
    language VARCHAR(255),
    score FLOAT,
    FOREIGN KEY (country_id) REFERENCES Country (id),
    CONSTRAINT chk_language_type CHECK (language IN ('ES', 'EN', 'RU', 'GER', 'POR')),
    CONSTRAINT chk_status_guide_type CHECK (
        language IN ('ONLINE', 'OFFLINE', 'DONOTBOTHER', 'SILENCE')
    )
);

CREATE TABLE Tourist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni INT UNIQUE,
    phone INT,
    user VARCHAR(255),
    profile_photo VARCHAR(255),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    gender VARCHAR(10),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT true,
    status VARCHAR(255) NOT NULL DEFAULT "OFFLINE",
    CONSTRAINT chk_status_guide_type CHECK (
        language IN ('ONLINE', 'OFFLINE', 'DONOTBOTHER', 'SILENCE')
    )
);

CREATE TABLE GuideService (
    id INT AUTO_INCREMENT PRIMARY KEY,
    guide_id INT NOT NULL,
    service_id INT NOT NULL,
    FOREIGN KEY (guide_id) REFERENCES Guide (id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES Service (id) ON DELETE CASCADE
);

CREATE TABLE TuristTrips (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tourist_id INT NOT NULL,
    service_id INT NOT NULL,
    FOREIGN KEY (tourist_id) REFERENCES Tourist (id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES Service (id) ON DELETE CASCADE
);

CREATE TABLE Trophy (
    id INT AUTO_INCREMENT PRIMARY KEY,
    condition_string VARCHAR(255) NOT NULL,
    date DATE NOT NULL
);

CREATE TABLE Reviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    score FLOAT NOT NULL,
    from_tourist INT,
    to_guide INT NOT NULL,
    date DATE NOT NULL,
    description TEXT,
    FOREIGN KEY (from_tourist) REFERENCES Tourist (id) ON DELETE
    SET
        NULL,
        FOREIGN KEY (to_guide) REFERENCES Guide (id) ON DELETE CASCADE
);

CREATE TABLE UserTrophy (
    id INT AUTO_INCREMENT PRIMARY KEY,
    guide_id INT,
    tourist_id INT,
    trophy_id INT NOT NULL,
    FOREIGN KEY (trophy_id) REFERENCES Trophy (id) ON DELETE CASCADE,
    FOREIGN KEY (guide_id) REFERENCES Guide (id) ON DELETE CASCADE,
    FOREIGN KEY (tourist_id) REFERENCES Tourist (id) ON DELETE CASCADE,
    CONSTRAINT chk_only_one_id CHECK (
        (
            guide_id IS NOT NULL
            AND tourist_id IS NULL
        )
        OR (
            guide_id IS NULL
            AND tourist_id IS NOT NULL
        )
    )
);