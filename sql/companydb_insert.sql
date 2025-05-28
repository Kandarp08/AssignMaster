insert into employee values
('123456789', 'Ramesh Gupta', '8316584719', '1974-04-22', 'A456, Silver Heights, CM Road', 'M', 45000, null),
('738924723', 'Suresh Jain', '9315382438', '1978-03-10', 'C402, Greenfield Bungalows, MG Road', 'M', 30000, null),
('988383293', 'Nidhika Ganatra', '9837249237', '1982-11-18', 'B203, Luxuria Palace, RT Road', 'F', 65000, null),
('886312498', 'Mukesh Agarwal', '9723833684', '1972-06-05', 'A108, Savan Society, FH Road', 'M', 50000, null),
('896329839', 'Nidhi Das', '9837292492', '1988-07-25', 'C201, Avadh Apartments, CH Street', 'F', 40000, null),
('836298242', 'Manish Singh', '9123894737', '1973-10-17', 'D401, Sunrise Heights, SP Road', 'M', 45000, null);

insert into project values
(1, 'Training'),
(2, 'Restructuring'),
(3, 'Database Design'),
(4, 'Kernel Modules');

insert into works_on values
('988383293', 4, 20),
('123456789', 4, 16),
('738924723', 1, 10),
('886312498', 4, 25),
('896329839', 2, 21),
('836298242', 3, 32);

insert into dependent values
('988383293', 'Radhika Ganatra', '8923832843', 'F', 'Mother'),
('886312498', 'Geeta Agarwal', '9832428987', 'F', 'Spouse'),
('886312498', 'Hitesh Agarwal', null, 'M', 'Son'),
('123456789', 'Naresh Gupta', '9827424973', 'M', 'Father'),
('738924723', 'Rita Jain', null, 'F', 'Daughter');