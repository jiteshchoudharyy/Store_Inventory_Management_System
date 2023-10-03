CREATE database storeInventoryDB;

USE storeInventoryDB;

CREATE table Products(
name varchar(30) NOT NULL,
id int PRIMARY KEY,
unit varchar(20) NOT NULL,
quantity int NOT NULL,
amountPerQuantity int NOT NULL,
minimumQuantityAlert int NOT NULL);

SELECT * from Products;