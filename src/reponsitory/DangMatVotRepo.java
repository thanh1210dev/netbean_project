package reponsitory;

import config.DBconnextSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.DangMatVot;

public class DangMatVotRepo {

    public ArrayList<DangMatVot> getAll() {
        String sql = "SELECT ID,MA_CHIEU_DAI_VOT TEN FROM DANG_MAT_VOT";
        ArrayList<DangMatVot> list = new ArrayList<>();
        
        try (Connection con = DBconnextSQL.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                DangMatVot dmv = new DangMatVot();
                dmv.setId(rs.getInt("ID"));
                dmv.setTen(rs.getString("TEN"));
                dmv.setMaDangMatVot(rs.getString("MA_CHIEU_DAI_VOT"));
                
                list.add(dmv);
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
