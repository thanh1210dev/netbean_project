/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author laptop
 */
public class LichSuHoaDon {
    private Integer id;
    private String ma;
    private Date ngayDatHang;
    private boolean trangThai;
    private Integer idKhachHang;
    private Integer idNhanVien;
    private String maKhachHang;
    private String tenKhachHang;
    private String maNhanVien;
    private String tenNhanVien;
    private String HTTT;
    private Integer HoaDonID;
    private String tongTien;

    public LichSuHoaDon() {
    }

    public LichSuHoaDon(Integer id, String ma, Date ngayDatHang, boolean trangThai, Integer idKhachHang, Integer idNhanVien, String maKhachHang, String tenKhachHang, String maNhanVien, String tenNhanVien, String HTTT, Integer HoaDonID, String tongTien) {
        this.id = id;
        this.ma = ma;
        this.ngayDatHang = ngayDatHang;
        this.trangThai = trangThai;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.HTTT = HTTT;
        this.HoaDonID = HoaDonID;
        this.tongTien = tongTien;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Date getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(Date ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Integer getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Integer idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getHTTT() {
        return HTTT;
    }

    public void setHTTT(String HTTT) {
        this.HTTT = HTTT;
    }

    public Integer getHoaDonID() {
        return HoaDonID;
    }

    public void setHoaDonID(Integer HoaDonID) {
        this.HoaDonID = HoaDonID;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
    
    
}
