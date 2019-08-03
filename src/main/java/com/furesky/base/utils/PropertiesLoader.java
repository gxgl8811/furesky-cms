package com.furesky.base.utils;
  
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;  
  
/** 
 * <p>Properties文件载入工具类</p>
 * 可载入多个properties文件,<br/> 
 * 同名属性，之后载入的值将会覆盖之前的值。<br/> 
 * 
 * @author jianda
 * @date 2017年7月29日
 */
public class PropertiesLoader {  

	private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);  
  
    private static ResourceLoader resourceLoader = new DefaultResourceLoader();  
  
    private final Properties properties;  
  
    public PropertiesLoader(String... resourcesPaths) {  
        properties = loadProperties(resourcesPaths);  
    }  
    
    /**
     * 获取配置文件对象
     */
    public Properties getProperties() {  
        return properties;  
    }  
   
    /** 
     * <p>获取属性值</p>
     * <p>优先：系统自身属性值 </p>
     */  
    public String getProperty(String key) {  
        String systemProperty = System.getProperty(key);  
        if (systemProperty != null) {  
            return systemProperty;  
        }  
        return properties.getProperty(key);  
    }  
    
    /** 
     * 载入多个文件, 文件路径使用Spring Resource格式. 
     */  
    private Properties loadProperties(String... resourcesPaths) {  
    	Properties props = new Properties();  
  
        for (String location : resourcesPaths) { 
        	Resource resource = resourceLoader.getResource(location); 
            try (InputStream is = resource.getInputStream()){  
                props.load(is);  
            } catch (IOException ex) {  
                logger.info("加载配置文件失败:" + location); 
                logger.info("错误信息:"+ex.getMessage());
            } 
        }  
        return props;  
    }  
}  
