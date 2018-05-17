/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImplementInterface;

import Utils.DatabaseHelper;
import Utils.Constant;
import java.util.ArrayList;
import Entity.Product;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author devtr
 */
public class ProductImpl {
    
    public ArrayList<Product> getAllpro() throws SQLException{
        DatabaseHelper Db = new DatabaseHelper();
        ArrayList<Product> lstpro = new ArrayList<>();
        
        Statement stm =  Db.getConnection().createStatement();
        ResultSet rs = stm.executeQuery(Constant.SQL_SELECT_ALL_SAN_PHAM);
        while (rs.next()) {            
            Product pro = new Product(rs.getInt("pro_id"),rs.getInt("cat_id"),rs.getString("pro_name"),rs.getFloat("pro_price"),rs.getInt("pro_status"), rs.getString("pro_desc"), rs.getInt("sort"));
            lstpro.add(pro);
            
        }
        
     return lstpro;
    }
    
    public void  Updatepro(Product pro) throws SQLException{
        DatabaseHelper db = new DatabaseHelper();
        CallableStatement catm = db.getConnection().prepareCall(Constant.PROC_UPDATE_PRO);
        catm.setInt(1, pro.getPro_id());
        catm.setInt(3, pro.getCat_id());
        catm.setString(2, pro.getPro_name());
        catm.setFloat(4, pro.getPro_price());
        catm.setString(5, pro.getPro_desc());
        catm.setInt(6, pro.getPro_status());
        catm.setInt(7, pro.getSort());
        catm.executeUpdate();
        
        System.out.println("update success");
        

    }
    
}
