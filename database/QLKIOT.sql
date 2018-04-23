create database QLKIOT
 use QLKIOT

 create table [user]
 (
 ID int  primary key Identity(1,1),
 name nvarchar(255) not null ,
 email nvarchar(255),
 pass varchar(255)not null,
 [role] int not null , 
 [desc] nvarchar(255)
 )
 go

 Create table group_provider(
 ID int  primary key Identity(1,1),
 name nvarchar(255) not null ,
 code nvarchar(255) not null ,
 [desc] nvarchar(255)
 )
 go 

 create table provider(
 ID int  primary key Identity(1,1),
 group_id int NOT NULL DEFAULT 0  FOREIGN KEY REFERENCES  group_provider(ID),
 name nvarchar(255) not null ,
 code nvarchar(255) not null ,
 phone nvarchar(255) ,
 email nvarchar(255) ,
 [status] int ,
 [desc] nvarchar(255)
 )
 go

 create table product(
 ID int  primary key Identity(1,1),
 group_id int NOT NULL DEFAULT 0  FOREIGN KEY REFERENCES  group_provider(ID),
 provider_id int NOT NULL DEFAULT 0  FOREIGN KEY REFERENCES  provider(ID),
 name nvarchar(255) not null ,
 price int NOT NULL,
 [status] int DEFAULT 1,
 [desc] nvarchar(255)
 )
 go

