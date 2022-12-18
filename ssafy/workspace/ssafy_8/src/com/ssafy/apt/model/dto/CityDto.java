package com.ssafy.apt.model.dto;

public class CityDto {

    private String sigungu_code;
    private String sido;
    private String gugun;

    public String getSigungu_code() {
        return sigungu_code;
    }

    public void setSigungu_code(String sigungu_code) {
        this.sigungu_code = sigungu_code;
    }

    public String getSido() {
        return sido;
    }

    public void setSido(String sido) {
        this.sido = sido;
    }

    public String getGugun() {
        return gugun;
    }

    public void setGugun(String gugun) {
        this.gugun = gugun;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CityDto{");
        sb.append("sigungu_code='").append(sigungu_code).append('\'');
        sb.append(", sido='").append(sido).append('\'');
        sb.append(", gugun='").append(gugun).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
