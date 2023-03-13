use mpfleet;

insert into country values(1,'BGN', 'Europe', 'Great nature', 'Bulgaria','Bulgarian');
insert into country values(2,'GR', 'Europe', 'Germany is a Western European country with a landscape of forests, rivers, mountain ranges and North Sea beaches.', 'Germany', 'Dutch');
insert into country values(3,'IT', 'Europe', 'Italy, a European country with a long Mediterranean coastline, has left a powerful mark on Western culture and cuisine.', 'Italy', 'Italian');
insert into country values(4,'UK', 'Europe', 'The United Kingdom, made up of England, Scotland, Wales and Northern Ireland, is an island nation in northwestern Europe.', 'United Kingdom', 'British');
insert into country values(5,'FR', 'Europe', 'France, in Western Europe, encompasses medieval cities, alpine villages and Mediterranean beaches.', 'France', 'French');
insert into country values(6,'NT', 'Europe', 'The Netherlands, a country in northwestern Europe, is known for a flat landscape of canals, tulip fields, windmills and cycling routes', 'Netherlands', 'Dutch');
insert into country values(7,'GR', 'Europe', 'Greece is a country in southeastern Europe with thousands of islands throughout the Aegean and Ionian seas.', 'Greece', 'Greek');
insert into country values(8,'SW', 'Europe', 'Switzerland is a mountainous Central European country, home to numerous lakes, villages and the high peaks of the Alps.', 'Switzerland', 'Swiss');
insert into country values(9,'AU', 'Europe', 'Austria, formally the Republic of Austria, is a landlocked country in the southern part of Central Europe, lying in the Eastern Alps', 'Austria', 'Dutch');
insert into country values(10,'IRL', 'Europe', 'The Republic of Ireland occupies most of the island of Ireland, off the coast of England and Wales.', 'Ireland', 'Irish');

insert into state values (1, 'Sofia', '1000', 'Capital of Bulgaria', 1);
insert into state values (2, 'Berlin', '0030', 'Capital of Germany', 2);
insert into state values (3, 'Rome', '0006', 'Capital of Italy', 3);
insert into state values (4, 'London', '0020', 'Capital of United Kingdom', 4);
insert into state values (5, 'Paris', '0033', 'Capital of France', 5);
insert into state values (6, 'Amsterdam', '0020', 'Capital of Netherlands', 6);
insert into state values (7, 'Athens', '0021', 'Capital of Greece', 7);
insert into state values (8, 'Bern', '3005', 'Capital of Switzerland', 8);
insert into state values (9, 'Vienna', '0001', 'Capital of Austria', 9);
insert into state values (10, 'Dublin', '3531', 'Capital of Ireland', 10);

insert into location values (1,'pl. "Sveti Aleksandar Nevski"', 'St. Alexander Nevsky Cathedral is a Bulgarian Orthodox cathedral in Sofia, the capital of Bulgaria', 'Built in Neo-Byzantine style, it serves as the cathedral church of the Patriarch of Bulgaria and it is one of the 50 largest Christian church buildings by volume in the world.',1,1);
insert into location values (2,'56294 Wierschem', 'Eltz Castle is a medieval castle nestled in the hills above the Moselle between Koblenz and Trier, Germany.', 'It is still owned by a branch of House of Eltz who have lived there since the 12th century.', 2, 2);
insert into location values (3,'Piazza del Colosseo, 1, 00184 Roma RM','The Colosseum is an elliptical amphitheatre in the centre of the city of Rome','It is the largest ancient amphitheatre ever built, and is still the largest standing amphitheatre in the world, despite its age', 3,3);
insert into location values (4,'London SW1A 1AA', 'Buckingham Palace is a London royal residence and the administrative headquarters of the monarch of the United Kingdom.', 'The palace is often at the centre of state occasions and royal hospitality.', 4, 4);
insert into location values (5,'Champ de Mars, 5 Av. Anatole France', 'The Eiffel Tower is a wrought-iron lattice tower on the Champ de Mars in Paris, France.','It is named after the engineer Gustave Eiffel, whose company designed and built the tower', 5, 5);
insert into location values (6,'Museumstraat 1', 'The Rijksmuseum is the national museum of the Netherlands dedicated to Dutch arts and history and is located in Amsterdam.', 'The museum is located at the Museum Square in the borough of Amsterdam South, close to the Van Gogh Museum', 6, 6);
insert into location values (7,'Athens 105 58', 'The Parthenon is a former temple on the Athenian Acropolis, Greece, that was dedicated to the goddess Athena during the fifth century BC.', 'Its decorative sculptures are considered some of the high points of Greek art', 7, 7);
insert into location values (8,'Bim Zytglogge 1', 'The Zytglogge is a landmark medieval tower in Bern, Switzerland.', 'Built in the early 13th century, it has served the city as guard tower, prison, clock tower, centre of urban life and civic memorial.', 8, 8);
insert into location values (9,'Stephansplatz 3', 'St. Stephen''s Cathedral is the mother church of the Roman Catholic Archdiocese of Vienna', 'Seat of the Archbishop of Vienna, Christoph Cardinal Schönborn, OP.', 9, 9);
insert into location values (10,'St Patrick''s Close', 'Saint Patrick''s Cathedral in Dublin, Ireland, founded in 1191 as a Roman Catholic cathedral, is currently the national cathedral of the Church of Ireland.', 'Designated as the local cathedral of the Diocese of Dublin and Glendalough', 10, 10);

