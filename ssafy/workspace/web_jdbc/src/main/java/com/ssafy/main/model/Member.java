package com.ssafy.main.model;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private String userid;
    private String userPw;
    private String name;
    private String email;
    private List<String> following = new ArrayList<>(); // 팔로우한 사람들

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("member{");
        sb.append("userid='").append(userid).append('\'');
        sb.append(", userPw='").append(userPw).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
