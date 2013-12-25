package me.lilei.tfs.uploadfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ConfigResources {
	
	/**
	 * 以静态方式首先加载日志框架的配置文件
	 */
	static{
		//System.out.println(System.getProperty("user.dir"));
		org.apache.log4j.PropertyConfigurator.configure(System.getProperty("user.dir") 
				+ "/conf/log4j.properties");
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigResources.class);
	
	public static final String SPRING_APP_CONTEXT_PATH
		= "file:" + System.getProperty("user.dir") + "/conf/root-context.xml";
	
	/**
	 * TFS系统DataServer线程个数，用于控制本机程序向TFS写入文件的线程个数localWrite2TFSThreadCount，这个个数小于等于DSThreadCount。
	 */
	private int tfsDSThreadCount = 3;
	
	public int getTfsDSThreadCount() {
		return tfsDSThreadCount;
	}

	public ConfigResources(){
		
		loadConfig();
	}
	
	/**
	 * 以spring的方式加载配置文件
	 */
	private void loadConfig(){
		logger.debug(this.getClass().getResource("/").getPath());
		loadSpringConfig();
		loadTFSConfig();
		logger.debug("Config loaded from file!");
	}

	/**
	 * 利用spring加载相关的配置文件，比如DB设置，这些都是设置再root-context.xml中的
	 */
	private void loadSpringConfig(){
		logger.debug(SPRING_APP_CONTEXT_PATH);
		String[] locations = {SPRING_APP_CONTEXT_PATH};
		//String[] locations = {"spring/appConfig/application-context.xml","spring/appConfig/application-mail.xml" };
		@SuppressWarnings("unused")
		ApplicationContext act = new FileSystemXmlApplicationContext(locations);
	}
	
	/**
	 * 处理TFS相关设置的配置文件
	 */
	private void loadTFSConfig(){
		//TODO:从配置文件中或从远程服务器中读取TFS配置
		tfsDSThreadCount = 4;
	}
}
