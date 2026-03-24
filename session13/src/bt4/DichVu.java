// File: DichVu.java
package bt4;

import java.sql.Timestamp;

public class DichVu {
    private int maDichVu;
    private String tenDichVu;
    private String loaiDichVu;
    private Timestamp thoiGianSuDung;

    // getters, setters ...

    public int getMaDichVu() {

        return maDichVu;
    }

    public void setMaDichVu(int maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public String getLoaiDichVu() {
        return loaiDichVu;
    }

    public void setLoaiDichVu(String loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
    }

    public Timestamp getThoiGianSuDung() {
        return thoiGianSuDung;
    }

    public void setThoiGianSuDung(Timestamp thoiGianSuDung) {
        this.thoiGianSuDung = thoiGianSuDung;
    }
}
