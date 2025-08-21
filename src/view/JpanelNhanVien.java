/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;
import java.sql.*;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import config.DBconnextSQL;
import entity.NhanVienModel;
import reponsitory.NhanVienRepos;

/**
 *
 * @author Asus
 */
public class JpanelNhanVien extends javax.swing.JPanel {
private NhanVienRepos rp=new NhanVienRepos();
List<NhanVienModel> list=new ArrayList<>();
private DefaultTableModel mol=new DefaultTableModel();

private int i;
    /**
     * Creates new form JpanelNhanVien
     */
    public JpanelNhanVien() {
        initComponents();
        this.fillTable(rp.getAll());
        this.fillComboTT();
        this.fillComboVT();
        this.fillComboLocVT();
        this.fillComboLocTT();
    }
    
    public void fillTable(ArrayList<NhanVienModel> list){ 
        mol = (DefaultTableModel) tblQLNV.getModel();
        mol.setRowCount(0);
        for (NhanVienModel nv : list) {
            mol.addRow(new Object[]{
                nv.getId(),
                nv.getMaNV(),
                nv.getHoTen(),
                nv.getGioiTinh() ? "Nam" : "Nữ",
                nv.getSdt(),
                nv.getEmail(),
                nv.isVaiTro()== true ? "Quản lý" : "Nhân viên",
                nv.isTrangThai()== true ? "Đang làm" : "Nghỉ việc"
            });
        }
    } 
    
    public void fillComboVT(){
        DefaultComboBoxModel cmol = (DefaultComboBoxModel) cboVaiTro.getModel();
        cmol.removeAllElements();
       
        cmol.addElement("Quản lý");
        cmol.addElement("Nhân viên");

    }
    public void fillComboLocVT(){
        DefaultComboBoxModel cmol = (DefaultComboBoxModel) cboLocVT.getModel();
        cmol.removeAllElements();
        cmol.addElement("Tất cả");
        cmol.addElement("Quản lý");
        cmol.addElement("Nhân viên");

    }
    
    public void fillComboTT(){
        DefaultComboBoxModel cmol = (DefaultComboBoxModel) cboTrangThai.getModel();
        cmol.removeAllElements();
      
        cmol.addElement("Đang làm");
        cmol.addElement("Nghỉ việc");

    }
    public void fillComboLocTT(){
        DefaultComboBoxModel cmol = (DefaultComboBoxModel) cboLocTT.getModel();
        cmol.removeAllElements();
        cmol.addElement("Tất cả");
        cmol.addElement("Đang làm");
        cmol.addElement("Nghỉ việc");

    }
    
