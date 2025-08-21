/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import entity.NhanVienModel;

/**
 *
 * @author Trong Phu
 */
public class XLogin {
    public static NhanVienModel user = null;
    
   public static void dangXuat(){
        XLogin.user = null;
    }
   public static boolean daDangNhap(){
      return XLogin.user != null;
   }
   public static boolean trangThaiTaiKhoan(){
       return "Đang làm".equals(XLogin.user.isTrangThai());
   }
    
    public static boolean phanQuyen(){
        return XLogin.daDangNhap() && XLogin.user.isVaiTro()== true;
    }
    
}
