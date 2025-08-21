/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import config.DBconnextSQL;
import entity.HinhThucThanhToan;
import entity.HoaDon;
import entity.HoaDonChiTiet;
import entity.PhieuGiamGia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import response.HoaDonCT_Response;
import response.HoaDon_Response;
import response.SanPhamChiTietResponse;

/**
 *
 * @author ADMIN
 */
public class BanHangRepository {


    //Các biến toàn cục thực hiện kết nối csdl
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    //Hàm tạo hóa đơn chờ mới;
    public Integer createInvoicePending(HoaDon invoice) {
        int result = 0;
        this.sql = "INSERT INTO [dbo].[HOA_DON] ([Id_KH], [Id_NV], [Id_PGG], [Id_THANH_TOAN], [MA_HOA_DON], [TEN_KHACH_HANG], [NGAY_DAT_HANG], [SDT], [TONG_TIEN], [TONG_TIEN_KHI_GIAM], [TRANG_THAI]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, invoice.getIdKhachHang());
            ps.setObject(2, invoice.getIdNhanVien());
            ps.setObject(3, invoice.getIdPGG());
            ps.setObject(4, invoice.getIdThanhToan());
            ps.setObject(5, invoice.getMa());
            ps.setObject(6, invoice.getTenKhachHang());
            ps.setObject(7, invoice.getNgayDatHang());
            ps.setObject(8, invoice.getSDT());
            ps.setObject(9, invoice.getTongTien());
            ps.setObject(10, invoice.getTongTienKhiGiam());
            ps.setObject(11, invoice.getTrangThai());
            result = ps.executeUpdate(); // Thực thi câu lệnh SQL
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result; // Trả về số bản ghi được thêm
    }

