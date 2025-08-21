/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author NKHOA_01
 */
public class ChieuDaiVot {
    private int id;
    private String ten;
    private String maChieuDaiVot;

    public ChieuDaiVot() {
    }

    public ChieuDaiVot(int id, String ten, String maChieuDaiVot) {
        this.id = id;
        this.ten = ten;
        this.maChieuDaiVot = maChieuDaiVot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMaChieuDaiVot() {
        return maChieuDaiVot;
    }

    public void setMaChieuDaiVot(String maChieuDaiVot) {
        this.maChieuDaiVot = maChieuDaiVot;
    }

    @Override
    public String toString() {
        return ten;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChieuDaiVot other = (ChieuDaiVot) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ten, other.ten)) {
            return false;
        }
        return Objects.equals(this.maChieuDaiVot, other.maChieuDaiVot);
    }


   
}

