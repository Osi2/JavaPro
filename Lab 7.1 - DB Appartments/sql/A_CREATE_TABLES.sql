USE YegorDb;

--DROP TABLE APPARTMENTS;
CREATE TABLE APPARTMENTS(
AppartmentID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
Address VARCHAR(200) NULL,
Area INT DEFAULT NULL,
RoomsCount INT DEFAULT NULL,
Price DOUBLE DEFAULT NULL,
DistrictID INT NOT NULL ,
FOREIGN KEY (DistrictID) REFERENCES DISTRICTS(DistrictID)
);

--DROP TABLE DISTRICTS;
CREATE TABLE DISTRICTS(
DistrictID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
Name varchar(100) DEFAULT NULL,
Description varchar(200) DEFAULT NULL
);