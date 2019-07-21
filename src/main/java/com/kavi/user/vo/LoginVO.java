package com.kavi.user.vo;

import java.io.Serializable;

public class LoginVO implements Serializable {

	private static final long serialVersionUID = -6364697576145614559L;

	private String userName;

	private String pwd;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
