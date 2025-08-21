/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Asus
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
public class PhieuGiamGia {
    private Integer id;
    private String ma;
    private String Ten ;
    private Date ngayTao;
    private Date NgayHet;
    private Boolean loaiGiam;
    private float giaTriGiamMax;
    private float hoaDonToiThieu;
    private int trangThai;

    @Override
    public String toString() {
        return this.Ten;
    }
    
    
}
