/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import config.DBconnextSQL;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ThongkeReponsitory {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private String sql = null;

    public Double doanhthungay() {
        sql = """
                    select SUM(TONG_TIEN_KHI_GIAM) as [tongDoanhThuNgay] from HOA_Don where (trang_Thai =1 ) and CAST(NGAY_DAT_HANG AS DATE) = CAST(GETDATE() AS DATE);
                    """;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
    public Double doanhThuThang(int thang, int nam) {
    String sql = """
                 SELECT SUM(TONG_TIEN_KHI_GIAM) as [tongDoanhThuThang] 
                 FROM HOA_DON 
                 WHERE trang_Thai = 1 
                 AND NGAY_DAT_HANG >= ? 
                 AND NGAY_DAT_HANG < ?
                 """;
    try {
        con = DBconnextSQL.getConnection();
        ps = con.prepareCall(sql);
        
        // Tính toán ngày bắt đầu và ngày kết thúc cho tháng và năm cho trước
        String ngayBatDau = String.format("%d-%02d-01", nam, thang);
        String ngayKetThuc = String.format("%d-%02d-01", thang == 12 ? nam + 1 : nam, thang == 12 ? 1 : thang + 1);
        
        ps.setString(1, ngayBatDau);
        ps.setString(2, ngayKetThuc);
        
        rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getDouble("tongDoanhThuThang");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

  public Double doanhThuThang(){
       sql = """
                     SELECT SUM(TONG_TIEN_KHI_GIAM) as [tongDoanhThuThang] FROM HOA_DON WHERE trang_Thai =1 AND NGAY_DAT_HANG >= DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0) AND NGAY_DAT_HANG < DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) + 1, 0)
             """;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                return rs.getDouble("tongDoanhThuThang");
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
   public Double tongDoanhThu(){
        
        sql ="select SUM(TONG_TIEN_KHI_GIAM) as [tongDoanhThu] from hoa_Don where trang_Thai = 1 ";
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                return rs.getDouble("tongDoanhThu");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     public Integer SoHoaDon() {
        sql = """
                    select COUNT(ID) as [SoHoaDon] from HOA_Don where (trang_Thai =1 ) AND NGAY_DAT_HANG >= DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0) AND NGAY_DAT_HANG < DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) + 1, 0)
                                 
                    """;
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("SoHoaDon");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
      public List<Object[]> danhSachDoanhThuNam() {
    sql = """
          SELECT
              YEAR(NGAY_DAT_HANG) AS Nam,
              SUM(tong_Tien) AS DoanhThu,
              MAX(MonthlyRevenue.MonthlyRevenue) AS ThangCaoNhat,
              MIN(MonthlyRevenue.MonthlyRevenue) AS ThangThapNhat,
              AVG(MonthlyRevenue.MonthlyRevenue) AS DoanhThuTrungBinh
          FROM
              hoa_Don 
              INNER JOIN (
                  SELECT
                      YEAR(NGAY_DAT_HANG) AS Nam,
                      MONTH(NGAY_DAT_HANG) AS Thang,
                      SUM(TONG_TIEN_KHI_GIAM) AS MonthlyRevenue
                  FROM
                      hoa_Don
                  WHERE
                      trang_Thai = 1
                  GROUP BY
                      YEAR(NGAY_DAT_HANG),
                      MONTH(NGAY_DAT_HANG)
              ) AS MonthlyRevenue ON YEAR(hoa_Don.NGAY_DAT_HANG) = MonthlyRevenue.Nam AND MONTH(hoa_Don.NGAY_DAT_HANG) = MonthlyRevenue.Thang
          	
          WHERE
              hoa_Don.trang_Thai = 1
          GROUP BY
              YEAR(NGAY_DAT_HANG)
          ORDER BY
              YEAR(NGAY_DAT_HANG);
         """;
    List<Object[]> resultList = new ArrayList<>();
    try {
        con = DBconnextSQL.getConnection();
        ps = con.prepareCall(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Object[] row = new Object[5];
            row[0] = rs.getInt("Nam");
            row[1] = rs.getBigDecimal("DoanhThu");
            row[2] = rs.getBigDecimal("ThangCaoNhat");
            row[3] = rs.getBigDecimal("ThangThapNhat");
            row[4] = rs.getBigDecimal("DoanhThuTrungBinh");
            resultList.add(row);
        }
        return resultList;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    } finally {
        
    }
}


}
