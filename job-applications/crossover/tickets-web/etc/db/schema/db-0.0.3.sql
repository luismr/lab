--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: etc/db/changelog.xml
--  Ran at: 11/07/16 09:41
--  Against: tickets_web@localhost@jdbc:mysql://localhost:3306/tickets_web
--  Liquibase version: 3.0.0-SNAPSHOT
--  *********************************************************************

--  Lock Database
--  Changeset etc/db/changelog.xml::1468240825036-2::luismr (generated)::(Checksum: 7:b146743bccc58ac5dfecc282ab9227f0)
CREATE TABLE tickets_web.flights (id BIGINT(19) AUTO_INCREMENT NOT NULL, creation_time datetime NOT NULL, modification_time datetime NOT NULL, version BIGINT(19) NOT NULL, boarding_date datetime NOT NULL, gate VARCHAR(10) NOT NULL, boarding_terminal VARCHAR(10) NOT NULL, checkin_date datetime NOT NULL, status VARCHAR(20) NOT NULL, takeoff_date datetime NOT NULL, id_route BIGINT(19) NOT NULL, id_staff BIGINT(19) NULL, CONSTRAINT PK_FLIGHTS PRIMARY KEY (id));

INSERT INTO tickets_web.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1468240825036-2', 'luismr (generated)', 'etc/db/changelog.xml', NOW(), 4, '7:b146743bccc58ac5dfecc282ab9227f0', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

--  Changeset etc/db/changelog.xml::1468240825036-3::luismr (generated)::(Checksum: 7:fb444289f94abf4be5e73da50ec15ba0)
CREATE TABLE tickets_web.aircrafts (id BIGINT(19) AUTO_INCREMENT NOT NULL, creation_time datetime NOT NULL, modification_time datetime NOT NULL, version BIGINT(19) NOT NULL, model VARCHAR(50) NOT NULL, name VARCHAR(20) NOT NULL, seat_cols INT(10) NOT NULL, seat_corridors VARCHAR(10) NOT NULL, seat_rows INT(10) NOT NULL, CONSTRAINT PK_AIRCRAFTS PRIMARY KEY (id));

INSERT INTO tickets_web.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1468240825036-3', 'luismr (generated)', 'etc/db/changelog.xml', NOW(), 5, '7:fb444289f94abf4be5e73da50ec15ba0', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

--  Changeset etc/db/changelog.xml::1468240825036-4::luismr (generated)::(Checksum: 7:4b343d49b93bc5d4dba9239c22b5bce0)
CREATE TABLE tickets_web.routes (id BIGINT(19) AUTO_INCREMENT NOT NULL, creation_time datetime NOT NULL, modification_time datetime NOT NULL, version BIGINT(19) NOT NULL, gate VARCHAR(10) NOT NULL, boarding_terminal VARCHAR(10) NOT NULL, destination VARCHAR(100) NOT NULL, destination_airport VARCHAR(10) NOT NULL, name VARCHAR(20) NOT NULL, origin VARCHAR(100) NOT NULL, origin_airport VARCHAR(10) NOT NULL, price_per_seat DOUBLE NOT NULL, time_to_board VARCHAR(8) NOT NULL, time_to_checkin VARCHAR(8) NOT NULL, time_to_departure VARCHAR(8) NOT NULL, weekdays VARCHAR(50) NOT NULL, id_aircraft BIGINT(19) NOT NULL, CONSTRAINT PK_ROUTES PRIMARY KEY (id));

INSERT INTO tickets_web.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1468240825036-4', 'luismr (generated)', 'etc/db/changelog.xml', NOW(), 6, '7:4b343d49b93bc5d4dba9239c22b5bce0', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

--  Changeset etc/db/changelog.xml::1468240825036-5::luismr (generated)::(Checksum: 7:5f6989d66f258bb77739553af47cae10)
CREATE TABLE tickets_web.tickets (id BIGINT(19) AUTO_INCREMENT NOT NULL, creation_time datetime NOT NULL, modification_time datetime NOT NULL, version BIGINT(19) NOT NULL, cancellation_date datetime NULL, notification_date datetime NULL, payment_date datetime NOT NULL, price DOUBLE NULL, status VARCHAR(20) NOT NULL, flight BIGINT(19) NULL, id_user BIGINT(19) NOT NULL, id_flight BIGINT(19) NULL, CONSTRAINT PK_TICKETS PRIMARY KEY (id));

INSERT INTO tickets_web.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1468240825036-5', 'luismr (generated)', 'etc/db/changelog.xml', NOW(), 7, '7:5f6989d66f258bb77739553af47cae10', 'Create Table', '', 'EXECUTED', '3.0.0-SNP');

--  Changeset etc/db/changelog.xml::1468240825036-7::luismr (generated)::(Checksum: 7:3e028fe250a5e48fcee6312a412c3aff)
ALTER TABLE tickets_web.users ADD CONSTRAINT email UNIQUE (email);

