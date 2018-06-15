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

    public ArrayList<Product> getAllpro() throws SQLException {
        DatabaseHelper Db = new DatabaseHelper();
        ArrayList<Product> lstpro = new ArrayList<>();

        Statement stm = Db.getConnection().createStatement();
        ResultSet rs = stm.executeQuery(Constant.SQL_SELECT_ALL_SAN_PHAM);
        while (rs.next()) {
            Product pro = new Product(rs.getInt("pro_id"), rs.getInt("cat_id"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getInt("pro_status"), rs.getString("pro_desc"), rs.getInt("sort"));
            lstpro.add(pro);

        }

        return lstpro;
    }

    public ArrayList<Product> getAllProToOrder() throws SQLException {
        DatabaseHelper Db = new DatabaseHelper();
        ArrayList<Product> lstpro = new ArrayList<>();

        Statement stm = Db.getConnection().createStatement();
        ResultSet rs = stm.executeQuery(Constant.SQL_SELECT_ALL_SAN_PHAM_EXCEPT_OUT_OF_PRO);
        while (rs.next()) {
            Product pro = new Product(rs.getInt("pro_id"), rs.getInt("cat_id"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getInt("pro_status"), rs.getString("pro_desc"), rs.getInt("sort"));
            lstpro.add(pro);

        }

        return lstpro;
    }

    public void Order() {

    }

    public boolean checknameEdit(int pro_id, String pro_name) throws SQLException {
        DatabaseHelper Db = new DatabaseHelper();
        ArrayList<Product> lstproNotIn = new ArrayList<>();
        int count = 0;
        boolean check = false;
        CallableStatement castm = Db.getConnection().prepareCall(Constant.PROC_NOT_IN_PRO);
        castm.setInt(1, pro_id);
        ResultSet rs = castm.executeQuery();

        while (rs.next()) {
            Product pro = new Product(rs.getInt("pro_id"), rs.getInt("cat_id"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getInt("pro_status"), rs.getString("pro_desc"), rs.getInt("sort"));
            lstproNotIn.add(pro);

        }
        for (int i = 0; i < lstproNotIn.size(); i++) {
            Product get = lstproNotIn.get(i);
            System.out.println(pro_name + "--" + get.getPro_name());
            if (pro_name.equals(get.getPro_name())) {
                System.out.println("Sai mà");
                count = count + 1;
            }
        }
        if (count > 0) {
            check = true;
            System.out.println(count);
        }
        System.out.println(check);
        return check;
    }

    public boolean checkname(String pro_name) throws SQLException {
        DatabaseHelper Db = new DatabaseHelper();
        ArrayList<Product> lstproNotIn = new ArrayList<>();
        int count = 0;
        boolean check = false;
        CallableStatement castm = Db.getConnection().prepareCall(Constant.PROC_CHECK_NAME_PRO);
        ResultSet rs = castm.executeQuery();

        while (rs.next()) {
            Product pro = new Product(rs.getInt("pro_id"), rs.getInt("cat_id"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getInt("pro_status"), rs.getString("pro_desc"), rs.getInt("sort"));
            lstproNotIn.add(pro);

        }
        for (int i = 0; i < lstproNotIn.size(); i++) {
            Product get = lstproNotIn.get(i);
            System.out.println(pro_name + "--" + get.getPro_name());
            if (pro_name.equals(get.getPro_name())) {
                System.out.println("Sai mà");
                count = count + 1;
            }
        }
        if (count > 0) {
            check = true;
            System.out.println(count);
        }
        System.out.println(check);
        return check;
    }

    public void Updatepro(Product pro) throws SQLException {
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

    public void InSertPro(Product pro) throws SQLException {
        DatabaseHelper db = new DatabaseHelper();
        CallableStatement catm = db.getConnection().prepareCall(Constant.PROC_ADD_PRO);
        catm.setString(1, pro.getPro_name());
        catm.setInt(2, pro.getCat_id());
        catm.setFloat(3, pro.getPro_price());
        catm.setString(4, pro.getPro_desc());
        catm.setInt(5, pro.getPro_status());
        catm.setInt(6, pro.getSort());
        int count = catm.executeUpdate();
        if (count > 0) {
            System.out.println("update success");
        } else {
            System.out.println("update fail");
        }

    }

    public boolean DeletePro(int pro_id) throws SQLException {
        DatabaseHelper db = new DatabaseHelper();
        CallableStatement catm = db.getConnection().prepareCall(Constant.PROC_DELETE_PRODUCT);
        catm.setInt(1, pro_id);
        int check = catm.executeUpdate();
        if (check > 0) {
            System.out.println("Delete Product_id =" + pro_id);
            return true;
        }

        return false;

    }

}
