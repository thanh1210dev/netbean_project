/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonChiTiet {
    private Integer idHD;
    
    private Integer idSPCT;
    
    private Integer soLuong;
    
    private Double thanhTien;
    
    private Integer trangThai;
    
    
    
}
