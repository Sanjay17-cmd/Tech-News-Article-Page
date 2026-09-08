-- 1. Create the database your code is looking for
CREATE DATABASE logindemo;

-- 2. Tell MySQL to use this new database
USE logindemo;

-- 3. Create the table for user credentials
CREATE TABLE login (
    uname VARCHAR(50),
    password VARCHAR(50)
);

-- 4. Insert a test user so you can actually log in!
INSERT INTO login (uname, password) VALUES ('admin', 'password123');