package com.ssafy.board.model;

public class BuildInfoDto extends BuildDeal {

    private String aptCode;             // 아파트 코드
    private String buildYear;           // 건축년도
    private String roadName;            // 도로명
    private String roadNameBonbun;      // 도로본번호
    private String roadNameBubun;       // 도로부번호
    private String roadNameSeq;         // 도로일련번호
    private String roadNameBasementCode;
    private String roadNameCode;        // 도로명코드
    private String address;             // 주소
    private String dong;                // 법률동
    private String bonbun;              // 본번호
    private String bubun;               // 부번호
    private String sigunguCode;         // 시코드
    private String eubmyundongCode;     // 면,읍,동 코드
    private String dongCode;            // 동코드
    private String landCode;
    private String apartmentName;       // 아파트명
    private String jibun;               // 지번
    private String lng;                 // 경도
    private String lat;                 // 위도

    private FileInfo img;               // 건물사진

    public String getAptCode() {
        return aptCode;
    }

    public void setAptCode(String aptCode) {
        this.aptCode = aptCode;
    }

    public String getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(String buildYear) {
        this.buildYear = buildYear;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getRoadNameBonbun() {
        return roadNameBonbun;
    }

    public void setRoadNameBonbun(String roadNameBonbun) {
        this.roadNameBonbun = roadNameBonbun;
    }

    public String getRoadNameBubun() {
        return roadNameBubun;
    }

    public void setRoadNameBubun(String roadNameBubun) {
        this.roadNameBubun = roadNameBubun;
    }

    public String getRoadNameSeq() {
        return roadNameSeq;
    }

    public void setRoadNameSeq(String roadNameSeq) {
        this.roadNameSeq = roadNameSeq;
    }

    public String getRoadNameBasementCode() {
        return roadNameBasementCode;
    }

    public void setRoadNameBasementCode(String roadNameBasementCode) {
        this.roadNameBasementCode = roadNameBasementCode;
    }

    public String getRoadNameCode() {
        return roadNameCode;
    }

    public void setRoadNameCode(String roadNameCode) {
        this.roadNameCode = roadNameCode;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }

    public String getBonbun() {
        return bonbun;
    }

    public void setBonbun(String bonbun) {
        this.bonbun = bonbun;
    }

    public String getBubun() {
        return bubun;
    }

    public void setBubun(String bubun) {
        this.bubun = bubun;
    }

    public String getSigunguCode() {
        return sigunguCode;
    }

    public void setSigunguCode(String sigunguCode) {
        this.sigunguCode = sigunguCode;
    }

    public String getEubmyundongCode() {
        return eubmyundongCode;
    }

    public void setEubmyundongCode(String eubmyundongCode) {
        this.eubmyundongCode = eubmyundongCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDongCode() {
        return dongCode;
    }

    public void setDongCode(String dongCode) {
        this.dongCode = dongCode;
    }

    public String getLandCode() {
        return landCode;
    }

    public void setLandCode(String landCode) {
        this.landCode = landCode;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getJibun() {
        return jibun;
    }

    public void setJibun(String jibun) {
        this.jibun = jibun;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public FileInfo getImg() {
        return img;
    }

    public void setImg(FileInfo img) {
        this.img = img;
    }
}
