package com.chocod.summarize.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 扩展配置文件加载
 */

public class PropertyPlaceholderConfigurerEx extends PropertyPlaceholderConfigurer {
	private static final Logger log = Logger.getLogger(PropertyPlaceholderConfigurerEx.class);
	
	//需要加载的配置文件名称
	private String[] propertyFileNames;
	private String fileEncoding;
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		
		//如果配置了本地环境变量,则替代(使用系统级的环境变量配置的properties文件地址)
		String dirPath = System.getProperty("local.config.path");
		if (StringUtils.isNotBlank(dirPath)) {
			props.clear();
			for (String propertyFileName : propertyFileNames) {
				try {
					File propFile = null;
					propFile = new File(dirPath + "/" + propertyFileName);					
					if (!propFile.exists()) {
						throw new RuntimeException("Config file <" + propFile.getAbsolutePath() + "> doesn't exists.");
					}
					InputStream is = null;
					InputStreamReader isr = null;
					try {
						is = new FileInputStream(propFile);
						if (this.fileEncoding != null) {
							isr = new InputStreamReader(is, this.fileEncoding);
						} else {
							isr = new InputStreamReader(is);
						}
						props.load(isr);
					} catch (IOException e) {
						throw new RuntimeException(e);
					} finally {
						if (is != null)
							is.close();
						if (isr != null)
							isr.close();
					}
				} catch (Exception e) {
					log.error("PropertyConfigUtil getconfig error:", e);
				}
			}			
		}		
		super.processProperties(beanFactoryToProcess, props);
		// 添加到系统变量中
		SystemEnv.addProperties(props);
		// 初始化Log4j
		PropertyConfigurator.configure(props);
	}
	
	public void setGroupId(String groupId) {
		SystemEnv.setGroupId(groupId);
	}
	
	public void setArtifactId(String artifactId) {
		SystemEnv.setArtifactId(artifactId);
	}
	
	public void setVersion(String version) {
		SystemEnv.setVersion(version);
	}
	
	public void setBuildTime(String buildTime) {
		SystemEnv.setBuildTime(buildTime);
	}

	

	public String[] getPropertyFileNames() {
		return propertyFileNames;
	}

	public void setPropertyFileNames(String[] propertyFileNames) {
		this.propertyFileNames = propertyFileNames;
	}

	public String getFileEncoding() {
		return fileEncoding;
	}

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

}
