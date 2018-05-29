/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Locale;

/**
 *
 * @author MinhVuFC
 */
public class Constant {

    public static Locale locale = new Locale("vi", "VN");

    public static String host = "localhost\\DESKTOP-755C9DH\\SQLEXPRESS"; // localhost\\MINHVUFC-PC\\SQLEXPRESS2014
    public static String port = "1433"; // 3306
    public static String schema = "QuanLyCuaHang";
    public static String username = "sa"; // root
    public static String password = "123456"; // 

    // Thông điệp
    public static String THONG_BAO_DANG_NHAP = "Thông báo đăng nhập";
    public static String SAI_TEN_MAT_KHAU = "Sai tên hoặc mật khẩu, vui lòng thử lại!";
    public static String DANG_NHAP_THANH_CONG = "Đăng nhập thành công";

    public static final int QUAN_LY_TAI_SAN = 1;
    public static final int QUAN_LY_NHAN_VIEN = 2;

    public static final int FLAG_ERROR = 1;
    public static final int FLAG_SUCCESS = 2;

    public static final int ACTION_INSERT = 1;
    public static final int ACTION_UPDATE = 2;
    public static final int ACTION_DELETE = 3;

    // Thông điệp
    public static final String MSG_UPDATE = "Cập nhật dữ liệu";
    public static final String MSG_ERROR_UPDATE = "Cập nhật dữ liệu không thành công";
    public static final String MSG_SUCCESS_UPDATE = "Cập nhật dữ liệu thành công";
    public static String MSG_THONG_BAO_DANG_NHAP = "Thông báo đăng nhập";
    public static String MSG_SAI_TEN_MAT_KHAU = "Sai tên hoặc mật khẩu, vui lòng thử lại!";
    public static String MSG_DANG_NHAP_THANH_CONG = "Đăng nhập thành công";
    public static String MSG_XAC_NHAN_XOA = "Bạn chắc chắn muốn xóa";
    public static String MSG_ADD = "Thêm dữ liệu";
    public static String MSG_ADD_USED = "Trùng tên đăng nhập";
    public static String MSG_ADD_NULL = "Không được để trống trường";
    public static String MSG_ADD_SUCCESS = "Thêm dữ liệu thành công";
    public static String MSG_ADD_NOT_EQUAL = "Hai mật khẩu không trùng khớp";
    public static String MSG_UPDATE_PRO= "Sửa dữ liệu";
    public static String MSG_UPDATE_PRO_NOT_NUL_NAME = "Không Được để trống tên";
    public static String MSG_UPDATE_PRO_NOT_NUL_PRICE = "Không Được để trống Giá";
    public static String MSG_DELETE_PRO= "Xóa Dữ Liệu";
    public static String MSG_DELETE_PRO_SUCCESS = "Xóa Sản Phẩm Thành Công";
    public static String MSG_DELETE_PRO_ERROR = "Xóa Sản Phẩm Thất Bại";
    
    

    // Cú pháp SQL
    public static String SQL_LOGIN = "SELECT * FROM [user] WHERE username = ? AND password = ?";
    public static String SQL_GET_ALL_USER = "SELECT [user_id], name, username, "
            + "password, user_role, [role].role_name FROM [user] INNER JOIN role"
            + " ON [user].user_role = [role].role_id";
    public static String SQL_GET_ALL_USER1 = "SELECT [user_id], name, username, "
            + "password, user_role, [role].role_name FROM [user] INNER JOIN role"
            + " ON [user].user_role = [role].role_id WHERE user_id != ";
    public static String SQL_GET_ROLE_TO_SET = "SELECT * FROM [role]";
    public static String SQL_GET_USER_BY_ID = "SELECT * FROM [user] WHERE user_id = ?";
    public static String SQL_INSERT_USER = "INSERT INTO [user] ([name] ,[username] ,[password] ,[user_role]) VALUES (?, ?, ?, ?)";
    public static String SQL_DELETE_USER_BY_ID = "DELETE FROM [user] WHERE user_id = ?";
    public static String SQL_GET_SORTED_USER = "SELECT [user_id], name, username, "
            + "password, user_role, [role].role_name FROM [user] INNER JOIN role ON [user].user_role = [role].role_id ORDER BY ";

