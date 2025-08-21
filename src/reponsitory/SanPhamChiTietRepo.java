package reponsitory;

import config.DBconnextSQL;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.DoCang;
import entity.DoCungDua;
import entity.MauSac;
import entity.SanPhamChiTiet;
import entity.TrongLuong;
import java.util.*;
import javax.swing.JOptionPane;
import response.SanPhamChiTietResponse;

/**
 *
 * @author NKHOA_01
 */
public class SanPhamChiTietRepo {

    public ArrayList<SanPhamChiTietResponse> getAllSPCT() {
        String sql = """
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
                    """;
        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
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

//    public boolean add(SanPhamChiTiet spct) {
//        String sql = """
//
//                      INSERT INTO SAN_PHAM_CHI_TIET (IDSP, MA_NHA_CUNG_CAP, MA_MAU, MA_DO_CUNG_DUA, MA_DO_CANG, SO_LUONG, DON_GIA, MO_TA, NGAY_TAO, MA_CD_VOT, MA_DIEM_CAN_BANG, MA_TRONG_LUONG, MA_DO_BAY, MA_DANG_MAT_VOT, TRANG_THAI)
//                             SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
//                             FROM SAN_PHAM SP
//                             JOIN NHA_CUNG_CAP NCC ON ? = NCC.ID
//                             JOIN MAU_SAC MS ON ? = MS.ID
//                             JOIN DO_CUNG_DUA DCD ON ? = DCD.ID
//                             JOIN DO_CANG DC ON ? = DC.ID
//                             JOIN CHIEU_DAI_VOT CDV ON ? = CDV.ID
//                             JOIN DIEM_CAN_BANG DCB ON ? = DCB.ID
//                             JOIN TRONG_LUONG TL ON ? = TL.ID
//                             JOIN DO_BAY DB ON ? = DB.ID
//                             JOIN DANG_MAT_VOT DMV ON ? = DMV.ID
//                             WHERE SP.ID = ?
//            """;
//        int check = 0;
//        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setInt(1, spct.getIdSp());
//            ps.setInt(2, spct.getMaNhaCungCap());
//            ps.setInt(3, spct.getMaMau());
//            ps.setInt(4, spct.getMaDoCungDua());
//            ps.setInt(5, spct.getMaDoCang());
//            ps.setInt(6, spct.getSoLuong());
//            ps.setFloat(7, spct.getDonGia());
//            ps.setString(8, spct.getMoTa());
//            ps.setInt(10, spct.getMaCdVot());
//            ps.setInt(11, spct.getMaDiemCanBang());
//            ps.setInt(12, spct.getMaTrongLuong());
//            ps.setInt(13, spct.getMaDoBay());
//            ps.setInt(14, spct.getMaDangMatVot());
//            ps.setBoolean(15, spct.isTrangThai());
//
//            check = ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thêm sản phẩm chi tiết", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//        return check > 0;
//    }
    public boolean update(SanPhamChiTiet spct) {
        String sql = """
     
            UPDATE [dbo].[SAN_PHAM_CHI_TIET]
            SET [IDSP] = ?,
                [MA_NHA_CUNG_CAP] = ?,
                [MA_MAU] = ?,
                [MA_DO_CUNG_DUA] = ?,
                [MA_DO_CANG] = ?,
                [SO_LUONG] = ?,
                [DON_GIA] = ?,
                [MO_TA] = ?,
                [NGAY_TAO] = ?,
                [MA_CD_VOT] = ?,
                [MA_DIEM_CAN_BANG] = ?,
                [MA_TRONG_LUONG] = ?,
                [MA_DO_BAY] = ?,
                [MA_DANG_MAT_VOT] = ?,
                [TRANG_THAI] = ?
            WHERE ID = ?
           """;
        int check = 0;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, spct.getIdSp());
            ps.setInt(2, spct.getMaNhaCungCap());
            ps.setInt(3, spct.getMaMau());
            ps.setInt(4, spct.getMaDoCungDua());
            ps.setInt(5, spct.getMaDoCang());
            ps.setInt(6, spct.getSoLuong());
            ps.setFloat(7, spct.getDonGia());
            ps.setString(8, spct.getMoTa());
//           ps.setDate(9, new Date(spct.getNgayTao().getTime()));
            ps.setInt(10, spct.getMaCdVot());
            ps.setInt(11, spct.getMaDiemCanBang());
            ps.setInt(12, spct.getMaTrongLuong());
            ps.setInt(13, spct.getMaDoBay());
            ps.setInt(14, spct.getMaDangMatVot());
            ps.setBoolean(15, spct.isTrangThai());
            ps.setInt(16, spct.getId());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật sản phẩm chi tiết", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return check > 0;
    }

