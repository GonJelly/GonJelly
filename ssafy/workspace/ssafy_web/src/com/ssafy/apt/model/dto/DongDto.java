package com.ssafy.apt.model.dto;

public class DongDto {

    private String Eubmyundong_code;
    private String Sigungu_code;
    private String Dong;

    public String getEubmyundong_code() {
        return Eubmyundong_code;
    }

    public void setEubmyundong_code(String eubmyundong_code) {
        Eubmyundong_code = eubmyundong_code;
    }

    public String getSigungu_code() {
        return Sigungu_code;
    }

    public void setSigungu_code(String sigungu_code) {
        Sigungu_code = sigungu_code;
    }

    public String getDong() {
        return Dong;
    }

    public void setDong(String dong) {
        Dong = dong;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DongDto{");
        sb.append("Eubmyundong_code='").append(Eubmyundong_code).append('\'');
        sb.append(", Sigungu_code='").append(Sigungu_code).append('\'');
        sb.append(", Dong='").append(Dong).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
