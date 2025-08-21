/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

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
public class HoaDon_Response {

    private Integer id;

    private String ma;

    private Date ngayDatHang;

    private Integer trangThai;

    private String SDT;

    private String diaChi;
    
    private double tongTien;

    private Integer idKhachHang;

    private Integer idNhanVien;

    private String maKhachHang;

    private String tenKhachHang;

    private String maNhanVien;

    private String tenNhanVien;
    
    private String HTTT;
    
    private Double tienSauGiam;
}
