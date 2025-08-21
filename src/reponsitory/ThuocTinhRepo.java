package reponsitory;

import config.DBconnextSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
 import entity.*;
import java.sql.*;

public class ThuocTinhRepo {

    public ArrayList<Object[]> getAllThuocTinh(String tenBang) {
        ArrayList<Object[]> listThuocTinh = new ArrayList<>();
        try (Connection conn = DBconnextSQL.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM " + tenBang
        //                     + "WHERE TrangThai = 0 ORDER BY" + getColumnNameForTable(tenBang) + "DESC"
        )) {
            while (rs.next()) {
                if (tenBang.equalsIgnoreCase("MAU_SAC")) {
                    String maMau = rs.getString("MA_MAU");
                    String tenMauSac = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maMau, tenMauSac});
                } else if (tenBang.equalsIgnoreCase("NHA_CUNG_CAP")) {
                    String maNhaCungCap = rs.getString("MA_NHA_CUNG_CAP");
                    String tenNhaCungCap = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maNhaCungCap, tenNhaCungCap});
                } else if (tenBang.equalsIgnoreCase("DO_CANG")) {
                    String maDoCang = rs.getString("MA_DO_CANG");
                    String tenDoCang = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maDoCang, tenDoCang});
                } else if (tenBang.equalsIgnoreCase("CHIEU_DAI_VOT")) {
                    String maChieuDai = rs.getString("MA_CD_VOT");
                    String tenChieuDai = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maChieuDai, tenChieuDai});
                } else if (tenBang.equalsIgnoreCase("DIEM_CAN_BANG")) {
                    String maDiemCanBang = rs.getString("MA_DIEM_CAN_BANG");
                    String tenDiemCanBang = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maDiemCanBang, tenDiemCanBang});
                } else if (tenBang.equalsIgnoreCase("DO_BAY")) {
                    String maDoBay = rs.getString("MA_DO_BAY");
                    String tenDoBay = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maDoBay, tenDoBay});
                } else if (tenBang.equalsIgnoreCase("DANG_MAT_VOT")) {
                    String maDangMatVot = rs.getString("MA_DANG_MAT_VOT");
                    String tenDangMatVot = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maDangMatVot, tenDangMatVot});
                } else if (tenBang.equalsIgnoreCase("DO_CUNG_DUA")) {
                    String maDoCungDua = rs.getString("MA_DO_CUNG_DUA");
                    String tenDoCungDua = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maDoCungDua, tenDoCungDua});
                } else if (tenBang.equalsIgnoreCase("TRONG_LUONG")) {
                    String maTrongLuong = rs.getString("MA_TRONG_LUONG");
                    String tenTrongLuong = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maTrongLuong, tenTrongLuong});
                } else {
                    // Các bảng khác, lấy mã thuộc tính và tên thuộc tính
                    String maThuocTinh = rs.getString(1); // Cột đầu tiên là mã
                    String tenThuocTinh = rs.getString(2); // Cột thứ hai là tên
                    listThuocTinh.add(new Object[]{maThuocTinh, tenThuocTinh});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listThuocTinh;
    }

    private String getColumnNameForTable(String tenBang) {
        switch (tenBang) {
            case "MAU_SAC":
                return "MA_MAU";
            case "NHA_CUNG_CAP":
                return "MA_NHA_CUNG_CAP";
            case "DO_CANG":
                return "MA_DO_CANG";
            // ... (các bảng khác)
            default:
                return "MA_THUOC_TINH"; // Giả sử các bảng khác đều có cột MA_THUOC_TINH
        }
    }

    public boolean kiemTraMaThuocTinhTonTai(String tenBang, String maThuocTinh) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tenBang + " WHERE MaThuocTinh = ?";
        try (Connection conn = DBconnextSQL.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maThuocTinh);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    public void addThuocTinh(String tenBang, String maThuocTinh, String tenThuocTinh) throws SQLException {
            String sql = "INSERT INTO " + tenBang + " VALUES (?, ?)";
            try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, maThuocTinh);
                ps.setString(2, tenThuocTinh);
                ps.executeUpdate();
            }
    }
    
     public void addNhaCC(String tenBang, String maThuocTinh, String tenThuocTinh, String diaChi) throws SQLException {
        if (tenBang.equalsIgnoreCase("NHA_CUNG_CAP")) {
            String sql = "INSERT INTO " + tenBang + " VALUES (?, ?, ?)";
            try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, maThuocTinh);
                ps.setString(2, tenThuocTinh);
                ps.setString(3, diaChi);
                ps.executeUpdate();
            }
        }
     }
     
     public void updateNhaCC(String tenBang, String maThuocTinh, String maTT, String tenThuocTinhMoi, String diaChi) throws SQLException {
        String sql = "UPDATE " + tenBang + " SET TEN = ?, DIA_CHI = ? WHERE " + maTT + " = ?";
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenThuocTinhMoi);
            ps.setString(2, diaChi);
            ps.setString(3, maThuocTinh);
            ps.executeUpdate();
        }
    }
     
     
     
     

    public void updateThuocTinh(String tenBang, String maThuocTinh, String maTT, String tenThuocTinhMoi) throws SQLException {
        String sql = "UPDATE " + tenBang + " SET TEN = ? WHERE " + maTT + " = ?";
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenThuocTinhMoi);
            ps.setString(2, maThuocTinh);
            ps.executeUpdate();
        }
    }

