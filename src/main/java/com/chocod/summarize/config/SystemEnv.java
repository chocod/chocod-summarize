package com.chocod.summarize.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Backend系统运行环境信息
 */
public final class SystemEnv {

	private static final Logger logger = LoggerFactory.getLogger(SystemEnv.class);

	private static final Properties systemProperties = new Properties();

	private SystemEnv(){}
	
	public static void addProperties(Properties properties) {
		if (properties != null) {
			for (Object key : properties.keySet()) {
				if (key != null) {
					if (systemProperties.keySet().contains(key)) {
						logger.error("系统Property配置项 {} 重复", key);
					}
					systemProperties.put(key, properties.get(key));
				}
			}
		}
	}
	
	public static void addProperty(String key ,String value){
		if (key != null) {
			if (systemProperties.keySet().contains(key)) {
				logger.error("系统Property配置项 {} 重复", key);
			}
			systemProperties.put(key, value);
		}
	}

	public static String getProperty(String key) {
		return systemProperties.getProperty(key);
	}
	
	public static String getProperty(String key,String defaultValue){
		String prop = systemProperties.getProperty(key);
		if(StringUtils.isBlank(prop)){
			prop = defaultValue;
		}
		return prop;
	}
	
	public static void setGroupId(String groupId) {
		addProperty("project.groupId", groupId);
	}
	public static String getGroupId(){
		return getProperty("project.groupId");
	}
	
	public static void setArtifactId(String artifactId) {
		addProperty("project.artifactId", artifactId);
	}
	public static String getArtifactId(){
		return getProperty("project.artifactId");
	}
	
	public static void setVersion(String version) {
		addProperty("project.version", version);
	}
	public static String getVersion(){
		return getProperty("project.version");
	}
	
	public static void setBuildTime(String buildTime) {
		addProperty("project.buildTime", buildTime);
	}
	public static String getBuildTime(){
		return getProperty("project.buildTime");
	}
	
	/**
	 * 当前环境
	 * 	fn.env
	 * 		local dev beta preview online
	 */
	private static String env;
	
	public static final String LOCAL = "local";
	public static final String DEV = "dev";
	public static final String BETA = "beta";
	public static final String PREVIEW = "preview";
	public static final String ONLINE = "online";
	
	public static void setCurrentEnv(String envVal){
		if(LOCAL.equalsIgnoreCase(envVal)){
			env =  LOCAL;
		}else if(DEV.equalsIgnoreCase(envVal)){
			env =  DEV;
		}else if(BETA.equalsIgnoreCase(envVal)){
			env =  BETA;
		}else if(PREVIEW.equalsIgnoreCase(envVal)){
			env = PREVIEW;
		}else if(ONLINE.equalsIgnoreCase(envVal)){
			env = ONLINE;
		}else{
			env = (envVal==null?ONLINE:envVal);
		}
	}
	
	public static String getCurrentEnv(){
		if(env == null){
			setCurrentEnv(systemProperties.getProperty("fn.env"));
		}
		return env;
	}
	
	
	public static boolean isEnv(String env){
		if(env==null){
			env = ONLINE;
		}
		return env.equalsIgnoreCase(getCurrentEnv());
	}
	
	public static boolean isLocalEnv(){
		return isEnv(LOCAL);
	}
	
	public static boolean isDevEnv(){
		return isEnv(DEV);
	}
	
	public static boolean isBetaEnv(){
		return isEnv(BETA);
	}
	
	public static boolean isPreviewEnv(){
		return isEnv(PREVIEW);
	}
	
	public static boolean isOnlineEnv(){
		return isEnv(ONLINE);
	}

	public static List<String>  getKeys(){
		List<String> keys = new ArrayList<String>(systemProperties.stringPropertyNames());
		Collections.sort(keys , new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		return keys;
	}
	
	public static List<String>  getKeys(String prefix){
		List<String> keys = new ArrayList<String>();
		for(String name : systemProperties.stringPropertyNames()){
			if(name.startsWith(prefix)){
				keys.add(name);
			}
		}
		Collections.sort(keys , new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		return keys;
	}
	
	public static List<String>  getPropertyByPrefix(String prefix){
		List<String> values = new ArrayList<String>();
		for(String name : systemProperties.stringPropertyNames()){
			if(name.startsWith(prefix)){
				values.add(systemProperties.getProperty(name));
			}
		}
		return values;
	}
	
}
