USE [QuanLyCuaHang]
GO

/****** Object:  StoredProcedure [dbo].[updatesanpham]    Script Date: 5/24/2018 7:33:25 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[addsanpham] 
	@pro_name nvarchar(255),
	@cat_id int ,
	@pro_price float,
	@pro_desc nvarchar(255),
	@pro_status int ,
	@sort int
as 
begin
	INSERT INTO [dbo].[product] ([pro_name] ,[cat_id] ,[pro_price] ,[pro_desc],[pro_status],[sort]) 
	VALUES ('%'+@pro_name+'%', @cat_id, @pro_price ,'%'+@pro_desc+'%',@pro_status,@sort)


end
GO

EXEC addsanpham 'mi bo',2,34,'ngon vl',1,5



----------deleteSanPham----------------------------

CREATE PROC deleteSanPham

@pro_id int
As 
begin
	delete From product where pro_id = @pro_id
end


EXEC deleteSanPham 2



----------------- delete user ------------------------
CREATE PROC deleteUser

@user_id int
As 
begin
	delete From [user] where user_id = @user_id
end


EXEC deleteUser 45


---------------------delete category ----------------------
CREATE PROC deleteCategory

@cat_id int
As 
begin
	delete From [category] where cat_id = @cat_id
end

EXEC deleteCategory 7