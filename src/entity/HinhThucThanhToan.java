/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

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
public class HinhThucThanhToan {
    
    private Integer id;
    private String HTTT;

    @Override
    public String toString() {
        return HTTT;
    }
    
    
}
