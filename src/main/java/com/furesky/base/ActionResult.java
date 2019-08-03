package com.furesky.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 结果集
 * 
 * @author jianda
 * @date 2018年1月20日
 */
public final class ActionResult implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * true成功；false失败
	 */
	private boolean successful;

	/**
	 * 0表示成功，其他则表示失败
	 */
	private int code;

	/**
	 * 返回信息
	 */
	private String message;

	/**
	 * 返回数据
	 */
	private Object data;

	/**
	 * 其他参数
	 */
	private Map<String, Object> params = new HashMap<String, Object>();

	/**
	 * 不可实例化
	 */
	private ActionResult() {

	}

	/**
	 * 获取成功结果集
	 * @date 2018年1月20日
	 */
	public static ActionResult getSuccess() {
		return instance(true, 0, "请求成功", null);
	}

	/**
	 * 获取成功结果集
	 * @param message 成功信息
	 * @date 2018年1月20日
	 */
	public static ActionResult getSuccess(String message) {
		return instance(true, 0, message, null);
	}

	/**
	 * 获取成功结果集
	 * @param data 数据
	 * @date 2018年1月20日
	 */
	public static ActionResult getSuccess(Object data) {
		return instance(true, 0, "请求成功", data);
	}

	/**
	 * 获取成功结果集
	 * @param message 成功信息
	 * @param data 数据
	 * @date 2018年1月20日
	 */
	public static ActionResult getSuccess(String message, Object data) {
		return instance(true, 0, message, data);
	}

	/**
	 * 获取失败结果集
	 * @date 2018年1月20日
	 */
	public static ActionResult getError() {
		return instance(false, 1000, "请求失败", null);
	}

	/**
	 * 获取失败结果集
	 * @param message 错误信息
	 * @date 2018年1月20日
	 */
	public static ActionResult getError(int code) {
		return instance(false, code, "请求失败", null);
	}
	
	/**
	 * 获取失败结果集
	 * @param message 错误信息
	 * @date 2018年1月20日
	 */
	public static ActionResult getError(String message) {
		return instance(false, 1000, message, null);
	}

	/**
	 * 获取失败结果集
	 * @param code 错误码
	 * @param message 错误信息
	 * @date 2018年1月20日
	 */
	public static ActionResult getError(int code, String message) {
		return instance(false, code, message, null);
	}
	
	/**
	 * 获取失败结果集
	 * @param message 错误信息
	 * @param data 数据
	 * @date 2018年1月20日
	 */
	public static ActionResult getError(String message, Object data) {
		return instance(false, 1000, message, data);
	}

	/**
	 * 获取失败结果集
	 * @param code 错误码
	 * @param message 错误信息
	 * @param data 数据
	 * @date 2018年1月20日
	 */
	public static ActionResult getError(int code, String message, Object data) {
		return instance(false, code, message, data);
	}

	/**
	 * 获取结果集
	 * @param successful 结果：true成功/false失败
	 * @param code 编码：0成功/其他失败
	 * @param message 信息
	 * @param data 数据
	 * @date 2018年1月20日
	 */
	public static ActionResult instance(boolean successful, int code, String message, Object data) {
		ActionResult result = new ActionResult();
		result.successful = successful;
		result.code = code;
		result.message = message;
		result.data = data;
		return result;
	}

	public boolean getSuccessful() {
		return successful;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public ActionResult setData(Object data) {
		this.data = data;
		return this;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public ActionResult addParams(String key, Object value) {
		if (this.params == null) {
			this.params = new HashMap<>();
		}
		this.params.put(key, value);
		return this;
	}

}
