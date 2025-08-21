package response;

import java.util.Date;
import lombok.Builder;
@Builder
public class SanPhamChiTietResponse {
    private int id;
    private String MaSP;
    private String tenNhaCungCap;
    private String mauSac;
    private String doCungDua;
    private String doCang;
    private int soLuong;
    private float donGia;
    private String moTa;
    private Date ngayTao;
    private String chieuDaiVot;
    private String diemCanBang;
    private String trongLuong;
    private String doBay;
    private String dangMatVot;
    private boolean trangThai;
    private String tenSP;

    // Constructor
    public SanPhamChiTietResponse(int id, String MaSP, String tenNhaCungCap, String mauSac, String doCungDua, 
                                  String doCang, int soLuong, float donGia, String moTa, Date ngayTao, 
                                  String chieuDaiVot, String diemCanBang, String trongLuong, String doBay, 
                                  String dangMatVot, boolean trangThai, String tenSP) {
        this.id = id;
        this.MaSP = MaSP;
        this.tenNhaCungCap = tenNhaCungCap;
        this.mauSac = mauSac;
        this.doCungDua = doCungDua;
        this.doCang = doCang;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.chieuDaiVot = chieuDaiVot;
        this.diemCanBang = diemCanBang;
        this.trongLuong = trongLuong;
        this.doBay = doBay;
        this.dangMatVot = dangMatVot;
        this.trangThai = trangThai;
        this.tenSP = tenSP;
    }

    // Getters and Setters (full list)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMaSP() { return MaSP; }
    public void setMaSP(String MaSP) { this.MaSP = MaSP; }

    public String getTenNhaCungCap() { return tenNhaCungCap; }
    public void setTenNhaCungCap(String maNhaCungCap) { this.tenNhaCungCap = tenNhaCungCap; }

    public String getMauSac() { return mauSac; }
    public void setMauSac(String mauSac) { this.mauSac = mauSac; }

    public String getDoCungDua() { return doCungDua; }
    public void setDoCungDua(String doCungDua) { this.doCungDua = doCungDua; }

    public String getDoCang() { return doCang; }
    public void setDoCang(String doCang) { this.doCang = doCang; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public float getDonGia() { return donGia; }
    public void setDonGia(float donGia) { this.donGia = donGia; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public Date getNgayTao() { return ngayTao; }
    public void setNgayTao(Date ngayTao) { this.ngayTao = ngayTao; }

    public String getChieuDaiVot() { return chieuDaiVot; }
    public void setChieuDaiVot(String chieuDaiVot) { this.chieuDaiVot = chieuDaiVot; }

    public String getDiemCanBang() { return diemCanBang; }
    public void setDiemCanBang(String diemCanBang) { this.diemCanBang = diemCanBang; }

    public String getTrongLuong() { return trongLuong; }
    public void setTrongLuong(String trongLuong) { this.trongLuong = trongLuong; }

    public String getDoBay() { return doBay; }
    public void setDoBay(String doBay) { this.doBay = doBay; }

    public String getDangMatVot() { return dangMatVot; }
    public void setDangMatVot(String dangMatVot) { this.dangMatVot = dangMatVot; }

    public boolean isTrangThai() { return trangThai; }
    public void setTrangThai(boolean trangThai) { this.trangThai = trangThai; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    @Override
    public String toString() {
        return "SanPhamChiTietResponse{" + "id=" + id + ", MaSP=" + MaSP + ", tenNhaCungCap=" + tenNhaCungCap + ", mauSac=" + mauSac + ", doCungDua=" + doCungDua + ", doCang=" + doCang + ", soLuong=" + soLuong + ", donGia=" + donGia + ", moTa=" + moTa + ", ngayTao=" + ngayTao + ", chieuDaiVot=" + chieuDaiVot + ", diemCanBang=" + diemCanBang + ", trongLuong=" + trongLuong + ", doBay=" + doBay + ", dangMatVot=" + dangMatVot + ", trangThai=" + trangThai + ", tenSP=" + tenSP + '}';
    }
    
    
}
