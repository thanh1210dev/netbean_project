/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.microsoft.sqlserver.jdbc.StringUtils;
import entity.PhieuGiamGia;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jdk.jfr.consumer.EventStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import reponsitory.PhieuGiamGiaReponsitory;

/**
 *
 * @author Asus
 */
public class JpanelPhieuGiamGia extends javax.swing.JPanel {

    DefaultTableModel model = new DefaultTableModel();
    PhieuGiamGiaReponsitory PGGR = new PhieuGiamGiaReponsitory();
    ArrayList<PhieuGiamGia> ListPGG = new ArrayList<>();

    /**
     * Creates new form JpanelPhieuGiamGia
     */
    public JpanelPhieuGiamGia() {
        initComponents();
        fillCombo();
        fillTable(PGGR.getAll());
        setAutoStatus();
        fillComboLoaiGiam();
        fillComboTT2();
        fillComboLoaiGiam2();
        tblPGG.setBackground(Color.white);
        tblPGG.setRowHeight(25);

    }

    public static String generateCouponCode(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder couponCode = new StringBuilder();
        Random rnd = new Random();
        while (couponCode.length() < length) {
            int index = (int) (rnd.nextFloat() * chars.length());
            couponCode.append(chars.charAt(index));
        }
        return couponCode.toString();
    }

    public static void main(String[] args) {
        JFrame j = new JFrame("Quản lý phiếu giảm giá");
        JpanelPhieuGiamGia j2 = new JpanelPhieuGiamGia();
        j.add(j2);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.pack();
        j.setVisible(true);
    }

    public void fillTable(List<PhieuGiamGia> list) {
        model = (DefaultTableModel) tblPGG.getModel();
        model.setRowCount(0);
        int c = 1;
        for (PhieuGiamGia phieu : list) {
            String t = "";
            if (phieu.getTrangThai() == 1) {
                t = "Sắp diễn ra";

            } else if (phieu.getTrangThai() == 2) {
                t = "Đang diễn ra";

            } else {
                t = "Hết hiệu lực";

            }
            model.addRow(new Object[]{
                c++,
                phieu.getMa(),
                phieu.getTen(),
                phieu.getNgayTao(),
                phieu.getNgayHet(),
                phieu.getHoaDonToiThieu(),
                phieu.getGiaTriGiamMax(),
                phieu.getLoaiGiam() == true ? "Tiền mặt" : "Phần trăm",
                t});
        }

    }
  //Hàm này có tác dụng format lại tiền
    private String formatCash(Double price) {
        try {
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            String formated = currencyFormatter.format(price);
            return formated;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi trong quá trình format");
            return "";
        }

    }

    //Hàm này có tác dụng format lại tiền
    private String formatCash(float price) {
        try {
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            String formated = currencyFormatter.format(price);
            return formated;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi trong quá trình format");
            return "";
        }

    }

    void fillCombo() {
        DefaultComboBoxModel cmodel = (DefaultComboBoxModel) cboStatus.getModel();
        cmodel.removeAllElements();
        cmodel.addElement("Sắp diễn ra");
        cmodel.addElement("Đang diễn ra");
        cmodel.addElement("Hết hiệu lực");
    }

    void fillComboLoaiGiam() {
        DefaultComboBoxModel cmodel = (DefaultComboBoxModel) cboLoaiGiam.getModel();
        cmodel.removeAllElements();
        cmodel.addElement("Tiền mặt");
        cmodel.addElement("Phần trăm");
    }

    void fillComboLoaiGiam2() {
        DefaultComboBoxModel cmodel = (DefaultComboBoxModel) cboLG2.getModel();
        cmodel.removeAllElements();
        cmodel.addElement("Tất cả");
        cmodel.addElement("Tiền mặt");
        cmodel.addElement("Phần trăm");
    }

    void fillComboTT2() {
        DefaultComboBoxModel cmodel = (DefaultComboBoxModel) cboTT2.getModel();
        cmodel.removeAllElements();
        cmodel.addElement("Tất cả");
        cmodel.addElement("Sắp diễn ra");
        cmodel.addElement("Đang diễn ra");
        cmodel.addElement("Hết hiệu lực");
    }

