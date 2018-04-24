/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImplementInterface;

import Entity.User;
import Interface.UserInterface;
import Utils.Constant;
import Utils.DatabaseHelper;
import Utils.Security;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang's PC
 */
public class UserImpl implements UserInterface {

    @Override
    public void insert(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int userID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<User> getAllUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean login(User u) {
        DatabaseHelper dtb = new DatabaseHelper();
        try {
            String param[] = new String[]{u.getUsername(), u.getPassword()};
            ResultSet rs = dtb.selectData(Constant.SQL_LOGIN, param);
            if (rs != null) {
                rs.last();
                if (rs.getRow() > 0) {
                    Security.curentLogin = u;
                    return true;
                } else {
                    return false;
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Lỗi truy cập CSDL");
        } finally {
            dtb.closeDB();

        }
        return false;
    }

}