    public ArrayList<SanPhamChiTietResponse> search(String tenSanPham) {
        String sql = """
                   SELECT SPCT.ID, SP.MA_SAN_PHAM, SP.TEN, NCC.TEN AS TEN_NHA_CUNG_CAP, MS.TEN, DCD.TEN AS DO_CUNG_DUA,
                                         DC.TEN AS DO_CANG, CDV.TEN AS CHIEU_DAI_VOT, DCB.TEN AS DIEM_CAN_BANG, 
                                         TL.TEN AS TRONG_LUONG, DB.TEN AS DO_BAY, DMV.TEN AS DANG_MAT_VOT, SPCT.TRANG_THAI
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
                                  WHERE SP.TEN LIKE ? 
                     """;
        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + tenSanPham + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SanPhamChiTietResponse spctResponse = new SanPhamChiTietResponse(
                            rs.getInt("ID"),
                            rs.getString("MA_SAN_PHAM"),
                            rs.getString("TEN_NHA_CUNG_CAP"),
                            rs.getString("TEN"),
                            rs.getString("DO_CUNG_DUA"),
                            rs.getString("DO_CANG"),
                            0,
                            0,
                            null,
                            null,
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
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm sản phẩm chi tiết: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi tìm kiếm sản phẩm chi tiết", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        return list;
    }

    public ArrayList<String> getAllThem(String tenBang) {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT TEN FROM " + tenBang;

        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("TEN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addThuocTinh(String tenBang, String tenThuocTinh) throws SQLException {
        String sql = "INSERT INTO " + tenBang + " (TEN) VALUES (?)";
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenThuocTinh);
            ps.executeUpdate();
        }
    }

