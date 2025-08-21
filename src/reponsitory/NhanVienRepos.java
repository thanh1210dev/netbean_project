/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import config.DBconnextSQL;
import entity.SendEmail;
import entity.TaoMatKhau;
import entity.NhanVienModel;

/**
 *
 * @author laptop
 */
public class NhanVienRepos {
    private static Connection con=null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;
    private String sql=null;
    

    public ArrayList<NhanVienModel> getAll() {
        ArrayList<NhanVienModel> listNV = new ArrayList<>();
        String sql = "SELECT ID, MA_NHAN_VIEN, TEN_NV, GIOI_TINH, SO_DIEN_THOAI, EMAIL, MAT_KHAU, VAI_TRO, TRANG_THAI FROM NHAN_VIEN WHERE TRANG_THAI =1";
        try (Connection con = DBconnextSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String ma = rs.getString("MA_NHAN_VIEN");
                String ten = rs.getString("TEN_NV");
                boolean gioiTinh = rs.getBoolean("GIOI_TINH");
                String sdt = rs.getString("SO_DIEN_THOAI");
                String email = rs.getString("EMAIL");
                String matkhau = rs.getString("MAT_KHAU");
                boolean vaiTro = rs.getBoolean("VAI_TRO");
                boolean trangThai = rs.getBoolean("TRANG_THAI");
                listNV.add(new NhanVienModel(id, ma, ten, gioiTinh, sdt, email, vaiTro, trangThai, matkhau));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    
    }
    public ArrayList<NhanVienModel> TatCaDS() {
        ArrayList<NhanVienModel> listNV = new ArrayList<>();
        String sql = "SELECT ID, MA_NHAN_VIEN, TEN_NV, GIOI_TINH, SO_DIEN_THOAI, EMAIL, MAT_KHAU, VAI_TRO, TRANG_THAI FROM NHAN_VIEN ";
        try (Connection con = DBconnextSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String ma = rs.getString("MA_NHAN_VIEN");
                String ten = rs.getString("TEN_NV");
                boolean gioiTinh = rs.getBoolean("GIOI_TINH");
                String sdt = rs.getString("SO_DIEN_THOAI");
                String email = rs.getString("EMAIL");
                String matkhau = rs.getString("MAT_KHAU");
                boolean vaiTro = rs.getBoolean("VAI_TRO");
                boolean trangThai = rs.getBoolean("TRANG_THAI");
                listNV.add(new NhanVienModel(id, ma, ten, gioiTinh, sdt, email, vaiTro, trangThai, matkhau));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    
    }
     public Integer add(NhanVienModel n) {
    Integer row = null;

    // Kiểm tra vai trò quản lý
    if (n.isVaiTro()) { // Giả sử true là "Quản lý"
        JOptionPane.showMessageDialog(null, "Không thể thêm nhân viên có vai trò quản lý.");
        return row;
    }

    String sql = """
                 INSERT INTO [dbo].[NHAN_VIEN]
                            ([MA_NHAN_VIEN]
                            ,[TEN_NV]
                            ,[GIOI_TINH]
                            ,[SO_DIEN_THOAI]
                            ,[EMAIL]
                            ,[MAT_KHAU]
                            ,[VAI_TRO]
                            ,[TRANG_THAI])
                      VALUES (?,?,?,?,?,?,?,?)""";
    Connection cn = DBconnextSQL.getConnection();
    try {
        // Tạo mật khẩu ngẫu nhiên
        String matKhauNgauNhien = TaoMatKhau.taoMatKhauNgauNhien();
        n.setMatKhau(matKhauNgauNhien);

        PreparedStatement pstm = cn.prepareStatement(sql);
        pstm.setObject(1, n.getMaNV());
        pstm.setObject(2, n.getHoTen());
        pstm.setObject(3, n.getGioiTinh());
        pstm.setObject(4, n.getSdt());
        pstm.setObject(5, n.getEmail());
        pstm.setObject(6, n.getMatKhau());
        pstm.setObject(7, n.isVaiTro());
        pstm.setObject(8, n.isTrangThai());

        row = pstm.executeUpdate();

        if (row > 0) {
            // Gửi email chứa mật khẩu ngẫu nhiên cho nhân viên
            String noiDungEmail = "Chào " + n.getHoTen() + ",\n\nMật khẩu của bạn là: " + matKhauNgauNhien;
            try {
                SendEmail.guiEmail(n.getEmail(), "Mật khẩu đăng nhập của bạn", noiDungEmail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (cn != null) {
                cn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return row;
}
    public Integer update(NhanVienModel n) {
    Integer row = null;
    String sql = """
                    UPDATE [dbo].[NHAN_VIEN] SET
                        TEN_NV = ?,
                        GIOI_TINH = ?,
                        SO_DIEN_THOAI = ?,
                        EMAIL = ?,
                        VAI_TRO = ?,
                        TRANG_THAI = ?
                    WHERE MA_NHAN_VIEN = ?""";
    
    try (Connection cn = DBconnextSQL.getConnection();
         PreparedStatement pstm = cn.prepareStatement(sql)) {
        
        pstm.setObject(1, n.getHoTen());
        pstm.setObject(2, n.getGioiTinh());
        pstm.setObject(3, n.getSdt());
        pstm.setObject(4, n.getEmail());
        pstm.setObject(5, n.isVaiTro());
        pstm.setObject(6, n.isTrangThai());
        pstm.setObject(7, n.getMaNV());
        
        row = pstm.executeUpdate();
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    return row;
    }
    
    public boolean delete(String id) {
        int check = 0;
        String sql = """
                     UPDATE [dbo].[NHAN_VIEN] SET TRANG_THAI =0
                          WHERE MA_NHAN_VIEN = ?
                     """;
        try (Connection con = DBconnextSQL.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;

    }
    public ArrayList<NhanVienModel> search(String keyword) {
    String sql = "SELECT ID,MA_NHAN_VIEN, TEN_NV, GIOI_TINH, SO_DIEN_THOAI, EMAIL, MAT_KHAU, VAI_TRO, TRANG_THAI "
               + "FROM dbo.NHAN_VIEN "
               + "WHERE MA_NHAN_VIEN LIKE ? "
               + "OR ID LIKE?"
               + "OR TEN_NV LIKE ? "
               + "OR GIOI_TINH LIKE ? "
               + "OR SO_DIEN_THOAI LIKE ? "
               + "OR EMAIL LIKE ? "
               + "OR MAT_KHAU LIKE ? "
               + "OR VAI_TRO LIKE ? "
               + "OR TRANG_THAI LIKE ?";

    ArrayList<NhanVienModel> lists = new ArrayList<>();
    try (Connection con = DBconnextSQL.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        String searchString = "%" + keyword + "%";
        for (int i = 1; i <= 9; i++) {
            ps.setString(i, searchString);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lists.add(new NhanVienModel(
                rs.getInt("ID"),
                rs.getString("MA_NHAN_VIEN"), 
                rs.getString("TEN_NV"), 
                rs.getBoolean("GIOI_TINH"), 
                rs.getString("SO_DIEN_THOAI"), 
                rs.getString("EMAIL"), 
                
                rs.getBoolean("VAI_TRO"), 
                rs.getBoolean("TRANG_THAI"),
                rs.getString("MAT_KHAU")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace(System.out); // Xử lý lỗi khi xảy ra
    }
    return lists;
}


}
