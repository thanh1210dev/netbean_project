/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor  // contructor full tham so
@NoArgsConstructor  // contrucor k tham so
@Getter
@Setter
@ToString
@Builder  // contructor tuy y tham so
/**
 *
 * @author Admin
 */
public class HoaDon {

    private Integer id;

    private String ma;

    private Date ngayDatHang;

    private double tongTien;
    
    private String tenKhachHang;
    
    private String SDT;

    private Integer trangThai;
    
    private Integer idKhachHang;
    
    private Integer idNhanVien;
    
    //Mới thêm
    private Integer idPGG;
    
    private Integer idThanhToan;
    
    private Integer tongTienKhiGiam;
    
    

}
