/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

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
/**s
 *
 * @author Admin
 */
public class HoaDonCT_Response {

    private Integer id;

    private String maSP;

    private String tenSP;

    private Integer soLuong;
    
    private double donGia;

    private Double thanhTien;

    private Integer hoaDonID;

    private Integer ctspID;
    
    private String tenMau;
    
    private String doCang;
    
    private String CDVot;
    
    

}
