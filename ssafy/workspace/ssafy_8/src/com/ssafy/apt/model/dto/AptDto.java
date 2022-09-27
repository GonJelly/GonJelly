package com.ssafy.apt.model.dto;

public class AptDto {

    private String Code;          // 일련번호
    private String LAWD_CD;     // 지역코드
    private String DEAL_YMD;    // 계약월
    private String Deal_Amount; // 거래금액
    private String regcode;     // 시군구코드
    private String Eubmyudong;   // 읍면동코드
    private String Dong;        // 법정동
    private String AptName;     // 건물명
    private String AreaExUse;   // 전용면적
    private String Jibun;       // 지번
    private String Floor;       // 층
    private String Build_Year;  // 건축년도
    private String Read_Name;   // 도로명
    private String Bonbun;      // 도로명건물본호코드
    private String Bubun;       // 도로명건물부번호코드

    public AptDto(String code, String LAWD_CD, String DEAL_YMD, String deal_Amount, String regcode, String eubmyudong, String dong, String aptName, String areaExUse, String jibun, String floor, String build_Year, String read_Name, String bonbun, String bubun) {
        Code = code;
        this.LAWD_CD = LAWD_CD;
        this.DEAL_YMD = DEAL_YMD;
        Deal_Amount = deal_Amount;
        this.regcode = regcode;
        Eubmyudong = eubmyudong;
        Dong = dong;
        AptName = aptName;
        AreaExUse = areaExUse;
        Jibun = jibun;
        Floor = floor;
        Build_Year = build_Year;
        Read_Name = read_Name;
        Bonbun = bonbun;
        Bubun = bubun;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getLAWD_CD() {
        return LAWD_CD;
    }

    public void setLAWD_CD(String LAWD_CD) {
        this.LAWD_CD = LAWD_CD;
    }

    public String getDEAL_YMD() {
        return DEAL_YMD;
    }

    public void setDEAL_YMD(String DEAL_YMD) {
        this.DEAL_YMD = DEAL_YMD;
    }

    public String getDeal_Amount() {
        return Deal_Amount;
    }

    public void setDeal_Amount(String deal_Amount) {
        Deal_Amount = deal_Amount;
    }

    public String getRegcode() {
        return regcode;
    }

    public void setRegcode(String regcode) {
        this.regcode = regcode;
    }

    public String getEubmyudong() {
        return Eubmyudong;
    }

    public void setEubmyudong(String eubmyudong) {
        Eubmyudong = eubmyudong;
    }

    public String getDong() {
        return Dong;
    }

    public void setDong(String dong) {
        Dong = dong;
    }

    public String getAptName() {
        return AptName;
    }

    public void setAptName(String aptName) {
        AptName = aptName;
    }

    public String getAreaExUse() {
        return AreaExUse;
    }

    public void setAreaExUse(String areaExUse) {
        AreaExUse = areaExUse;
    }

    public String getJibun() {
        return Jibun;
    }

    public void setJibun(String jibun) {
        Jibun = jibun;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getBuild_Year() {
        return Build_Year;
    }

    public void setBuild_Year(String build_Year) {
        Build_Year = build_Year;
    }

    public String getRead_Name() {
        return Read_Name;
    }

    public void setRead_Name(String read_Name) {
        Read_Name = read_Name;
    }

    public String getBonbun() {
        return Bonbun;
    }

    public void setBonbun(String bonbun) {
        Bonbun = bonbun;
    }

    public String getBubun() {
        return Bubun;
    }

    public void setBubun(String bubun) {
        Bubun = bubun;
    }

    @Override
    public String toString() {
        return "AptDto{" +
                "Code='" + Code + '\'' +
                ", LAWD_CD='" + LAWD_CD + '\'' +
                ", DEAL_YMD='" + DEAL_YMD + '\'' +
                ", Deal_Amount='" + Deal_Amount + '\'' +
                ", regcode='" + regcode + '\'' +
                ", Eubmyudong='" + Eubmyudong + '\'' +
                ", Dong='" + Dong + '\'' +
                ", AptName='" + AptName + '\'' +
                ", AreaExUse='" + AreaExUse + '\'' +
                ", Jibun='" + Jibun + '\'' +
                ", Floor='" + Floor + '\'' +
                ", Build_Year='" + Build_Year + '\'' +
                ", Read_Name='" + Read_Name + '\'' +
                ", Bonbun='" + Bonbun + '\'' +
                ", Bubun='" + Bubun + '\'' +
                '}';
    }
}
