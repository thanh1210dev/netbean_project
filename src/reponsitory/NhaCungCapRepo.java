package reponsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import config.DBconnextSQL;
import entity.NhaCungCap;

public class NhaCungCapRepo {

    public ArrayList<NhaCungCap> getAll() {
        String sql = "SELECT ID, TEN, DIA_CHI, MA_NHA_CUNG_CAP FROM NHA_CUNG_CAP";
        ArrayList<NhaCungCap> list = new ArrayList<>();
        
        try (Connection con = DBconnextSQL.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                NhaCungCap nhaCungCap = new NhaCungCap();
                nhaCungCap.setId(rs.getInt("ID"));
                nhaCungCap.setTen(rs.getString("TEN"));
                nhaCungCap.setDiaChi(rs.getString("DIA_CHI"));
                nhaCungCap.setMaNhaCungCap(rs.getString("MA_NHA_CUNG_CAP"));
                
                list.add(nhaCungCap);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return list;
    }
     public void addNhaCungCap(String tenNhaCungCap) throws SQLException {
        String sql = "INSERT INTO NHA_CUNG_CAP (TEN) VALUES (?)";
        try (Connection con = DBconnextSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenNhaCungCap);
            ps.executeUpdate();
        }
    }
}
