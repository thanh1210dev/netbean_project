package reponsitory;

import config.DBconnextSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.SanPham;
import javax.swing.JOptionPane;

public class SanPhamRepo {

    public ArrayList<SanPham> getAll() {
        String sql = """
          SELECT 
                sp.[ID],
                sp.[MA_SAN_PHAM],
                sp.[TEN],
                sp.[MO_TA],
                sp.[NGAY_TAO],
                sp.[TRANG_THAI],
                sp.[DON_GIA],
                SUM(spct.SO_LUONG) AS SO_LUONG
            FROM 
                [dbo].[SAN_PHAM] sp
               LEFT JOIN dbo.SAN_PHAM_CHI_TIET spct ON spct.IDSP = sp.ID
            GROUP BY 
                sp.[ID],
                sp.[MA_SAN_PHAM],
                sp.[TEN],
                sp.[MO_TA],
                sp.[NGAY_TAO],
                sp.[TRANG_THAI],
                sp.[DON_GIA];
        """;
        ArrayList<SanPham> list = new ArrayList<>();

        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setId(rs.getInt("ID"));
                sanPham.setMaSP(rs.getString("MA_SAN_PHAM"));
                sanPham.setTenSP(rs.getString("TEN"));
                sanPham.setMoTa(rs.getString("MO_TA"));
                sanPham.setNgayTao(rs.getDate("NGAY_TAO"));
                sanPham.setTrangThai(rs.getBoolean("TRANG_THAI"));
                sanPham.setDonGia(rs.getFloat("DON_GIA"));
                sanPham.setSoLuong(rs.getInt("SO_LUONG"));

                list.add(sanPham);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }

    }

    public boolean remove(Integer id) {
        int check = 0;
        String sql = """
//                     UPDATE [dbo].[San_Pham]
//                            SET [trangthai] = 1
//                          WHERE ID = ?
                     
                     """;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;

    }

    public ArrayList<SanPham> search(String keyword) {
        String sql ="""
                    SELECT 
                                      sp.[ID],
                                      sp.[MA_SAN_PHAM],
                                      sp.[TEN],
                                      sp.[MO_TA],
                                      sp.[NGAY_TAO],
                                      sp.[TRANG_THAI],
                                      sp.[DON_GIA],
                                      SUM(spct.SO_LUONG) AS SO_LUONG
                                  FROM 
                                      [dbo].[SAN_PHAM] sp
                                     LEFT JOIN dbo.SAN_PHAM_CHI_TIET spct ON spct.IDSP = sp.ID
                    WHERE TEN LIKE ?
                                  GROUP BY 
                                      sp.[ID],
                                      sp.[MA_SAN_PHAM],
                                      sp.[TEN],
                                      sp.[MO_TA],
                                      sp.[NGAY_TAO],
                                      sp.[TRANG_THAI],
                                      sp.[DON_GIA]
                      
                    """;
         
        ArrayList<SanPham> list = new ArrayList<>();
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = SanPham.builder()
                        .id(rs.getInt("ID"))    
                        .tenSP(rs.getString("TEN"))
                        .maSP(rs.getString("MA_SAN_PHAM"))
                        .soLuong(rs.getInt("SO_LUONG"))
                        .trangThai(rs.getBoolean("TRANG_THAI"))
                        .build();
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi tìm kiếm sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null; // Trả về null nếu có lỗi
        }
        return list;
    }


    public boolean update(SanPham newSanPham) {
    int check = 0;
    String sql = """
      UPDATE [dbo].[SAN_PHAM]
      SET 
        [TEN] = ?,
        [MO_TA] = ?,
        [TRANG_THAI] = ?,
        [DON_GIA] = ?
      WHERE ID = ? 
      """; 

    try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, newSanPham.getTenSP());
        ps.setString(2, newSanPham.getMoTa());
        ps.setBoolean(3, newSanPham.isTrangThai());
        ps.setFloat(4, newSanPham.getDonGia());
        ps.setInt(5, newSanPham.getId()); 
        check = ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace(System.out);
        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    return check > 0;
}


    public boolean add(SanPham sanPham) {
        int check = 0;
        String sql = """
                INSERT INTO [dbo].[SAN_PHAM]
                    ([MA_SAN_PHAM]
                    ,[TEN]
                    ,[MO_TA]
                    ,[NGAY_TAO]
                    ,[TRANG_THAI]
                    ,[DON_GIA]
                    )
                VALUES
                    (?, ?, ?, ?, ?, ?) 
                """;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sanPham.getMaSP());
            ps.setObject(2, sanPham.getTenSP());
            ps.setObject(3, sanPham.getMoTa());
            ps.setObject(4, sanPham.getNgayTao());
            ps.setObject(5, sanPham.isTrangThai());
            ps.setObject(6, sanPham.getDonGia());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

}
