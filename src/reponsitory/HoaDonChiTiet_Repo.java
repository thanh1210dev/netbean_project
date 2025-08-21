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
import response.HoaDonCT_Response;

/**
 *
 * @author Admin
 */
public class HoaDonChiTiet_Repo {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<HoaDonCT_Response> getAll(Integer hoaDonID) {
        ArrayList<HoaDonCT_Response> list = new ArrayList<>();

        // code
        // b1: tao sql
        String sql = """
                    SELECT dbo.HOA_DON_CHI_TIET.Id, 
                                                                   dbo.SAN_PHAM.MA_SAN_PHAM, 
                                                                   dbo.SAN_PHAM.TEN, 
                                                                   dbo.MAU_SAC.TEN AS MAU_SAC, 
                                                                   dbo.DO_CANG.TEN AS DO_CANG, 
                                                                   dbo.CHIEU_DAI_VOT.TEN AS CHIEU_DAI_VOT, 
                                                                   dbo.HOA_DON_CHI_TIET.SO_LUONG, 
                                                                   dbo.SAN_PHAM_CHI_TIET.DON_GIA, 
                                                                   dbo.HOA_DON_CHI_TIET.THANH_TIEN,
                                                                   dbo.HOA_DON_CHI_TIET.ID_SPCT
                                                            FROM dbo.HOA_DON_CHI_TIET
                                                             JOIN dbo.SAN_PHAM_CHI_TIET ON dbo.HOA_DON_CHI_TIET.ID_SPCT = dbo.SAN_PHAM_CHI_TIET.ID
                                                             JOIN dbo.SAN_PHAM ON dbo.SAN_PHAM_CHI_TIET.IDSP = dbo.SAN_PHAM.ID
                                       			JOIN dbo.MAU_SAC ON dbo.SAN_PHAM_CHI_TIET.MA_MAU = dbo.MAU_SAC.ID
                                                             JOIN dbo.DO_CANG ON dbo.SAN_PHAM_CHI_TIET.MA_DO_CANG = dbo.DO_CANG.ID
                                                             JOIN dbo.CHIEU_DAI_VOT ON dbo.SAN_PHAM_CHI_TIET.MA_CD_VOT = dbo.CHIEU_DAI_VOT.ID
                                                            WHERE dbo.HOA_DON_CHI_TIET.ID_HD = ? 
                     """;

        // Mở cổng kết nối: try...catch
        // try...with..resource
        try {
            con = DBconnextSQL.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hoaDonID);
            rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonCT_Response reponse = HoaDonCT_Response.builder()
                        .id(rs.getInt(1))
                        .maSP(rs.getString(2))
                        .tenSP(rs.getString(3))
                        .tenMau(rs.getString(4))
                        .doCang(rs.getString(5))
                        .CDVot(rs.getString(6))
                        .soLuong(rs.getInt(7))
                        .donGia(rs.getDouble(8))
                        .thanhTien(rs.getDouble(9))
                        .ctspID(rs.getInt(10))
                        .build();

                list.add(reponse);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return list;
    }
}
