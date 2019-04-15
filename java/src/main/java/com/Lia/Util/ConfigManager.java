package com.Lia.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigManager {
	private static ConfigManager configManager;
	private Properties properties;

	private ConfigManager() {       //构造方法。private，外部类不可以直接创建调用构造方法new一个CongigManager对象
		String configFile = "Database.properties";//配置文件名
		InputStream in  = ConfigManager.class.getClassLoader().getResourceAsStream(configFile);//类加载器
		properties = new Properties();   
		try {
			properties.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ConfigManager getInstance() {
		if(configManager==null) {
			configManager = new ConfigManager();
		}
		return configManager;
	}
	
	
	public String getString(String key) {
		return properties.getProperty(key);
	}
	
	
}
