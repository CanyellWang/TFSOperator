package me.lilei.tfs.operate.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("ConfigResourcesDao")
public class ConfigResourcesDao {

    private final String PROJ_NAME = "TFSOperator";
    
    protected JdbcTemplate jdbcTemplate;

    @Resource(name="dataSourceMySql")
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    /**
     * 将和本项目相关的所有配置项目查询出来，默认是最新版本
     * @return 不可修改的配置项目列表
     */
    public List<Map<String,Object>> getConfigItems(){
        
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT `name`,`value` FROM `TB_CONFIG` ")
        .append("WHERE `proj` = (SELECT `id` FROM `TB_PROJ` ")
        .append(")WHERE `name` = '")
        .append(PROJ_NAME)
        .append("' ORDER BY `version` DESC LIMIT 1)");
        List<Map<String,Object>> items = this.jdbcTemplate.queryForList(sb.toString());
        return Collections.unmodifiableList(items);
    }
}
