/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImplementInterface;

import Interface.OrderInterface;
import Utils.Constant;
import Utils.DatabaseHelper;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang's PC
 */
public class OrderImpl {

    public void InsertOrder(String param[]) {
        DatabaseHelper dtb = new DatabaseHelper();
        try {
            dtb.insertData(Constant.SQL_ORDER_INSERT, param);

        } catch (SQLException ex) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