    public boolean updateSoLuong(SanPhamChiTiet spct) {
        String sql = """
 
            UPDATE [dbo].[SAN_PHAM_CHI_TIET]
            SET [SO_LUONG] = ?
            WHERE ID = ?

           """;
        int check = 0;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, spct.getSoLuong());
            ps.setInt(2, spct.getId());

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật sản phẩm chi tiết", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return check > 0;
    }

    public ArrayList<SanPhamChiTietResponse> LocSPCT(Integer mauSac_id, Integer Do_cung_dua_ID, Integer Do_cang_ID, Integer trong_luong_id) {
        String sql = """
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
                     where SP.trang_Thai = 0 and
                                       
                                       (SPCT.MA_MAU = ? OR ? IS NULL OR 1 = '')
                                       AND (SPCT.MA_DO_CUNG_DUA = ? OR ? IS NULL OR 1 = '')
                                       AND (SPCT.MA_DO_CANG = ? OR ? IS NULL OR 1 = '');
                                         AND (SPCT.MA_TRONG_LUONG = ? OR ? IS NULL OR 1 = '');
                    """;
        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ps.setObject(1, mauSac_id);
            ps.setObject(2, mauSac_id);
            ps.setObject(3, Do_cung_dua_ID);
            ps.setObject(4, Do_cung_dua_ID);
            ps.setObject(5, Do_cang_ID);
            ps.setObject(6, Do_cang_ID);
            ps.setObject(5, trong_luong_id);
            ps.setObject(6, trong_luong_id);
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

    public MauSac selectByTenMau(String tenMau) {
        String sql = " SELECT ID, TEN ,MA_MAU FROM MAU_SAC where TEN like ?";
        List<MauSac> lst = new ArrayList<>();
        try {
            Connection con = DBconnextSQL.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, tenMau);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac(
                        rs.getInt(1), rs.getString(2),
                        rs.getString(3)
                );
                lst.add(ms);
            }
            if (!lst.isEmpty()) {
                return lst.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public TrongLuong SelectbyTenTrongLuong(String tenTrongLuong) {
        String sql = "SELECT ID, TEN, MA_TRONG_LUONG FROM TRONG_LUONG WHERE TEN like?";
        List<TrongLuong> list = new ArrayList<>();

        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ps.setObject(1, tenTrongLuong);

            while (rs.next()) {
                TrongLuong trongLuong = new TrongLuong();
                trongLuong.setId(rs.getInt("ID"));
                trongLuong.setTen(rs.getString("TEN"));
                trongLuong.setMaTrongLuong(rs.getString("MA_TRONG_LUONG"));

                list.add(trongLuong);
            }
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }

        return null;

    }

    public DoCang SelectbyTenDoCang(String tenDoCang) {
        String sql = "SELECT ID, TEN, MA_DO_CANG FROM DO_CANG WHERE TEN like ?";
        List<DoCang> list = new ArrayList<>();

        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ps.setObject(1, tenDoCang);
            while (rs.next()) {
                DoCang doCang = new DoCang();
                doCang.setId(rs.getInt("ID"));
                doCang.setTen(rs.getString("TEN"));
                doCang.setMaDoCang(rs.getString("MA_DO_CANG"));

                list.add(doCang);
            }
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }

        return null;
    }

    public DoCungDua SelectbyTenDoCungDua(String tenDoCungDua) {
        String sql = "SELECT ID, TEN, MA_DO_CUNG_DUA FROM DO_CUNG_DUA WHERE TEN like ?";
        List<DoCungDua> list = new ArrayList<>();

        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ps.setObject(1, tenDoCungDua);
            while (rs.next()) {
                DoCungDua doCung = new DoCungDua();
                doCung.setId(rs.getInt("ID"));
                doCung.setTen(rs.getString("TEN"));
                doCung.setMaDoCungDua(rs.getString("MA_DO_CUNG_DUA"));
                list.add(doCung);
            }
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
        return null;
    }

