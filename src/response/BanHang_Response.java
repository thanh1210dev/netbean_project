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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

/**
 *
 * @author thananh
 */
public class BanHang_Response {

    private Integer id;

    private String ma;

    private Date ngayDatHang;

    private Integer trangThai;

    private Integer idNhanVien;
    
    private String maNhanVien;


 
    private Integer soLuong;


  
}
