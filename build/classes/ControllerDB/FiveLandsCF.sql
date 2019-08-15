CREATE DATABASE QuanLyQuanCafe
GO

USE QuanLyQuanCafe
GO

CREATE TABLE Employ
(
	idEmploy VARCHAR(10) PRIMARY KEY,
	name NVARCHAR(20)  DEFAULT N'NO NAME',
	age DATE ,
	gender bit,
	phoneNumber VARCHAR(11),
	address NVARCHAR(30),
	position NVARCHAR(20) ,
	
)
GO
CREATE TABLE Account
(
	idAccount VARCHAR(10) PRIMARY KEY,
	userName VARCHAR(100) DEFAULT N'NO NAME',	
	passWord VARCHAR(100) DEFAULT 0,
	idEEmploy VARCHAR(10),
	FOREIGN KEY (idEEmploy) REFERENCES dbo.Employ(idEmploy)
)
GO
CREATE TABLE Access
(
	idAccess VARCHAR(10) PRIMARY KEY,
	idAccess_Account VARCHAR(10),
	FOREIGN KEY (idAccess_Account) REFERENCES dbo.Account(idAccount)
)
GO
CREATE TABLE AccessAll
(
	id INT IDENTITY PRIMARY KEY,
	idAccessMini VARCHAR(10),
	FOREIGN KEY (idAccessMini) REFERENCES dbo.Access(idAccess)
)
GO




CREATE TABLE MEnuCategory
(
	idMenuCategory VARCHAR(10) PRIMARY KEY,
	name NVARCHAR(100) DEFAULT N'NO NAME'
)
GO

CREATE TABLE Menu
(
	idMenu VARCHAR(10) PRIMARY KEY,
	idCategory VARCHAR(10) ,
	nameFood NVARCHAR(100)  DEFAULT N'NO NAME',
	promotion FLOAT DEFAULT 0,
	price FLOAT  DEFAULT 0.
	FOREIGN KEY (idCategory) REFERENCES dbo.MenuCategory(idMenuCategory)
)
GO



CREATE TABLE StartOrder
(
	idOrder VARCHAR(10) PRIMARY KEY,
	idEmploy_Bill VARCHAR(10) ,
	DateCheckin DATE ,
	FOREIGN KEY (idEmploy_Bill) REFERENCES dbo.Employ(idEmploy),
)
GO

CREATE TABLE Bill
(
	id INT IDENTITY PRIMARY KEY,
	idMenuBill VARCHAR(10),
	idBillorder VARCHAR(10),
	count INT NOT NULL,
	totalBill FLOAT,
	FOREIGN KEY (idMenuBill) REFERENCES dbo.Menu(idMenu),
	FOREIGN KEY (idBillorder) REFERENCES dbo.StartOrder(idOrder)
)
GO


CREATE TABLE RawMaterials
(
	id INT IDENTITY PRIMARY KEY,
	name NVARCHAR(20)  DEFAULT N'NO NAME',
	Mass VARCHAR(10),
	price FLOAT,
	DateInput DATE,
)
GO