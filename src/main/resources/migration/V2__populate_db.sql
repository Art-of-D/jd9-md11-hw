INSERT INTO client (name) VALUES ('John');
INSERT INTO client (name) VALUES ('Alice');
INSERT INTO client (name) VALUES ('Maria');
INSERT INTO client (name) VALUES ('Huggo');
INSERT INTO client (name) VALUES ('David');
INSERT INTO client (name) VALUES ('Milly');
INSERT INTO client (name) VALUES ('Sasha');
INSERT INTO client (name) VALUES ('Anna');
INSERT INTO client (name) VALUES ('Ashley');
INSERT INTO client (name) VALUES ('Drayk');


INSERT INTO planet (id, name) VALUES ('URANUS','Uranus is the seventh planet from the Sun and is a gaseous cyan ice giant');
INSERT INTO planet (id, name) VALUES ('MARS','Mars is the fourth planet and the furthest terrestrial planet from the Sun');
INSERT INTO planet (id, name) VALUES ('JUPITER','Jupiter is the fifth planet from the Sun and the largest in the Solar System');
INSERT INTO planet (id, name) VALUES ('SATURN','Saturn is the sixth planet from the Sun and has an intricate ring system');
INSERT INTO planet (id, name) VALUES ('NEPTUNE','Neptune is the eighth planet from the Sun and the farthest known planet in the Solar System');


INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (CURRENT_TIMESTAMP(), 1,'URANUS','SATURN');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (CURRENT_TIMESTAMP(), 2,'NEPTUNE','SATURN');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (CURRENT_TIMESTAMP(), 3,'URANUS','MARS');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (CURRENT_TIMESTAMP(), 4,'JUPITER','SATURN');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (CURRENT_TIMESTAMP(), 1,'SATURN','MARS');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (CURRENT_TIMESTAMP(), 6,'URANUS','SATURN');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (CURRENT_TIMESTAMP(), 7,'NEPTUNE','MARS');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (CURRENT_TIMESTAMP(), 8,'URANUS','NEPTUNE');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (CURRENT_TIMESTAMP(), 9,'JUPITER','MARS');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (CURRENT_TIMESTAMP(), 10,'NEPTUNE','URANUS');
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES (CURRENT_TIMESTAMP(), 10,'URANUS','MARS');


