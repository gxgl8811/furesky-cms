package com.furesky.base.utils;

import java.util.HashMap;
import java.util.Map;

public class LocalCache {
	private static Map<String,String> cacheMap = new HashMap<>();
	static {
		cacheMap.put("className", "JavaSE");
	}
	
	public static String get(String key) {
		return cacheMap.get(key);
	}
	public static void put(String key,String value) {
		cacheMap.put(key, value);
	}
	
}
