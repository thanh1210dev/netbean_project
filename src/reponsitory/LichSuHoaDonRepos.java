/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import config.DBconnextSQL;
import entity.LichSuHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.LichSuHoaDon_Response;



public class LichSuHoaDonRepos {
    private static Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<LichSuHoaDon> getAll(String maKhachHang) {
        ArrayList<LichSuHoaDon> listHD = new ArrayList<>();
        String sql = """
                    SELECT dbo.KHACH_HANG.MA_KHACH_HANG, dbo.HOA_DON.MA_HOA_DON, dbo.HOA_DON.NGAY_DAT_HANG, dbo.HOA_DON.TONG_TIEN
                    FROM   dbo.KHACH_HANG INNER JOIN
                                 dbo.HOA_DON ON dbo.KHACH_HANG.ID = dbo.HOA_DON.Id_KH
                    WHERE  dbo.KHACH_HANG.MA_KHACH_HANG = ?
                 """;
        try {
            Connection con = DBconnextSQL.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, maKhachHang);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LichSuHoaDon hd = new LichSuHoaDon();
                hd.setMaKhachHang(rs.getString(1));
                hd.setMa(rs.getString(2));
                hd.setNgayDatHang(rs.getDate(3));
                hd.setTongTien(rs.getString(4));
                listHD.add(hd);
            }
            ps.close();
            con.close();
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể lấy hóa đơn theo mã khách hàng", e);
        }
    }
    
     public ArrayList<LichSuHoaDon_Response> getAll2(Integer ID) {
        ArrayList<LichSuHoaDon_Response> list = new ArrayList<>();

        // code
        // b1: tao sql
        String sql = """
                  SELECT  distinct   dbo.HOA_DON.Id, dbo.HINH_THUC_THANH_TOAN.TEN_PHUONG_THUC, dbo.NHAN_VIEN.MA_NHAN_VIEN, dbo.HOA_DON.NGAY_DAT_HANG
                  FROM            dbo.HOA_DON INNER JOIN
                                                             dbo.HINH_THUC_THANH_TOAN ON dbo.HOA_DON.Id_THANH_TOAN = dbo.HINH_THUC_THANH_TOAN.Id INNER JOIN
                                                             dbo.NHAN_VIEN ON dbo.HOA_DON.Id_NV = dbo.NHAN_VIEN.ID INNER JOIN
                                                             dbo.HOA_DON_CHI_TIET ON dbo.HOA_DON.Id = dbo.HOA_DON_CHI_TIET.ID_HD
                    AND ID_HD = ?
                     AND HOA_DON.TRANG_THAI = 1
                     """;

        // Mở cổng kết nối: try...catch
        // try...with..resource
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ID);
            rs = ps.executeQuery();

            while (rs.next()) {
                LichSuHoaDon_Response reponse = LichSuHoaDon_Response.builder()
                        .id(rs.getInt(1))
                       .HTTT(rs.getString(2))
                        .maNhanVien(rs.getString(3))
                        .ngayDatHang(rs.getDate(4))
                        .build();

                list.add(reponse);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return list;
    }
}
