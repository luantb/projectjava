/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalFrame;

import Entity.Category;
import Entity.Product;
import ImplementInterface.*;
import Utils.Constant;
import Utils.DialogThongBao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hoang's PC
 */
public class QuanLySanPham extends javax.swing.JInternalFrame {

    /**
     * Creates new form QuanLySanPham
     */
    ProductImpl Proimpl = new ProductImpl();
    categoryImpl catImpl = new categoryImpl();
    public ArrayList<Product> lstProducts;

    public ArrayList<Product> lstpro;
    public ArrayList<Category> lstCategory;
    String strSetModel[] = new String[]{"Mã Sp", "Danh mục", "Tên Sp", "Giá Sp", "Trạng thái", "Mô tả", "Số TT"};
    DefaultTableModel dtmd = new DefaultTableModel(strSetModel, 0);

    String setmodelSort[] = new String[]{"Mã Sp ", "Tên Sp", "Giá", "Còn hàng", "Hết Hàng"};
    DefaultComboBoxModel dcbm = new DefaultComboBoxModel<>(setmodelSort);
    DefaultComboBoxModel dcbm1;

    public void setModels() {
        tlbproducts.setDefaultEditor(Object.class, null);
        jComboBoxSort.setModel(dcbm);
    }

    public void setdata() throws SQLException {
        lstCategory = catImpl.getAllCat();
        lstProducts = Proimpl.getAllpro();
        dtmd.setRowCount(0);
        for (int i = 0; i < lstProducts.size(); i++) {
            Product get = lstProducts.get(i);
            String status, cat_name = null;
            for (int j = 0; j < lstCategory.size(); j++) {
                Category get_cat = lstCategory.get(j);

                if (get_cat.getCat_id() == get.getCat_id()) {
                    cat_name = get_cat.getCat_name();
                }
            }

            if (get.getPro_status() == 1) {
                status = "Còn Hàng";
            } else {
                status = "Hết Hàng";
            }
            Object data[] = {get.getPro_id(), cat_name, get.getPro_name(), get.getPro_price(), status, get.getPro_desc(), get.getSort()};
            dtmd.addRow(data);
        }
        tlbproducts.setModel(dtmd);
    }

