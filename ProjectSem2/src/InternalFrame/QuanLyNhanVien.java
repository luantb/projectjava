/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalFrame;

import Entity.User;
import ImplementInterface.UserImpl;
import Interface.UserInterface;
import Utils.Constant;
import Utils.DatabaseHelper;
import Utils.DialogThongBao;
import Utils.Security;
import Utils.Utils;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Hoang's PC
 */
public class QuanLyNhanVien extends javax.swing.JInternalFrame {

    /**
     * Creates new form AddNew
     */
    public String getid() {
        String UserId = tblUser.getValueAt(tblUser.getSelectedRow(), 0).toString();
        if (UserId != null) {
            return UserId;
        } else {
            return "";
        }
    }
    public String mannv, tennv, tendn, title, maintitle;

    public void changeLanguage() {
        ResourceBundle rb = Utils.getLocale();
        this.title = rb.getString("titleborder");
        this.mannv = rb.getString("employid");
        this.tennv = rb.getString("employname");
        this.tendn = rb.getString("employuser");
        this.maintitle = rb.getString("maintitle");
        btn_xuatfileexcel.setText(rb.getString("exportexcel"));
        btn_themmoi.setText(rb.getString("addnew"));
        danhsachnv.setText(rb.getString("listemploye"));
        btn_searchbyname.setText(rb.getString("searchbyname"));
        btn_sort.setText(rb.getString("sort"));
        tennhanvien.setText(rb.getString("name"));
        tendangnhap.setText(rb.getString("username"));
        diachi.setText(rb.getString("address"));
        chucvu.setText(rb.getString("regency"));
        ngaysinh.setText(rb.getString("birthday"));
        gioitinh.setText(rb.getString("sex"));
        sodienthoai.setText(rb.getString("numberphone"));
        btn_xoa.setText(rb.getString("delete"));
    }

    UserInterface ui = new UserImpl();
//    DefaultTableModel dtm;
//    DefaultComboBoxModel dcbm;
    public ArrayList<User> lstUser;
    DefaultTableModel dtm;
    DefaultComboBoxModel dcbm;
    int curentId = Security.curentLogin.getUserId();

    private void setModel() {
        tblUser.setDefaultEditor(Object.class, null);
        TitledBorder tbd = new TitledBorder(this.title);
        jPanel3.setBorder(tbd);
        this.setTitle(this.maintitle);
        String strSetModel[] = new String[]{mannv, tennv, tendn};
        dcbm = new DefaultComboBoxModel<>(strSetModel);
        jComboBox1.setModel(dcbm);
    }

    protected void setData() {
//        String strSort[] = new String[]{"Mã nhân viên", "Mã nhân viên(giảm)",
//            "Tên nhân viên", "Tên nhân viên (giảm)", "Tên đăng nhập", "Tên đăng nhập (giảm)"};
        lstUser = ui.getAllUserExceptCurrent(curentId);
        String strSetModel[] = new String[]{mannv, tennv, tendn};
        dtm = new DefaultTableModel(strSetModel, 0);
//        dtm.setRowCount(0);
        for (int i = 0; i < lstUser.size(); i++) {
            User get = lstUser.get(i);
            Object data[] = {get.getUserId(), get.getName(), get.getUsername()};
            dtm.addRow(data);
        }
        tblUser.setModel(dtm);
    }

    private boolean checkOnly(JInternalFrame innerFrame) {
        JInternalFrame[] arrFrame = jDesktopPane1.getAllFrames();
        for (JInternalFrame frame : arrFrame) {
            if (frame.getClass().getName() == innerFrame.getClass().getName()) {
                return true;
            }
        }
        return false;
    }

