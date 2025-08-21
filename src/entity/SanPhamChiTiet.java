/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import lombok.Builder;
import response.SanPhamChiTietResponse;

@Builder
/**
 *
 * @author NKHOA_01
 */

public class SanPhamChiTiet {
    private int id;
    private int idSp;
    private int maNhaCungCap;
    private int maMau;
    private int maDoCungDua;
    private int maDoCang;
    private int soLuong;
    private float donGia;
    private String moTa;
    private Date ngayTao;
    private int maCdVot;
    private int maDiemCanBang;
    private int maTrongLuong;
    private int maDoBay;
    private int maDangMatVot;
    private boolean trangThai;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(int id, int idSp, int maNhaCungCap, int maMau, int maDoCungDua, int maDoCang, int soLuong, float donGia, String moTa, Date ngayTao, int maCdVot, int maDiemCanBang, int maTrongLuong, int maDoBay, int maDangMatVot, boolean trangThai) {
        this.id = id;
        this.idSp = idSp;
        this.maNhaCungCap = maNhaCungCap;
        this.maMau = maMau;
        this.maDoCungDua = maDoCungDua;
        this.maDoCang = maDoCang;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.maCdVot = maCdVot;
        this.maDiemCanBang = maDiemCanBang;
        this.maTrongLuong = maTrongLuong;
        this.maDoBay = maDoBay;
        this.maDangMatVot = maDangMatVot;
        this.trangThai = trangThai;
    }
    
    public SanPhamChiTiet(int idSp, int maNhaCungCap, int maMau, int maDoCungDua, int maDoCang, int soLuong, float donGia, String moTa, Date ngayTao, int maCdVot, int maDiemCanBang, int maTrongLuong, int maDoBay, int maDangMatVot, boolean trangThai) {
        this.idSp = idSp;
        this.maNhaCungCap = maNhaCungCap;
        this.maMau = maMau;
        this.maDoCungDua = maDoCungDua;
        this.maDoCang = maDoCang;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.maCdVot = maCdVot;
        this.maDiemCanBang = maDiemCanBang;
        this.maTrongLuong = maTrongLuong;
        this.maDoBay = maDoBay;
        this.maDangMatVot = maDangMatVot;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSp() {
        return idSp;
    }

    public void setIdSp(int idSp) {
        this.idSp = idSp;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public int getMaMau() {
        return maMau;
    }

    public void setMaMau(int maMau) {
        this.maMau = maMau;
    }

    public int getMaDoCungDua() {
        return maDoCungDua;
    }

    public void setMaDoCungDua(int maDoCungDua) {
        this.maDoCungDua = maDoCungDua;
    }

    public int getMaDoCang() {
        return maDoCang;
    }

    public void setMaDoCang(int maDoCang) {
        this.maDoCang = maDoCang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
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

    public int getMaCdVot() {
        return maCdVot;
    }

    public void setMaCdVot(int maCdVot) {
        this.maCdVot = maCdVot;
    }

    public int getMaDiemCanBang() {
        return maDiemCanBang;
    }

    public void setMaDiemCanBang(int maDiemCanBang) {
        this.maDiemCanBang = maDiemCanBang;
    }

    public int getMaTrongLuong() {
        return maTrongLuong;
    }

    public void setMaTrongLuong(int maTrongLuong) {
        this.maTrongLuong = maTrongLuong;
    }

    public int getMaDoBay() {
        return maDoBay;
    }

    public void setMaDoBay(int maDoBay) {
        this.maDoBay = maDoBay;
    }

    public int getMaDangMatVot() {
        return maDangMatVot;
    }

    public void setMaDangMatVot(int maDangMatVot) {
        this.maDangMatVot = maDangMatVot;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPhamChiTiet{" + "id=" + id + ", idSp=" + idSp + ", maNhaCungCap=" + maNhaCungCap + ", maMau=" + maMau + ", maDoCungDua=" + maDoCungDua + ", maDoCang=" + maDoCang + ", soLuong=" + soLuong + ", donGia=" + donGia + ", moTa=" + moTa + ", ngayTao=" + ngayTao + ", maCdVot=" + maCdVot + ", maDiemCanBang=" + maDiemCanBang + ", maTrongLuong=" + maTrongLuong + ", maDoBay=" + maDoBay + ", maDangMatVot=" + maDangMatVot + ", trangThai=" + trangThai + '}';
    }

    
    
}
