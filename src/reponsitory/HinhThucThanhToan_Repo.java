/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import config.DBconnextSQL;
import entity.HinhThucThanhToan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.HinhThucThanhToan_Response;
import response.HoaDon_Response;

/**
 *
 * @author Admin
 */
public class HinhThucThanhToan_Repo {
    
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<HinhThucThanhToan_Response> getAll(Integer hoaDonID) {
        ArrayList<HinhThucThanhToan_Response> list = new ArrayList<>();

        // code
        // b1: tao sql
        String sql = """
                    SELECT        dbo.HINH_THUC_THANH_TOAN.Id, dbo.HINH_THUC_THANH_TOAN.TEN_PHUONG_THUC, dbo.HOA_DON.NGAY_DAT_HANG, dbo.NHAN_VIEN.MA_NHAN_VIEN
                    FROM            dbo.HINH_THUC_THANH_TOAN INNER JOIN
                                             dbo.HOA_DON ON dbo.HINH_THUC_THANH_TOAN.Id = dbo.HOA_DON.Id_THANH_TOAN INNER JOIN
                                             dbo.NHAN_VIEN ON dbo.HOA_DON.Id_NV = dbo.NHAN_VIEN.ID
                     
                   AND ID_HD = ?
                     """;

        // Mở cổng kết nối: try...catch
        // try...with..resource
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(sql);
             ps.setObject(1, hoaDonID);
            rs = ps.executeQuery();

            while (rs.next()) {
                 HinhThucThanhToan_Response reponse = HinhThucThanhToan_Response.builder()
                        .id(rs.getInt(1))
                        .HTTT(rs.getString(2))
                        .ngayDatHang(rs.getDate(3))
                        .maNhanVien(rs.getString(4))
                        
                        .build();

                list.add(reponse);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return list;
    }
}
