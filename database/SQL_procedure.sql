USE [QuanLyCuaHang]
GO

/****** Object:  StoredProcedure [dbo].[addCategory]    Script Date: 5/24/2018 7:20:57 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[addCategory]
	@cat_name nvarchar(36),
	@sort int 

AS
BEGIN
 insert Into category(cat_name,sort) values (@cat_name,@sort)
END
GO


/****** Object:  StoredProcedure [dbo].[addNewUser]    Script Date: 5/24/2018 7:21:27 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[addNewUser]
@name nvarchar(255),
@username nvarchar(255),
@pass nvarchar(255),
@role int
As
INSERT INTO [dbo].[user] ([name] ,[username] ,[password] ,[user_role]) VALUES ('%'+@name+'%', '%'+@username+'%', '%'+@pass+'%', @role)

GO


/****** Object:  StoredProcedure [dbo].[updateCategory]    Script Date: 5/24/2018 7:22:22 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[updateCategory]
	@cat_id int,
	@cat_name nvarchar(36),
	@sort int 

AS
BEGIN
 	UPDATE category
    SET cat_name=(@cat_name), 
		sort = @sort
    WHERE cat_id=@cat_id
END
GO


/****** Object:  StoredProcedure [dbo].[updateCategory]    Script Date: 5/24/2018 7:22:22 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[updateCategory]
	@cat_id int,
	@cat_name nvarchar(36),
	@sort int 

AS
BEGIN
 	UPDATE category
    SET cat_name=(@cat_name), 
		sort = @sort
    WHERE cat_id=@cat_id
END
GO


