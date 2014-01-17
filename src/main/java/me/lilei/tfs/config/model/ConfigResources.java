package me.lilei.tfs.config.model;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import me.lilei.tfs.operate.dao.ConfigResourcesDao;

/**
 * 加载本地及远程服务器中相关的配置文件
 * 
 * @author jeffrey
 * 
 */
public class ConfigResources {
    /**
     * 1.首先加载SPRING相关 2.加载远程配置文件服务器相关 3.下载远程服务器的TFS相关配置 4.若无法连接远程配置服务器则退出
     */

    @Resource(name="ConfigResourcesDao")
    private ConfigResourcesDao crd;
    
    private List<Map<String,Object>> configItems = null;
    
    private DataSource dsFileIndex = null;
    
    protected ConfigResources() {

        //do nothing
    }
    
    protected void init(){
        
        //获取到所有的配置项目
        configItems = crd.getConfigItems();
        
    }
    
    /**
     * 根据配置参数为访问FIndex数据库生成数据源 
     * @param configItems
     */
    private void initDataSourceFileIndex(final List<Map<String,Object>> configItems){
        
/*      Spring 加载方式
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName( "org.hsqldb.jdbcDriver");
        dataSource.setUrl( "jdbc:hsqldb:hsql://localhost:");
        dataSource.setUsername( "sa");
        dataSource.setPassword( "");*/
        
/*        Apache提供的简单连接池创建数据源

        以这种方式创建数据源必须先准备两个jar文件：commons-dbcp.jar 和 commons-pool.jar。

        以这种方式创建的数据源就不再是javax.sql.DataSource。DataSource了，而是org.apache.commons.dbcp.BasicDataSource。而且不再需要配置任何文件就可以直接使用。代码如下：

        // 创建BasicDataSource对象

            BasicDataSource ds = new BasicDataSource();

            ds.setDriverClassName("com.mysql.jdbc.Driver");

            ds.setUrl("jdbc:mysql://localhost:3306/cheng");

            ds.setUsername("root");

            ds.setPassword("root");

            ds.setInitialSize(50);

            ds.setMaxActive(100);

            ds.setMaxIdle(30);

            ds.setMaxWait(10000);

           

            // 关闭数据源连接

        ds.close();*/
        dsFileIndex = new DataSource()
    }
}
