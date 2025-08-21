/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.security.SecureRandom;

/**
 *
 * @author laptop
 */
public class TaoMatKhau {
     private static final String kyTu = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int doDaiMatKhau = 10;

    public static String taoMatKhauNgauNhien() {
        SecureRandom random = new SecureRandom();
        StringBuilder matKhau = new StringBuilder(doDaiMatKhau);

        for (int i = 0; i < doDaiMatKhau; i++) {
            int index = random.nextInt(kyTu.length());
            matKhau.append(kyTu.charAt(index));
        }

        return matKhau.toString();
    }
}
