/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


import java.util.Date;
import lombok.Builder;

@Builder
/**
 *
 * @author NKHOA_01
 */
public class SanPham {
    private int id;
    private String maSP;
    private String tenSP;
    private String moTa;
    private Date ngayTao;
    private boolean trangThai;
    private float donGia;
    private int soLuong;

    public SanPham() {
    }

    public SanPham(int id, String maSP, String tenSP, String moTa, Date ngayTao, boolean trangThai, float donGia, int soLuong) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public float getDonGia() { 
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    
}
