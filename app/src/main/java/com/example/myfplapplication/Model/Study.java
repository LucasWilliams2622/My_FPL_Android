package com.example.myfplapplication.Model;

public class Study {
    public  String mon_hoc_id, ten_mon_hoc, ca, dia_diem, ngay_hoc;

    public Study(String mon_hoc_id, String ten_mon_hoc, String ca, String dia_diem, String ngay_hoc) {
        this.mon_hoc_id = mon_hoc_id;
        this.ten_mon_hoc = ten_mon_hoc;
        this.ca = ca;
        this.dia_diem = dia_diem;
        this.ngay_hoc = ngay_hoc;
    }

    public String getMon_hoc_id() {
        return mon_hoc_id;
    }

    public void setMon_hoc_id(String mon_hoc_id) {
        this.mon_hoc_id = mon_hoc_id;
    }

    public String getTen_mon_hoc() {
        return ten_mon_hoc;
    }

    public void setTen_mon_hoc(String ten_mon_hoc) {
        this.ten_mon_hoc = ten_mon_hoc;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public String getDia_diem() {
        return dia_diem;
    }

    public void setDia_diem(String dia_diem) {
        this.dia_diem = dia_diem;
    }

    public String getNgay_hoc() {
        return ngay_hoc;
    }

    public void setNgay_hoc(String ngay_hoc) {
        this.ngay_hoc = ngay_hoc;
    }
}
