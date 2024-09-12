CREATE TABLE hotels (
                        id int PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        address VARCHAR(255) NOT NULL,
                        phone VARCHAR(255) NOT NULL
);

CREATE TABLE rooms (
                       id INT PRIMARY KEY,
                       hotel_id INT REFERENCES hotels(id) ON DELETE CASCADE ON UPDATE CASCADE,
                       room_number VARCHAR(10) NOT NULL,
                       isAvailable boolean NOT NULL ,
                       capacity int NOT NULL,
                       price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE reservations (
                              id INT PRIMARY KEY,
                              room_id INT REFERENCES rooms(id) ON DELETE CASCADE ON UPDATE CASCADE ,
                              user_id INT REFERENCES clients(id) ON DELETE CASCADE ON UPDATE CASCADE ,
                              client VARCHAR(100) NOT NULL,
                              check_in DATE NOT NULL,
                              check_out DATE NOT NULL,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE clients (
                         id int primary key ,
                         firstname varchar(255) NOT NULL ,
                         lastname varchar(255) NOT NULL
);

ALTER TABLE reservations
    ADD COLUMN user_id INT REFERENCES clients(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE reservations
DROP COLUMN client ;

ALTER TABLE hotels ALTER COLUMN id SET DATA TYPE SERIAL;
ALTER TABLE rooms ALTER COLUMN id SET DATA TYPE SERIAL;
ALTER TABLE reservations ALTER COLUMN id SET DATA TYPE SERIAL;
ALTER TABLE clients ALTER COLUMN id SET DATA TYPE SERIAL;