    public boolean checkForm() {
        if (txtTenPhieu.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Bạn nhập tên phiếu giảm giá quá 20 kí tự");
            return false;

        }
        if (txtTenPhieu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên phiếu giảm giá");
            return false;

        }
        if (txtNgayBD.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập ngày bắt đầu phiếu giảm giá");
            return false;
        }
        if (txtNgayKT.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập ngày kết thúc phiếu giảm giá");
            return false;
        }
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DATE, -1); // Trừ đi 1 ngày từ ngày hiện tại
        Date oneDayBeforeNow = cal.getTime();

        if (txtNgayBD.getDate().before(oneDayBeforeNow)) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải là hôm nay hoặc tương lai");
            return false;
        }
        if (txtNgayKT.getDate().before(txtNgayBD.getDate())) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày bắt đầu");
            return false;
        }

        if (txtGiaTriGiam.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá trị giảm cao nhất của  phiếu giảm giá");
            return false;

        }
        if (txtHDTT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập Hóa đơn tối thiếu của phiếu giảm giá");
            return false;

        }
        float hdttValue = Float.parseFloat(txtHDTT.getText());
        float giaTriGiamValue = Float.parseFloat(txtGiaTriGiam.getText());

        if (hdttValue < 0 || giaTriGiamValue < 0) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập số dương vào ô giá trị giảm và giá trị tối thiểu.");
            return false;
        }

        if (!StringUtils.isNumeric(txtHDTT.getText()) || !StringUtils.isNumeric(txtGiaTriGiam.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập số vào ô giá trị giảm và giá trị tối thiểu ");
            return false;
        }

        if (isValidCouponCode(txtTenPhieu.getText())) {
            JOptionPane.showMessageDialog(this, "Tên phiếu giảm giá chỉ được chứa chữ và số.");
            return false;
        }
        if (cboLoaiGiam.getSelectedItem().equals("Phần trăm")) {
            if (giaTriGiamValue > 100) {
                JOptionPane.showMessageDialog(this, "Loại giảm là phần trăm nên bạn phải nhập nhỏ hơn 100%");
                return false;
            }
        }

        return true;
    }

    private boolean isValidCouponCode(String str) {
        // Biểu thức chính quy cho phép các ký tự chữ và số
        String regex = "^[$,^,&,*,<,>,|,!,;,:,  ,#,'',+,=,{}]+$";
        return str.matches(regex);
    }

    private void setAutoStatus() {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        break;
                    }
                    List<PhieuGiamGia> listPGG = PGGR.getAll();
                    for (PhieuGiamGia p : listPGG) {
                        Date now = new Date();
                        Date hetHan = p.getNgayHet();
                        Date BatDau = p.getNgayTao();
                        if (hetHan.before(now)) {
                            PGGR.setStatusEXP(p.getMa(), 3);

                        }
                        if (BatDau.before(now)) {
                            PGGR.setStatusEXP(p.getMa(), 2);

                        }
                        if (BatDau.after(now)) {
                            PGGR.setStatusEXP(p.getMa(), 1);

                        }
                    }
                }
            }
        };
        t.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaPhieu = new javax.swing.JTextField();
        txtTenPhieu = new javax.swing.JTextField();
        txtHDTT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtGiaTriGiam = new javax.swing.JTextField();
        cboStatus = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        txtNgayBD = new com.toedter.calendar.JDateChooser();
        cboLoaiGiam = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPGG = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btnTCDS = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cboTT2 = new javax.swing.JComboBox<>();
        cboLG2 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnLamMoiData = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Form thông tin phiếu giảm giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã Phiếu:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên Phiếu:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Ngày Bắt Đầu:");

        txtMaPhieu.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Ngày Kết Thúc:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Giá Trị Tối Thiểu:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Trạng Thái:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Giá Trị Giảm:");

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboStatus.setEnabled(false);
        cboStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboStatusItemStateChanged(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton4.setText("jButton2");

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

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/icon/refresh (1).png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(204, 204, 204));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/icon/x-button.png"))); // NOI18N
        jButton8.setText("Hết hiệu lực");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton8)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoi, btnSua, btnThem, jButton8});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi)
                .addGap(18, 18, 18)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLamMoi, btnSua, btnThem, jButton8});

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Loại giảm:");

        cboLoaiGiam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel2)))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtTenPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(txtNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHDTT)
                            .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboLoaiGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtGiaTriGiam, txtHDTT, txtMaPhieu, txtTenPhieu});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtHDTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel9)
                                .addComponent(cboLoaiGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel7)
                                .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("PHIẾU GIẢM GIÁ");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phiếu giảm giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblPGG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Phiếu", "Tên Phiếu", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giá Trị Tối Thiểu", "Giá Trị Giảm ", "Loại Giảm", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPGG.setShowGrid(true);
        tblPGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPGGMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPGG);

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search_icon.png"))); // NOI18N
        jButton1.setText("Tìm Kiếm");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnTCDS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTCDS.setText("Tất cả danh sách");
        btnTCDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTCDSActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bộ Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Loại Giảm:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Trạng Thái:");

        cboTT2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboLG2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setBackground(new java.awt.Color(204, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Lọc");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel10))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cboLG2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboTT2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jButton2.setBackground(new java.awt.Color(0, 204, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Xuất File");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnLamMoiData.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/icon/refresh (1).png"))); // NOI18N
        btnLamMoiData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiDataMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(76, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnTCDS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(12, 12, 12)))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLamMoiData, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLamMoiData, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1))
                                .addGap(18, 18, 18)
                                .addComponent(btnTCDS)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(378, 378, 378)
                        .addComponent(jLabel1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkForm()) {

            Date now = new Date();
            Date BatDau = txtNgayBD.getDate();
            int tt = 1;
            if (BatDau.before(now)) {
                tt = 2;
            } else {
                tt = 1;
            }
            PhieuGiamGia PGG = PhieuGiamGia.builder()
                    .ma(generateCouponCode(10))
                    .Ten(txtTenPhieu.getText())
                    .ngayTao(txtNgayBD.getDate())
                    .NgayHet(txtNgayKT.getDate())
                    .hoaDonToiThieu(Float.parseFloat(txtHDTT.getText()))
                    .giaTriGiamMax(Float.parseFloat(txtGiaTriGiam.getText()))
                    .loaiGiam(cboLoaiGiam.getSelectedItem().equals("Tiền mặt") ? true : false)
                    .trangThai(tt)
                    .build();
            PGGR.add(PGG);
            fillTable(PGGR.getAll());
            JOptionPane.showMessageDialog(this, "Bạn đã thêm mới thành công");
        }


    }//GEN-LAST:event_btnThemActionPerformed

    private void tblPGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPGGMouseClicked
        // TODO add your handling code here:
        int index = tblPGG.getSelectedRow();
        String trangThai = tblPGG.getValueAt(index, 8).toString();
        if (trangThai.equals("Sắp diễn ra")) {
            cboStatus.setSelectedItem("Sắp diễn ra");
        } else if (trangThai.equals("Đang diễn ra")) {
            cboStatus.setSelectedItem("Đang diễn ra");
        } else {
            cboStatus.setSelectedItem("Hết hiệu lực");
        }
        String LoaiGiam = tblPGG.getValueAt(index, 7).toString();
        if (LoaiGiam.equals("Tiền mặt")) {
            cboLoaiGiam.setSelectedItem("Tiền mặt");
        } else {
            cboLoaiGiam.setSelectedItem("Phần trăm");
        }
        txtMaPhieu.setText(tblPGG.getValueAt(index, 1).toString());
        txtTenPhieu.setText(tblPGG.getValueAt(index, 2).toString());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date ngayBD;
        Date ngayKT;
        try {
            ngayBD = format.parse(tblPGG.getValueAt(index, 3).toString());
            txtNgayBD.setDate(ngayBD);
            ngayKT = format.parse(tblPGG.getValueAt(index, 4).toString());
            txtNgayKT.setDate(ngayKT);
        } catch (ParseException ex) {
            Logger.getLogger(JpanelPhieuGiamGia.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtGiaTriGiam.setText(tblPGG.getValueAt(index, 6).toString());
        txtHDTT.setText(tblPGG.getValueAt(index, 5).toString());

        txtTenPhieu.setText(tblPGG.getValueAt(index, 2).toString());
        tblPGG.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_tblPGGMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (checkForm()) {
            PhieuGiamGia PGG = PhieuGiamGia.builder()
                    .Ten(txtTenPhieu.getText())
                    .ngayTao(txtNgayBD.getDate())
                    .NgayHet(txtNgayKT.getDate())
                    .hoaDonToiThieu(Float.parseFloat(txtHDTT.getText()))
                    .giaTriGiamMax(Float.parseFloat(txtGiaTriGiam.getText()))
                    .loaiGiam(cboLoaiGiam.getSelectedItem().equals("Tiền mặt") ? true : false)
                    .trangThai(cboStatus.getSelectedIndex() + 1)
                    .build();
            PGGR.update(PGG, model.getValueAt(tblPGG.getSelectedRow(), 1).toString());
            if (!cboLoaiGiam.equals("Tất cả") || !cboStatus.equals("Tất cả")) {
                LocPGG();
                JOptionPane.showMessageDialog(this, "Bạn đã sửa thành công");
                return;
            }
            fillTable(PGGR.getAll());
            JOptionPane.showMessageDialog(this, "Bạn đã sửa thành công");

        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtMaPhieu.setText("");
        txtTenPhieu.setText("");
        txtNgayBD.setDate(new Date());
        txtNgayKT.setDate(new Date());
        txtGiaTriGiam.setText("");
        txtHDTT.setText("");
        cboLoaiGiam.setSelectedIndex(0);
        cboStatus.setSelectedIndex(0);


    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        int t = JOptionPane.showConfirmDialog(this, "Bạn muốn chuyển hết hiện lực phiếu không?", "Hiệu lực phiếu", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (t == JOptionPane.YES_OPTION) {
            PGGR.remove(model.getValueAt(tblPGG.getSelectedRow(), 1).toString());
            fillTable(PGGR.getAll());
            JOptionPane.showMessageDialog(this, "Bạn đã chuyển hạn thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một Phiếu giảm giá để chuyển .", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    int i = 1;
    private void btnTCDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTCDSActionPerformed
        // TODO add your handling code here:
        if (i == 1) {
            fillTable(PGGR.TatCaDS());
            btnTCDS.setText("Ẩn phiếu đã xóa");
            i++;
        } else {
            fillTable(PGGR.getAll());
            btnTCDS.setText("Tất cả danh sách");
            i = 1;

        }


    }//GEN-LAST:event_btnTCDSActionPerformed
    public boolean checkTimkiem() {
        if (isValidCouponCode(txtTimKiem.getText())) {
            JOptionPane.showMessageDialog(this, "Tìm tên với mã không được dùng kí tự đặt biệt.");
            return false;
        }
        if (txtTimKiem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống trường tìm kiếm");
            return false;
        }
        return true;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        if (checkTimkiem()) {
            try {
                if (null != PGGR.search(txtTimKiem.getText().trim())) {
                    fillTable(PGGR.search(txtTimKiem.getText()));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm!!");
            }
        }


    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            String path = "E:\\Du_an_mau\\PRO1041 (2)\\PRO1041\\src\\excel";
            JFileChooser jFileChooser = new JFileChooser(path);
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = (Sheet) wb.createSheet("Account");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tblPGG.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblPGG.getColumnName(i));
                }
                for (int j = 0; j < tblPGG.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tblPGG.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (tblPGG.getValueAt(j, k) != null) {
                            cell.setCellValue(tblPGG.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                EventStream.openFile(saveFile.toPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void cboStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboStatusItemStateChanged
        // TODO add your handling code here:{
        //        if(cboStatus.getSelectedItem() != null){
        //             System.out.println(cboStatus.getSelectedItem());
        //        if(cboStatus.getSelectedItem().equals("Hết hạn")){
        //            System.out.println(1);
        //        }else{
        //            System.out.println(0);
        //        }
        //        }
    }//GEN-LAST:event_cboStatusItemStateChanged

    private void btnLamMoiDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiDataMouseClicked
        // TODO add your handling code here:
        fillTable(PGGR.getAll());
    }//GEN-LAST:event_btnLamMoiDataMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        LocPGG();


    }//GEN-LAST:event_jButton3ActionPerformed

    private void LocPGG() {
        // TODO add your handling code here:
        String loaiGiamSelected = (String) cboLG2.getSelectedItem();
        String trangThaiSelected = (String) cboTT2.getSelectedItem();

        if (loaiGiamSelected.equals("Tất cả") && trangThaiSelected.equals("Tất cả")) {
            fillTable(PGGR.TatCaDS());

        } else if (loaiGiamSelected.equals("Tất cả")) {
            int trangThai;
            if (trangThaiSelected.equalsIgnoreCase("Sắp diễn ra")) {
                trangThai = 1;
            } else if (trangThaiSelected.equalsIgnoreCase("Đang diễn ra")) {
                trangThai = 2;
            } else {
                trangThai = 3;
            }
            ArrayList<PhieuGiamGia> listPGG = getTrangThai(trangThai);
            if (listPGG != null) {
                fillTable(listPGG);
            } else {
                System.out.println("Không có dữ liệu trả về");
            }
        } else if (trangThaiSelected.equals("Tất cả")) {
            Boolean loaiGiam = loaiGiamSelected.equals("Tiền mặt") ? true : false;
            ArrayList<PhieuGiamGia> listPGG = getLoaiGiam(loaiGiam);
            if (listPGG != null) {
                fillTable(listPGG);
            } else {
                System.out.println("Không có dữ liệu trả về");
            }
        } else {
            Boolean loaiGiam = loaiGiamSelected.equals("Tiền mặt") ? true : false;
            int trangThai;
            if (trangThaiSelected.equalsIgnoreCase("Sắp diễn ra")) {
                trangThai = 1;
            } else if (trangThaiSelected.equalsIgnoreCase("Đang diễn ra")) {
                trangThai = 2;
            } else {
                trangThai = 3;
            }

            ArrayList<PhieuGiamGia> listPGG = PGGR.getLoaiGiamVaTrangThai(loaiGiam, trangThai);
            if (listPGG != null) {
                fillTable(listPGG);
            } else {
                System.out.println("Không có dữ liệu trả về");
            }
        }
    }

    public ArrayList<PhieuGiamGia> getTrangThai(int trangThai) {
        ListPGG = PGGR.TatCaDS();
        ArrayList<PhieuGiamGia> result = new ArrayList<>();
        for (PhieuGiamGia pgg : ListPGG) {
            if (pgg.getTrangThai() == trangThai) {
                result.add(pgg);
            }
        }
        return result;
    }

    public ArrayList<PhieuGiamGia> getLoaiGiam(boolean loaiGiam) {
        ListPGG = PGGR.TatCaDS();
        ArrayList<PhieuGiamGia> result = new ArrayList<>();
        for (PhieuGiamGia pgg : ListPGG) {
            if (pgg.getLoaiGiam() == loaiGiam) {
                result.add(pgg);
            }
        }
        return result;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoiData;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTCDS;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboLG2;
    private javax.swing.JComboBox<String> cboLoaiGiam;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.JComboBox<String> cboTT2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JColorChooser jColorChooser1;
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
    private javax.swing.JTable tblPGG;
    private javax.swing.JTextField txtGiaTriGiam;
    private javax.swing.JTextField txtHDTT;
    private javax.swing.JTextField txtMaPhieu;
    private com.toedter.calendar.JDateChooser txtNgayBD;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    private javax.swing.JTextField txtTenPhieu;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
