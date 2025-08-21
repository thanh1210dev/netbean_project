/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import javax.print.attribute.standard.SheetCollate;
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

import reponsitory.HoaDonChiTiet_Repo;
import reponsitory.HoaDon_Repo;
import reponsitory.LichSuHoaDonRepos;

import response.HinhThucThanhToan_Response;
import response.HoaDonCT_Response;
import response.HoaDon_Response;
import response.LichSuHoaDon_Response;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
/**
 *
 * @author Admin
 */
public class JpanelHoaDon1 extends javax.swing.JPanel {

    private HoaDon_Repo hdrp = new HoaDon_Repo();
    private HoaDonChiTiet_Repo hdct = new HoaDonChiTiet_Repo();
    private LichSuHoaDonRepos lshd = new LichSuHoaDonRepos();
    private DefaultTableModel mol = new DefaultTableModel();
    private DefaultComboBoxModel dcbm = new DefaultComboBoxModel();

    /**
     * Creates new form JpanelHoaDon1
     */
    public JpanelHoaDon1() {
        initComponents();

        this.showDataTableHoaDon(hdrp.getAll());

        this.fillCombo();

    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Hoá đơn");
        JpanelHoaDon1 jbh = new JpanelHoaDon1();
        jFrame.add(jbh);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    //
    public String status(Integer status) {
        if (null == status) {
            return "Đã hủy";
        } else {
            switch (status) {
                case 1:
                    return "Đã thanh toán";
                case 2:
                    return "Chưa thanh toán";
                default:
                    return "Đã hủy";
            }
        }
    }

    public void showDataTableHoaDon(ArrayList<HoaDon_Response> list) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        mol = (DefaultTableModel) tbl_banghoadon.getModel();
        mol.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        list.forEach(s
                -> {
            String FormatDonGia = currencyFormatter.format(s.getTongTien());
            String FormatSauGiam = currencyFormatter.format(s.getTienSauGiam());

            mol.addRow(new Object[]{
                index.getAndIncrement(), s.getMa(),
                s.getMaNhanVien(),
                s.getTenKhachHang(),
                s.getSDT(),
                s.getDiaChi(),
                FormatDonGia,
                FormatSauGiam,
                status(s.getTrangThai())
            });
        });
    }

    public void showDataTableLSHoaDon(ArrayList<LichSuHoaDon_Response> list) {
        mol = (DefaultTableModel) tbl_lsuhoadon.getModel();
        mol.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        list.forEach(s -> mol.addRow(new Object[]{
            index.getAndIncrement(), s.getMaNhanVien(), s.getNgayDatHang(), s.getHTTT()
        }));
    }

    public void showDataTableHoaDonCT(ArrayList<HoaDonCT_Response> list) {
        mol = (DefaultTableModel) tbl_hoadonct.getModel();
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        mol.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        list.forEach(s -> {
            String FormatDonGia = currencyFormatter.format(s.getDonGia());
            String FormatThanhTien = currencyFormatter.format(s.getThanhTien());
            mol.addRow(new Object[]{
                index.getAndIncrement(), s.getMaSP(),
                s.getTenSP(),
                s.getTenMau(),
                s.getDoCang(),
                s.getCDVot(),
                s.getSoLuong(),
                FormatDonGia,
                FormatThanhTien
            });
        });
    }

