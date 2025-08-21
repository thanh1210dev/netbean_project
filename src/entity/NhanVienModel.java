/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import javax.mail.MessagingException;

public class NhanVienModel {
    private Integer id;
    private String maNV;
    private String hoTen;
    private boolean gioiTinh;
    private String sdt;
    private String email;
    private boolean vaiTro;
    private boolean trangThai;
    private String matKhau;

    public NhanVienModel() {}

    public NhanVienModel(Integer id, String maNV, String hoTen, boolean gioiTinh, String sdt, String email, boolean vaiTro, boolean trangThai, String matKhau) {
        this.id = id;
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.email = email;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
        this.matKhau = matKhau;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

//    public Object[] toDataRow(){
//        return new Object[]{
//            this.getId(),
//            this.getMaNV(),
//            this.getHoTen(),
//            this.getGioiTinh() ? "Nam" : "Nữ",
//            this.getSdt(),
//            this.getEmail(),
//            this.getVaiTro() ? "Quản lý" : "Nhân viên",
//            this.getTrangThai() ? "Đang làm" : "Nghỉ việc"
//        };
//    }

    public void guiEmail(String emailNhan, String tieuDe, String noiDung) throws MessagingException {
        SendEmail.guiEmail(emailNhan, tieuDe, noiDung);
    }
}
