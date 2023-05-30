CREATE TABLE client (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR (200) NOT NULL CHECK (LENGTH (name) > 3));

CREATE TABLE planet (
    id VARCHAR PRIMARY KEY CHECK (id REGEXP '^[A-Z0-9]+$'),
    name VARCHAR(500) NOT NULL CHECK (LENGTH(name) > 1)
);

CREATE TABLE ticket (
        id INT PRIMARY KEY AUTO_INCREMENT,
        created_at TIMESTAMP WITH TIME ZONE,
        client_id INT,
        from_planet_id VARCHAR,
        to_planet_id VARCHAR,
        FOREIGN KEY (client_id) REFERENCES client(id),
        FOREIGN KEY (from_planet_id) REFERENCES planet(id),
        FOREIGN KEY (to_planet_id) REFERENCES planet(id));

-- Client (клієнт) - клієнт компанії. Має наступні властивості:
--id - ідентифікатор, первинний сурогатний ключ, автоінкрементне число
--name - ім'я, від 3 до 200 символів включно

--Planet (планета). Початковий або кінцевий пункт відправлення. Має наступні властивості:
--id - ідентифікатор планети. Рядок, що складається виключно з латинських букв у верхньому регістрі та цифр. Наприклад, MARS, VEN
--name - назва планети, рядок від 1 до 500 символів включно

--Ticket (квиток). Має наступні властивості:
--id - ідентифікатор квитка, первинний сурогатний ключ, автоінкрементне число.
--created_at - TIMESTAMP в UTC, коли був створений цей квиток
--client_id - ідентифікатор клієнта, якому належить цей квиток.
--from_planet_id - ідентифікатор планети, звідки відправляється пасажир
--to_planet_id - ідентифікатор планети, куди летить пасажир