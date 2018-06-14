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
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
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

    private String setTitle() {
        String title;
        if (getid() != "") {
            title = "Mã nhân viên: " + getid();
        } else {
            title = "Thông tin chi tiết";
        }
        return title;
    }

    UserInterface ui = new UserImpl();
//    DefaultTableModel dtm;
//    DefaultComboBoxModel dcbm;
    public ArrayList<User> lstUser;
    String strSetModel[] = new String[]{"Mã nhân viên", "Tên nhân viên", "Tên đăng nhập"};
    DefaultTableModel dtm = new DefaultTableModel(strSetModel, 0);
    DefaultComboBoxModel dcbm = new DefaultComboBoxModel<>(strSetModel);
    int curentId = Security.curentLogin.getUserId();

    private void setModel() {
        tblUser.setDefaultEditor(Object.class, null);
        jComboBox1.setModel(dcbm);
    }

    protected void setData() {
//        String strSort[] = new String[]{"Mã nhân viên", "Mã nhân viên(giảm)",
//            "Tên nhân viên", "Tên nhân viên (giảm)", "Tên đăng nhập", "Tên đăng nhập (giảm)"};
        lstUser = ui.getAllUserExceptCurrent(curentId);
        dtm.setRowCount(0);
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
            DialogThongBao.showError(this, "Lỗi", "File đang được sử dụng!\n"
                    + "Vui lòng đặt một tên khác!");
        } catch (IOException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public QuanLyNhanVien() {
        initComponents();
        setData();
        setModel();
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
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblUserID = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        lblUserRole = new javax.swing.JLabel();
        tfUserName = new javax.swing.JLabel();
        tfUserUserName = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBirth = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btn_exportExcel = new javax.swing.JButton();
        btn_addNew = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel8.setText("Mã nhân viên:");

        jLabel9.setText("Tên nhân viên:");

        jLabel10.setText("Tên đăng nhập:");

        jLabel11.setText("Địa chỉ:");

        jLabel12.setText("Chức vụ:");

        lblUserID.setText("...");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Delete-group-icon.png"))); // NOI18N
        jButton7.setText("Xóa");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        lblUserRole.setText("...");

        tfUserName.setText("...");

        tfUserUserName.setText("...");

        lblAddress.setText("...");

        jLabel2.setText("Ngày sinh:");

        lblBirth.setText("...");

        jLabel4.setText("Giới tính:");

        jLabel5.setText("...");

        jLabel3.setText("Số điện thoại:");

        lblPhone.setText("...");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblUserID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblBirth, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                            .addComponent(lblUserRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPhone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                    .addComponent(tfUserUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(tfUserName))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(tfUserUserName))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(lblAddress))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel12))
                    .addComponent(lblUserRole, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBirth)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(lblPhone)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jButton7))
        );

        tfSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSearchActionPerformed(evt);
            }
        });

        jButton2.setText("Tìm theo tên");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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

        jButton3.setText("Sắp xếp");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);

        btn_exportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/document-excel-icon.png"))); // NOI18N
        btn_exportExcel.setText("Xuất file Excel");
        btn_exportExcel.setFocusable(false);
        btn_exportExcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_exportExcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_exportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportExcelActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_exportExcel);

        btn_addNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Add-group-icon.png"))); // NOI18N
        btn_addNew.setText("Thêm mới");
        btn_addNew.setFocusable(false);
        btn_addNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_addNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_addNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addNewActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_addNew);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Danh sách nhân viên hiện hành");

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
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                        .addGap(50, 50, 50)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2)
                    .addComponent(tfSearch)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btn_addNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addNewActionPerformed
        // TODO add your handling code here:
        JInternalFrame add = new AddNewUser();
        showFrame(add);
    }//GEN-LAST:event_btn_addNewActionPerformed

    private void btn_exportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportExcelActionPerformed
        // TODO add your handling code here:
        this.writeExcelFile();
    }//GEN-LAST:event_btn_exportExcelActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        sort();
    }//GEN-LAST:event_jButton3ActionPerformed

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblUserjTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserjTable1MouseClicked
        String UserId = getid();
        for (int i = 0; i < lstUser.size(); i++) {
            User get = lstUser.get(i);
            if (UserId.equals(String.valueOf(get.getUserId()))) {
                lblUserID.setText(String.valueOf(get.getUserId()));
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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String UserId = tblUser.getValueAt(tblUser.getSelectedRow(), 0).toString();
        int userid = Integer.parseInt(UserId);
        if (DialogThongBao.showAlert(this, "Xóa", "Bạn có muốn xóa?") == 0) {
            ui.delete(userid);
            DialogThongBao.showSuccess(this, "Xóa", "Đã xóa!");
            setData();
            lblUserID.setText("");
            tfUserName.setText("");
            tfUserUserName.setText("");
            lblAddress.setText("");
            lblUserRole.setText("");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tfSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addNew;
    private javax.swing.JButton btn_exportExcel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblBirth;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblUserID;
    private javax.swing.JLabel lblUserRole;
    public javax.swing.JTable tblUser;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JLabel tfUserName;
    private javax.swing.JLabel tfUserUserName;
    // End of variables declaration//GEN-END:variables

}
