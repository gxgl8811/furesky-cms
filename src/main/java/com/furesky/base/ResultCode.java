package com.furesky.base;

public enum ResultCode {
	ERROR("0","请求失败"),
	SUCCESS("1","请求成功");
	
	private String code;
	private String msg;
	
	private ResultCode(String code,String msg) {
		this.code=code;
		this.msg=msg;
	}
	
	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
