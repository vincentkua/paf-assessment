-- Insert Manually
insert into user 
(user_id, username , name) 
values 
('1b80114c','fred','Fred'),
('cf66dae3','wilma','Wilma'),
('a8b9800d','barney','Barney'),
('66223e28','betty','Betty');

-- Load From CSV
-- LOAD DATA INFILE './data.csv'
-- INTO TABLE user
-- FIELDS TERMINATED BY ','
-- IGNORE 1 ROWS;
