package com.furesky.base.utils;

import java.io.File;

/**
 * <p>系统路径</p>
 * 
 * @author jianda
 * @date 2017年7月28日
 */
public class SystemPath {
	
	/**
	 * 获取文件分隔符
	 *  
	 * <pre>
	 * Windows系统: "\"
	 * UNIX系统:    "/"
	 * </pre>
	 */
	public static String getSeparator() {
		return System.getProperty("file.separator");
	}
	
	/**
	 * 获取项目所在盘符
	 * 
	 * <p>D:\</p>
	 */
	public static String getDisk() {
		return new File("/").getAbsolutePath();
	}
	
	/**
	 * 获取项目根目录
	 * 
	 * <p>D:\Javaweb\webapps\myApp</p>
	 */
	public static String getProjectPath(){
		return System.getProperty("user.dir");
	}
	
	/**
	 * 默认的临时文件路径
	 * 
	 * <p>C:\Users\ADMIN~1.ADM\AppData\Local\Temp\</p>
	 */
	public static String getProjectTempPath() {
		return System.getProperty("java.io.tmpdir");
	}
	
	/**
	 * 获取当前应用生成class文件的系统路径
	 * 
	 * <p>D:\Javaweb\webapps\myApp\target\classes\</p>
	 */
	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		path = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
		return path.replaceAll("/", getSeparator() + getSeparator());
	}

    /**
     * 测试
     */
    public static void main(String[] args) {
    	System.out.println(getSeparator());
        System.out.println(getDisk());
        System.out.println(getProjectPath());
        System.out.println(getClassPath());
        System.out.println(getProjectTempPath());
    }
}
