/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import config.DBconnextSQL;
import entity.PhieuGiamGia;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Asus
 */
public class PhieuGiamGiaReponsitory {

    public ArrayList<PhieuGiamGia> getAll() {
        String query = """
                        SELECT ID,
                              [MA_PHIEU]
                              ,[TEN_PHIEU]
                              ,[NGAY_TAO]
                              ,[NGAY_HET_HAN]
                              ,[LOAI_GIAM]
                              ,[GIA_TRI_GIAM_MAX]
                               ,[HOA_DON_TOI_THIEU]
                              ,[TRANG_THAI]
                          FROM [dbo].[PHIEU_GIAM_GIA]
                      WHERE [TRANG_THAI] Between 1 and 2
                       ORDER BY ID DESC
                      """;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            ArrayList<PhieuGiamGia> listPGG = new ArrayList();
            while (rs.next()) {
                PhieuGiamGia PGG = new PhieuGiamGia(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4), rs.getDate(5), rs.getBoolean(6), rs.getFloat(7),
                        rs.getFloat(8), rs.getInt(9));
                listPGG.add(PGG);

            }
            return listPGG;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean add(PhieuGiamGia PGG) {

        int check = 0;

        String sql = """
                    INSERT INTO [dbo].[PHIEU_GIAM_GIA]
                               ([MA_PHIEU]
                               ,[TEN_PHIEU]
                               ,[NGAY_TAO]
                               ,[NGAY_HET_HAN]
                               ,[LOAI_GIAM]
                               ,[HOA_DON_TOI_THIEU]
                               ,[GIA_TRI_GIAM_MAX]
                               ,[TRANG_THAI])
                         VALUES
                               (?,?,?,?,?,?,?,?)
                    """;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, PGG.getMa());
            ps.setObject(2, PGG.getTen());
            ps.setObject(3, PGG.getNgayTao());
            ps.setObject(4, PGG.getNgayHet());
            ps.setObject(5, PGG.getLoaiGiam());
            ps.setObject(6, PGG.getHoaDonToiThieu());
            ps.setObject(7, PGG.getGiaTriGiamMax());
            ps.setObject(8, PGG.getTrangThai());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

