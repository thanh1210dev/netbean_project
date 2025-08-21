package reponsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import config.DBconnextSQL;
import entity.DoBay;

public class DoBayRepo {

    public ArrayList<DoBay> getAll() {
        String sql = "SELECT ID, TEN,MA_DO_BAY FROM DO_BAY";
        ArrayList<DoBay> list = new ArrayList<>();
        
        try (Connection con = DBconnextSQL.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                DoBay doBay = new DoBay();
                doBay.setId(rs.getInt("ID"));
                doBay.setTen(rs.getString("TEN"));
                doBay.setMaDoBay(rs.getString("MA_DO_BAY"));
                
                list.add(doBay);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return list;
    }
     public void addDoBay(String tenDoBay) throws SQLException { 
        String sql = "INSERT INTO DO_BAY (TEN) VALUES (?)"; 
        try (Connection con = DBconnextSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenDoBay);
            ps.executeUpdate();
        }
    }
}
