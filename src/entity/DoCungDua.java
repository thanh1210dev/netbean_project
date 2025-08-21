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
public class DoCungDua {
    private int id;
    private String ten;
    private String maDoCungDua;

    public DoCungDua() {
    }

    public DoCungDua(int id, String ten, String maDoCungDua) {
        this.id = id;
        this.ten = ten;
        this.maDoCungDua = maDoCungDua;
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

    public String getMaDoCungDua() {
        return maDoCungDua;
    }

    public void setMaDoCungDua(String maDoCungDua) {
        this.maDoCungDua = maDoCungDua;
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
        final DoCungDua other = (DoCungDua) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ten, other.ten)) {
            return false;
        }
        return Objects.equals(this.maDoCungDua, other.maDoCungDua);
    }

    
}

