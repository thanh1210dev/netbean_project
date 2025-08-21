/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import entity.MauSac;
import entity.NhaCungCap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import reponsitory.ThuocTinhRepo;
import entity.*;
import java.awt.Component;
import javax.swing.ButtonGroup;
import javax.swing.*;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import reponsitory.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 6
 *
 * @author ngkho
 */
public class JPanelThuocTinh extends javax.swing.JPanel {

    private ThuocTinhRepo thuocTinhRepo = new ThuocTinhRepo();
    private DefaultTableModel model;
    private String selectedTable;
    private String matt;

    /**
     * Creates new form JPanelThuocTinh
     */
    public JPanelThuocTinh() {
        initComponents();
        model = new DefaultTableModel(new String[]{"STT", "Mã Thuộc Tính", "Tên Thuộc Tính"}, 0);
        tblThuocTinh.setModel(model);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbtMauSac);
        buttonGroup.add(rbtChieuDai);
        buttonGroup.add(rbtDoCang);
        buttonGroup.add(rbtNhaCungCap);
        buttonGroup.add(jRadioButton4);
        buttonGroup.add(rbtDoBay);
        buttonGroup.add(rbtDangMatVot);
        buttonGroup.add(rbtDoCungDua);
        buttonGroup.add(rbtTrongluong);