    // Nhóm quyền
    public static String SQL_SELECT_NHOM_QUYEN = "SELECT * FROM tbl_nhom_quyen";
    public static String SQL_INSERT_NHOM_QUYEN = "INSERT INTO tbl_nhom_quyen ("
            + "ma_nhom_quyen, ten_nhom_quyen, ghi_chu) VALUES ("
            + "?, ?, ?);";
    public static String SQL_UPDATE_NHOM_QUYEN = "UPDATE tbl_nhom_quyen SET "
            + "ma_nhom_quyen=?,"
            + "ten_nhom_quyen=?,"
            + "ghi_chu=? WHERE id = ?";
    public static String SQL_DELETE_NHOM_QUYEN = "DELETE FROM tbl_nhom_quyen WHERE id = ?";

    // Hiện trạng
    public static String SQL_SELECT_HIEN_TRANG = "SELECT * FROM tbl_hientrang";
    public static String SQL_INSERT_HIEN_TRANG = "INSERT INTO tbl_hientrang ("
            + "ten_hientrang, ghi_chu) VALUES ("
            + "?, ?);";
    public static String SQL_UPDATE_HIEN_TRANG = "UPDATE tbl_hientrang SET "
            + "ten_hientrang=?,"
            + "ghi_chu=? WHERE id = ?";
    public static String SQL_DELETE_HIEN_TRANG = "DELETE FROM tbl_hientrang WHERE id = ?";

    // Nhóm tài sản
    public static String SQL_SELECT_NHOM_TAI_SAN = "SELECT * FROM tbl_loai_taisan";
    public static String SQL_INSERT_NHOM_TAI_SAN = "INSERT INTO tbl_loai_taisan(ten_loai_taisan, ghi_chu) "
            + "VALUES (?,?)";
    public static String SQL_UPDATE_NHOM_TAI_SAN = "UPDATE tbl_loai_taisan "
            + "SET ten_loai_taisan=?,"
            + "ghi_chu=? WHERE id = ?";
    public static String SQL_DELETE_NHOM_TAI_SAN = "DELETE FROM tbl_loai_taisan WHERE id = ?";
    // san pham 
     public  static String SQL_SELECT_ALL_SAN_PHAM = " SELECT * from product ";
     
     public static String PROC_UPDATE_PRO = "{Call updatesanpham(?,?,?,?,?,?,?)}";
     public static String PROC_ADD_CAT = "{Call addCategory(?,?)}";
     public static String PROC_UPDATE_CAT = "{Call updateCategory(?,?,?)}";
     public static String PROC_DELETE_PRODUCT = "{Call deleteSanPham(?)}";
     public static String PROC_NOT_IN_PRO = "{Call getAllProNotIn(?)}";
     public static String PROC_CHECK_NAME_PRO = "{Call checkname}";
     public static String PROC_ADD_PRO = "{Call addsanpham(?,?,?,?,?,?)}";
     public static String PROC_CHECK_NAME_CAT_EDIT = "{Call checkNameEdit(?)}";
     
     
     
     // category 
     
     public static String SQL_SELECT_ALL_CATEGORY = "SELECT * FROM category";
    // Khu vuc
    public static String SQL_SELECT_PHONG_BAN = "SELECT * FROM tbl_phongban_khuvuc";
    public static String SQL_INSERT_PHONG_BAN = "INSERT INTO tbl_phongban_khuvuc ("
            + "ma_pb_kv, ten_pb_kv, mo_ta) VALUES ("
            + "?, ?, ?);";
    public static String SQL_UPDATE_PHONG_BAN = "UPDATE tbl_phongban_khuvuc SET "
            + "ma_pb_kv=?,"
            + "ten_pb_kv=?,"
            + "mo_ta=? WHERE id = ?";
    public static String SQL_DELETE_PHONG_BAN = "DELETE FROM tbl_phongban_khuvuc WHERE id = ?";

    // Nhà Cung Cấp
    public static String SQL_SELECT_NHA_CUNGCAP = "SELECT * FROM tbl_nha_cungcap";
    public static String SQL_INSERT_NHA_CUNGCAP = "INSERT INTO tbl_nha_cungcap"
            + "(ten_nha_cungcap, dia_chi, so_dienthoai, fax, email, ghi_chu)"
            + " VALUES (?,?,?,?,?,?)";
    public static String SQL_UPDATE_NHA_CUNGCAP = "UPDATE tbl_nha_cungcap SET "
            + "id=?,"
            + "ten_nha_cungcap=?,"
            + "dia_chi=?,"
            + "so_dienthoai=?,"
            + "fax=?,"
            + "email=?,"
            + "ghi_chu=? WHERE id = ?";
    public static String SQL_DELETE_NHA_CUNGCAP = "DELETE FROM tbl_nha_cungcap WHERE id = ?";
}
