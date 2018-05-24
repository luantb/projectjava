/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import InternalFrame.AddNewCat;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author MinhVuFC
 */
public class DialogThongBao {

    public static void showError(Component parent, String tieuDe, String noidung) {
        JOptionPane.showMessageDialog(parent, noidung, tieuDe, JOptionPane.ERROR_MESSAGE);
    }

    public static void showSuccess(Component parent, String tieuDe, String noidung) {
        JOptionPane.showMessageDialog(parent, noidung, tieuDe, JOptionPane.INFORMATION_MESSAGE);
    }

    public static int showAlert(Component parent, String tieuDe, String noidung) {
        return JOptionPane.showOptionDialog(parent, noidung, tieuDe, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Đồng ý", "Hủy"}, null);
    }

    public static void showSuccess(AddNewCat aThis, String đã_xóa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
