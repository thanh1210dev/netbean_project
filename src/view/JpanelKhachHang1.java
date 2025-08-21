/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import config.DBconnextSQL;
import entity.KhachHangModel;
import entity.LichSuHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reponsitory.KhachHangRepos;
import reponsitory.LichSuHoaDonRepos;

/**
 *
 * @author Asus
 */
public class JpanelKhachHang1 extends javax.swing.JPanel {

    private LichSuHoaDonRepos hdrp = new LichSuHoaDonRepos();
    private KhachHangRepos rp = new KhachHangRepos();
    List<LichSuHoaDon> listHD = new ArrayList<>();
    List<KhachHangModel> list = new ArrayList<>();
    private DefaultTableModel mol = new DefaultTableModel();
    private int i;

    /**
     * Creates new form JpanelKhachHang1
     */
    public JpanelKhachHang1() {
        initComponents();
        this.fillTable(rp.getAll());
        this.tblQLKH.getSelectionModel().addListSelectionListener(e -> showData());
        this.fillCombo();
        this.fillComboLoc();

    }

    public void fillTable(ArrayList<KhachHangModel> list) {
        mol = (DefaultTableModel) tblQLKH.getModel();
        mol.setRowCount(0);
        for (KhachHangModel kh : list) {
            mol.addRow(kh.toDataRow());
        }
    }

    public void fillBangLichSuHoaDon(ArrayList<LichSuHoaDon> listHD) {
        mol = (DefaultTableModel) tblLSHD.getModel();
        mol.setRowCount(0);
        for (LichSuHoaDon hd : listHD) {
            Object row[] = new Object[]{
                hd.getMaKhachHang(), hd.getMa(), hd.getNgayDatHang(), hd.getTongTien()
            };
            mol.addRow(row);
        }
    }

    public void fillCombo() {
        DefaultComboBoxModel cmol = (DefaultComboBoxModel) cboTrangThai.getModel();
        cmol.removeAllElements();
        cmol.addElement("Tất cả");
        cmol.addElement("Đang hoạt động");
        cmol.addElement("Ngừng hoạt động");

    }

    public void fillComboLoc() {
        DefaultComboBoxModel cmol = (DefaultComboBoxModel) cboLocTT.getModel();
        cmol.removeAllElements();
        cmol.addElement("Tất cả");
        cmol.addElement("Đang hoạt động");
        cmol.addElement("Ngừng hoạt động");

    }