insert into contact values (1,'ameliathompson@email.com','Amelia', 'Thompson', '(310) 555-0169', '(310) 555-2875','Always strives to deliver high-quality work');
insert into contact values (2,'noahrodriguez@email.com', 'Noah', 'Rodriguez', '(415) 555-6210', '(415) 555-9932', 'Highly creative individual with a keen eye for detail.');
insert into contact values (3,'oliviapatel@email.com', 'Olivia', 'Patel', '(202) 555-0126', '(202) 555-3479', 'Natural leader and great communication skills');
insert into contact values (4,'ethankim@gmail.com', 'Ethan', 'Kim', '(213) 555-7384', '(213) 555-8973', 'Hard-working and reliable team player');
insert into contact values (5,'charlottesingh@gmail.com', 'Charlotte', 'Singh', '(312) 555-2810', '(312) 555-6374', 'Organized individual with excellent time-management skills');
insert into contact values (6,'liamchen@gmail.com', 'Liam', 'Chen', '(212) 555-7129', '(212) 555-9847', 'Analytical thinker with a talent for strategic planning');
insert into contact values (7,'avagupta@yahoo.com', 'Ava', 'Gupta', '(718) 555-9305', '(718) 555-1958', 'Motivated and ambitious individual with a strong work ethic');
insert into contact values (8,'williamlee@yahoo.com', 'William', 'Lee', '(404) 555-6298', '(404) 555-3271', ' Detail-oriented individual with a passion for accuracy and precision');
insert into contact values (9,'sophianguyen@yahoo.com', 'Sophia', 'Nguyen', '(206) 555-0153', '(206) 555-4829', 'Creative individual with a passion for innovation');
insert into contact values (10,'parunev@gmail.com', 'Martin', 'Parunev', '(02) 356-02', '(359) 888-223344', 'The developer');

insert into client values (1,'123 Main St','Acme Corporation is a global leader in industrial supplies and manufacturing.',' info@acmecorp.com', '555-987-6543','Acme Corporation','555-123-4567', 'www.acmecorp.com',1,1);
insert into client values (2,'456 Park Ave, Suite 100','Stellar Designs is a high-end fashion and accessory retailer with a focus on sustainable materials and ethical production.','contact@stellardesigns.com','555-111-1111','Stellar Designs','555-555-5555','www.stellardesigns.com',2,2);
insert into client values (3,'789 Oak St, Suite 200', 'Global Ventures is a private equity firm focused on investing in innovative technology startups.', 'contact@globalventures.net', '555-222-2222', 'Global Ventures', '555-444-4444', 'www.globalventures.net', 3, 3);
insert into client values (4,'321 Elm St', 'Green Earth Co. is a leading provider of eco-friendly cleaning and household products.', 'info@greenearthco.com', '555-333-3333', 'Green Earth Co.', '555-888-8888', 'www.greenearthco.com', 4, 4);
insert into client values (5,'555 Pine St, Suite 300', 'Summit Marketing is a full-service marketing agency specializing in digital marketing and branding.', ' info@summitmarketing.com', '555-777-7777', 'Summit Marketing', '555-444-4444', ' www.summitmarketing.com', 5, 5);

