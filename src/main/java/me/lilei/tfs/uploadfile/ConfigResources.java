package me.lilei.tfs.uploadfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ConfigResources {
	
	static{
		//System.out.println(System.getProperty("user.dir"));
		org.apache.log4j.PropertyConfigurator.configure(System.getProperty("user.dir") 
				+ "/conf/log4j.properties");
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigResources.class);
	
	public static final String SPRING_APP_CONTEXT_PATH
		= "file:" + System.getProperty("user.dir") + "/conf/root-context.xml";
	
	public ConfigResources(){
		
		loadConfig();
	}
	
	/**
	 * 以spring的方式加载配置文件
	 */
	private void loadConfig(){
		logger.debug(this.getClass().getResource("/").getPath());
		logger.debug(SPRING_APP_CONTEXT_PATH);
		String[] locations = {SPRING_APP_CONTEXT_PATH};
		//String[] locations = {"spring/appConfig/application-context.xml","spring/appConfig/application-mail.xml" };
		@SuppressWarnings("unused")
		ApplicationContext act = new FileSystemXmlApplicationContext(locations);
		logger.debug("Config loaded from file!");
	}

}
