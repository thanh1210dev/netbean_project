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
public class MauSac {
    private int id;
    private String tenMau;
    private String maMauSac;

    public MauSac() {
    }

    public MauSac(int id, String tenMau, String maMauSac) {
        this.id = id;
        this.tenMau = tenMau;
        this.maMauSac = maMauSac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public String getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    @Override
    public String toString() {
        return tenMau;
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
        final MauSac other = (MauSac) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.tenMau, other.tenMau)) {
            return false;
        }
        return Objects.equals(this.maMauSac, other.maMauSac);
    }
    
    
    

}