    private void filterTable(String vaiTro, String trangThai) {
    String sql = "SELECT * FROM [dbo].[NHAN_VIEN] WHERE 1=1";
    
    // Xây dựng câu lệnh SQL dựa trên các điều kiện lọc
    if (!"Tất cả".equals(vaiTro)) {
        sql += " AND VAI_TRO = ?";
    }
    if (!"Tất cả".equals(trangThai)) {
        sql += " AND TRANG_THAI = ?";
    }
    
    try (Connection cn = DBconnextSQL.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        
        int index = 1;
        if (!"Tất cả".equals(vaiTro)) {
            ps.setBoolean(index++, "Quản lý".equals(vaiTro));
        }
        if (!"Tất cả".equals(trangThai)) {
            ps.setBoolean(index++, "Đang làm".equals(trangThai));
        }
        
        ResultSet rs = ps.executeQuery();
        
        // Xóa dữ liệu cũ trong bảng
        DefaultTableModel model = (DefaultTableModel) tblQLNV.getModel();
        model.setRowCount(0);
        
        // Thêm dữ liệu mới vào bảng
        while (rs.next()) {
            Object[] row = new Object[]{
                rs.getInt("ID"),
                rs.getString("MA_NHAN_VIEN"),
                rs.getString("TEN_NV"),
                rs.getBoolean("GIOI_TINH") ? "Nam" : "Nữ",
                rs.getString("SO_DIEN_THOAI"),
                rs.getString("EMAIL"),
                rs.getBoolean("VAI_TRO") ? "Quản lý" : "Nhân viên",
                rs.getBoolean("TRANG_THAI") ? "Đang làm" : "Nghỉ"
            };
            model.addRow(row);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    
     public void showData() {
        txtMaNV.setText(tblQLNV.getValueAt(tblQLNV.getSelectedRow(), 1).toString());
        txtHoTen.setText(tblQLNV.getValueAt(tblQLNV.getSelectedRow(), 2).toString());
        if (tblQLNV.getValueAt(tblQLNV.getSelectedRow(), 3).toString().equalsIgnoreCase("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtSDT.setText(tblQLNV.getValueAt(tblQLNV.getSelectedRow(), 4).toString());
        txtEmail.setText(tblQLNV.getValueAt(tblQLNV.getSelectedRow(), 5).toString());        

        String vaiTro = tblQLNV.getValueAt(tblQLNV.getSelectedRow(), 6).toString();
        if (vaiTro.equalsIgnoreCase("Quản lý")) {
            cboVaiTro.setSelectedItem("Quản lý");
        } else {
            cboVaiTro.setSelectedItem("Nhân viên");
        }

        String trangThai = tblQLNV.getValueAt(tblQLNV.getSelectedRow(), 7).toString();
        if (trangThai.equalsIgnoreCase("Đang làm")) {
            cboTrangThai.setSelectedItem("Đang làm");
        } else {
            cboTrangThai.setSelectedItem("Nghỉ việc");
        }
    }


private void clearForm() {
    txtMaNV.setText("");
    txtHoTen.setText("");
    txtSDT.setText("");
    rdoNam.setSelected(false);
    rdoNu.setSelected(false);
     cboVaiTro.setSelectedIndex(-1); 
    cboTrangThai.setSelectedIndex(-1);
    txtEmail.setText("");
}
 

  public static void main(String[] args) {
        JFrame jFrame = new JFrame("Nhân Viên");
        JpanelNhanVien jbh = new JpanelNhanVien();
        jFrame.add(jbh);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        cboVaiTro = new javax.swing.JComboBox<>();
        cboTrangThai = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLNV = new javax.swing.JTable();
        btnTCDS = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        cboLocVT = new javax.swing.JComboBox<>();
        cboLocTT = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Form điền thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Mã Nhân Viên :");

        jLabel2.setText("Họ và tên :");

        jLabel3.setText("Giới tính :");

        jLabel4.setText("Số điện thoại :");

        rdoNam.setBackground(new java.awt.Color(255, 252, 229));
        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        rdoNu.setBackground(new java.awt.Color(255, 252, 229));
        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel5.setText("Email :");

        jLabel7.setText("Trạng Thái :");

        jLabel8.setText("Vai Trò :");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setBackground(new java.awt.Color(0, 204, 102));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/icon/Create.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 204, 0));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/icon/Edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/icon/refresh (1).png"))); // NOI18N
        jButton3.setText("Làm mới");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 51, 51));
        btnXoa.setText("Nghỉ việc");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSua, btnThem, jButton3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(14, 14, 14))
        );

        cboVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboVaiTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboVaiTroActionPerformed(evt);
            }
        });

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaNV))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNu))
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(130, 130, 130))
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtEmail, txtHoTen, txtMaNV, txtSDT});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("QUẢN LÝ NHÂN VIÊN");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblQLNV.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblQLNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Nhân viên", "Họ và tên", "Giới tính", "Số điện thoại", "Email", "Vai trò", "Trạng thái"
            }
        ));
        tblQLNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLNV);

        btnTCDS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTCDS.setText("Tất cả danh sách");
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
                        .addGap(33, 33, 33)
                        .addComponent(btnTCDS))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(btnTCDS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 204), null, null), "Tìm kiếm ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        cboLocVT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboLocTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Vai Trò :");

        jLabel11.setText("Trạng Thái :");

        btnTimKiem.setBackground(new java.awt.Color(204, 255, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search_icon.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboLocVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboLocTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnTimKiem)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLocVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLocTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(392, 392, 392)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 85, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblQLNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLNVMouseClicked
        // TODO add your handling code here:
        i=tblQLNV.getSelectedRow();
        showData();
    }//GEN-LAST:event_tblQLNVMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
    try {
    String maNV = txtMaNV.getText();
    String hoTen = txtHoTen.getText();
    String sdt = txtSDT.getText();
    String email = txtEmail.getText();

    // Kiểm tra mã nhân viên
    if (maNV == null || maNV.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không được để trống mã nhân viên");
        return;
    } else {
        maNV = maNV.trim();
        if (maNV.length() > 7) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được quá 7 ký tự");
            return;
        }
    }

    // Kiểm tra mã nhân viên trùng lặp
    for (NhanVienModel nv : rp.getAll()) {
        if (nv.getMaNV() != null && nv.getMaNV().trim().equalsIgnoreCase(maNV)) {
            JOptionPane.showMessageDialog(this, "Trùng mã nhân viên");
            return;
        }
    }

    // Kiểm tra tên nhân viên
    if (hoTen == null || hoTen.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không được để trống tên");
        txtHoTen.requestFocus();
        return;
    } else {
        hoTen = hoTen.trim();
        if (hoTen.length() > 50) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được quá 50 ký tự");
            txtHoTen.requestFocus();
            return;
        } else if (!hoTen.matches("^[\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Tên không hợp lệ.");
            txtHoTen.requestFocus();
            return;
        }
    }

    // Kiểm tra số điện thoại
    if (sdt == null || sdt.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại");
        txtSDT.requestFocus();
        return;
    } else {
        sdt = sdt.trim();
        if (sdt.length() != 10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 ký tự");
            txtSDT.requestFocus();
            return;
        }else if (!sdt.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
        txtSDT.requestFocus();
        return;
    }
    }

    // Kiểm tra email
    if (email == null || email.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập email");
        txtEmail.requestFocus();
        return;
    } else {
        email = email.trim();
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ");
            txtEmail.requestFocus();
            return;
        }
    }

    // Lấy giá trị vai trò từ combobox
    boolean vaiTro = cboVaiTro.getSelectedItem().toString().equals("Quản lý");

    // Không cho phép thêm nhân viên có vai trò "Quản lý"
    if (vaiTro) {
        JOptionPane.showMessageDialog(this, "Không thể thêm nhân viên có vai trò quản lý");
        return;
    }

    // Lấy giá trị trạng thái từ combobox
    boolean trangThai = cboTrangThai.getSelectedItem().toString().equals("Đang làm");

    // Tiến hành thêm nhân viên
    NhanVienModel viewModel = new NhanVienModel();
    viewModel.setMaNV(maNV);
    viewModel.setHoTen(hoTen);
    viewModel.setSdt(sdt);
    viewModel.setGioiTinh(rdoNam.isSelected());
    viewModel.setTrangThai(trangThai);
    viewModel.setVaiTro(vaiTro);
    viewModel.setEmail(email);

    if (rp.add(viewModel) > 0) {
        fillTable(rp.getAll());
        JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công và mật khẩu đã được gửi đến Gmail");
    } else {
        JOptionPane.showMessageDialog(this, "Lỗi thêm nhân viên");
    }
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Lỗi hệ thống, không thể thêm nhân viên");
}
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
     try {
    String maNV = txtMaNV.getText().trim();
    String hoTen = txtHoTen.getText().trim();
    String sdt = txtSDT.getText().trim();
    String email = txtEmail.getText().trim();

    // Kiểm tra mã nhân viên
    if (maNV.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không được để trống mã nhân viên");
        return;
    } else if (maNV.length() > 7) {
        JOptionPane.showMessageDialog(this, "Mã nhân viên không được quá 7 ký tự");
        return;
    }

    // Kiểm tra tên nhân viên
    if (hoTen.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không được để trống tên");
        txtHoTen.requestFocus();
        return;
    } else if (hoTen.length() > 50) {
        JOptionPane.showMessageDialog(this, "Tên nhân viên không được quá 50 ký tự");
        txtHoTen.requestFocus();
        return;
    } else if (!hoTen.matches("^[\\p{L}\\s]+$")) {
        JOptionPane.showMessageDialog(this, "Tên không hợp lệ.");
        txtHoTen.requestFocus();
        return;
    }

    // Kiểm tra số điện thoại
    if (sdt.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại");
        txtSDT.requestFocus();
        return;
    } else if (sdt.length() != 10) {
        JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 ký tự");
        txtSDT.requestFocus();
        return;
    } else if (!sdt.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
        txtSDT.requestFocus();
        return;
    }

    // Kiểm tra email
    if (email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập email");
        txtEmail.requestFocus();
        return;
    } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
        JOptionPane.showMessageDialog(this, "Email không hợp lệ");
        txtEmail.requestFocus();
        return;
    }

    // Kiểm tra vai trò hiện tại của nhân viên
