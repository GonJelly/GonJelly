package com.ssafy.apt.model.dto;

import java.util.Objects;

public class RoadDto {
	private String Road_code;
	private String Road_Seq;
	private String Sigungu_Code;
	private String Road_Bonbun;
	private String Road_Bubun;
	private String Road_Basement_Code;
	private String Road_Name;
	
	
	
	// ====== getter & setter ======
	public String getRoad_code() {
		return Road_code;
	}
	public void setRoad_code(String road_code) {
		Road_code = road_code;
	}
	public String getRoad_Seq() {
		return Road_Seq;
	}
	public void setRoad_Seq(String road_Seq) {
		Road_Seq = road_Seq;
	}
	public String getSigungu_Code() {
		return Sigungu_Code;
	}
	public void setSigungu_Code(String sigungu_Code) {
		Sigungu_Code = sigungu_Code;
	}
	public String getRoad_Bonbun() {
		return Road_Bonbun;
	}
	public void setRoad_Bonbun(String road_Bonbun) {
		Road_Bonbun = road_Bonbun;
	}
	public String getRoad_Bubun() {
		return Road_Bubun;
	}
	public void setRoad_Bubun(String road_Bubun) {
		Road_Bubun = road_Bubun;
	}
	public String getRoad_Basement_Code() {
		return Road_Basement_Code;
	}
	public void setRoad_Basement_Code(String road_Basement_Code) {
		Road_Basement_Code = road_Basement_Code;
	}
	public String getRoad_Name() {
		return Road_Name;
	}
	public void setRoad_Name(String road_Name) {
		Road_Name = road_Name;
	}

	public RoadDto() {}

	// ====== constructor ======
	public RoadDto(String road_code, String road_Seq, String sigungu_Code, String road_Bonbun, String road_Bubun,
				   String road_Basement_Code, String road_Name) {
		super();
		Road_code = road_code;
		Road_Seq = road_Seq;
		Sigungu_Code = sigungu_Code;
		Road_Bonbun = road_Bonbun;
		Road_Bubun = road_Bubun;
		Road_Basement_Code = road_Basement_Code;
		Road_Name = road_Name;
	}
	
	
	
	// ====== toString() ======
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoadDto [Road_code=");
		builder.append(Road_code);
		builder.append(", Road_Seq=");
		builder.append(Road_Seq);
		builder.append(", Sigungu_Code=");
		builder.append(Sigungu_Code);
		builder.append(", Road_Bonbun=");
		builder.append(Road_Bonbun);
		builder.append(", Road_Bubun=");
		builder.append(Road_Bubun);
		builder.append(", Road_Basement_Code=");
		builder.append(Road_Basement_Code);
		builder.append(", Road_Name=");
		builder.append(Road_Name);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RoadDto roadDto = (RoadDto) o;
		return Objects.equals(Road_code, roadDto.Road_code);
	}

	@Override
	public int hashCode() {
		return Objects.hash(Road_code);
	}
}
