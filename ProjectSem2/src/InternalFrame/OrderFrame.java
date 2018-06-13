/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalFrame;

import Entity.Category;
import Entity.Product;
import ImplementInterface.ProductImpl;
import ImplementInterface.categoryImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hoang's PC
 */
public class OrderFrame extends javax.swing.JInternalFrame {

    ProductImpl Proimpl = new ProductImpl();
    categoryImpl catImpl = new categoryImpl();
    public ArrayList<Product> lstProducts;

    public ArrayList<Product> lstpro;
    public ArrayList<Category> lstCategory;
    String strSetModel[] = new String[]{"Mã Sp", "Danh mục", "Tên Sp", "Giá Sp", "Trạng thái"};
    String strSetModel_order[] = new String[]{"Mã Sp", "Tên Sp", "Giá Sp", "Số Lượng"};
    DefaultTableModel dtmd = new DefaultTableModel(strSetModel, 0);
    DefaultTableModel dtmd_order = new DefaultTableModel(strSetModel_order, 0);
    DefaultComboBoxModel dcbm1;

    public OrderFrame() throws SQLException {
        initComponents();
        setModelDanhMuc();
        setModelProduct(0);
        tbl_list_order.setModel(dtmd_order);

    }

    public void setModelDanhMuc() throws SQLException {
        lstCategory = catImpl.getAllCat();
        Category cat1[] = new Category[lstCategory.size()];
        for (int j = 0; j < lstCategory.size(); j++) {
            Category get = lstCategory.get(j);
            cat1[j] = get;
        }
        dcbm1 = new DefaultComboBoxModel<>(cat1);
        jcb_danh_muc.setModel(dcbm1);

    }

    public void setModelProduct(int cat_id) throws SQLException {
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
            if (cat_id == 0) {
                Object data[] = {get.getPro_id(), cat_name, get.getPro_name(), get.getPro_price(), status};
                dtmd.addRow(data);
            } else {
                if (get.getCat_id() == cat_id) {
                    Object data[] = {get.getPro_id(), cat_name, get.getPro_name(), get.getPro_price(), status};
                    dtmd.addRow(data);
                }
            }

        }
        tbl_list_sp.setModel(dtmd);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_list_sp = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_list_order = new javax.swing.JTable();
        btn_submit_sp = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lb_gia = new javax.swing.JLabel();
        lb_ten = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lb_desc = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jcb_danh_muc = new javax.swing.JComboBox<>();
        btn_chon_danh_muc = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Order");

