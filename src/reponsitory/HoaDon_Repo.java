/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import config.DBconnextSQL;
import entity.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.HoaDon_Response;

/**
 *
 * @author Admin
 */
public class HoaDon_Repo {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private String sql = null;

    public ArrayList<HoaDon_Response> getAll() {
        ArrayList<HoaDon_Response> list = new ArrayList<>();

        sql = """
            SELECT        dbo.HOA_DON.Id,
              dbo.HOA_DON.MA_HOA_DON, 
              dbo.KHACH_HANG.TEN_KHACH_HANG, 
              dbo.HOA_DON.NGAY_DAT_HANG, 
              dbo.KHACH_HANG.SDT, 
              dbo.HOA_DON.TONG_TIEN,
              dbo.HOA_DON.TRANG_THAI, 
                                       
              dbo.KHACH_HANG.DIA_CHI,
              dbo.NHAN_VIEN.MA_NHAN_VIEN,
              dbo.HOA_DON.TONG_TIEN_KHI_GIAM
             FROM            dbo.HOA_DON INNER JOIN
                                      dbo.KHACH_HANG ON dbo.HOA_DON.Id_KH = dbo.KHACH_HANG.ID INNER JOIN
                                      dbo.NHAN_VIEN ON dbo.HOA_DON.Id_NV = dbo.NHAN_VIEN.ID
              
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
                        .tienSauGiam(rs.getDouble(10))
                        .build();
                list.add(reponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon_Response> search(String keyword, Integer trangThai, Double minTongTien, Double maxTongTien) {
        ArrayList<HoaDon_Response> list = new ArrayList<>();

        sql = """
             SELECT        dbo.HOA_DON.Id,
                                                          dbo.HOA_DON.MA_HOA_DON, 
                                                          dbo.KHACH_HANG.TEN_KHACH_HANG, 
                                                          dbo.HOA_DON.NGAY_DAT_HANG, 
                                                          dbo.KHACH_HANG.SDT, 
                                                          dbo.HOA_DON.TONG_TIEN,
                                                          dbo.HOA_DON.TRANG_THAI, 
                                                                                   
                                                          dbo.KHACH_HANG.DIA_CHI,
                                                          dbo.NHAN_VIEN.MA_NHAN_VIEN,
                                                          dbo.HOA_DON.TONG_TIEN_KHI_GIAM
                                                         FROM            dbo.HOA_DON INNER JOIN
                                                                                  dbo.KHACH_HANG ON dbo.HOA_DON.Id_KH = dbo.KHACH_HANG.ID 
              INNER JOIN
                                                                                  dbo.NHAN_VIEN ON dbo.HOA_DON.Id_NV = dbo.NHAN_VIEN.ID
               WHERE (?=0 or HOA_DON.TRANG_THAI= ?)
               
               """;

        // check neu keyword k nhap gi => k can them gi ca 
        // nhap => moi can cong them vao sql 
        if (keyword.length() > 0) {
            sql += """
                    AND (HOA_DON.MA_HOA_DON LIKE ?
                        OR NHAN_VIEN.MA_NHAN_VIEN LIKE ?
                        OR KHACH_HANG.TEN_KHACH_HANG LIKE ?
                        OR KHACH_HANG.SDT LIKE ?
                        OR KHACH_HANG.DIA_CHI LIKE ?
                        )
                                        
                   """;

        }

        if (minTongTien != null && maxTongTien != null) {
            sql += " AND HOA_DON.TONG_TIEN_KHI_GIAM BETWEEN ? AND ?";
        }
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(sql);
            int index =1 ;
            // Vi tri cua dau hoi cham dau tien 
            ps.setObject(index++, trangThai);
            ps.setObject(index++, trangThai);

            if (keyword.length() > 0) {
                String value = "%" + keyword + "%";
                // search 1 o input nhieu truong
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
//                ps.setObject(8, value);
            }

            if (minTongTien != null && maxTongTien != null) {
                ps.setObject(index++, minTongTien);
                ps.setObject(index++, maxTongTien);
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon_Response reponse = HoaDon_Response.builder()
                                                .id(rs.getInt("Id"))
                        .ma(rs.getString("MA_HOA_DON"))
                        .tenKhachHang(rs.getString("TEN_KHACH_HANG"))
                        .ngayDatHang(rs.getDate("NGAY_DAT_HANG"))
                        .tongTien(rs.getDouble("TONG_TIEN"))
                        .SDT(rs.getString("SDT"))
                        .tienSauGiam(rs.getDouble("TONG_TIEN_KHI_GIAM"))
                        .trangThai(rs.getInt("TRANG_THAI"))
                        .diaChi(rs.getString("DIA_CHI"))
                        .maNhanVien(rs.getString("MA_NHAN_VIEN"))
                        .build();

                list.add(reponse);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
