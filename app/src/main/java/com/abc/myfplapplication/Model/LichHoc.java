package com.abc.myfplapplication.Model;

import java.util.Date;

public class LichHoc {
//    "id": 14,
//    "mon_hoc_id": "SYB3012",
//    "ca": "1",
//    "dia_diem": "Phan Mem Quang Trung",
//    "ngay_hoc": "2023-07-26",
//    "lop_hoc": "MD18707",
//    "ten_mon_hoc": "Khởi sự doanh nghiệp"
    public String id;
    public String mon_hoc_id;
    public String ca;
    public String dia_diem;
    public String ngay_hoc;
    public String lop_hoc;
    public String ten_mon_hoc;

    public String getCaHoc() {
        switch (ca) {
            case "1":
                return "Ca 1 - 07:30 - 09:30";
            case "2":
                return "Ca 2 - 09:45 - 11:45";
            case "3":
                return "Ca 3 - 13:30 - 15:30";
            case "4":
                return "Ca 4 - 15:30 - 17:30";
            case "5":
                return "Ca 5 - 17:30 - 19:30";
            case "6":
                return "Ca 6 - 19:30 - 21:30";
        }
        return "";
    }

    public String getCaHocTime() {
        switch (ca) {
            case "1":
                return "07:30 - 09:30";
            case "2":
                return "09:45 - 11:45";
            case "3":
                return "13:30 - 15:30";
            case "4":
                return "15:30 - 17:30";
            case "5":
                return "17:30 - 19:30";
            case "6":
                return "19:30 - 21:30";
        }
        return "";
    }
}
