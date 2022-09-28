package com.ssafy.apt.model.dto;

public class LikeDto {
	private String memberId;
	private String Eubmyundong_code;
	private String Sigungu_code;
	
	
	// ====== getter & setter ======
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
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

	
	// ====== constructor ======
	public LikeDto(String memberId, String eubmyundong_code, String sigungu_code) {
		super();
		this.memberId = memberId;
		Eubmyundong_code = eubmyundong_code;
		Sigungu_code = sigungu_code;
	}

	
	// ====== getter & setter ======
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Like [memberId=");
		builder.append(memberId);
		builder.append(", Eubmyundong_code=");
		builder.append(Eubmyundong_code);
		builder.append(", Sigungu_code=");
		builder.append(Sigungu_code);
		builder.append("]");
		return builder.toString();
	}
}