insert into supplier values (1, '123 Main St, Anytown, State 2', 'ABC Suppliers provides industrial tools and equipment for construction and manufacturing industries.','info@abcsuppliers.com', '+1-555-555-5555','ABC Suppliers','+1-555-555-5555','www.abcsuppliers.com',4,2);
insert into supplier values (2, '456 Oak St, Somewhere, State 8', 'XYZ Inc. offers computer hardware and software for businesses and individuals.', 'info@xyzinc.com', '+1-555-555-5555', 'XYZ Inc.', '+1-555-555-5555', 'www.xyzinc.com', 7, 8);
insert into supplier values (3, '789 Maple Ave, Nowhere, State 5', '123 Manufacturing produces custom parts and components for industrial and commercial equipment.', 'info@123manufacturing.com', '+1-555-555-5555', '123 Manufacturing', '+1-555-555-5555', 'www.123manufacturing.com', 2, 5);
insert into supplier values (4, '456 Pine St, Anytown, State 2', 'Acme Corp supplies building materials and construction supplies for contractors and builders.', 'info@acmecorp.com', '+1-555-555-5555', 'Acme Corp', '+1-555-555-5555', ' www.acmecorp.com', 4, 2);
insert into supplier values (5, '789 Oak St, Nowhere, State 5', 'QRS Enterprises provides office equipment and supplies for businesses and organizations.', 'info@qrsenterprises.com', '+1-555-555-5555', 'QRS Enterprises', '+1-555-555-5555', 'www.qrsenterprises.com', 2, 5);

insert into vehicle_type values (1, 'Sedan', 'Four-door car with a separate trunk compartment and seating for four or more people.');
insert into vehicle_type values (2, 'SUV', 'Large, rugged vehicle with four-wheel drive, designed for off-road use and carrying a large number of passengers.');
insert into vehicle_type values (3, 'Pickup Truck', 'A vehicle with an open bed for carrying cargo or equipment and seating for up to six passengers.');
insert into vehicle_type values (4, 'Hatchback', 'A small car with a rear door that opens upwards to provide access to the cargo area.');
insert into vehicle_type values (5, 'Coupe', 'A two-door car with a fixed roof and seating for two or four passengers.');
insert into vehicle_type values (6, 'Minivan', 'A vehicle with a high roof and sliding side doors, designed to carry a large number of passengers.');
insert into vehicle_type values (7, 'Convertible', 'A car with a roof that can be folded down or removed, allowing for an open-air driving experience.');
insert into vehicle_type values (8, 'Sports Car', 'A high-performance vehicle designed for speed and agility, typically with two seats and a sleek, aerodynamic design.');
insert into vehicle_type values (9, 'Station Wagon', 'A car with a long roof-line and a rear cargo area, designed for carrying large amounts of cargo or passengers.');
insert into vehicle_type values (10, 'Motorcycle', 'A two-wheeled vehicle powered by an engine, with seating for one or two riders.');

