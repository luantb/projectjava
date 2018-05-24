/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImplementInterface;

import java.util.ArrayList;
import Entity.Category;
import Utils.Constant;
import Utils.DatabaseHelper;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author devtr
 */
public class categoryImpl {
    public  ArrayList<Category>  getAllCat() throws SQLException{
        DatabaseHelper db = new DatabaseHelper();
        ArrayList<Category> lstCategorys = new ArrayList<>();
            Statement stm = db.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(Constant.SQL_SELECT_ALL_CATEGORY);
        while (rs.next()) {            
            Category cat  = new Category(rs.getInt("cat_id"),rs.getString("cat_name"),rs.getInt("sort"));
            lstCategorys.add(cat);
        }
        return lstCategorys;
    }
    
    public boolean addCategory(Category cat) throws SQLException{
        DatabaseHelper db = new DatabaseHelper();
        CallableStatement cabt = db.getConnection().prepareCall(Constant.PROC_ADD_CAT);
        cabt.setString(1,cat.getCat_name());
        cabt.setInt(2, cat.getSort());
        int check = cabt.executeUpdate();
        if(check >0){
           System.out.println("add category success");
           return true;
        }
        return false;
        
        
    }
    
    public  boolean udateCategory(Category cat ) throws SQLException{
        DatabaseHelper db = new DatabaseHelper();
        CallableStatement cabt = db.getConnection().prepareCall(Constant.PROC_UPDATE_CAT);
        cabt.setInt(1, cat.getCat_id());
        cabt.setString(2,cat.getCat_name());
        cabt.setInt(3, cat.getSort());
        int check = cabt.executeUpdate();
        if(check !=0){
           System.out.println("update category success");
           return true;
        }
        return false;
    }
}