    void fillCombo() {
        DefaultComboBoxModel cmol = (DefaultComboBoxModel) cbo_trangthai.getModel();
        cmol.removeAllElements();
        cmol.addElement("Tất cả");
        cmol.addElement("Đã thanh toán");
        cmol.addElement("Chưa thanh toán");
        cmol.addElement("Đã hủy");
    }

//    public void showComboBox(ArrayList<HoaDon_Response> list){
//        cbo_trangthai.removeAllItems();
//        hdrp.getAll().forEach(th -> dcbm.addElement(th.isTrangThai()));
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_lsuhoadon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_banghoadon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_min = new javax.swing.JTextField();
        txt_max = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        cbo_trangthai = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_hoadonct = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1121, 671));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lịch sử hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tbl_lsuhoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã NV", "Ngày đặt hàng", "HTTT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_lsuhoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_lsuhoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_lsuhoadon);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ HOÁ ĐƠN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hoá Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(765, 301));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Tìm kiếm tất cả các trường:");

        txt_search.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btn_search.setBackground(new java.awt.Color(204, 255, 255));
        btn_search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search_icon.png"))); // NOI18N
        btn_search.setText("Tìm Kiếm");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 204, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("XUẤT FILE HOÁ ĐƠN");

        tbl_banghoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hoá đơn", "Mã nhân viên", "Tên khách hàng", "SDT", "Địa chỉ", "Tổng tiền", "Tiền sau giảm", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_banghoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_banghoadonMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_banghoadon);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setBackground(new java.awt.Color(0, 204, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("XUẤT FILE HOÁ ĐƠN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bill.png"))); // NOI18N
        jButton3.setText("IN HOÁ ĐƠN");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(300, 43));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tìm theo khoảng giá:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_min, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_max, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_max, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbo_trangthai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_trangthai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbo_trangthaiMouseClicked(evt);
            }
        });
        cbo_trangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_trangthaiActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Trạng thái:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(cbo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(296, 296, 296)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(54, 54, 54)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(488, 488, 488))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(497, 497, 497)))
                .addComponent(jButton1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(232, 232, 232)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(8, 8, 8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hoá đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tbl_hoadonct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Màu sắc", "Độ căng", "Chiều dài vợt", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbl_hoadonct);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(474, 474, 474)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      int rowCountBangHoaDon = tbl_banghoadon.getRowCount();
    int rowCountHoaDonCt = tbl_hoadonct.getRowCount();

    int[] selectedRowsBangHoaDon = tbl_banghoadon.getSelectedRows();

// Kiểm tra xem có dòng nào được chọn trong bảng hóa đơn hay không
if (selectedRowsBangHoaDon.length == 0) {
    JOptionPane.showMessageDialog(null, "Bạn cần chọn ít nhất một dòng trong bảng hóa đơn để xuất.");
    return;
}
    if (rowCountHoaDonCt == 0) {
        JOptionPane.showMessageDialog(null, "Không có dữ liệu trong bảng chi tiết hóa đơn để xuất.");
        return;
    }

    // Tiến hành tạo file PDF
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn vị trí và tên file để lưu PDF");
    int userSelection = fileChooser.showSaveDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();
        String outputPath = fileToSave.getAbsolutePath();

        // Đảm bảo file có phần mở rộng .pdf
        if (!outputPath.toLowerCase().endsWith(".pdf")) {
            outputPath += ".pdf";
        }

        // Tạo file PDF
        try {
            // Tạo tài liệu mới
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open(); // Mở tài liệu để ghi

            // Tải font tiếng Việt
            BaseFont baseFont = BaseFont.createFont("E:\\Du_An_1-View\\lib\\TimesNewRoman\\SVN-Times New Roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(baseFont, 18, Font.BOLD);
            Font boldFont = new Font(baseFont, 12, Font.BOLD);
            Font regularFont = new Font(baseFont, 12, Font.NORMAL);

            // Thêm tiêu đề PDF
            Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG\nBILL OF SALE", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            for (int i = 0; i < rowCountBangHoaDon; i++) {
                // Lấy dữ liệu từ dòng trong tbl_banghoadon
                String maHoaDon = (String) tbl_banghoadon.getValueAt(i, 1); // Giả sử cột 1 là MaHoaDon
                String hoTen = (String) tbl_banghoadon.getValueAt(i, 3); // Giả sử cột 3 là HoTen
                
                // Lấy dữ liệu từ bảng tbl_hoadonct tương ứng
                PdfPTable productTable = new PdfPTable(5);
                productTable.setWidthPercentage(100);
                productTable.setSpacingBefore(20f);

                // Thêm tiêu đề bảng sản phẩm
                productTable.addCell(new PdfPCell(new Phrase("STT", boldFont)));
                productTable.addCell(new PdfPCell(new Phrase("Tên sản phẩm", boldFont)));
                productTable.addCell(new PdfPCell(new Phrase("Số lượng", boldFont)));
                productTable.addCell(new PdfPCell(new Phrase("Đơn giá", boldFont)));
                productTable.addCell(new PdfPCell(new Phrase("Thành tiền", boldFont)));

                // Thêm chi tiết sản phẩm từ bảng tbl_hoadonct
                for (int j = 0; j < rowCountHoaDonCt; j++) {
                    String tenSanPham = (String) tbl_hoadonct.getValueAt(j, 2); // Giả sử cột 2 là TenSanPham
                    Integer soLuong = Integer.parseInt(tbl_hoadonct.getValueAt(j, 6).toString().replaceAll("[^\\d]", "")); // Giả sử cột 6 là SoLuong
                    Double donGia = Double.parseDouble(tbl_hoadonct.getValueAt(j, 7).toString().replaceAll("[^\\d.]", "")); // Giả sử cột 7 là DonGia

                    productTable.addCell(new PdfPCell(new Phrase(String.valueOf(j + 1), regularFont)));
                    productTable.addCell(new PdfPCell(new Phrase(tenSanPham, regularFont)));
                    productTable.addCell(new PdfPCell(new Phrase(String.valueOf(soLuong), regularFont)));
                    productTable.addCell(new PdfPCell(new Phrase(String.format("%,.0f đ", donGia*1000), regularFont)));
                    productTable.addCell(new PdfPCell(new Phrase(String.format("%,.0f đ", soLuong * donGia*1000), regularFont)));
                }

                document.add(productTable);

                // Thêm tổng tiền và trạng thái đơn hàng
                PdfPTable totalTable = new PdfPTable(2);
                totalTable.setWidthPercentage(100);
                totalTable.setSpacingBefore(10f);
                totalTable.setSpacingAfter(10f);
                totalTable.addCell(getCell("Trạng thái đơn hàng:", PdfPCell.ALIGN_LEFT, boldFont));
                totalTable.addCell(getCell("Hoàn thành", PdfPCell.ALIGN_RIGHT, regularFont));
                document.add(totalTable);

                // Tạo mã QR cho hóa đơn
                String qrContent = maHoaDon;
                BarcodeQRCode qrCode = new BarcodeQRCode(qrContent, 100, 100, null);
                com.itextpdf.text.Image qrImage = qrCode.getImage();
                qrImage.setAlignment(Element.ALIGN_RIGHT);
                qrImage.setSpacingBefore(10f);
                document.add(qrImage);

                // Thêm footer
                Paragraph footer = new Paragraph("Copyright © 2023 Yonex\nAll rights reserved", boldFont);
                footer.setAlignment(Element.ALIGN_CENTER);
                footer.setSpacingBefore(20f);
                document.add(footer);

                // Tạo ngắt trang nếu không phải là hóa đơn cuối cùng
                if (i < rowCountBangHoaDon - 1) {
                    document.newPage();
                }
            }

            // Đóng tài liệu
            document.close();
            JOptionPane.showMessageDialog(null, "In thành công! File đã được lưu tại: " + outputPath);
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tạo file PDF.");
        }
    }
    }//GEN-LAST:event_jButton3ActionPerformed
