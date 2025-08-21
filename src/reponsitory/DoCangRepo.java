package reponsitory;

import config.DBconnextSQL;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.DoCang;

public class DoCangRepo {

    public ArrayList<DoCang> getAll() {
        String sql = "SELECT ID, TEN, MA_DO_CANG FROM DO_CANG";
        ArrayList<DoCang> list = new ArrayList<>();
        
        try (Connection con = DBconnextSQL.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                DoCang doCang = new DoCang();
                doCang.setId(rs.getInt("ID"));
                doCang.setTen(rs.getString("TEN"));
                doCang.setMaDoCang(rs.getString("MA_DO_CANG"));
                
                list.add(doCang);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return list;
    }
    public void addDoCang(String tenDoCang) throws SQLException { 
        String sql = "INSERT INTO DO_CANG (TEN) VALUES (?)"; 
        try (Connection con = DBconnextSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenDoCang);
            ps.executeUpdate();
        }
    }

}
