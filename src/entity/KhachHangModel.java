/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;



/**
 *
 * @author laptop
 */

public class KhachHangModel {
    private Integer id;
    private String maKH;
    private String hoTen;
    private String diaChi;
    private String sdt;
    private boolean gioiTinh;
    private boolean trangThai;
    private String ghiChu;

    public KhachHangModel() {
    }

    public KhachHangModel(String maKH, String hoTen, String diaChi, String sdt, boolean gioiTinh, boolean trangThai, String ghiChu) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

   public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    public Object[] toDataRow(){
        return new Object[]{this.getMaKH(),this.getHoTen(),this.getDiaChi()
                ,this.getSdt(),this.getGioiTinh()?"Nam":"Nữ",this.getTrangThai()?"Ngừng hoạt động":"Đang hoạt động",this.getGhiChu()};
    }
}