private PdfPCell getCell(String text, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
    private void cbo_trangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_trangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_trangthaiActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
//        showDataTableHoaDon(hdrp.search(txt_search.getText(), cbo_trangthai.getSelectedIndex()));
//        String keyword = txt_search.getText();
//        Integer trangThai = cbo_trangthai.getSelectedIndex();
//        Double minTongTien = null;
//        Double maxTongTien = null;
//
//        if (!txt_min.getText().isEmpty()) {
//            minTongTien = Double.parseDouble(txt_min.getText());
//        }
//        if (!txt_max.getText().isEmpty()) {
//            maxTongTien = Double.parseDouble(txt_max.getText());
//        }
//
//        ArrayList<HoaDon_Response> search = hdrp.search(keyword, trangThai, minTongTien, maxTongTien);
//        this.showDataTableHoaDon(search);
        String keyword = txt_search.getText();
        Integer trangThai = cbo_trangthai.getSelectedIndex();
        Double minTongTien = null;
        Double maxTongTien = null;

         if (isValidCouponCode(txt_search.getText())) {
        JOptionPane.showMessageDialog(this, "Hóa đơn chỉ được chứa chữ và số.");
        
    }
        if (!txt_min.getText().isEmpty()) {
            minTongTien = Double.parseDouble(txt_min.getText());
        }
        if (!txt_max.getText().isEmpty()) {
            maxTongTien = Double.parseDouble(txt_max.getText());
        }

        if (minTongTien != null && (minTongTien < 0 || minTongTien > 1_000_000_000)) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lớn hơn 0 và nhỏ hơn 1 tỷ.");
            return;
        }
        if (maxTongTien != null && (maxTongTien < 0 || maxTongTien > 1_000_000_000)) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lớn hơn 0 và nhỏ hơn 1 tỷ.");
            return;
        }

