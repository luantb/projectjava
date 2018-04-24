/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhVuFC
 */
public class DatabaseHelper {

    private Connection connection;

//    public Connection getConnection() {
//        return connection;
//    }
//    private static DatabaseHelper instance = new DatabaseHelper();
    public DatabaseHelper() {
        try {
            // connectMySQL();
            connectMicrosoftSQLServer();
            System.out.println("Kết nối thành công");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.err.println("Lỗi: " + ex.getMessage());
        }
    }

    private void connectMicrosoftSQLServer() throws ClassNotFoundException, SQLException {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        Class.forName(driver);
        this.connection = DriverManager.getConnection(
                "jdbc:sqlserver://" + Constant.host + ":" + Constant.port + ";databaseName=" + Constant.schema, Constant.username, Constant.password);
    }

    private void connectMySQL() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
        this.connection = DriverManager.getConnection("jdbc:mysql://" + Constant.host
                + ":" + Constant.port + "/" + Constant.schema + "?useUnicode=true&characterEncoding=UTF8", Constant.username, Constant.password);
    }

    private <E> PreparedStatement getPrepareStatement(boolean isInsert, String sql, E... args) throws SQLException {
        PreparedStatement pstm;
        if (isInsert) {
            // 1. Tạo PreparedStatement với tùy chọn lấy về danh sách ID của  dòng trong câu lệnh insert
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } else {
            // 2. Tạo PreparedStatement với tùy chọn ResultSet cho phép cuộn và cập nhật dữ liệu
            pstm = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Integer) {
                pstm.setInt(i + 1, (Integer) args[i]);
            } else if (args[i] instanceof Float) {
                pstm.setFloat(i + 1, (Float) args[i]);
            } else if (args[i] instanceof Double) {
                pstm.setDouble(i + 1, (Double) args[i]);
            } else if (args[i] instanceof Long) {
                pstm.setLong(i + 1, (Long) args[i]);
            } else if (args[i] instanceof String) {
                pstm.setString(i + 1, (String) args[i]);
            }
        }
        return pstm;
    }

    /**
     * Hàm lấy về dữ liệu: SELECT
     *
     * @param sql cú pháp SQL kèm tham số
     * @param args mảng tham số truyền vào
     */
    public <E> ResultSet selectData(String sql, E... args) throws SQLException {
        PreparedStatement pstm = getPrepareStatement(false, sql, args);
        return pstm.executeQuery();
    }

    /**
     * Hàm cập nhật dữ liệu: INSERT | UPFATE | DELETE
     *
     * @param sql cú pháp SQL kèm tham số
     * @param args mảng tham số truyền vào
     */
    public <E> int updateData(String sql, E... args) throws SQLException {
        PreparedStatement pstm = getPrepareStatement(false, sql, args);
        return pstm.executeUpdate();
    }

    /**
     * Hàm cập nhật dữ liệu: INSERT
     *
     * @param sql cú pháp SQL kèm tham số
     * @param args mảng tham số truyền vào
     */
    public <E> ResultSet insertData(String sql, E... args) throws SQLException {
        PreparedStatement pstm = getPrepareStatement(true, sql, args);
        if (pstm.executeUpdate() > 0) {
            return pstm.getGeneratedKeys();
        } else {
            return null;
        }
    }

    public void closeDB() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
