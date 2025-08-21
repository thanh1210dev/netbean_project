package reponsitory;

import config.DBconnextSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.TrongLuong;

public class TrongLuongRepo {

    public ArrayList<TrongLuong> getAll() {
        String sql = "SELECT ID, TEN, MA_TRONG_LUONG FROM TRONG_LUONG";
        ArrayList<TrongLuong> list = new ArrayList<>();
        
        try (Connection con = DBconnextSQL.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                TrongLuong trongLuong = new TrongLuong();
                trongLuong.setId(rs.getInt("ID"));
                trongLuong.setTen(rs.getString("TEN"));
                trongLuong.setMaTrongLuong(rs.getString("MA_TRONG_LUONG"));
                
                list.add(trongLuong);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return list;
    }
     public void addTrongLuong(String tenTrongLuong) throws SQLException { 
        String sql = "INSERT INTO TRONG_LUONG (TEN) VALUES (?)";
        try (Connection con = DBconnextSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenTrongLuong);
            ps.executeUpdate();
        }
    }
}