////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<SanPhamChiTietResponse> getProductDetailByIdProduct(Integer idSP) {
        String sql = """
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
                     WHERE SPCT.IDSP = ?
                    """;
        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idSP);
            ResultSet rs = ps.executeQuery();

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


    public SanPhamChiTietResponse getProductDetailResponseById(Integer idSPCT) {
        String sql = """
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
                     WHERE SPCT.ID = ?
                    """;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idSPCT);
            ResultSet rs = ps.executeQuery();

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
    
    
    //udpate trạng thái
     public boolean updateStatus( Integer idSpct,Boolean trangThai) {
        String sql = """
            UPDATE [dbo].[SAN_PHAM_CHI_TIET]
            SET [TRANG_THAI] = ?
            WHERE ID = ?
           """;
        int check = 0;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, trangThai);
            ps.setObject(2, idSpct);

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật sản phẩm chi tiết", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return check > 0;
    }
    
    
    public SanPhamChiTiet getProductDetailyId(Integer idSPCT) {
        String sql = """
                  SELECT [ID]
                           ,[IDSP]
                           ,[MA_NHA_CUNG_CAP]
                           ,[MA_MAU]
                           ,[MA_DO_CUNG_DUA]
                           ,[MA_DO_CANG]
                           ,[SO_LUONG]
                           ,[DON_GIA]
                           ,[MO_TA]
                           ,[NGAY_TAO]
                           ,[MA_CD_VOT]
                           ,[MA_DIEM_CAN_BANG]
                           ,[MA_TRONG_LUONG]
                           ,[MA_DO_BAY]
                           ,[MA_DANG_MAT_VOT]
                           ,[TRANG_THAI]
                       FROM [dbo].[SAN_PHAM_CHI_TIET]
                     WHERE ID = ?
                    """;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idSPCT);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet(
                        rs.getInt(1), 
                        rs.getInt(2), 
                        rs.getInt(3), 
                        rs.getInt(4), 
                        rs.getInt(5),
                        rs.getInt(6), 
                        rs.getInt(7), 
                        rs.getFloat(8), 
                        rs.getString(9),
                        rs.getDate(10), 
                        rs.getInt(11), 
                        rs.getInt(12), 
                        rs.getInt(13), 
                        rs.getInt(14), 
                        rs.getInt(15), 
                        rs.getBoolean(16));
                return spct;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public int creatNewProductDetail(SanPhamChiTiet spct) {
        String sql = """
                  INSERT INTO [dbo].[SAN_PHAM_CHI_TIET]
                                ([IDSP]
                                ,[MA_NHA_CUNG_CAP]
                                ,[MA_MAU]
                                ,[MA_DO_CUNG_DUA]
                                ,[MA_DO_CANG]
                                ,[SO_LUONG]
                                ,[DON_GIA]
                                ,[MO_TA]
                                ,[NGAY_TAO]
                                ,[MA_CD_VOT]
                                ,[MA_DIEM_CAN_BANG]
                                ,[MA_TRONG_LUONG]
                                ,[MA_DO_BAY]
                                ,[MA_DANG_MAT_VOT]
                                ,[TRANG_THAI])
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?)
                    """;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, spct.getIdSp());
            ps.setObject(2, spct.getMaNhaCungCap());
            ps.setObject(3, spct.getMaMau());
            ps.setObject(4, spct.getMaDoCungDua());
            ps.setObject(5, spct.getMaDoCang());
            ps.setObject(6, spct.getSoLuong());
            ps.setObject(7, spct.getDonGia());
            ps.setObject(8, spct.getMoTa());
            ps.setObject(9, spct.getNgayTao());
            ps.setObject(10, spct.getMaCdVot());
            ps.setObject(11, spct.getMaDiemCanBang());
            ps.setObject(12, spct.getMaTrongLuong());
            ps.setObject(13, spct.getMaDoBay());
            ps.setObject(14, spct.getMaDangMatVot());
            ps.setObject(15, spct.isTrangThai());
            
            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }
    
    
    
    public int updateProductDetail(SanPhamChiTiet spct) {
        String sql = """
                 UPDATE [dbo].[SAN_PHAM_CHI_TIET]
                                   SET 
                                      [MA_NHA_CUNG_CAP] = ?
                                      ,[MA_MAU] = ?
                                      ,[MA_DO_CUNG_DUA] = ?
                                      ,[MA_DO_CANG] = ?
                                      ,[SO_LUONG] = ?
                                      ,[DON_GIA] = ?
                                      ,[MO_TA] = ?
                                      ,[MA_CD_VOT] = ?
                                      ,[MA_DIEM_CAN_BANG] = ?
                                      ,[MA_TRONG_LUONG] = ?
                                      ,[MA_DO_BAY] = ?
                                      ,[MA_DANG_MAT_VOT] = ?
                                 WHERE ID = ?
                    """;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, spct.getMaNhaCungCap());
            ps.setObject(2, spct.getMaMau());
            ps.setObject(3, spct.getMaDoCungDua());
            ps.setObject(4, spct.getMaDoCang());
            ps.setObject(5, spct.getSoLuong());
            ps.setObject(6, spct.getDonGia());
            ps.setObject(7, spct.getMoTa());
            ps.setObject(8, spct.getMaCdVot());
            ps.setObject(9, spct.getMaDiemCanBang());
            ps.setObject(10, spct.getMaTrongLuong());
            ps.setObject(11, spct.getMaDoBay());
            ps.setObject(12, spct.getMaDangMatVot());
            ps.setObject(13, spct.getId());
            
            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }
    
    
}
