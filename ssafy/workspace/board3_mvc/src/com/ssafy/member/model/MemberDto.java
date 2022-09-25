package com.ssafy.member.model;

public class MemberDto {

	private String userId;
	private String userName;
	private String userPwd;
	private String emailId;
	private String emailDomain;
	private String joinDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("MemberDto{");
		sb.append("userId='").append(userId).append('\'');
		sb.append(", userName='").append(userName).append('\'');
		sb.append(", userPwd='").append(userPwd).append('\'');
		sb.append(", emailId='").append(emailId).append('\'');
		sb.append(", emailDomain='").append(emailDomain).append('\'');
		sb.append(", joinDate='").append(joinDate).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