    public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension jInternalFrameSize = jif.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }

    private void showFrame(JInternalFrame jintr) {
        if (!checkOnly(jintr)) {
            jDesktopPane1.add(jintr);
            centerJIF(jintr);
        }
    }

    public void sort() {
        dtm.setRowCount(0);
        String str = (String) jComboBox1.getSelectedItem();
        DatabaseHelper dtb = new DatabaseHelper();
        String strSort = "name";
        ArrayList<User> lstU = new ArrayList<>();
        try {
            switch (str) {
                case "Mã nhân viên":
                    strSort = "user_id";
                    break;

                case "Mã nhân viên (giảm)":
                    break;

                case "Tên nhân viên":
                    strSort = "name";
                    break;

                case "Tên nhân viên (giảm)":
                    break;

                case "Tên đăng nhập":
                    strSort = "username";
                    break;

                case "Tên đăng nhập (giảm)":
                    break;
            }

            ResultSet rs = dtb.selectData(Constant.SQL_GET_ALL_USER1 + "ORDER BY " + strSort, curentId);
            while (rs.next()) {
                User u = new User(rs.getInt("user_id"), rs.getString("name"),
                        rs.getString("username"), rs.getString("password"),
                        rs.getInt("user_role"), rs.getString("role_name"));
                lstU.add(u);
            }
            for (int i = 0; i < lstU.size(); i++) {
                User get = lstU.get(i);
                dtm.addRow(get.toArray());
            }
            tblUser.setModel(dtm);

        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String chooseFilepath() {
// Dùng dialog chọn file lấy về đường dẫn để ghi file
        File file = null;
        jFileChooser1.setCurrentDirectory(new File("C:\\Users\\ThinkPad\\Desktop"));
        int chooser = jFileChooser1.showSaveDialog(this);
        if (chooser == JFileChooser.APPROVE_OPTION) {
            file = jFileChooser1.getSelectedFile();
        }
        return file.toString();
    }

    public void writeExcelFile() {
//        Khai báo các biến để cài đặt trong file excel
        HSSFWorkbook hw = new HSSFWorkbook();
        HSSFSheet hs = hw.createSheet(Security.curentLogin.getName()); // Tạo sheet
        Cell cell;
        int rownum = 0;
        Row row = hs.createRow(rownum);

        lstUser = ui.getAllUser();  // Lấy thông tin từ danh sách User để set data cho file 

//        Tạo các dòng đầu cố định
        cell = row.createCell(0);
        cell.setCellValue("Mã nhân viên");

        cell = row.createCell(1);
        cell.setCellValue("Tên nhân viên");

        cell = row.createCell(2);
        cell.setCellValue("Tên đăng nhập");

        cell = row.createCell(3);
        cell.setCellValue("Địa chỉ");

        cell = row.createCell(4);
        cell.setCellValue("Chức vụ");

        cell = row.createCell(5);
        cell.setCellValue("Ngày sinh");

        cell = row.createCell(6);
        cell.setCellValue("Giới tính");

        cell = row.createCell(7);
        cell.setCellValue("Số điện thoại");

//        Lấy data và set giá trị cho các ô
        for (int i = 0; i < lstUser.size(); i++) { // Vòng for lặp theo chiều từ trên xuống dưới trong file excel
            User get = lstUser.get(i);
            rownum++;
            row = hs.createRow(rownum);

            cell = row.createCell(0);
            cell.setCellValue(get.getUserId());

            cell = row.createCell(1);
            cell.setCellValue(get.getName());

            cell = row.createCell(2);
            cell.setCellValue(get.getUsername());

            cell = row.createCell(3);
            cell.setCellValue(get.getAddress());

            cell = row.createCell(4);
            cell.setCellValue(get.getRole());

            cell = row.createCell(5);
            cell.setCellValue(get.getBirthday());

            cell = row.createCell(6);
            String sex;
            if (get.getSex() == 1) {
                sex = "Nam";
            } else if (get.getSex() == 2) {
                sex = "Nữ";
            } else {
                sex = "Không xác định";
            }
            cell.setCellValue(sex);

            cell = row.createCell(7);
            cell.setCellValue(get.getPhone());

        }
//        Ghi file

        File file = new File(chooseFilepath() + ".xls");
        file.getParentFile().mkdirs();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            hw.write(fos);
            DialogThongBao.showSuccess(this, "Thông báo", "Ghi file thành công\n"
                    + "Đường dẫn: " + file.getAbsolutePath());
        } catch (FileNotFoundException ex) {
            DialogThongBao.showError(this, "Lỗi", "File đang được sử dụng bởi một ứng dụng khác!");
        } catch (IOException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public QuanLyNhanVien() {
        initComponents();
        changeLanguage();
        setModel();
        setData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        tennhanvien = new javax.swing.JLabel();
        tendangnhap = new javax.swing.JLabel();
        diachi = new javax.swing.JLabel();
        chucvu = new javax.swing.JLabel();
        btn_xoa = new javax.swing.JButton();
        lblUserRole = new javax.swing.JLabel();
        tfUserName = new javax.swing.JLabel();
        tfUserUserName = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        ngaysinh = new javax.swing.JLabel();
        lblBirth = new javax.swing.JLabel();
        gioitinh = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sodienthoai = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        btn_searchbyname = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        btn_sort = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btn_xuatfileexcel = new javax.swing.JButton();
        btn_themmoi = new javax.swing.JButton();
        danhsachnv = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Quản Lý Nhân Viên");

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserjTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblUser);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tennhanvien.setText("Tên nhân viên:");

        tendangnhap.setText("Tên đăng nhập:");

        diachi.setText("Địa chỉ:");

        chucvu.setText("Chức vụ:");

        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Delete-group-icon.png"))); // NOI18N
        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        lblUserRole.setText("...");

        tfUserName.setText("...");

        tfUserUserName.setText("...");

        lblAddress.setText("...");

        ngaysinh.setText("Ngày sinh:");

        lblBirth.setText("...");

        gioitinh.setText("Giới tính:");

        jLabel5.setText("...");

        sodienthoai.setText("Số điện thoại:");

        lblPhone.setText("...");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gioitinh, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ngaysinh, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chucvu, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(diachi, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tendangnhap, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tennhanvien, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBirth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblUserRole, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfUserUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sodienthoai)
                        .addGap(18, 18, 18)
                        .addComponent(lblPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_xoa)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tennhanvien)
                    .addComponent(tfUserName))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tendangnhap)
                    .addComponent(tfUserUserName))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(diachi)
                    .addComponent(lblAddress))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(chucvu))
                    .addComponent(lblUserRole, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBirth)
                    .addComponent(ngaysinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sodienthoai)
                        .addComponent(lblPhone))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(gioitinh)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(btn_xoa))
        );

        tfSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSearchActionPerformed(evt);
            }
        });

        btn_searchbyname.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/search-icon.png"))); // NOI18N
        btn_searchbyname.setText("Tìm theo tên");
        btn_searchbyname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchbynameActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item1", "Item2" }));
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBox1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBox1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jComboBox1MouseReleased(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        btn_sort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/sort-columns-icon.png"))); // NOI18N
        btn_sort.setText("Sắp xếp");
        btn_sort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sortActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);

        btn_xuatfileexcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/document-excel-icon.png"))); // NOI18N
        btn_xuatfileexcel.setText("Xuất file Excel");
        btn_xuatfileexcel.setFocusable(false);
        btn_xuatfileexcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_xuatfileexcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_xuatfileexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatfileexcelActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_xuatfileexcel);

        btn_themmoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Add-group-icon.png"))); // NOI18N
        btn_themmoi.setText("Thêm mới");
        btn_themmoi.setFocusable(false);
        btn_themmoi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_themmoi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_themmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themmoiActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_themmoi);

        danhsachnv.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        danhsachnv.setForeground(new java.awt.Color(0, 153, 204));
        danhsachnv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        danhsachnv.setText("Danh sách nhân viên hiện hành");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(tfSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_searchbyname)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_sort, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(danhsachnv, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                        .addGap(50, 50, 50)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(danhsachnv)
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_searchbyname)
                    .addComponent(tfSearch)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_sort)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jDesktopPane1.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_themmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themmoiActionPerformed
        // TODO add your handling code here:
        JInternalFrame add = new AddNewUser();
        showFrame(add);
    }//GEN-LAST:event_btn_themmoiActionPerformed

    private void btn_xuatfileexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuatfileexcelActionPerformed
        // TODO add your handling code here:
        this.writeExcelFile();
    }//GEN-LAST:event_btn_xuatfileexcelActionPerformed

    private void btn_sortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sortActionPerformed
        // TODO add your handling code here:
        sort();
    }//GEN-LAST:event_btn_sortActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseReleased

    private void jComboBox1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MousePressed

    private void jComboBox1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseEntered

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void btn_searchbynameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchbynameActionPerformed
        // TODO add your handling code here:
        String str = tfSearch.getText();
        if (str.length() == 0) {
            DialogThongBao.showError(this, "Lỗi", "Vui lòng nhập vào ô tìm kiếm");
        } else {

            ArrayList<User> lstUser = ui.getUserbyName(str);
            dtm.setRowCount(0);
            for (int i = 0; i < lstUser.size(); i++) {
                User get = lstUser.get(i);
                Object data[] = {get.getUserId(), get.getName(), get.getUsername()};
                dtm.addRow(data);
            }
            tblUser.setModel(dtm);
        }
    }//GEN-LAST:event_btn_searchbynameActionPerformed

    private void tblUserjTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserjTable1MouseClicked
        String UserId = getid();
        for (int i = 0; i < lstUser.size(); i++) {
            User get = lstUser.get(i);
            if (UserId.equals(String.valueOf(get.getUserId()))) {
                TitledBorder tbd = new TitledBorder(this.mannv + ": " + String.valueOf(get.getUserId()));
                jPanel3.setBorder(tbd);
                tfUserName.setText(get.getName());
                tfUserUserName.setText(get.getUsername());
                lblUserRole.setText(get.getRole());
                lblAddress.setText(get.getAddress());
                lblBirth.setText(get.getBirthday());
                if (get.getSex() == 1) {
                    jLabel5.setText("Nam");
                } else {
                    jLabel5.setText("Nữ");
                }
                lblPhone.setText(get.getPhone());
            }
        }
    }//GEN-LAST:event_tblUserjTable1MouseClicked

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        String UserId = tblUser.getValueAt(tblUser.getSelectedRow(), 0).toString();
        int userid = Integer.parseInt(UserId);
        if (DialogThongBao.showAlert(this, "Xóa", "Bạn có muốn xóa?") == 0) {
            ui.delete(userid);
            DialogThongBao.showSuccess(this, "Xóa", "Đã xóa!");
            setData();
            TitledBorder tbd = new TitledBorder(this.title);
            jPanel3.setBorder(tbd);
            tfUserName.setText("");
            tfUserUserName.setText("");
            lblAddress.setText("");
            lblUserRole.setText("");
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void tfSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_searchbyname;
    private javax.swing.JButton btn_sort;
    private javax.swing.JButton btn_themmoi;
    public javax.swing.JButton btn_xoa;
    private javax.swing.JButton btn_xuatfileexcel;
    private javax.swing.JLabel chucvu;
    private javax.swing.JLabel danhsachnv;
    private javax.swing.JLabel diachi;
    private javax.swing.JLabel gioitinh;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblBirth;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblUserRole;
    private javax.swing.JLabel ngaysinh;
    private javax.swing.JLabel sodienthoai;
    public javax.swing.JTable tblUser;
    private javax.swing.JLabel tendangnhap;
    private javax.swing.JLabel tennhanvien;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JLabel tfUserName;
    private javax.swing.JLabel tfUserUserName;
    // End of variables declaration//GEN-END:variables

}
