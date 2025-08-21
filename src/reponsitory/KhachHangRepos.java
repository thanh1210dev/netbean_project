package reponsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import config.DBconnextSQL;
import entity.KhachHangModel;

public class KhachHangRepos {
    private static Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<KhachHangModel> getAll() {
        ArrayList<KhachHangModel> listKH = new ArrayList<>();
        sql = "select Ma_Khach_Hang, Ten_Khach_Hang, Dia_Chi, SDT, Gioi_Tinh, Trang_Thai, Ghi_Chu from KHACH_HANG WHere Trang_Thai = 0";
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangModel kh = new KhachHangModel();
                kh.setMaKH(rs.getString(1));
                kh.setHoTen(rs.getString(2));
                kh.setDiaChi(rs.getString(3));
                kh.setSdt(rs.getString(4));
                kh.setGioiTinh(rs.getBoolean(5));
                kh.setTrangThai(rs.getBoolean(6));
                kh.setGhiChu(rs.getString(7));
                listKH.add(kh);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public ArrayList<KhachHangModel> TatCaDS() {
        ArrayList<KhachHangModel> listKH = new ArrayList<>();
        sql = "select Ma_Khach_Hang, Ten_Khach_Hang, Dia_Chi, SDT, Gioi_Tinh, Trang_Thai, Ghi_Chu from KHACH_HANG  ";
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangModel kh = new KhachHangModel();
                kh.setMaKH(rs.getString(1));
                kh.setHoTen(rs.getString(2));
                kh.setDiaChi(rs.getString(3));
                kh.setSdt(rs.getString(4));
                kh.setGioiTinh(rs.getBoolean(5));
                kh.setTrangThai(rs.getBoolean(6));
                kh.setGhiChu(rs.getString(7));
                listKH.add(kh);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer add(KhachHangModel n) {
        Integer row = null;
        String sql = """
                     INSERT INTO [dbo].[KHACH_HANG]
                                ([MA_KHACH_HANG]
                                ,[TEN_KHACH_HANG]
                                ,[DIA_CHI]
                                ,[SDT]
                                ,[GIOI_TINH]
                                ,[TRANG_THAI]
                                ,[GHI_CHU])
                      VALUES (?,?,?,?,?,?,?)""";
        Connection cn = DBconnextSQL.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setObject(1, n.getMaKH());
            pstm.setObject(2, n.getHoTen());
            pstm.setObject(3, n.getDiaChi());
            pstm.setObject(4, n.getSdt());
            pstm.setObject(5, n.getGioiTinh());
            pstm.setObject(6, n.getTrangThai());
            pstm.setObject(7, n.getGhiChu());

            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return row;
    }

    public Integer update(KhachHangModel n) {
        Integer row = null;
        String sql = """
                     UPDATE [dbo].[KHACH_HANG] SET
                         TEN_KHACH_HANG= ?
                        ,DIA_CHI= ?
                        ,SDT= ?
                        ,GIOI_TINH= ?
                        ,TRANG_THAI= ?
                        ,GHI_CHU= ?
                     WHERE MA_KHACH_HANG = ?""";

        try (Connection cn = DBconnextSQL.getConnection();
             PreparedStatement pstm = cn.prepareStatement(sql)) {

            pstm.setObject(1, n.getHoTen());
            pstm.setObject(2, n.getDiaChi());
            pstm.setObject(3, n.getSdt());
            pstm.setObject(4, n.getGioiTinh());
            pstm.setObject(5, n.getTrangThai());
            pstm.setObject(6, n.getGhiChu());
            pstm.setObject(7, n.getMaKH());

            row = pstm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return row;
    }

    public boolean delete(String id) {
        int check = 0;
        String sql = """
                    UPDATE [dbo].[KHACH_HANG] SET TRANG_THAI = 1
                          WHERE MA_KHACH_HANG = ?""";
        try (Connection con = DBconnextSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public ArrayList<KhachHangModel> search(String keyword) {
    String sql = "SELECT Ma_Khach_Hang, Ten_Khach_Hang, Dia_Chi, SDT, Gioi_Tinh, Trang_Thai, Ghi_Chu "
               + "FROM dbo.KHACH_HANG "
               + "WHERE Ma_Khach_Hang LIKE ? "
               + "OR Ten_Khach_Hang LIKE ? "
               + "OR Dia_Chi LIKE ? "
               + "OR SDT LIKE ? "
               + "OR Gioi_Tinh LIKE ? "
               + "OR Trang_Thai LIKE ? "
               + "OR Ghi_Chu LIKE ?";

    ArrayList<KhachHangModel> lists = new ArrayList<>();
    try (Connection con = DBconnextSQL.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        String searchString = "%" + keyword + "%";
        for (int i = 1; i <= 7; i++) {
            ps.setString(i, searchString);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lists.add(new KhachHangModel(
                rs.getString("Ma_Khach_Hang"), 
                rs.getString("Ten_Khach_Hang"), 
                rs.getString("Dia_Chi"), 
                rs.getString("SDT"), 
                rs.getBoolean("Gioi_Tinh"), 
                rs.getBoolean("Trang_Thai"), 
                rs.getString("Ghi_Chu")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace(System.out); // Xử lý lỗi khi xảy ra
    }
    return lists;
}
    
    
    
    /////////////////////////////////////
    public ArrayList<KhachHangModel> getKHDialog() {
        ArrayList<KhachHangModel> listKH = new ArrayList<>();
        sql = "select id, Ma_Khach_Hang, Ten_Khach_Hang, Dia_Chi, SDT, Gioi_Tinh, Trang_Thai, Ghi_Chu from KHACH_HANG WHere Trang_Thai = 0";
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangModel kh = new KhachHangModel();
                kh.setId(rs.getInt(1));
                kh.setMaKH(rs.getString(2));
                kh.setHoTen(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                kh.setSdt(rs.getString(5));
                kh.setGioiTinh(rs.getBoolean(6));
                kh.setTrangThai(rs.getBoolean(7));
                kh.setGhiChu(rs.getString(8));
                listKH.add(kh);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