    public void showData() {
        int selectedRow = tblQLKH.getSelectedRow();
        if (selectedRow >= 0) {
            txtMa.setText(tblQLKH.getValueAt(selectedRow, 0).toString());
            txtHoTen.setText(tblQLKH.getValueAt(selectedRow, 1).toString());
            txtDiaChi.setText(tblQLKH.getValueAt(selectedRow, 2).toString());
            txtSDT.setText(tblQLKH.getValueAt(selectedRow, 3).toString());
            if (tblQLKH.getValueAt(selectedRow, 4).toString().equalsIgnoreCase("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            if (tblQLKH.getValueAt(selectedRow, 5).toString().equalsIgnoreCase("Ngừng hoạt động")) {
                cboTrangThai.setSelectedItem("Ngừng hoạt động");
            } else {
                cboTrangThai.setSelectedItem("Đang hoạt động");
            }
            txtGhiChu.setText(tblQLKH.getValueAt(selectedRow, 6).toString());

            // Điền bảng lịch sử hóa đơn dựa trên mã khách hàng được chọn
            String maKhachHang = tblQLKH.getValueAt(selectedRow, 0).toString();
            ArrayList<LichSuHoaDon> listLichSuHoaDon = hdrp.getAll(maKhachHang);
            fillBangLichSuHoaDon(listLichSuHoaDon);
        }
    }

    private void filterTable(String trangThai) {
        String sql = "SELECT * FROM [dbo].[KHACH_HANG] WHERE 1=1";

        // Xây dựng câu lệnh SQL dựa trên các điều kiện lọc
        if (!"Tất cả".equals(trangThai)) {
            sql += " AND TRANG_THAI = ?";
        }

        try (Connection cn = DBconnextSQL.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            int index = 1;

            if (!"Tất cả".equals(trangThai)) {
                ps.setBoolean(index++, "Ngừng hoạt động".equals(trangThai));
            }

            ResultSet rs = ps.executeQuery();

            // Xóa dữ liệu cũ trong bảng
            DefaultTableModel model = (DefaultTableModel) tblQLKH.getModel();
            model.setRowCount(0);

            // Thêm dữ liệu mới vào bảng
            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("MA_KHACH_HANG"),
                    rs.getString("TEN_KHACH_HANG"),
                    rs.getString("DIA_CHI"),
                    rs.getString("SDT"),
                    rs.getBoolean("GIOI_TINH") ? "Nam" : "Nữ",
                    rs.getBoolean("TRANG_THAI")== false ?  "Đang hoạt động": "Ngừng hoạt động",
                    rs.getString("GHI_CHU"),};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLKH = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        cboLocTT = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btnTCDS = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLSHD = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        btnThemKH = new javax.swing.JButton();
        btnSuaKH = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setName(""); // NOI18N

        tblQLKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên khách hàng", "Địa chỉ", "SĐT", "Giới tính", "Trạng thái", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQLKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLKH);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 10))); // NOI18N

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(204, 255, 255));
        btnTimKiem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        cboLocTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Trạng Thái");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboLocTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKiem)
                .addGap(34, 34, 34))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem)
                    .addComponent(cboLocTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(17, 17, 17))
        );

        btnTCDS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTCDS.setText("Danh sách");
        btnTCDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTCDSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnTCDS)
                        .addGap(15, 15, 15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTCDS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lịch sử hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblLSHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ KH", "MÃ HD", "Ngày đặt hàng", "Tổng tiền"
            }
        ));
        tblLSHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLSHDMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblLSHD);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã khách hàng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Họ tên");

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdoNu.setText("Nữ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Giới tính");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Ghi chú");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("SĐT");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Địa chỉ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Trạng thái");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThemKH.setBackground(new java.awt.Color(0, 204, 51));
        btnThemKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemKH.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/icon/Add.png"))); // NOI18N
        btnThemKH.setText("Thêm");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        btnSuaKH.setBackground(new java.awt.Color(255, 204, 51));
        btnSuaKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/icon/Edit.png"))); // NOI18N
        btnSuaKH.setText("Sửa");
        btnSuaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKHActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(204, 0, 0));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/icon/x-button.png"))); // NOI18N
        btnXoa.setText("Ngừng HĐ");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSuaKH, btnThemKH, btnXoa});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSuaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSuaKH, btnThemKH, btnXoa});

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(rdoNam)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNu)))
                .addGap(135, 135, 135)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(54, 54, 54)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(40, 40, 40))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rdoNam)
                                        .addComponent(rdoNu)))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(434, 434, 434)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblQLKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLKHMouseClicked
        // TODO add your handling code here:
        int i = tblQLKH.getSelectedRow();
        showData();
    }//GEN-LAST:event_tblQLKHMouseClicked

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate

    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

        // TODO add your handling code here:
        fillTable(rp.search(txtTimKiem.getText()));
        String trangThai = cboLocTT.getSelectedItem().toString();
        filterTable(trangThai);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblLSHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLSHDMouseClicked

    }//GEN-LAST:event_tblLSHDMouseClicked

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        try {
            KhachHangModel viewModel = new KhachHangModel();

            viewModel.setMaKH(txtMa.getText());
            viewModel.setHoTen(txtHoTen.getText());
            viewModel.setGioiTinh(rdoNam.isSelected());
            viewModel.setDiaChi(txtDiaChi.getText());
            viewModel.setSdt(txtSDT.getText());
            viewModel.setTrangThai(cboTrangThai.getSelectedItem().toString().equals("Ngừng hoạt động")); // Sử dụng combobox trạng thái
            viewModel.setGhiChu(txtGhiChu.getText());

            // Kiểm tra mã khách hàng
            if (txtMa.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống mã khách hàng");
                return;
            }

            for (KhachHangModel kh : rp.getAll()) {
                if (kh.getMaKH().trim().equalsIgnoreCase(txtMa.getText())) {
                    JOptionPane.showMessageDialog(this, "Trùng mã khách hàng");
                    return;
                }
            }

            // Kiểm tra số điện thoại
            for (KhachHangModel kh : rp.getAll()) {
                if (kh.getSdt().trim().equalsIgnoreCase(txtSDT.getText())) {
                    JOptionPane.showMessageDialog(this, "Trùng SDT");
                    return;
                }
            }

            // Kiểm tra tên khách hàng
            if (txtHoTen.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được trống Tên");
                txtHoTen.requestFocus();
                return;
            }

            // Kiểm tra địa chỉ
            if (txtDiaChi.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ");
                txtDiaChi.requestFocus();
                return;
            }

            // Kiểm tra số điện thoại không rỗng và đúng định dạng
            if (txtSDT.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được trống Số điện thoại");
                txtSDT.requestFocus();
                return;
            }

            if (!txtSDT.getText().matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
                txtSDT.requestFocus();
                return;
            }

            Integer result = rp.add(viewModel);

            if (result != null && result > 0) {
                JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
                fillTable(rp.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm khách hàng");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm khách hàng");
        }
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void btnSuaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKHActionPerformed
        try {
            if (txtMa.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống mã khách hàng");
                return;
            }

            //    for (KhachHangModel kh : rp.getAll()) {
            //        if (kh.getMaKH().trim().equalsIgnoreCase(txtMa.getText().trim())) {
            //            JOptionPane.showMessageDialog(this, "Trùng mã khách hàng");
            //            return;
            //        }
            //    }
            if (txtHoTen.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được trống Tên");
                txtHoTen.requestFocus();
                return;
            }

            if (txtDiaChi.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ");
                txtDiaChi.requestFocus();
                return;
            }

            if (txtSDT.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được trống Số điện thoại");
                txtSDT.requestFocus();
                return;
            }

            if (!txtSDT.getText().matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
                txtSDT.requestFocus();
                return;
            }

            if (cboTrangThai.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái");
                cboTrangThai.requestFocus();
                return;
            }

            KhachHangModel viewModel = new KhachHangModel();
            viewModel.setMaKH(txtMa.getText());
            viewModel.setHoTen(txtHoTen.getText());
            viewModel.setGioiTinh(rdoNam.isSelected());
            viewModel.setSdt(txtSDT.getText());
            viewModel.setDiaChi(txtDiaChi.getText());
            viewModel.setTrangThai(cboTrangThai.getSelectedItem().toString().equals("Ngừng hoạt động"));
            viewModel.setGhiChu(txtGhiChu.getText());

            Integer result = rp.update(viewModel);

            if (result != null && result > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin khách hàng thành công");
                fillTable(rp.getAll());
                //         showData();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin khách hàng");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin khách hàng");
        }
    }//GEN-LAST:event_btnSuaKHActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int index = tblQLKH.getSelectedRow();
        if (index != -1) {
            KhachHangModel nv = rp.getAll().get(index);
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn ngừng hoạt động khách hàng này?", "Xác nhận ngừng hoạt động", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                rp.delete(nv.getMaKH());
                JOptionPane.showMessageDialog(null, "Khách hàng đã được ngừng hoạt động thành công.");
                fillTable(rp.getAll());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một khách hàng để ngừng hoạt động.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaActionPerformed
    int t = 1;
    private void btnTCDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTCDSActionPerformed
        // TODO add your handling code here:
         if (t == 1) {
            fillTable(rp.TatCaDS());
            btnTCDS.setText("Ẩn ngừng ");
            t++;
        } else {
            fillTable(rp.getAll());
            btnTCDS.setText("Danh sách");
            t = 1;

        }
    }//GEN-LAST:event_btnTCDSActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnTCDS;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboLocTT;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblLSHD;
    private javax.swing.JTable tblQLKH;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
