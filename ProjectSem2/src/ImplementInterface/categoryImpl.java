/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImplementInterface;

import java.util.ArrayList;
import Entity.Category;
import Entity.Product;
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
    
    public boolean checknameEdit(int cat_id ,String cat_name) throws SQLException{
                DatabaseHelper Db = new DatabaseHelper();
        ArrayList<Category> lstCategorys = new ArrayList<>();
        int count = 0 ;
        boolean check = false;
        CallableStatement castm =  Db.getConnection().prepareCall(Constant.PROC_CHECK_NAME_CAT_EDIT);
        castm.setInt(1, cat_id);
        ResultSet rs = castm.executeQuery();
        
        while (rs.next()) {            
                Category cat = new Category(rs.getInt("cat_id"),rs.getString("cat_name"),rs.getInt("sort"));
            
        }
        for (int i = 0; i < lstCategorys.size(); i++) {
            Category  get = lstCategorys.get(i);
            System.out.println(cat_name +"--"+ get.getCat_name());
            if (cat_name.equals(get.getCat_name())) {
                System.out.println("Sai mà");
                 count = count+1;
            }
        }
        if (count>0) {
              check= true;
             System.out.println(count);
        }
        System.out.println(check);
        return check ;
        
    }
    public boolean checkname(String cat_name) throws SQLException{
                DatabaseHelper Db = new DatabaseHelper();
        ArrayList<Category> lstCategorys = new ArrayList<>();
        int count = 0 ;
        boolean check = false;
        CallableStatement castm =  Db.getConnection().prepareCall(Constant.SQL_SELECT_ALL_CATEGORY);
        ResultSet rs = castm.executeQuery();
        
        while (rs.next()) {            
                Category cat = new Category(rs.getInt("cat_id"),rs.getString("cat_name"),rs.getInt("sort"));
        }
        for (int i = 0; i < lstCategorys.size(); i++) {
            Category  get = lstCategorys.get(i);
            System.out.println(cat_name +"--"+ get.getCat_name());
            if (cat_name.equals(get.getCat_name())) {
                System.out.println("Sai mà");
                 count = count+1;
            }
        }
        if (count>0) {
              check= true;
             System.out.println(count);
        }
        System.out.println(check);
        return check ;
        
    }
}