//    boolean isManager = false;
//    for (NhanVienModel nv : rp.getAll()) {
//        if (nv.getMaNV().equals(maNV) && nv.isVaiTro()) {
//            isManager = true;
//            break;
//        }
//    }
//    
//    if (isManager) {
//        JOptionPane.showMessageDialog(this, "Không thể thay đổi thông tin của nhân viên có vai trò quản lý.");
//        return;
//    }

    // Tiến hành cập nhật nhân viên
    NhanVienModel viewModel = new NhanVienModel();
    viewModel.setMaNV(maNV);
    viewModel.setHoTen(hoTen);
    viewModel.setGioiTinh(rdoNam.isSelected());
    viewModel.setSdt(sdt);
    viewModel.setEmail(email);

    // Lấy giá trị từ JComboBox
    viewModel.setVaiTro(cboVaiTro.getSelectedItem().equals("Quản lý"));
    viewModel.setTrangThai(cboTrangThai.getSelectedItem().equals("Đang làm"));

    Integer result = rp.update(viewModel);

    if (result != null && result > 0) {
        JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên thành công");
        fillTable(rp.getAll());
    } else {
        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin nhân viên");
    }
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Lỗi hệ thống, không thể cập nhật thông tin nhân viên");
}

       
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
         fillTable(rp.search(txtTimKiem.getText()));
          String vaiTro = cboLocVT.getSelectedItem().toString();
        String trangThai = cboLocTT.getSelectedItem().toString();
        
        // Lọc dữ liệu trong bảng dựa trên vai trò và trạng thái
        filterTable(vaiTro, trangThai);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clearForm();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
    int index = tblQLNV.getSelectedRow();
    if (index != -1) {
    NhanVienModel nv = rp.getAll().get(index);
    int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn cho nghỉ việc nhân viên này?", "Xác nhận nghỉ việc", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        rp.delete(nv.getMaNV());
        JOptionPane.showMessageDialog(null, "Nhân viên đã được chuyển nghỉ việc thành công.");
        fillTable(rp.getAll());
    }
    } else {
    JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để cho nghỉ việc.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void cboVaiTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVaiTroActionPerformed
        
    }//GEN-LAST:event_cboVaiTroActionPerformed
       int t = 1;
    private void btnTCDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTCDSActionPerformed
        // TODO add your handling code here:
       
        if (t == 1) {
            fillTable(rp.TatCaDS());
            btnTCDS.setText("Ẩn phiếu đã xóa");
            t++;
        } else {
            fillTable(rp.getAll());
            btnTCDS.setText("Tất cả danh sách");
            t = 1;

        }
    }//GEN-LAST:event_btnTCDSActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTCDS;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboLocTT;
    private javax.swing.JComboBox<String> cboLocVT;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cboVaiTro;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblQLNV;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
    
