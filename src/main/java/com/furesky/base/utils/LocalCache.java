package com.furesky.base.utils;

import java.util.HashMap;
import java.util.Map;

public class LocalCache {
	private static Map<String,String> cacheMap = new HashMap<>();
	private LocalCache() {
		
	}
	public static Map<String,String> getLocalCache(){
		return cacheMap;
	}	
}