          rbtMauSac.addActionListener(e -> {
            if (rbtMauSac.isSelected()) {
                selectedTable = "MAU_SAC";
                matt = "MA_MAU";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });
        rbtChieuDai.addActionListener(e -> {
            if (rbtChieuDai.isSelected()) {
                selectedTable = "CHIEU_DAI_VOT";
                matt = "MA_CD_VOT";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });
        rbtDoCang.addActionListener(e -> {
            if (rbtDoCang.isSelected()) {
                selectedTable = "DO_CANG";
                matt = "MA_DO_CANG";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });
        rbtNhaCungCap.addActionListener(e -> {
            if (rbtNhaCungCap.isSelected()) {
                selectedTable = "NHA_CUNG_CAP";
                matt = "MA_NHA_CUNG_CAP";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });

        jRadioButton4.addActionListener(e -> {
            if (jRadioButton4.isSelected()) {
                selectedTable = "DIEM_CAN_BANG";
                matt = "MA_DIEM_CAN_BANG";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });

        rbtDoBay.addActionListener(e -> {
            if (rbtDoBay.isSelected()) {
                selectedTable = "DO_BAY";
                matt = "MA_DO_BAY";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });

        rbtDangMatVot.addActionListener(e -> {
            if (rbtDangMatVot.isSelected()) {
                selectedTable = "DANG_MAT_VOT";
                matt = "MA_DANG_MAT_VOT";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });

        rbtDoCungDua.addActionListener(e -> {
            if (rbtDoCungDua.isSelected()) {
                selectedTable = "DO_CUNG_DUA";
                matt = "MA_DO_CUNG_DUA";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });

        rbtTrongluong.addActionListener(e -> {
            if (rbtTrongluong.isSelected()) {
                selectedTable = "TRONG_LUONG";
                matt = "MA_TRONG_LUONG";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });

        // Hiển thị bảng thuộc tính màu sắc mặc định khi mở form
        rbtMauSac.setSelected(true);
        hienThiThuocTinhTrenBang("MAU_SAC");
        selectedTable = "MAU_SAC";
        matt = "MA_MAU";

    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("SP");
        JPanelThuocTinh jbh = new JPanelThuocTinh();
        jFrame.add(jbh);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
    

//    private void hienThiThuocTinhTrenBang(String tenBang) {
//        model.setRowCount(0);
//        ArrayList<Object[]> listThuocTinh = thuocTinhRepo.getAllThuocTinh(tenBang);
//        for (Object[] thuocTinh : listThuocTinh) {
//            JButton btnChinhSua = new JButton("Chỉnh Sửa");
//            btnChinhSua.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    int row = tblThuocTinh.getSelectedRow();
//                    if (row != -1) {
//                        String maThuocTinh = (String) tblThuocTinh.getValueAt(row, 1);
//                        String tenThuocTinh = (String) tblThuocTinh.getValueAt(row, 2);
//                        txtMaThuocTinh.setText(maThuocTinh);
//                        txtTenThuocTinh.setText(tenThuocTinh);
//                    }
//                }
//            });
//
//            model.addRow(new Object[]{model.getRowCount() + 1, thuocTinh[0], thuocTinh[1]}); 
//        }
//    }
     private void hienThiThuocTinhTrenBang(String tenBang) {
        model.setRowCount(0); 
        ArrayList<Object[]> listThuocTinh = thuocTinhRepo.getAllThuocTinh(tenBang);
        for (int i = 0; i < listThuocTinh.size(); i++) {
            Object[] thuocTinh = listThuocTinh.get(i);
            model.addRow(new Object[]{i + 1, thuocTinh[0], thuocTinh[1]}); 
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        rbtChieuDai = new javax.swing.JRadioButton();
        rbtDoCungDua = new javax.swing.JRadioButton();
        rbtNhaCungCap = new javax.swing.JRadioButton();
        rbtTrongluong = new javax.swing.JRadioButton();
        rbtDoBay = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        rbtDangMatVot = new javax.swing.JRadioButton();
        rbtMauSac = new javax.swing.JRadioButton();
        rbtDoCang = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaThuocTinh = new javax.swing.JTextField();
        txtTenThuocTinh = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Thuộc Tính");

        buttonGroup1.add(rbtChieuDai);
        rbtChieuDai.setText("Chiều Dài");

        buttonGroup1.add(rbtDoCungDua);
        rbtDoCungDua.setText("Độ Cứng Đũa");
        rbtDoCungDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtDoCungDuaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtNhaCungCap);
        rbtNhaCungCap.setText("Nhà Cung Cấp");

        buttonGroup1.add(rbtTrongluong);
        rbtTrongluong.setText("Trọng Lượng");
        rbtTrongluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtTrongluongActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtDoBay);
        rbtDoBay.setText("Độ Bay");

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("Điểm Cân Bằng");

        buttonGroup1.add(rbtDangMatVot);
        rbtDangMatVot.setText("Dạng mặt Vợt");

        buttonGroup1.add(rbtMauSac);
        rbtMauSac.setText("Màu Sắc");

        buttonGroup1.add(rbtDoCang);
        rbtDoCang.setText("Độ Căng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtChieuDai)
                    .addComponent(rbtDoCungDua)
                    .addComponent(rbtDoBay))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtTrongluong)
                    .addComponent(rbtNhaCungCap)
                    .addComponent(jRadioButton4))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtDoCang)
                    .addComponent(rbtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtDangMatVot))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtChieuDai)
                    .addComponent(rbtNhaCungCap)
                    .addComponent(rbtMauSac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtDoCungDua)
                    .addComponent(rbtTrongluong)
                    .addComponent(rbtDangMatVot))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton4)
                    .addComponent(rbtDoBay)
                    .addComponent(rbtDoCang))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel1.setText("Mã Thuộc Tính");

        jLabel2.setText("Tên Thuộc Tính");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(367, 367, 367))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(txtMaThuocTinh)
                        .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(46, 46, 46)
                        .addComponent(btnSua)
                        .addGap(44, 44, 44)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoi))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua)
                            .addComponent(btnXoa)
                            .addComponent(btnLamMoi))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 181, Short.MAX_VALUE)
        );

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Thuộc Tính", "Tên Thuộc Tính"
            }
        ));
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThuocTinh);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void rbtTrongluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtTrongluongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtTrongluongActionPerformed

    private void rbtDoCungDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtDoCungDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtDoCungDuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        String maThuocTinh = txtMaThuocTinh.getText();
        String tenThuocTinh = txtTenThuocTinh.getText();

        if (maThuocTinh.isEmpty() || tenThuocTinh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ mã và tên thuộc tính.");
            return;
        }
        try {
            thuocTinhRepo.addThuocTinh(selectedTable, maThuocTinh, tenThuocTinh);
            JOptionPane.showMessageDialog(this, "Thêm " + selectedTable + " thành công!");
            hienThiThuocTinhTrenBang(selectedTable);
            txtMaThuocTinh.setText("");
            txtTenThuocTinh.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm " + selectedTable + ": " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int row = tblThuocTinh.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một thuộc tính để sửa.");
            return;
        }
        Object maThuocTinhObj = tblThuocTinh.getValueAt(row, 1);
   
        String maThuocTinh = (String) tblThuocTinh.getValueAt(row, 0);
        String tenThuocTinhMoi = txtTenThuocTinh.getText();

        if (tenThuocTinhMoi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thuộc tính mới.");
            return;
        }
        try {
            thuocTinhRepo.updateThuocTinh(selectedTable, maThuocTinh,matt, tenThuocTinhMoi);
            JOptionPane.showMessageDialog(this, "Sửa " + selectedTable + " thành công!");
            hienThiThuocTinhTrenBang(selectedTable);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi sửa " + selectedTable + ": " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
//        int row = tblThuocTinh.getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn một thuộc tính để xóa.");
//            return;
//        }
//        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa thuộc tính này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
//        if (confirm == JOptionPane.YES_OPTION) {
//            String maThuocTinh = (String) tblThuocTinh.getValueAt(row, 1);
//            try {
//                thuocTinhRepo.deleteThuocTinh(selectedTable, maThuocTinh);
//                JOptionPane.showMessageDialog(this, "Xóa " + selectedTable + " thành công!");
//                hienThiThuocTinhTrenBang(selectedTable);
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(this, "Lỗi khi xóa " + selectedTable + ": " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//                e.printStackTrace();
//            }
//        }
 int row = tblThuocTinh.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một thuộc tính để xóa.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa thuộc tính này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        model.removeRow(row);
        JOptionPane.showMessageDialog(this, "Xóa thuộc tính thành công!");
    }
        
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
        // TODO add your handling code here:
        tblThuocTinh.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tblThuocTinh.getSelectedRow();
        if (row != -1) { 
            String maThuocTinh = (String) tblThuocTinh.getValueAt(row, 1); 
            String tenThuocTinh = (String) tblThuocTinh.getValueAt(row, 2); 

            txtMaThuocTinh.setText(maThuocTinh);
            txtTenThuocTinh.setText(tenThuocTinh);

            // Kích hoạt nút Sửa và vô hiệu hóa nút Thêm
//            btnSua.setEnabled(true);
//            btnThem.setEnabled(false);
        }
    }
});
    }//GEN-LAST:event_tblThuocTinhMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rbtChieuDai;
    private javax.swing.JRadioButton rbtDangMatVot;
    private javax.swing.JRadioButton rbtDoBay;
    private javax.swing.JRadioButton rbtDoCang;
    private javax.swing.JRadioButton rbtDoCungDua;
    private javax.swing.JRadioButton rbtMauSac;
    private javax.swing.JRadioButton rbtNhaCungCap;
    private javax.swing.JRadioButton rbtTrongluong;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextField txtTenThuocTinh;
    // End of variables declaration//GEN-END:variables
}
