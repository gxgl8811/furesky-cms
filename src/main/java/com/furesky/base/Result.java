package com.furesky.base;

import java.io.Serializable;
/**
 * 结果集
 * 
 * @author jiandax
 * @date 2019年11月4日
 */
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 5331508219478611943L;
	
	private String code;
	private String message;
	private T data;

	public Result(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccessful() {
		return ResultCode.SUCCESS.getCode().equals(this.getCode());
	}

	public static <T> Result<T> success() {
		return success(null);
	}

	public static <T> Result<T> success(T data) {
		return instance(ResultCode.SUCCESS, data);
	}

	public static <T> Result<T> error() {
		return instance(ResultCode.ERROR,null);
	}

	public static <T> Result<T> error(String message) {
		return error(ResultCode.ERROR.getCode(), message);
	}

	public static <T> Result<T> error(String code, String message) {
		return new Result<>(code, message, null);
	}
	
	public static <T> Result<T> instance(ResultCode msgCode, T data) {
		return new Result<>(msgCode.getCode(),msgCode.getMsg(),data);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