//    public void deleteThuocTinh(String tenBang, String maThuocTinh) throws SQLException {
//    String cotMaThuocTinh = tenBang.substring(0, tenBang.length() - 1); // Xác định tên cột mã 
//    String sql = "DELETE FROM " + tenBang + " WHERE " + cotMaThuocTinh + " = ?";
//    try (Connection con = DBConnect.getConnection();
//         PreparedStatement ps = con.prepareStatement(sql)) {
//        ps.setString(1, maThuocTinh);
//        ps.executeUpdate();
//    }
//}
    public void deleteThuocTinh(String tenBang, String maThuocTinh) throws SQLException {
        String cotMaThuocTinh;
        switch (tenBang) {
            case "DO_BAY":
                cotMaThuocTinh = "MA_DO_BAY";
                break;
            case "CHIEU_DAI_VOT":
                cotMaThuocTinh = "MA_CD_VOT";
                break;

            default:
                cotMaThuocTinh = tenBang.substring(0, tenBang.length() - 1);
        }

        String sql = "DELETE FROM " + tenBang + " WHERE " + cotMaThuocTinh + " = ?";
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maThuocTinh);
            ps.executeUpdate();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Lấy danh sách độ căng
    public ArrayList<DoCang> getDoCang() {
        ArrayList<DoCang> listDoCang = new ArrayList<>();
        String query = "SELECT * FROM DO_CANG";
        try (Connection conn = DBconnextSQL.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {

            while (rs.next()) {
                DoCang dc = new DoCang();
                dc.setId(rs.getInt(1));
                dc.setMaDoCang(rs.getString(2));
                dc.setTen(rs.getString(3));
                listDoCang.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDoCang;
    }

    public ArrayList<ChieuDaiVot> getChieuDaiVot() {
        ArrayList<ChieuDaiVot> listCDV = new ArrayList<>();
        String query = "SELECT * FROM CHIEU_DAI_VOT";
        try (Connection conn = DBconnextSQL.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {

            while (rs.next()) {
                ChieuDaiVot dc = new ChieuDaiVot();
                dc.setId(rs.getInt(1));
                dc.setMaChieuDaiVot(rs.getString(2));
                dc.setTen(rs.getString(3));
                listCDV.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCDV;
    }

    public ArrayList<DangMatVot> getDangMatVot() {
        ArrayList<DangMatVot> listDoCang = new ArrayList<>();
        String query = "SELECT * FROM DANG_MAT_VOT";
        try (Connection conn = DBconnextSQL.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {

            while (rs.next()) {
                DangMatVot dc = new DangMatVot();
                dc.setId(rs.getInt(1));
                dc.setMaDangMatVot(rs.getString(2));
                dc.setTen(rs.getString(3));
                listDoCang.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDoCang;
    }

    public ArrayList<DiemCanBang> getDiemCanBang() {
        ArrayList<DiemCanBang> listDoCang = new ArrayList<>();
        String query = "SELECT * FROM DIEM_CAN_BANG";
        try (Connection conn = DBconnextSQL.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {

            while (rs.next()) {
                DiemCanBang dc = new DiemCanBang();
                dc.setId(rs.getInt(1));
                dc.setMaDiemCanBang(rs.getString(2));
                dc.setTen(rs.getString(3));
                listDoCang.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDoCang;
    }

    public ArrayList<DoBay> getDoBay() {
        ArrayList<DoBay> listDoCang = new ArrayList<>();
        String query = "SELECT * FROM DO_BAY";
        try (Connection conn = DBconnextSQL.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {

            while (rs.next()) {
                DoBay dc = new DoBay();
                dc.setId(rs.getInt(1));
                dc.setMaDoBay(rs.getString(2));
                dc.setTen(rs.getString(3));
                listDoCang.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDoCang;
    }

    public ArrayList<DoCungDua> getDoCungDua() {
        ArrayList<DoCungDua> listDoCang = new ArrayList<>();
        String query = "SELECT * FROM DO_CUNG_DUA";
        try (Connection conn = DBconnextSQL.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {

            while (rs.next()) {
                DoCungDua dc = new DoCungDua();
                dc.setId(rs.getInt(1));
                dc.setMaDoCungDua(rs.getString(2));
                dc.setTen(rs.getString(3));
                listDoCang.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDoCang;
    }

    public ArrayList<MauSac> getMauSac() {
        ArrayList<MauSac> listDoCang = new ArrayList<>();
        String query = "SELECT * FROM MAU_SAC";
        try (Connection conn = DBconnextSQL.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {
            while (rs.next()) {
                MauSac dc = new MauSac();
                dc.setId(rs.getInt(1));
                dc.setMaMauSac(rs.getString(2));
                dc.setTenMau(rs.getString(3));
                listDoCang.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDoCang;
    }

    public ArrayList<NhaCungCap> getNhaCungCap() {
        ArrayList<NhaCungCap> listDoCang = new ArrayList<>();
        String query = "SELECT * FROM NHA_CUNG_CAP";
        try (Connection conn = DBconnextSQL.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {
            while (rs.next()) {
                NhaCungCap dc = new NhaCungCap();
                dc.setId(rs.getInt(1));
                dc.setMaNhaCungCap(rs.getString(2));
                dc.setTen(rs.getString(3));
                dc.setDiaChi(rs.getString(4));
                listDoCang.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDoCang;
    }

    public ArrayList<TrongLuong> getTrongLuong() {
        ArrayList<TrongLuong> listDoCang = new ArrayList<>();
        String query = "SELECT * FROM TRONG_LUONG";
        try (Connection conn = DBconnextSQL.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {
            while (rs.next()) {
                TrongLuong dc = new TrongLuong();
                dc.setId(rs.getInt(1));
                dc.setMaTrongLuong(rs.getString(2));
                dc.setTen(rs.getString(3));
                listDoCang.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDoCang;
    }

}
