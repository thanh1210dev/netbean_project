package reponsitory;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBconnextSQL;
import entity.MauSac;

public class MauSacRepo {

    public ArrayList<MauSac> getAll() {
        String sql = "SELECT ID, TEN ,MA_MAU FROM MAU_SAC";
        ArrayList<MauSac> list = new ArrayList<>();
        
        try (Connection con = DBconnextSQL.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                MauSac mauSac = new MauSac();
                mauSac.setId(rs.getInt("ID"));
                mauSac.setTenMau(rs.getString("TEN"));
                mauSac.setMaMauSac(rs.getString("MA_MAU"));
       
                list.add(mauSac);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
     public void addMauSac(String tenMauSac, String maMau) throws SQLException { 
        String sql = "INSERT INTO MAU_SAC (TEN, MA_MAU) VALUES (?, ?)"; 
        try (Connection con = DBconnextSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenMauSac);
            ps.setString(2, maMau); // Lưu cả mã màu
            ps.executeUpdate();
        }
    }
}
