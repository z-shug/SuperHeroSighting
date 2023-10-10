DROP database IF EXISTS superHeroSightingTest;
create database superHeroSightingTest;

USE superHeroSightingTest;

create table super(
	superId INT primary key auto_increment,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(50),
	powerid VARCHAR(30) 
);

create table organization (
	organizationId INT primary key auto_increment,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(50)  ,
    address  VARCHAR(50) ,
    contact  VARCHAR(30)
);

create table location (
	locationID INT primary key auto_increment,
	name VARCHAR(50) NOT NULL,
    description VARCHAR(50)  ,
	address  VARCHAR(50) ,
    latitude int NOT NULL,
    longitude int NOT NULL
);

create table sighting (
	sightingId INT primary key auto_increment,
	superId INT NOT NULL,
    locationId INT NOT NULL,
    date datetime NOT NULL, 
    foreign key (superId) references super(superId),
    foreign key (locationId) references location(locationId)
);

create table power(
	powerId Int primary key auto_increment,
    name VARCHAR(30) NOT NULL
);

create table super_organization (
	superId INT NOT NULL,
    organizationId INT NOT NULL,
    primary key(superId,organizationId),
    foreign key (superId) references super(superId),
    foreign key (organizationId) references organization(organizationId)
);