// Kiểm tra xem minTongTien có lớn hơn maxTongTien không
        if (minTongTien != null && maxTongTien != null && minTongTien > maxTongTien) {
            JOptionPane.showMessageDialog(null, "Giá trị tối thiểu không thể lớn hơn giá trị tối đa.");
            return;
        }

        ArrayList<HoaDon_Response> search = hdrp.search(keyword, trangThai, minTongTien, maxTongTien);
        this.showDataTableHoaDon(search);

    }//GEN-LAST:event_btn_searchActionPerformed
 private boolean isValidCouponCode(String str) {
    // Biểu thức chính quy cho phép các ký tự chữ và số
    String regex = "^[$,^,&,*,<,>,|,!,;,:,  ,#,'',+,=,{}]+$";
    return str.matches(regex);
}

    private void tbl_banghoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_banghoadonMouseClicked
        int id = hdrp.getAll().get(tbl_banghoadon.getSelectedRow()).getId();
        showDataTableHoaDonCT(hdct.getAll(id));
        showDataTableLSHoaDon(lshd.getAll2(id));

    }//GEN-LAST:event_tbl_banghoadonMouseClicked

    private void cbo_trangthaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_trangthaiMouseClicked
        // TODO add your handling code here:
//        if (cbo_trangthai.getSelectedItem().equals("Tất cả")) {
//            hdrp.getAll();
//        }else if (cbo_trangthai.getSelectedItem().equals("Chưa thanh toán")) {
//            
//        }
    }//GEN-LAST:event_cbo_trangthaiMouseClicked

    private void tbl_lsuhoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_lsuhoadonMouseClicked
        // TODO add your handling code here:
        int id = hdrp.getAll().get(tbl_lsuhoadon.getSelectedRow()).getId();
        showDataTableLSHoaDon(lshd.getAll2(id));
    }//GEN-LAST:event_tbl_lsuhoadonMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

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
                for (int i = 0; i < tbl_banghoadon.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tbl_banghoadon.getColumnName(i));
                }
                for (int j = 0; j < tbl_banghoadon.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tbl_banghoadon.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (tbl_banghoadon.getValueAt(j, k) != null) {
                            cell.setCellValue(tbl_banghoadon.getValueAt(j, k).toString());
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_search;
    private javax.swing.JComboBox<String> cbo_trangthai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tbl_banghoadon;
    private javax.swing.JTable tbl_hoadonct;
    private javax.swing.JTable tbl_lsuhoadon;
    private javax.swing.JTextField txt_max;
    private javax.swing.JTextField txt_min;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
