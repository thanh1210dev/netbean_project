package reponsitory;

import config.DBconnextSQL;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.DoCungDua;

public class DoCungDuaRepo {

    public ArrayList<DoCungDua> getAll() {
        String sql = "SELECT ID, TEN, MA_DO_CUNG_DUA FROM DO_CUNG_DUA";
        ArrayList<DoCungDua> list = new ArrayList<>();
        
        try (Connection con = DBconnextSQL.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                DoCungDua doCung = new DoCungDua();
                doCung.setId(rs.getInt("ID"));
                doCung.setTen(rs.getString("TEN"));
                doCung.setMaDoCungDua(rs.getString("MA_DO_CUNG_DUA"));              
                list.add(doCung);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
     public void addDoCungDua(String tenDoCungDua) throws SQLException {
        String sql = "INSERT INTO DO_CUNG_DUA (TEN) VALUES (?)";
        try (Connection con = DBconnextSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenDoCungDua);
            ps.executeUpdate();
        }
    }
}