        tbl_list_sp.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_list_sp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_list_spMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_list_sp);

        tbl_list_order.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbl_list_order);

        btn_submit_sp.setText("chọn");
        btn_submit_sp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submit_spActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Tổng tiền hóa đơn");

        jLabel3.setText("jLabel3");

        jLabel4.setText("Tên");

        jLabel5.setText("Giá");

        lb_gia.setText("jLabel8");

        lb_ten.setText("jLabel9");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mô tả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11))); // NOI18N

        lb_desc.setColumns(20);
        lb_desc.setLineWrap(true);
        lb_desc.setRows(5);
        lb_desc.setToolTipText("");
        jScrollPane3.setViewportView(lb_desc);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_ten)
                                    .addComponent(lb_gia))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lb_ten))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lb_gia))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        jLabel1.setText("Danh Mục");

        jcb_danh_muc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcb_danh_muc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_danh_mucActionPerformed(evt);
            }
        });

        btn_chon_danh_muc.setText("Chọn");
        btn_chon_danh_muc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chon_danh_mucActionPerformed(evt);
            }
        });

        jLabel6.setText("Danh sách đã chọn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcb_danh_muc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btn_chon_danh_muc)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(btn_submit_sp))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jcb_danh_muc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_chon_danh_muc, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_submit_sp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton2))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcb_danh_mucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_danh_mucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcb_danh_mucActionPerformed

    private void btn_chon_danh_mucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chon_danh_mucActionPerformed
        try {
            Category cat = (Category) jcb_danh_muc.getSelectedItem();
            int cat_id = cat.getCat_id();
            System.out.println("catid= " + cat_id);
            setModelProduct(cat_id);
        } catch (SQLException ex) {
            Logger.getLogger(OrderFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_chon_danh_mucActionPerformed

    private void btn_submit_spActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submit_spActionPerformed
        try {
            ArrayList<Integer> lstpro_order = new ArrayList<>();

            int quantity = 1;
            lstCategory = catImpl.getAllCat();
            String pro_id = tbl_list_sp.getValueAt(tbl_list_sp.getSelectedRow(), 0).toString();
            for (int i = 0; i < lstProducts.size(); i++) {
                Product get = lstProducts.get(i);
                if (pro_id.equals(String.valueOf(get.getPro_id()))) {

                    Object data[] = {get.getPro_id(), get.getPro_name(), get.getPro_price(), quantity};

                    dtmd_order.addRow(data);

                }

            }
            tbl_list_order.setModel(dtmd_order);
            
            

            int rowCount = tbl_list_order.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String pro = tbl_list_order.getValueAt(i, 0).toString();
                lstpro_order.add(Integer.valueOf(pro));
            }
            for (int i = 0; i < lstpro_order.size(); i++) {
                Integer get = lstpro_order.get(i);
                if (get == Integer.parseInt(pro_id)) {
                    System.out.println("Trùng");
                    if (rowCount >= i) {
                        dtmd_order.removeRow(i);
//                    continue;
                    }
                }else{
                    System.out.println("Ok");
                }
            }
            
//            System.out.println("rowCount =" + rowCount);
//            if (rowCount > 1) {
//                for (int i = 0; i < rowCount; i++) {
//                    String pro = tbl_list_order.getValueAt(i, 0).toString();
//                    lstpro_order.add(Integer.valueOf(pro));
//                }
//                for (int i = 0; i < lstpro_order.size(); i++) {
//                    Integer get = lstpro_order.get(i);
//                    if (get == Integer.valueOf(pro_id)) {
//                        System.out.println("removeRow at" +get);
//                        dtmd_order.removeRow(i);
//                        
//                    }else{
//                        for (int j = 0; j < lstProducts.size(); j++) {
//                            Product get_sp = lstProducts.get(j);
//                            if (pro_id.equals(String.valueOf(get_sp.getPro_id())) && !Objects.equals(get, pro_id)) {
//                                Object data[] = {get_sp.getPro_id(), get_sp.getPro_name(), get_sp.getPro_price(), quantity};
//                                dtmd_order.addRow(data);
//                            }
//                        }
//                    }
//                }
//                tbl_list_order.setModel(dtmd_order);
//
//            }

        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_submit_spActionPerformed

    private void tbl_list_spMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_list_spMouseClicked
        try {
            lstCategory = catImpl.getAllCat();
            String pro_id = tbl_list_sp.getValueAt(tbl_list_sp.getSelectedRow(), 0).toString();
            for (int i = 0; i < lstProducts.size(); i++) {
                Product pro = lstProducts.get(i);
                if (pro_id.equals(String.valueOf(pro.getPro_id()))) {
                    lb_ten.setText(pro.getPro_name());
                    lb_gia.setText(String.valueOf(pro.getPro_price()));
                    lb_desc.setText(pro.getPro_desc());
                    lb_desc.setEditable(false);

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbl_list_spMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ArrayList<Integer> lstpro_order = new ArrayList<>();
        int rowCount = tbl_list_order.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String pro_id = tbl_list_order.getValueAt(i, 0).toString();
            lstpro_order.add(Integer.valueOf(pro_id));
        }
        for (int i = 0; i < lstpro_order.size(); i++) {
            Integer get = lstpro_order.get(i);
            System.out.println(get);

        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_chon_danh_muc;
    private javax.swing.JButton btn_submit_sp;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> jcb_danh_muc;
    private javax.swing.JTextArea lb_desc;
    private javax.swing.JLabel lb_gia;
    private javax.swing.JLabel lb_ten;
    private javax.swing.JTable tbl_list_order;
    private javax.swing.JTable tbl_list_sp;
    // End of variables declaration//GEN-END:variables
}
