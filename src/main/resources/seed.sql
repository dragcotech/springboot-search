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