package com.furesky.base.utils;

import java.util.Random;

/**
 * 随机码工具类
 * 
 * @author jianda
 * @date 2018年1月20日
 */
public class RandomCode {

	// 默认随机码长度
	private static final int CODE_SIZE = 4;

	// 默认字符源
	private static final String CODE_SOURCE = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

	/**
	 * 获取随机随机码
	 * 
	 * @return code 随机码
	 * @date 2018年1月20日
	 */
	public static String get() {
		return get(CODE_SIZE);
	}

	/**
	 * 获取随机随机码
	 * 
	 * @param codeSize
	 *            随机码长度
	 * @return code 随机码
	 * @date 2018年1月20日
	 */
	public static String get(int codeSize) {
		return get(codeSize, CODE_SOURCE);
	}

	/**
	 * 使用指定字符源生成随机码
	 * 
	 * @param codeSize
	 *            随机码长度
	 * @param source
	 *            字符源
	 * @return code 随机码
	 * @date 2018年1月20日
	 */
	public static String get(int codeSize, String source) {
		if (source == null || source.length() == 0) {
			source = CODE_SOURCE;
		}
		int sourceSize = source.length();
		Random rand = new Random();
		StringBuilder code = new StringBuilder(codeSize);
		for (int i = 0; i < codeSize; i++) {
			code.append(source.charAt(rand.nextInt(sourceSize - 1)));
		}
		return code.toString();
	}
}
