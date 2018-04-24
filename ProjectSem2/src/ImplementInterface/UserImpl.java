/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImplementInterface;

import Entity.User;
import Interface.UserInterface;
import InternalFrame.DangNhap;
import Utils.Constant;
import Utils.DatabaseHelper;
import Utils.Security;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
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
    public User getUserById(int Id) {
        User u = new User();
        try {
            DatabaseHelper dtb = new DatabaseHelper();
            String param = String.valueOf(Id);
            ResultSet rs = dtb.selectData(Constant.SQL_GETUSERBYID, param);
            while (rs.next()) {
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setUserRole(rs.getInt("user_role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
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
    
    @Override
    public User getUser(User u) {
        User user = new User();
        try {
            DatabaseHelper dtb = new DatabaseHelper();
            String param[] = new String[]{u.getUsername(), u.getPassword()};
            ResultSet rs = dtb.selectData(Constant.SQL_LOGIN, param);
            while (rs.next()) {
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUserRole(rs.getInt("user_role"));
                user.setName(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
}
