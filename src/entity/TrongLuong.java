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
public class TrongLuong {
    private int id;
    private String ten;
    private String maTrongLuong;

    public TrongLuong() {
    }

    public TrongLuong(int id, String ten, String maTrongLuong) {
        this.id = id;
        this.ten = ten;
        this.maTrongLuong = maTrongLuong;
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

    public String getMaTrongLuong() {
        return maTrongLuong;
    }

    public void setMaTrongLuong(String maTrongLuong) {
        this.maTrongLuong = maTrongLuong;
    }

    @Override
    public String toString() {
        return ten;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final TrongLuong other = (TrongLuong) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ten, other.ten)) {
            return false;
        }
        return Objects.equals(this.maTrongLuong, other.maTrongLuong);
    }

    
}

