package reponsitory;

import config.DBconnextSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.DiemCanBang;

public class DiemCanBangRepo {

    public ArrayList<DiemCanBang> getAll() {
        String sql = "SELECT ID, TEN, MA_DIEM_CAN_BANG FROM DIEM_CAN_BANG";
        ArrayList<DiemCanBang> list = new ArrayList<>();
        
        try (Connection con = DBconnextSQL.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                DiemCanBang dcbv = new DiemCanBang();
                dcbv.setId(rs.getInt("ID"));
                dcbv.setTen(rs.getString("TEN"));
                dcbv.setMaDiemCanBang(rs.getString("MA_DIEM_CAN_BANG"));
                
                list.add(dcbv);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return list;
    }
    public void addThuocTinh(String tenBang, String tenCot, String giaTri) throws SQLException {
        String sql = "INSERT INTO " + tenBang + " (" + tenCot + ") VALUES (?)";
        try (Connection con = DBconnextSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, giaTri);
            ps.executeUpdate();
        }
    }
}
