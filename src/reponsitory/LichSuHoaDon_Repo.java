/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import config.DBconnextSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.HoaDon_Response;
import response.LichSuHoaDon_Response;


/**
 *
 * @author Admin
 */
public class LichSuHoaDon_Repo {
     private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<LichSuHoaDon_Response> getAll(Integer ID) {
        ArrayList<LichSuHoaDon_Response> list = new ArrayList<>();

        // code
        // b1: tao sql
        String sql = """
                  SELECT        dbo.HOA_DON.Id, dbo.HINH_THUC_THANH_TOAN.TEN_PHUONG_THUC, dbo.NHAN_VIEN.MA_NHAN_VIEN, dbo.HOA_DON.NGAY_DAT_HANG
                  FROM            dbo.HOA_DON INNER JOIN
                                                             dbo.HINH_THUC_THANH_TOAN ON dbo.HOA_DON.Id_THANH_TOAN = dbo.HINH_THUC_THANH_TOAN.Id INNER JOIN
                                                             dbo.NHAN_VIEN ON dbo.HOA_DON.Id_NV = dbo.NHAN_VIEN.ID INNER JOIN
                                                             dbo.HOA_DON_CHI_TIET ON dbo.HOA_DON.Id = dbo.HOA_DON_CHI_TIET.ID_HD
                     
                    AND ID_HD = ?
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