insert into vehicle_make values (1, 'Toyota', 'A Japanese car manufacturer known for producing reliable and efficient vehicles, ranging from compact cars to SUVs and pickup trucks.');
insert into vehicle_make values (2, 'Ford', 'An American car manufacturer known for producing a wide range of vehicles, including trucks, SUVs, and sports cars.');
insert into vehicle_make values (3, 'Honda', 'A Japanese car manufacturer known for producing reliable and high-quality vehicles, ranging from compact cars to luxury sedans.');
insert into vehicle_make values (4, 'Chevrolet', 'An American car manufacturer known for producing a wide range of vehicles, including trucks, SUVs, and sports cars.');
insert into vehicle_make values (5, 'BMW', 'A German luxury car manufacturer known for producing high-performance vehicles, ranging from compact cars to large luxury sedans.');
insert into vehicle_make values (6, 'Mercedes-Benz', 'A German luxury car manufacturer known for producing high-quality and innovative vehicles, ranging from compact cars to luxury sedans and SUVs.');
insert into vehicle_make values (7, 'Nissa', 'A Japanese car manufacturer known for producing reliable and affordable vehicles, ranging from compact cars to SUVs and pickup trucks.');
insert into vehicle_make values (8, 'Jeep', 'An American car manufacturer known for producing rugged and capable off-road vehicles, including SUVs and pickup trucks.');
insert into vehicle_make values (9, 'Audi', 'A German luxury car manufacturer known for producing high-quality vehicles with advanced technology and innovative designs.');
insert into vehicle_make values (10, 'Subaru', 'A Japanese car manufacturer known for producing all-wheel-drive vehicles that are capable of handling rough terrain and inclement weather.');

insert into vehicle_model values (1, 'Toyota Corolla', 'A reliable and affordable compact car that is popular worldwide, known for its fuel efficiency and practicality.');
insert into vehicle_model values (2, 'Honda Civic', 'Another popular compact car known for its reliability, fuel efficiency, and sporty design.');
insert into vehicle_model values (3, 'Ford F-150', 'A best-selling pickup truck that is known for its durability, towing capacity, and versatility.');
insert into vehicle_model values (4, 'Chevrolet Silverado', 'A popular pickup truck known for its strength and durability, with various models designed for different purposes.');
insert into vehicle_model values (5, 'BMW 3 Series', 'A luxury compact car that is known for its agile handling, advanced technology, and sleek design.');
insert into vehicle_model values (6, 'Mercedes-Benz S-Class', 'A flagship luxury sedan that is known for its comfort, advanced technology, and elegant styling.');
insert into vehicle_model values (7, 'Jeep Wrangler', 'A rugged and capable off-road SUV that is known for its iconic design, versatility, and customization options.');
insert into vehicle_model values (8, 'Tesla Model S', 'An all-electric luxury sedan that is known for its advanced technology, performance, and eco-friendliness.');
insert into vehicle_model values (9, 'Subaru Outback ', 'A versatile and capable crossover SUV that is known for its all-wheel-drive system, spacious interior, and off-road capabilities.');
insert into vehicle_model values (10, 'Porsche 911', 'A high-performance sports car that is known for its iconic design, precision handling, and powerful engines.');

insert into vehicle_status values (1, 'New', 'A vehicle that has never been owned or registered before, typically sold by a dealership with a manufacturer''s warranty.');
insert into vehicle_status values (2, 'Used', 'A vehicle that has been previously owned and may have a history of wear and tear, accidents, or repairs.');
insert into vehicle_status values (3, 'CPO', 'A used vehicle that has undergone a thorough inspection and meets certain manufacturer standards, often with an extended warranty.');
insert into vehicle_status values (4, 'Salvage', 'A vehicle that has been damaged to the extent that it is deemed a total loss by an insurance company, often sold for parts or to be rebuilt.');
insert into vehicle_status values (5, 'Rebuilt', 'A salvage vehicle that has been repaired and rebuilt to a drivable condition, typically with a rebuilt title.');
insert into vehicle_status values (6, 'Leased', 'A vehicle that is owned by a leasing company and rented out to a driver for a set period of time, typically with mileage and wear-and-tear restrictions.');
insert into vehicle_status values (7, 'Rental', 'A vehicle that is owned by a rental company and rented out to drivers on a short-term basis, often for travel or leisure purposes.');
insert into vehicle_status values (8, 'Classic', 'A vehicle that is at least 20 years old and considered to be of historical or cultural significance, often kept as a collector''s item.');
insert into vehicle_status values (9, 'Antique', 'A vehicle that is at least 25 years old and considered to be a rare or valuable collector''s item, often kept for display or show.');
insert into vehicle_status values (10, 'Demo', 'A vehicle that has been used for display or test-driving purposes by a dealership or manufacturer, often with low mileage and a discounted price.');