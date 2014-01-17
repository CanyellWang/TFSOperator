package me.lilei.tfs.operate.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("FileIndexDao")
public class FileIndexDao {

	protected JdbcTemplate jdbcTemplate;

	@Resource(name="dataSourceMySql")
	public void init(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 以批量的方式提交数据
	 */
	public void submitDataByBatch(){
		
		StringBuilder sb = new StringBuilder();
		//id localName tfsName md5 dbDel tfsDel timestamp
	}
	
}