INSERT INTO tickets_web.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1468240825036-7', 'luismr (generated)', 'etc/db/changelog.xml', NOW(), 9, '7:3e028fe250a5e48fcee6312a412c3aff', 'Adds a unique constrant to an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

--  Changeset etc/db/changelog.xml::1468240825036-8::luismr (generated)::(Checksum: 7:f5a4303958bffc5e2db6a68cbd538c66)
ALTER TABLE tickets_web.UserConnection ADD CONSTRAINT UserConnectionRank UNIQUE (userId, providerId, rank);

INSERT INTO tickets_web.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1468240825036-8', 'luismr (generated)', 'etc/db/changelog.xml', NOW(), 10, '7:f5a4303958bffc5e2db6a68cbd538c66', 'Adds a unique constrant to an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

--  Changeset etc/db/changelog.xml::1468240825036-9::luismr (generated)::(Checksum: 7:4498118c3e20e9d826c88f316e02e0e6)
ALTER TABLE tickets_web.UserConnection ADD PRIMARY KEY (userId, providerId, providerUserId);

INSERT INTO tickets_web.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1468240825036-9', 'luismr (generated)', 'etc/db/changelog.xml', NOW(), 11, '7:4498118c3e20e9d826c88f316e02e0e6', 'Adds creates a primary key out of an existing column or set of columns.', '', 'EXECUTED', '3.0.0-SNP');

--  Changeset etc/db/changelog.xml::1468240825036-10::luismr (generated)::(Checksum: 7:cb1981731284abe0b441262edaff9d30)
ALTER TABLE tickets_web.tickets ADD CONSTRAINT FK_g0wrfkx7ia034eh8g3jebnbc9 FOREIGN KEY (id_flight) REFERENCES tickets_web.flights (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO tickets_web.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1468240825036-10', 'luismr (generated)', 'etc/db/changelog.xml', NOW(), 12, '7:cb1981731284abe0b441262edaff9d30', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

--  Changeset etc/db/changelog.xml::1468240825036-11::luismr (generated)::(Checksum: 7:d999b235136c064482a19f434c3b9cde)
ALTER TABLE tickets_web.tickets ADD CONSTRAINT FK_jn3ibbmmepxyq0my01v55ly3q FOREIGN KEY (id_user) REFERENCES tickets_web.users (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO tickets_web.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1468240825036-11', 'luismr (generated)', 'etc/db/changelog.xml', NOW(), 13, '7:d999b235136c064482a19f434c3b9cde', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

--  Changeset etc/db/changelog.xml::1468240825036-12::luismr (generated)::(Checksum: 7:502aa5fac4c25b5d74c52a8e4120c3e7)
ALTER TABLE tickets_web.flights ADD CONSTRAINT FK_sk24bug76fx6pq6x9rp88ptj0 FOREIGN KEY (id_staff) REFERENCES tickets_web.users (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO tickets_web.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1468240825036-12', 'luismr (generated)', 'etc/db/changelog.xml', NOW(), 14, '7:502aa5fac4c25b5d74c52a8e4120c3e7', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

--  Changeset etc/db/changelog.xml::1468240825036-13::luismr (generated)::(Checksum: 7:5419df4540ec686f1104df7c12755554)
ALTER TABLE tickets_web.tickets ADD CONSTRAINT FK_lh7yclwcb58eq89sxmxhurptl FOREIGN KEY (flight) REFERENCES tickets_web.flights (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO tickets_web.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1468240825036-13', 'luismr (generated)', 'etc/db/changelog.xml', NOW(), 15, '7:5419df4540ec686f1104df7c12755554', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

--  Changeset etc/db/changelog.xml::1468240825036-14::luismr (generated)::(Checksum: 7:83abea0e91884458aec5d24acdf73f9f)
ALTER TABLE tickets_web.flights ADD CONSTRAINT FK_nko3i73iykgkglpis500eaij7 FOREIGN KEY (id_route) REFERENCES tickets_web.routes (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO tickets_web.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1468240825036-14', 'luismr (generated)', 'etc/db/changelog.xml', NOW(), 16, '7:83abea0e91884458aec5d24acdf73f9f', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

--  Changeset etc/db/changelog.xml::1468240825036-15::luismr (generated)::(Checksum: 7:e019b7f2250dcc9e1d7b25921be463dd)
ALTER TABLE tickets_web.routes ADD CONSTRAINT FK_4ig9x7y83ywkuinrp4tm467oj FOREIGN KEY (id_aircraft) REFERENCES tickets_web.aircrafts (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO tickets_web.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1468240825036-15', 'luismr (generated)', 'etc/db/changelog.xml', NOW(), 17, '7:e019b7f2250dcc9e1d7b25921be463dd', 'Adds a foreign key constraint to an existing column', '', 'EXECUTED', '3.0.0-SNP');

--  Release Database Lock
--  Release Database Lock