    public QuanLySanPham() throws SQLException {
        initComponents();
        setModels();
        setdata();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buttonGroup_pro_status = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tlbproducts = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jComboBoxSort = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lblpro_id = new javax.swing.JLabel();
        lblproname = new javax.swing.JLabel();
        tf_proname = new javax.swing.JTextField();
        lblpro_price = new javax.swing.JLabel();
        lblpro_status = new javax.swing.JLabel();
        rd_conhang = new javax.swing.JRadioButton();
        rd_hethang = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tf_pro_desc = new javax.swing.JTextArea();
        lblpro_sort = new javax.swing.JLabel();
        tf_pro_sort = new javax.swing.JTextField();
        jButton316 = new javax.swing.JButton();
        bt_pro_add = new javax.swing.JButton();
        bt_pro_update = new javax.swing.JButton();
        bt_pro_delete = new javax.swing.JButton();
        tf_pro_id = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jcb_cat_name = new javax.swing.JComboBox<>();
        tf_pro_price = new javax.swing.JFormattedTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleName("");
        jPanel1.getAccessibleContext().setAccessibleDescription("");

        jLabel1.setText("jLabel1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tlbproducts.setModel(new javax.swing.table.DefaultTableModel(
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
        tlbproducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tlbproductsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tlbproducts);

        jComboBoxSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSortActionPerformed(evt);
            }
        });

        jButton1.setText("Tìm Kiếm");

        jButton2.setText("Sắp Xếp");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lblpro_id.setText("Mã Sản Phẩm:");

        lblproname.setText("Tên Sản Phẩm: ");

        lblpro_price.setText("Giá Sản phẩm:");

        lblpro_status.setText("Trạng Thái SP:");

        buttonGroup_pro_status.add(rd_conhang);
        rd_conhang.setText("Còn Hàng");
        rd_conhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_conhangActionPerformed(evt);
            }
        });

        buttonGroup_pro_status.add(rd_hethang);
        rd_hethang.setText("Hết Hàng");

        jLabel2.setText("Mô Tả :");

        tf_pro_desc.setColumns(20);
        tf_pro_desc.setRows(5);
        jScrollPane3.setViewportView(tf_pro_desc);

        lblpro_sort.setText("Thứ Tự HT:");

        jButton316.setText("Xuất Dữ Liệu");
        jButton316.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton316ActionPerformed(evt);
            }
        });

        bt_pro_add.setText("Thêm");
        bt_pro_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_pro_addActionPerformed(evt);
            }
        });

        bt_pro_update.setText("sửa");
        bt_pro_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_pro_updateActionPerformed(evt);
            }
        });

        bt_pro_delete.setText("Xóa");
        bt_pro_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_pro_deleteActionPerformed(evt);
            }
        });

        tf_pro_id.setText("...");

        jLabel3.setText("Danh mục:");

        jcb_cat_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tf_pro_price.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#################"))));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblpro_sort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblpro_status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblpro_price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblproname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblpro_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton316)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(tf_pro_sort)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jcb_cat_name, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tf_pro_price)
                    .addComponent(tf_proname)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(rd_conhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(108, 108, 108)
                        .addComponent(rd_hethang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(bt_pro_add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_pro_update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_pro_delete))
                    .addComponent(jScrollPane3)
                    .addComponent(tf_pro_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_pro_id)
                    .addComponent(lblpro_id))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_proname, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblproname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_pro_price, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblpro_price))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rd_conhang)
                    .addComponent(rd_hethang)
                    .addComponent(lblpro_status))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_pro_sort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblpro_sort))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcb_cat_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_pro_add)
                    .addComponent(bt_pro_update)
                    .addComponent(bt_pro_delete)
                    .addComponent(jButton316)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addGap(16, 16, 16)
                .addComponent(jComboBoxSort, 0, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(389, 389, 389))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxSort, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rd_conhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_conhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_conhangActionPerformed

    private void bt_pro_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pro_updateActionPerformed
        Product pro = new Product();
        Category cat = (Category) jcb_cat_name.getSelectedItem();
        int cai_i = cat.getCat_id();
        System.out.println("catid= " + cai_i);
        pro.setPro_id(Integer.valueOf(tf_pro_id.getText()));

        pro.setCat_id(cai_i);
        pro.setPro_name(String.valueOf(tf_proname.getText()));
        pro.setPro_desc(String.valueOf(tf_pro_desc.getText()));
        pro.setSort(Integer.valueOf(tf_pro_sort.getText()));
        pro.setPro_price(Float.valueOf(tf_pro_price.getText()));
        rd_conhang.setActionCommand("1");
        rd_hethang.setActionCommand("0");
        pro.setPro_status(Integer.valueOf(buttonGroup_pro_status.getSelection().getActionCommand()));
        System.out.println(pro.getPro_price());
        if (tf_proname.getText().length() <= 0) {
            DialogThongBao.showSuccess(this, Constant.MSG_UPDATE_PRO, Constant.MSG_UPDATE_PRO_NOT_NUL_NAME);
        } else {

            try {
                Proimpl.Updatepro(pro);
                DialogThongBao.showSuccess(this, "Thành Công", "Sửa Sản Phẩm thành công ");
                setdata();

            } catch (SQLException ex) {
                Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_bt_pro_updateActionPerformed

    private void jComboBoxSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSortActionPerformed

    private void tlbproductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tlbproductsMouseClicked
        try {
            lstCategory = catImpl.getAllCat();
            String pro_id = tlbproducts.getValueAt(tlbproducts.getSelectedRow(), 0).toString();
            jcb_cat_name.removeAllItems();
            Category cat1[] = new Category[lstCategory.size()];
            for (int i = 0; i < lstProducts.size(); i++) {
                Product pro = lstProducts.get(i);
                if (pro_id.equals(String.valueOf(pro.getPro_id()))) {
                    tf_pro_id.setText(String.valueOf(pro.getPro_id()));
                    tf_proname.setText(pro.getPro_name());
                    tf_pro_price.setText(String.valueOf(pro.getPro_price()));
                    tf_pro_desc.setText(pro.getPro_desc());
                    tf_pro_sort.setText(String.valueOf(pro.getSort()));
                    if (pro.getPro_status() == 1) {
                        rd_conhang.setSelected(true);
                    } else {
                        rd_hethang.setSelected(true);
                    }

                    for (int j = 0; j < lstCategory.size(); j++) {
                        Category get = lstCategory.get(j);
                        cat1[j] = get;

//                        if (pro.getCat_id() == get.getCat_id()) {
//                            System.out.println(get.getCat_id() + get.getCat_name());
//                            jcb_cat_name.getModel().setSelectedItem(get.getCat_name());
//                        }
                    }

                    dcbm1 = new DefaultComboBoxModel<>(cat1);
                    jcb_cat_name.setModel(dcbm1);

                    for (int j = 0; j < lstCategory.size(); j++) {
                        Category get = lstCategory.get(j);
//                        if (pro.getCat_id() == get.getCat_id()) {
//                            System.out.println(pro.getCat_id());
//                            jcb_cat_name.getModel().setSelectedItem(get.getCat_name());
//                        }

                    }

                }

            }

//            bt_pro_update.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tlbproductsMouseClicked

    private void bt_pro_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pro_addActionPerformed
        Product pro = new Product();
        Category cat = (Category) jcb_cat_name.getSelectedItem();
        int cai_i = cat.getCat_id();
        System.out.println("catid= " + cai_i);
        pro.setPro_id(Integer.valueOf(tf_pro_id.getText()));

        pro.setCat_id(cai_i);
        pro.setPro_name(String.valueOf(tf_proname.getText()));
        pro.setPro_desc(String.valueOf(tf_pro_desc.getText()));
        pro.setSort(Integer.valueOf(tf_pro_sort.getText()));
        pro.setPro_price(Float.valueOf(tf_pro_price.getText()));
        rd_conhang.setActionCommand("1");
        rd_hethang.setActionCommand("0");
        pro.setPro_status(Integer.valueOf(buttonGroup_pro_status.getSelection().getActionCommand()));
        System.out.println(pro.getPro_price());
        if (tf_proname.getText().length() <= 0) {
            DialogThongBao.showSuccess(this, Constant.MSG_UPDATE_PRO, Constant.MSG_UPDATE_PRO_NOT_NUL_NAME);
        } else if (tf_pro_price.getText().length() <= 0) {
            DialogThongBao.showSuccess(this, Constant.MSG_UPDATE_PRO, Constant.MSG_UPDATE_PRO_NOT_NUL_PRICE);
        } else {
            try {
                boolean checkName = Proimpl.checkname(tf_proname.getText().trim());
                if (!checkName) {
                    Proimpl.InSertPro(pro);
                    setdata();
                } else {
                    DialogThongBao.showAlert(this, " Sai thông tin", "Tên Sản phẩm đã tồn tại");
                }
            } catch (SQLException ex) {
                Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_bt_pro_addActionPerformed

    private void bt_pro_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pro_deleteActionPerformed
           try {
            int pro_id = Integer.valueOf(tf_pro_id.getText());
            if (DialogThongBao.showAlert(this, "Xác Nhận", "Bạn có chắc muốn xóa") == 0) {
                if (Proimpl.DeletePro(pro_id)) {
                    DialogThongBao.showSuccess(this, Constant.MSG_DELETE_PRO, Constant.MSG_DELETE_PRO_SUCCESS);
                    setdata();
                } else {
                    DialogThongBao.showError(this, Constant.MSG_DELETE_PRO, Constant.MSG_DELETE_PRO_ERROR);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_pro_deleteActionPerformed

    private void jButton316ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton316ActionPerformed
        
    }//GEN-LAST:event_jButton316ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_pro_add;
    private javax.swing.JButton bt_pro_delete;
    private javax.swing.JButton bt_pro_update;
    private javax.swing.ButtonGroup buttonGroup_pro_status;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton316;
    private javax.swing.JComboBox<String> jComboBoxSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> jcb_cat_name;
    private javax.swing.JLabel lblpro_id;
    private javax.swing.JLabel lblpro_price;
    private javax.swing.JLabel lblpro_sort;
    private javax.swing.JLabel lblpro_status;
    private javax.swing.JLabel lblproname;
    private javax.swing.JRadioButton rd_conhang;
    private javax.swing.JRadioButton rd_hethang;
    private javax.swing.JTextArea tf_pro_desc;
    private javax.swing.JLabel tf_pro_id;
    private javax.swing.JFormattedTextField tf_pro_price;
    private javax.swing.JTextField tf_pro_sort;
    private javax.swing.JTextField tf_proname;
    private javax.swing.JTable tlbproducts;
    // End of variables declaration//GEN-END:variables
}
