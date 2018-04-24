/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.itrd.qlts.utils;

import bkap.itrd.qlts.internalframe.DangNhap;
import bkap.itrd.qlts.internalframe.QuanLyNhanVien;
import bkap.itrd.qlts.internalframe.QuanLyTaiSan;
import javax.swing.JInternalFrame;

/**
 *
 * @author MinhVuFC
 */
public class InternalFrameFactory {

    public JInternalFrame getInternalFrame(int id) {
        switch (id) {
            case Constants.QUAN_LY_TAI_SAN:
                return new QuanLyTaiSan();
            case Constants.QUAN_LY_NHAN_VIEN:
                return new QuanLyNhanVien();
            default:
                return DangNhap.getInstance();
        }
    }
}
