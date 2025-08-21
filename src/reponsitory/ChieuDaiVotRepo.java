package reponsitory;

import config.DBconnextSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.ChieuDaiVot;

public class ChieuDaiVotRepo {

    public ArrayList<ChieuDaiVot> getAll() {
        String sql = "SELECT ID, TEN,MA_CHIEU_DAI_VOT FROM CHIEU_DAI_VOT";
        ArrayList<ChieuDaiVot> list = new ArrayList<>();
        
        try (Connection con = DBconnextSQL.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ChieuDaiVot chieuDaiVot = new ChieuDaiVot();
                chieuDaiVot.setId(rs.getInt("ID"));
                chieuDaiVot.setTen(rs.getString("TEN"));
                chieuDaiVot.setMaChieuDaiVot(rs.getString("MA_CHIEU_DAI_VOT"));
                
                list.add(chieuDaiVot);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return list;
    }
     public void addThuocTinh(ChieuDaiVot chieuDaiVot) throws SQLException {
        String sql = "INSERT INTO CHIEU_DAI_VOT (TEN, MA_CHIEU_DAI_VOT) VALUES (?, ?)"; // Không cần chèn ID vì nó có thể tự động tăng
        try (Connection con = DBconnextSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, chieuDaiVot.getTen());
            ps.setString(2, chieuDaiVot.getMaChieuDaiVot());
            ps.executeUpdate();
        } }
}
