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
public class DoCang {
    private int id;
    private String ten;
    private String maDoCang;

    public DoCang() {
    }

    public DoCang(int id, String ten, String maDoCang) {
        this.id = id;
        this.ten = ten;
        this.maDoCang = maDoCang;
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

    public String getMaDoCang() {
        return maDoCang;
    }

    public void setMaDoCang(String maDoCang) {
        this.maDoCang = maDoCang;
    }

    @Override
    public String toString() {
        return ten;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final DoCang other = (DoCang) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ten, other.ten)) {
            return false;
        }
        return Objects.equals(this.maDoCang, other.maDoCang);
    }

     
    
}