    //Hàm lấy spct theo id;
    public SanPhamChiTietResponse getProductDetailById(Integer idSPCT) {
        this.sql = """
                   SELECT SPCT.ID, SP.MA_SAN_PHAM, SP.TEN, NCC.TEN AS TEN_NHA_CUNG_CAP, MS.TEN AS TEN_MAU, DCD.TEN AS DO_CUNG_DUA,
                                  DC.TEN AS DO_CANG, SPCT.SO_LUONG, SPCT.DON_GIA, SPCT.MO_TA, SPCT.NGAY_TAO,
                                  CDV.TEN AS CHIEU_DAI_VOT, DCB.TEN AS DIEM_CAN_BANG, TL.TEN AS TRONG_LUONG,
                                  DB.TEN AS DO_BAY, DMV.TEN AS DANG_MAT_VOT, SPCT.TRANG_THAI
                           FROM SAN_PHAM_CHI_TIET SPCT
                           JOIN SAN_PHAM SP ON SPCT.IDSP = SP.ID
                           JOIN NHA_CUNG_CAP NCC ON SPCT.MA_NHA_CUNG_CAP = NCC.ID
                           JOIN MAU_SAC MS ON SPCT.MA_MAU = MS.ID
                           JOIN DO_CUNG_DUA DCD ON SPCT.MA_DO_CUNG_DUA = DCD.ID
                           JOIN DO_CANG DC ON SPCT.MA_DO_CANG = DC.ID
                           JOIN CHIEU_DAI_VOT CDV ON SPCT.MA_CD_VOT = CDV.ID
                           JOIN DIEM_CAN_BANG DCB ON SPCT.MA_DIEM_CAN_BANG = DCB.ID
                           JOIN TRONG_LUONG TL ON SPCT.MA_TRONG_LUONG = TL.ID
                           JOIN DO_BAY DB ON SPCT.MA_DO_BAY = DB.ID
                           JOIN DANG_MAT_VOT DMV ON SPCT.MA_DANG_MAT_VOT = DMV.ID WHERE SPCT.ID = ?
                    """;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, idSPCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTietResponse spctResponse = new SanPhamChiTietResponse(
                        rs.getInt("ID"),
                        rs.getString("MA_SAN_PHAM"),
                        rs.getString("TEN_NHA_CUNG_CAP"),
                        rs.getString("TEN_MAU"),
                        rs.getString("DO_CUNG_DUA"),
                        rs.getString("DO_CANG"),
                        rs.getInt("SO_LUONG"),
                        rs.getFloat("DON_GIA"),
                        rs.getString("MO_TA"),
                        rs.getDate("NGAY_TAO"),
                        rs.getString("CHIEU_DAI_VOT"),
                        rs.getString("DIEM_CAN_BANG"),
                        rs.getString("TRONG_LUONG"),
                        rs.getString("DO_BAY"),
                        rs.getString("DANG_MAT_VOT"),
                        rs.getBoolean("TRANG_THAI"),
                        rs.getString("TEN")
                );
                return spctResponse;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    //Hàm load danh sách hóa đơn chờ thanh toán.
    public ArrayList<HoaDon_Response> getAllInvoicePending() {
        ArrayList<HoaDon_Response> list = new ArrayList<>();
        sql = """
            SELECT        dbo.HOA_DON.Id, dbo.HOA_DON.MA_HOA_DON, dbo.HOA_DON.TEN_KHACH_HANG, dbo.HOA_DON.NGAY_DAT_HANG, dbo.HOA_DON.SDT, dbo.HOA_DON.TONG_TIEN, dbo.HOA_DON.TRANG_THAI, 
                                       dbo.KHACH_HANG.DIA_CHI, dbo.NHAN_VIEN.MA_NHAN_VIEN
             FROM            dbo.HOA_DON INNER JOIN
                                      dbo.KHACH_HANG ON dbo.HOA_DON.Id_KH = dbo.KHACH_HANG.ID INNER JOIN
                                      dbo.NHAN_VIEN ON dbo.HOA_DON.Id_NV = dbo.NHAN_VIEN.ID WHERE dbo.HOA_DON.TRANG_THAI = 2 ORDER BY dbo.HOA_DON.NGAY_DAT_HANG DESC
              """;

        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon_Response reponse = HoaDon_Response.builder()
                        .id(rs.getInt(1))
                        .ma(rs.getString(2))
                        .tenKhachHang(rs.getString(3))
                        .ngayDatHang(rs.getDate(4))
                        .SDT(rs.getString(5))
                        .tongTien(rs.getDouble(6))
                        .trangThai(rs.getInt(7))
                        .diaChi(rs.getString(8))
                        .maNhanVien(rs.getString(9))
                        .build();
                list.add(reponse);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    //Hàm tạo hóa đơn chi tiết;
    public int createInvoieDetail(HoaDonChiTiet invoiceDetail) {
        this.sql = "INSERT INTO [dbo].[HOA_DON_CHI_TIET] ([ID_HD], [ID_SPCT], [SO_LUONG], [THANH_TIEN], [TRANG_THAI]) VALUES (?, ?, ?, ?, ?)";
        int result = 0;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, invoiceDetail.getIdHD());
            ps.setObject(2, invoiceDetail.getIdSPCT());
            ps.setObject(3, invoiceDetail.getSoLuong());
            ps.setObject(4, invoiceDetail.getThanhTien());
            ps.setObject(5, invoiceDetail.getTrangThai());
            result = ps.executeUpdate(); // Thực thi câu lệnh SQL
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Hàm lấy ra giá của spct;
    public Double getPriceProductDetail(Integer idSPCT) {
        this.sql = "SELECT DON_GIA from SAN_PHAM_CHI_TIET WHERE ID = ?";
        Double price = null;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, idSPCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                price = rs.getDouble(1);
                return price;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0d;
    }

    //Hàm hủy hóa đơn chưa phát sinh hdct;
    public int cancelInvoie(Integer idHD) {
        this.sql = "UPDATE HOA_DON SET TRANG_THAI = 3, TONG_TIEN = 0 WHERE ID = ?;";
        int result = 0;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, idHD);

            result = ps.executeUpdate(); // Thực thi câu lệnh SQL
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Hàm hủy hóa đơn đã phát sinh hdct;
    public int cancelInvoie2(Integer idHD, Integer idSPCT) {
        this.sql = """
            UPDATE HOA_DON SET TRANG_THAI = 3 WHERE ID = ?;
            UPDATE HOA_DON_CHI_TIET SET TRANG_THAI = 3 WHERE ID_HD = ? AND ID_SPCT = ?
            """;
        int result = 0;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, idHD);
            ps.setObject(2, idHD);
            ps.setObject(3, idSPCT);
            result = ps.executeUpdate(); // Thực thi câu lệnh SQL
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Hàm lấy số lượng hóa đơn có trong hệ thống;
    public Integer getSizeInvoice() {
        Integer result = 0;
        this.sql = "SELECT COUNT(*) from dbo.HOA_DON";
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result; // Trả về số bản ghi được thêm
    }

    //Hàm load danh sách sản phẩm;
    public ArrayList<SanPhamChiTietResponse> getAllProductDetail() {
         sql = """
                   SELECT SPCT.ID, SP.MA_SAN_PHAM, SP.TEN, NCC.TEN AS TEN_NHA_CUNG_CAP, MS.TEN AS TEN_MAU, DCD.TEN AS DO_CUNG_DUA,
                                  DC.TEN AS DO_CANG, SPCT.SO_LUONG, SPCT.DON_GIA, SPCT.MO_TA, SPCT.NGAY_TAO,
                                  CDV.TEN AS CHIEU_DAI_VOT, DCB.TEN AS DIEM_CAN_BANG, TL.TEN AS TRONG_LUONG,
                                  DB.TEN AS DO_BAY, DMV.TEN AS DANG_MAT_VOT, SPCT.TRANG_THAI
                           FROM SAN_PHAM_CHI_TIET SPCT
                           JOIN SAN_PHAM SP ON SPCT.IDSP = SP.ID
                           JOIN NHA_CUNG_CAP NCC ON SPCT.MA_NHA_CUNG_CAP = NCC.ID
                           JOIN MAU_SAC MS ON SPCT.MA_MAU = MS.ID
                           JOIN DO_CUNG_DUA DCD ON SPCT.MA_DO_CUNG_DUA = DCD.ID
                           JOIN DO_CANG DC ON SPCT.MA_DO_CANG = DC.ID
                           JOIN CHIEU_DAI_VOT CDV ON SPCT.MA_CD_VOT = CDV.ID
                           JOIN DIEM_CAN_BANG DCB ON SPCT.MA_DIEM_CAN_BANG = DCB.ID
                           JOIN TRONG_LUONG TL ON SPCT.MA_TRONG_LUONG = TL.ID
                           JOIN DO_BAY DB ON SPCT.MA_DO_BAY = DB.ID
                           JOIN DANG_MAT_VOT DMV ON SPCT.MA_DANG_MAT_VOT = DMV.ID
               WHERE SPCT.TRANG_THAI = 0
                    """;
        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();
        try {
             con = DBconnextSQL.getConnection();
             ps = con.prepareStatement(sql);
             rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTietResponse spctResponse = new SanPhamChiTietResponse(
                        rs.getInt("ID"),
                        rs.getString("MA_SAN_PHAM"),
                        rs.getString("TEN_NHA_CUNG_CAP"),
                        rs.getString("TEN_MAU"),
                        rs.getString("DO_CUNG_DUA"),
                        rs.getString("DO_CANG"),
                        rs.getInt("SO_LUONG"),
                        rs.getFloat("DON_GIA"),
                        rs.getString("MO_TA"),
                        rs.getDate("NGAY_TAO"),
                        rs.getString("CHIEU_DAI_VOT"),
                        rs.getString("DIEM_CAN_BANG"),
                        rs.getString("TRONG_LUONG"),
                        rs.getString("DO_BAY"),
                        rs.getString("DANG_MAT_VOT"),
                        rs.getBoolean("TRANG_THAI"),
                        rs.getString("TEN")
                );
                list.add(spctResponse);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return list;
    }
    
    public ArrayList<SanPhamChiTietResponse> getAllProductDetailByPriceMinMax(Double priceMin, Double priceMax) {
         sql = """
                   SELECT SPCT.ID, SP.MA_SAN_PHAM, SP.TEN, NCC.TEN AS TEN_NHA_CUNG_CAP, MS.TEN AS TEN_MAU, DCD.TEN AS DO_CUNG_DUA,
                                  DC.TEN AS DO_CANG, SPCT.SO_LUONG, SPCT.DON_GIA, SPCT.MO_TA, SPCT.NGAY_TAO,
                                  CDV.TEN AS CHIEU_DAI_VOT, DCB.TEN AS DIEM_CAN_BANG, TL.TEN AS TRONG_LUONG,
                                  DB.TEN AS DO_BAY, DMV.TEN AS DANG_MAT_VOT, SPCT.TRANG_THAI
                           FROM SAN_PHAM_CHI_TIET SPCT
                           JOIN SAN_PHAM SP ON SPCT.IDSP = SP.ID
                           JOIN NHA_CUNG_CAP NCC ON SPCT.MA_NHA_CUNG_CAP = NCC.ID
                           JOIN MAU_SAC MS ON SPCT.MA_MAU = MS.ID
                           JOIN DO_CUNG_DUA DCD ON SPCT.MA_DO_CUNG_DUA = DCD.ID
                           JOIN DO_CANG DC ON SPCT.MA_DO_CANG = DC.ID
                           JOIN CHIEU_DAI_VOT CDV ON SPCT.MA_CD_VOT = CDV.ID
                           JOIN DIEM_CAN_BANG DCB ON SPCT.MA_DIEM_CAN_BANG = DCB.ID
                           JOIN TRONG_LUONG TL ON SPCT.MA_TRONG_LUONG = TL.ID
                           JOIN DO_BAY DB ON SPCT.MA_DO_BAY = DB.ID
                           JOIN DANG_MAT_VOT DMV ON SPCT.MA_DANG_MAT_VOT = DMV.ID
               WHERE SPCT.TRANG_THAI = 0 AND SPCT.DON_GIA BETWEEN ? AND ?
                    """;
        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();
        try {
             con = DBconnextSQL.getConnection();
             ps = con.prepareStatement(sql);
             ps.setObject(1, priceMin);
             ps.setObject(2, priceMax);
             rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTietResponse spctResponse = new SanPhamChiTietResponse(
                        rs.getInt("ID"),
                        rs.getString("MA_SAN_PHAM"),
                        rs.getString("TEN_NHA_CUNG_CAP"),
                        rs.getString("TEN_MAU"),
                        rs.getString("DO_CUNG_DUA"),
                        rs.getString("DO_CANG"),
                        rs.getInt("SO_LUONG"),
                        rs.getFloat("DON_GIA"),
                        rs.getString("MO_TA"),
                        rs.getDate("NGAY_TAO"),
                        rs.getString("CHIEU_DAI_VOT"),
                        rs.getString("DIEM_CAN_BANG"),
                        rs.getString("TRONG_LUONG"),
                        rs.getString("DO_BAY"),
                        rs.getString("DANG_MAT_VOT"),
                        rs.getBoolean("TRANG_THAI"),
                        rs.getString("TEN")
                );
                list.add(spctResponse);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return list;
    }
    
    public ArrayList<SanPhamChiTietResponse> getAllProductDetailByKeyword(String Keyword) {
         sql = """
                   SELECT SPCT.ID, SP.MA_SAN_PHAM, SP.TEN, NCC.TEN AS TEN_NHA_CUNG_CAP, MS.TEN AS TEN_MAU, DCD.TEN AS DO_CUNG_DUA,
                                  DC.TEN AS DO_CANG, SPCT.SO_LUONG, SPCT.DON_GIA, SPCT.MO_TA, SPCT.NGAY_TAO,
                                  CDV.TEN AS CHIEU_DAI_VOT, DCB.TEN AS DIEM_CAN_BANG, TL.TEN AS TRONG_LUONG,
                                  DB.TEN AS DO_BAY, DMV.TEN AS DANG_MAT_VOT, SPCT.TRANG_THAI
                           FROM SAN_PHAM_CHI_TIET SPCT
                           JOIN SAN_PHAM SP ON SPCT.IDSP = SP.ID
                           JOIN NHA_CUNG_CAP NCC ON SPCT.MA_NHA_CUNG_CAP = NCC.ID
                           JOIN MAU_SAC MS ON SPCT.MA_MAU = MS.ID
                           JOIN DO_CUNG_DUA DCD ON SPCT.MA_DO_CUNG_DUA = DCD.ID
                           JOIN DO_CANG DC ON SPCT.MA_DO_CANG = DC.ID
                           JOIN CHIEU_DAI_VOT CDV ON SPCT.MA_CD_VOT = CDV.ID
                           JOIN DIEM_CAN_BANG DCB ON SPCT.MA_DIEM_CAN_BANG = DCB.ID
                           JOIN TRONG_LUONG TL ON SPCT.MA_TRONG_LUONG = TL.ID
                           JOIN DO_BAY DB ON SPCT.MA_DO_BAY = DB.ID
                           JOIN DANG_MAT_VOT DMV ON SPCT.MA_DANG_MAT_VOT = DMV.ID
               WHERE SPCT.TRANG_THAI = 0 AND SP.MA_SAN_PHAM LIKE N'%' + ? + N'%' OR SP.TEN LIKE N'%' + ? + N'%' OR MS.TEN LIKE N'%' + ? + N'%' OR DCB.TEN LIKE N'%' + ? + N'%'
                    """;
        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();
        try {
             con = DBconnextSQL.getConnection();
             ps = con.prepareStatement(sql);
             ps.setObject(1, Keyword);
             ps.setObject(2, Keyword);
             ps.setObject(3, Keyword);
             ps.setObject(4, Keyword);
            
    

             rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTietResponse spctResponse = new SanPhamChiTietResponse(
                        rs.getInt("ID"),
                        rs.getString("MA_SAN_PHAM"),
                        rs.getString("TEN_NHA_CUNG_CAP"),
                        rs.getString("TEN_MAU"),
                        rs.getString("DO_CUNG_DUA"),
                        rs.getString("DO_CANG"),
                        rs.getInt("SO_LUONG"),
                        rs.getFloat("DON_GIA"),
                        rs.getString("MO_TA"),
                        rs.getDate("NGAY_TAO"),
                        rs.getString("CHIEU_DAI_VOT"),
                        rs.getString("DIEM_CAN_BANG"),
                        rs.getString("TRONG_LUONG"),
                        rs.getString("DO_BAY"),
                        rs.getString("DANG_MAT_VOT"),
                        rs.getBoolean("TRANG_THAI"),
                        rs.getString("TEN")
                );
                list.add(spctResponse);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return list;
    }
    
    
    
    
    

    //hàm load hdct theo mã hóa đơn;
    public ArrayList<HoaDonCT_Response> getInvoiceDetailByCodeInvoice(Integer idHD) {
        ArrayList<HoaDonCT_Response> list = new ArrayList<>();
        this.sql = """
                    SELECT dbo.HOA_DON_CHI_TIET.Id, 
                                               dbo.SAN_PHAM.MA_SAN_PHAM, 
                                               dbo.SAN_PHAM.TEN, 
                                               dbo.MAU_SAC.TEN AS MAU_SAC, 
                                               dbo.DO_CANG.TEN AS DO_CANG, 
                                               dbo.CHIEU_DAI_VOT.TEN AS CHIEU_DAI_VOT, 
                                               dbo.HOA_DON_CHI_TIET.SO_LUONG, 
                                               dbo.SAN_PHAM_CHI_TIET.DON_GIA, 
                                               dbo.HOA_DON_CHI_TIET.THANH_TIEN,
                                               dbo.HOA_DON_CHI_TIET.ID_SPCT
                                        FROM dbo.HOA_DON_CHI_TIET
                                         JOIN dbo.SAN_PHAM_CHI_TIET ON dbo.HOA_DON_CHI_TIET.ID_SPCT = dbo.SAN_PHAM_CHI_TIET.ID
                                         JOIN dbo.SAN_PHAM ON dbo.SAN_PHAM_CHI_TIET.IDSP = dbo.SAN_PHAM.ID
                   			JOIN dbo.MAU_SAC ON dbo.SAN_PHAM_CHI_TIET.MA_MAU = dbo.MAU_SAC.ID
                                         JOIN dbo.DO_CANG ON dbo.SAN_PHAM_CHI_TIET.MA_DO_CANG = dbo.DO_CANG.ID
                                         JOIN dbo.CHIEU_DAI_VOT ON dbo.SAN_PHAM_CHI_TIET.MA_CD_VOT = dbo.CHIEU_DAI_VOT.ID
                                        WHERE dbo.HOA_DON_CHI_TIET.ID_HD = ? AND dbo.HOA_DON_CHI_TIET.TRANG_THAI = 2
                     """;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, idHD);
            rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonCT_Response reponse = HoaDonCT_Response.builder()
                        .id(rs.getInt(1))
                        .maSP(rs.getString(2))
                        .tenSP(rs.getString(3))
                        .tenMau(rs.getString(4))
                        .doCang(rs.getString(5))
                        .CDVot(rs.getString(6))
                        .soLuong(rs.getInt(7))
                        .donGia(rs.getDouble(8))
                        .thanhTien(rs.getDouble(9))
                        .ctspID(rs.getInt(10))
                        .build();

                list.add(reponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    

    //Hàm update lại số lượng sản phẩm trong kho;
    public int updateQuantityProductDetailInStock(Integer quantity, Integer idSPCT) {
        this.sql = "UPDATE [dbo].[SAN_PHAM_CHI_TIET] SET SO_LUONG = ? WHERE ID = ?";
        int result = 0;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, quantity);
            ps.setObject(2, idSPCT);

            result = ps.executeUpdate(); // Thực thi câu lệnh SQL
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Hàm lấy ra số lượng sản phẩm trong kho
    public Integer getQuantityProductDetail(Integer idSPCT) {
        this.sql = "SELECT SO_LUONG from SAN_PHAM_CHI_TIET WHERE ID = ?";
        Integer quantity = null;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, idSPCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                quantity = rs.getInt(1);
                return quantity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    //Hàm lấy ra tổng tiền của hóa đơn
    public Double getTotalpriceInvoice(Integer idHD) {
        this.sql = "Select SUM(HOA_DON_CHI_TIET.THANH_TIEN) as TONG_TIEN from HOA_DON Join HOA_DON_CHI_TIET ON HOA_DON.Id = HOA_DON_CHI_TIET.ID_HD WHERE HOA_DON.Id = ?;";
        Double totalPrice = null;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, idHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalPrice = rs.getDouble(1);
                return totalPrice;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0d;
    }

    //Hàm kiểm tra xem spct đã có trong hd chưa
    public int checkProductInInvoice(Integer idHD, Integer idSPCT) {
        this.sql = "select * from HOA_DON_CHI_TIET where HOA_DON_CHI_TIET.ID_HD = ? and HOA_DON_CHI_TIET.ID_SPCT = ?;";
        Integer check = 0;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, idHD);
            ps.setObject(2, idSPCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                check = rs.getInt(1);
                return check;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;

    }

    //Hàm update lại tổng tiền của hóa đơn
    public int updateToTalPriceInvoice(Double totalPrice, Integer idHD) {
        this.sql = "UPDATE [dbo].[HOA_DON] SET TONG_TIEN = ?, TONG_TIEN_KHI_GIAM = ? WHERE ID = ?";
        int result = 0;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, totalPrice);
            ps.setObject(2, totalPrice);
            ps.setObject(3, idHD);
            result = ps.executeUpdate(); // Thực thi câu lệnh SQL
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Hàm hợp nhất spct vào hdct có spct giống nó
    public int mergeInvoiceDetail(Integer idHD, Integer idSPCT, Integer quantity, Double price) {
        this.sql = "UPDATE [dbo].[HOA_DON_CHI_TIET] SET SO_LUONG = ?, THANH_TIEN = ? WHERE ID_HD = ? AND ID_SPCT = ?";
        int result = 0;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, quantity);
            ps.setObject(2, price);
            ps.setObject(3, idHD);
            ps.setObject(4, idSPCT);
            result = ps.executeUpdate(); // Thực thi câu lệnh SQL
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Hàm lấy số lượng của hdct theo idHD và idSPCT
    public int getQuantityInvoiceDetailByIdHDAndIdSPCT(Integer idHD, Integer idSPCT) {
        this.sql = "Select SO_LUONG from HOA_DON_CHI_TIET where ID_HD = ? AND ID_SPCT = ?;";
        Integer quantity = 0;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, idHD);
            ps.setObject(2, idSPCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                quantity = rs.getInt(1);
                return quantity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    //Hàm xóa spct ra khỏi giỏ hàng
    public int deleteInvoiceDetail(Integer idHD, Integer idSPCT) {
        this.sql = "DELETE FROM [dbo].[HOA_DON_CHI_TIET] WHERE ID_HD = ? AND ID_SPCT = ?";
        int result = 0;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, idHD);
            ps.setObject(2, idSPCT);
            result = ps.executeUpdate(); // Thực thi câu lệnh SQL
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Hàm update lại số lượng sản phẩm sau khi xóa khỏi giỏ hàng
    public int updateQuantityWhenDeleteInCart(Integer idSPCT, Integer quantity) {
        this.sql = "UPDATE FROM [dbo].[SAN_PHAM_CHI_TIET] SET SO_LUONG WHERE ID = ?";
        int result = 0;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, idSPCT);
            result = ps.executeUpdate(); // Thực thi câu lệnh SQL
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Hàm load danh sách thanh toán
    public List<HinhThucThanhToan> getListFormOfPayment() {
        this.sql = """
                   SELECT [Id]
                         ,[TEN_PHUONG_THUC]
                     FROM [dbo].[HINH_THUC_THANH_TOAN]""";
        List<HinhThucThanhToan> list = new ArrayList<>();
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HinhThucThanhToan hinhThucThanhToan = new HinhThucThanhToan();
                hinhThucThanhToan.setId(rs.getInt(1));
                hinhThucThanhToan.setHTTT(rs.getString(2));
                list.add(hinhThucThanhToan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    //Hàm load danh sách voucher
    public List<PhieuGiamGia> getListVoucher() {
        this.sql = """
                        SELECT ID,
                              [MA_PHIEU]
                              ,[TEN_PHIEU]
                              ,[NGAY_TAO]
                              ,[NGAY_HET_HAN]
                              ,[LOAI_GIAM]
                              ,[GIA_TRI_GIAM_MAX]
                               ,[HOA_DON_TOI_THIEU]
                              ,[TRANG_THAI]
                          FROM [dbo].[PHIEU_GIAM_GIA]
                      WHERE [TRANG_THAI] = 2
                       ORDER BY ID DESC
                      """;
        ArrayList<PhieuGiamGia> listPGG = new ArrayList();

        try {
            con = DBconnextSQL.getConnection();
            this.ps = con.prepareStatement(this.sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PhieuGiamGia PGG = new PhieuGiamGia(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4), rs.getDate(5), rs.getBoolean(6), rs.getFloat(7),
                        rs.getFloat(8), rs.getInt(9));
                listPGG.add(PGG);

            }
            return listPGG;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Hàm chỉnh sửa số lượng sản phẩm và thành tiền trong hóa đơn chi tiết;
    public int updateQuantityAndTotalPriceInvoiceDetail(Integer idSPCT, Integer idHD, Integer quantity, Double totalPrice) {
        this.sql = "UPDATE [dbo].[HOA_DON_CHI_TIET] SET SO_LUONG = ?, THANH_TIEN = ? WHERE ID_HD = ? AND ID_SPCT = ?";
        int result = 0;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, quantity);
            ps.setObject(2, totalPrice);
            ps.setObject(3, idHD);
            ps.setObject(4, idSPCT);
            result = ps.executeUpdate(); // Thực thi câu lệnh SQL
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Hàm lấy Hóa đơn theo id
    public HoaDon_Response getInvoiceById(Integer idHD) {
        sql = """
            SELECT        dbo.HOA_DON.Id, dbo.HOA_DON.MA_HOA_DON, dbo.HOA_DON.TEN_KHACH_HANG, dbo.HOA_DON.NGAY_DAT_HANG, dbo.HOA_DON.SDT, dbo.HOA_DON.TONG_TIEN, dbo.HOA_DON.TRANG_THAI, 
                                       dbo.KHACH_HANG.DIA_CHI, dbo.NHAN_VIEN.MA_NHAN_VIEN
             FROM            dbo.HOA_DON INNER JOIN
                                      dbo.KHACH_HANG ON dbo.HOA_DON.Id_KH = dbo.KHACH_HANG.ID INNER JOIN
                                      dbo.NHAN_VIEN ON dbo.HOA_DON.Id_NV = dbo.NHAN_VIEN.ID
              WHERE HOA_DON.Id = ?
              """;

        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, idHD);
            rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon_Response reponse = HoaDon_Response.builder()
                        .id(rs.getInt(1))
                        .ma(rs.getString(2))
                        .tenKhachHang(rs.getString(3))
                        .ngayDatHang(rs.getDate(4))
                        .SDT(rs.getString(5))
                        .tongTien(rs.getDouble(6))
                        .trangThai(rs.getInt(7))
                        .diaChi(rs.getString(8))
                        .maNhanVien(rs.getString(9))
                        .build();
                return reponse;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    //Hàm kiểm tra phiếu giảm giá có hợp lệ với hóa đơn không
//    Hàm thanh toán
    public int payment(
            Integer idHD,
            Integer idTT,
            String tenKH,
            String sdt,
            Double tienSauGiamGia,
            Integer idPGG,
            Integer idKH) {
        this.sql = """
                     UPDATE [dbo].[HOA_DON] 
                     SET 
                      ID_THANH_TOAN = ?
                     , TEN_KHACH_HANG = ?
                     , SDT = ?
                     , TONG_TIEN_KHI_GIAM = ?
                     , TRANG_THAI = 1
                   ,  ID_PGG = ?
                   , ID_KH = ?
                     WHERE ID = ?;     
                      
UPDATE HOA_DON_CHI_TIET SET TRANG_THAI = 1 WHERE ID_HD = ?;                     """;
        int result = 0;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(this.sql);
            ps.setObject(1, idTT);
            ps.setObject(2, tenKH);
            ps.setObject(3, sdt);
            ps.setObject(4, tienSauGiamGia);
            ps.setObject(5, idPGG);
            ps.setObject(6, idKH);
            ps.setObject(7, idHD);
            ps.setObject(8, idHD);

            result = ps.executeUpdate(); // Thực thi câu lệnh SQL
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