    public boolean update(PhieuGiamGia PGG, String ma) {
        int check = 0;
        String sql = """
                    UPDATE [dbo].[PHIEU_GIAM_GIA]
                       SET 
                          [TEN_PHIEU] = ?
                          ,[NGAY_TAO] = ?
                          ,[NGAY_HET_HAN] =?
                          ,[LOAI_GIAM] = ?
                          ,[HOA_DON_TOI_THIEU] = ?
                          ,[GIA_TRI_GIAM_MAX] = ?
                          ,[TRANG_THAI] = ?
                     WHERE MA_PHIEU =?
                     """;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, PGG.getTen());
            ps.setObject(2, PGG.getNgayTao());
            ps.setObject(3, PGG.getNgayHet());
            ps.setObject(4, PGG.getLoaiGiam());
            ps.setObject(5, PGG.getHoaDonToiThieu());
            ps.setObject(6, PGG.getGiaTriGiamMax());
            ps.setObject(7, PGG.getTrangThai());
            ps.setObject(8, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean remove(String Ma) {
        int check = 0;
        String sql = """
                     UPDATE [dbo].[PHIEU_GIAM_GIA]
                            SET [TRANG_THAI] = 3
                            ,[NGAY_HET_HAN] = GETDATE()
                          WHERE MA_PHIEU=?
                     """;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, Ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean setStatusEXP(String ma, int status) {
        int check = 0;
        String sql = """
                     UPDATE [dbo].[PHIEU_GIAM_GIA]
                            SET [TRANG_THAI] = ?
                          WHERE MA_PHIEU = ?
                     """;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, status);
            ps.setObject(2, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ArrayList<PhieuGiamGia> TatCaDS() {
        String query = """
                        SELECT [ID]
                              ,[MA_PHIEU]
                              ,[TEN_PHIEU]
                              ,[NGAY_TAO]
                              ,[NGAY_HET_HAN]
                              ,[LOAI_GIAM]
                              ,[GIA_TRI_GIAM_MAX]
                               ,[HOA_DON_TOI_THIEU]
                              ,[TRANG_THAI]
                          FROM [dbo].[PHIEU_GIAM_GIA]
                       ORDER BY ID DESC
                      """;
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            ArrayList<PhieuGiamGia> listPGG = new ArrayList();
            while (rs.next()) {
                PhieuGiamGia PGG = new PhieuGiamGia(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4), rs.getDate(5), rs.getBoolean(6), rs.getFloat(7),
                        rs.getFloat(8), rs.getInt(9));
                listPGG.add(PGG);

            }
            return listPGG;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<PhieuGiamGia> search(String keyword) {
        String sql = """
                                                 SELECT ID,
                                                   [MA_PHIEU]
                                                   ,[TEN_PHIEU]
                                                   ,[NGAY_TAO]
                                                   ,[NGAY_HET_HAN]
                                                   ,[LOAI_GIAM]
                                                   ,[GIA_TRI_GIAM_MAX]
                                                    ,[HOA_DON_TOI_THIEU]
                                                   ,[TRANG_THAI]
                                               FROM [dbo].[PHIEU_GIAM_GIA]
                                                    WHERE ([TEN_PHIEU] LIKE N'%' + ? + N'%' )
                                                    OR ([MA_PHIEU] LIKE '%' + ? + '%')
                                                                        
                                                                        
                
                     """;
        ArrayList<PhieuGiamGia> lists = new ArrayList<>();
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, keyword);
            ps.setObject(2, keyword);
//            ps.setObject(3,  keyword );
//            ps.setObject(4,  keyword  );

            // like => %%: contans : Kiem tra chua
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuGiamGia Pgg = new PhieuGiamGia();
                Pgg.setId(rs.getInt("ID"));
                Pgg.setMa(rs.getString("MA_PHIEU"));
                Pgg.setTen(rs.getString("TEN_PHIEU"));
                Pgg.setNgayTao(rs.getDate("NGAY_TAO"));
                Pgg.setNgayHet(rs.getDate("NGAY_HET_HAN"));
                Pgg.setLoaiGiam(rs.getBoolean("LOAI_GIAM"));
                Pgg.setGiaTriGiamMax(rs.getFloat("GIA_TRI_GIAM_MAX"));
                Pgg.setHoaDonToiThieu(rs.getFloat("HOA_DON_TOI_THIEU"));
                Pgg.setTrangThai(rs.getInt("TRANG_THAI"));
                lists.add(Pgg);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
            return null;
        }

    }

    public ArrayList<PhieuGiamGia> LocPhieu(Boolean loaiGiam, int trangThai) {
        String sql = """
                                                 SELECT ID,
                                                   [MA_PHIEU]
                                                   ,[TEN_PHIEU]
                                                   ,[NGAY_TAO]
                                                   ,[NGAY_HET_HAN]
                                                   ,[LOAI_GIAM]
                                                   ,[GIA_TRI_GIAM_MAX]
                                                    ,[HOA_DON_TOI_THIEU]
                                                   ,[TRANG_THAI]
                                               FROM [dbo].[PHIEU_GIAM_GIA]
                                                    WHERE (LOAI_GIAM = ? OR ? IS NULL OR ? = '') " 
                                                            "AND (TRANG_THAI = ? OR ? IS NULL OR ? = '')
                                                                        
                                                                        
                
                     """;
        ArrayList<PhieuGiamGia> lists = new ArrayList<>();
        try (Connection con = DBconnextSQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, loaiGiam);
            ps.setObject(2, loaiGiam);
            ps.setObject(3, loaiGiam);
            ps.setObject(4, trangThai);
            ps.setObject(5, trangThai);
            ps.setObject(6, trangThai);

            // like => %%: contans : Kiem tra chua
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuGiamGia Pgg = new PhieuGiamGia();
                Pgg.setId(rs.getInt("ID"));
                Pgg.setMa(rs.getString("MA_PHIEU"));
                Pgg.setTen(rs.getString("TEN_PHIEU"));
                Pgg.setNgayTao(rs.getDate("NGAY_TAO"));
                Pgg.setNgayHet(rs.getDate("NGAY_HET_HAN"));
                Pgg.setLoaiGiam(rs.getBoolean("LOAI_GIAM"));
                Pgg.setGiaTriGiamMax(rs.getFloat("GIA_TRI_GIAM_MAX"));
                Pgg.setHoaDonToiThieu(rs.getFloat("HOA_DON_TOI_THIEU"));
                Pgg.setTrangThai(rs.getInt("TRANG_THAI"));
                lists.add(Pgg);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
            return null;
        }

    }
     public ArrayList<PhieuGiamGia> getLoaiGiamVaTrangThai(Boolean loaiGiam, int trangThai) {
    String query = "SELECT ID, MA_PHIEU, TEN_PHIEU, NGAY_TAO, NGAY_HET_HAN, LOAI_GIAM, GIA_TRI_GIAM_MAX, HOA_DON_TOI_THIEU, TRANG_THAI "
                 + "FROM PHIEU_GIAM_GIA "
                 + "WHERE LOAI_GIAM = ? AND TRANG_THAI = ?";
    try (Connection con = DBconnextSQL.getConnection(); 
         PreparedStatement ps = con.prepareStatement(query)) {
        ps.setBoolean(1, loaiGiam);
        ps.setInt(2, trangThai);
        ResultSet rs = ps.executeQuery();
        ArrayList<PhieuGiamGia> listPGG = new ArrayList<>();
        while (rs.next()) {
            PhieuGiamGia PGG = new PhieuGiamGia(
                    rs.getInt("ID"), 
                    rs.getString("MA_PHIEU"), 
                    rs.getString("TEN_PHIEU"),
                    rs.getDate("NGAY_TAO"), 
                    rs.getDate("NGAY_HET_HAN"), 
                    rs.getBoolean("LOAI_GIAM"), 
                    rs.getFloat("GIA_TRI_GIAM_MAX"),
                    rs.getFloat("HOA_DON_TOI_THIEU"), 
                    rs.getInt("TRANG_THAI")
            );
            listPGG.add(PGG);
        }
        return listPGG;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
     }
    
}
