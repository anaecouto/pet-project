INSERT INTO customer (id, username, password, role, verification_code, enable)
    VALUES
        (1, 'anacouto', ' $2a$12$rEndBk7nHjMIvwuNKq6.LuvfZqeAPVjUwY2DDcbnHtLT.v0hEe1w.', 1, 'code', true),
        (2, 'elisa', ' $2a$12$rEndBk7nHjMIvwuNKq6.LuvfZqeAPVjUwY2DDcbnHtLT.v0hEe1w.', 1, 'code', true);

ALTER SEQUENCE customer_id_seq RESTART WITH 3;

INSERT INTO pet (id, name, breed, type, owner_id)
    VALUES
        (1, 'name', 'breed', 'type', 1),
        (2, 'name2', 'breed2', 'type2', 1),
        (3, 'name3', 'breed3', 'type3', 2);

ALTER SEQUENCE pet_id_seq RESTART WITH 4;