package me.lilei.tfs.uploadfile.model;

import java.util.ArrayList;
import java.util.List;

import me.lilei.tfs.info.model.JobInfo;

public class UploadFilesJobInfo extends JobInfo{
	
	//要上传的文件地址或文件夹地址
	private List<String> pathInfoList = new ArrayList<String>(16);
	
	/**
	 * 上传文件的一些过滤规则
	 * 比如 大于多少MB的文件才上传，后缀是什么的文件才上传，文件名称符合什么的才上传
	 */
	//文件基于后缀进行过滤
	private List<String> suffixFilter = new ArrayList<String>(16); 
	//基于正在表达式的文件名成过滤
	private String fileNameRegexFilter = "";
	//文件上传大小的区间{min，max},0为不限制
	private int[] fileSizeReginFilter = {0,0};
	
	
	
}
