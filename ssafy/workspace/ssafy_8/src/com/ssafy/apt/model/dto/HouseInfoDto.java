package com.ssafy.apt.model.dto;

public class HouseInfoDto {
	
	private int house_idx;
	private String house_Code;
	private String house_Name;
	private String build_Year;
	private String Deal_Year;
	private String Deal_Month;
	private String Deal_Day;
	private String Deal_Amount;
	private String Use_Area;
	private String Floor;
	private String Sigungu_code;
	private String Eubmyundong_code;
	private String Road_code;
	private String Bonbun;
	private String Bubun;
	private String Jibun;
	
	
	
	// ====== getter & setter ======
	public int getHouse_idx() {
		return house_idx;
	}
	public void setHouse_idx(int house_idx) {
		this.house_idx = house_idx;
	}
	public String getHouse_Code() {
		return house_Code;
	}
	public void setHouse_Code(String house_Code) {
		this.house_Code = house_Code;
	}
	public String getHouse_Name() {
		return house_Name;
	}
	public void setHouse_Name(String house_Name) {
		this.house_Name = house_Name;
	}
	public String getBuild_Year() {
		return build_Year;
	}
	public void setBuild_Year(String build_Year) {
		this.build_Year = build_Year;
	}
	public String getDeal_Year() {
		return Deal_Year;
	}
	public void setDeal_Year(String deal_Year) {
		Deal_Year = deal_Year;
	}
	public String getDeal_Month() {
		return Deal_Month;
	}
	public void setDeal_Month(String deal_Month) {
		Deal_Month = deal_Month;
	}
	public String getDeal_Day() {
		return Deal_Day;
	}
	public void setDeal_Day(String deal_Day) {
		Deal_Day = deal_Day;
	}
	public String getUse_Area() {
		return Use_Area;
	}
	public void setUse_Area(String use_Area) {
		Use_Area = use_Area;
	}
	public String getFloor() {
		return Floor;
	}
	public void setFloor(String floor) {
		Floor = floor;
	}
	public String getSigungu_code() {
		return Sigungu_code;
	}
	public void setSigungu_code(String sigungu_code) {
		Sigungu_code = sigungu_code;
	}
	public String getEubmyundong_code() {
		return Eubmyundong_code;
	}
	public void setEubmyundong_code(String eubmyundong_code) {
		Eubmyundong_code = eubmyundong_code;
	}
	public String getRoad_code() {
		return Road_code;
	}
	public void setRoad_code(String road_code) {
		Road_code = road_code;
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
	public String getJibun() {
		return Jibun;
	}
	public void setJibun(String jibun) {
		Jibun = jibun;
	}
	public String getDeal_Amount() { return Deal_Amount; }
	public void setDeal_Amount(String deal_Amount) { Deal_Amount = deal_Amount; }

	// ====== constructor ======
	public HouseInfoDto(String house_Code, String house_Name, String build_Year, String deal_Year,
			String deal_Month, String deal_Day,String deal_Amount, String use_Area, String floor, String sigungu_code,
			String eubmyundong_code, String road_code, String bonbun, String bubun, String jibun) {
		this.house_Code = house_Code;
		this.house_Name = house_Name;
		this.build_Year = build_Year;
		Deal_Year = deal_Year;
		Deal_Month = deal_Month;
		Deal_Day = deal_Day;
		Deal_Amount = deal_Amount;
		Use_Area = use_Area;
		Floor = floor;
		Sigungu_code = sigungu_code;
		Eubmyundong_code = eubmyundong_code;
		Road_code = road_code;
		Bonbun = bonbun;
		Bubun = bubun;
		Jibun = jibun;
	}
	
	
	
	
	// ====== toString() ======
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoadDto [house_idx=");
		builder.append(house_idx);
		builder.append(", house_Code=");
		builder.append(house_Code);
		builder.append(", house_Name=");
		builder.append(house_Name);
		builder.append(", build_Year=");
		builder.append(build_Year);
		builder.append(", Deal_Year=");
		builder.append(Deal_Year);
		builder.append(", Deal_Month=");
		builder.append(Deal_Month);
		builder.append(", Deal_Day=");
		builder.append(Deal_Day);
		builder.append(", Use_Area=");
		builder.append(Use_Area);
		builder.append(", Floor=");
		builder.append(Floor);
		builder.append(", Sigungu_code=");
		builder.append(Sigungu_code);
		builder.append(", Eubmyundong_code=");
		builder.append(Eubmyundong_code);
		builder.append(", Road_code=");
		builder.append(Road_code);
		builder.append(", Bonbun=");
		builder.append(Bonbun);
		builder.append(", Bubun=");
		builder.append(Bubun);
		builder.append(", Jibun=");
		builder.append(Jibun);
		builder.append("]");
		return builder.toString();
	}
}
