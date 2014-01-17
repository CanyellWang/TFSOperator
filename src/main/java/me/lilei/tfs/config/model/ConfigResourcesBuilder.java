package me.lilei.tfs.config.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 负责初始化程序启动时候的相关参数
 * @author jeffrey
 *
 */
public class ConfigResourcesBuilder {

    /**
     * 以静态方式首先加载日志框架的配置文件
     */
    static{
        
        org.apache.log4j.PropertyConfigurator.configure(System.getProperty("user.dir") 
                + "/conf/log4j.properties");
    }
    
    private static final Logger logger = LoggerFactory.getLogger(ConfigResourcesBuilder.class);
    
    /**
     * SPRING基本配置
     */
    private static final String SPRING_APP_ROOT_CONTEXT_PATH
        = "file:" + System.getProperty("user.dir") + "/conf/root-context.xml";
    
    private static final String[] locations = { SPRING_APP_ROOT_CONTEXT_PATH };
    
    private static final ApplicationContext act = new FileSystemXmlApplicationContext(locations);
    
    /**
     * 
     */
    private static ConfigResources cr = null;
    
    /**
     * 返回加载所有配置文件完成的配置资源类实例
     * @return
     */
    public synchronized static ConfigResources build(){
        
        if(null == cr){
            
            cr = new ConfigResources();
            cr.init();
            
        }
        return cr;
    }
}
