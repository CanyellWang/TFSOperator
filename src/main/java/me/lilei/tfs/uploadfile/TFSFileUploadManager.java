package me.lilei.tfs.uploadfile;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.lilei.tfs.info.model.EnvironmentInfo;
import me.lilei.tfs.info.model.EnvironmentInfoMaker;

public class TFSFileUploadManager {

	private static final Logger logger = LoggerFactory.getLogger(TFSFileUploadManager.class);
	
	public static void main(String[] args) {
		//0 加载配置文件
		ConfigResources config = new ConfigResources();
		//1.初始化环境信息
		//EnvironmentInfo envInfo = EnvironmentInfoMaker.getCurrentEnvInfo();
		
		//2.heartbeat通信，同时获取任务信息
		
		//3.执行获取到的任务
		
	
		//本地上传文件位置取得
		List<String> path = new ArrayList<String>(1);
		path.add("/home/jeffrey/TFS-test");
		LocalResources lr = new LocalResources(config);
		lr.getAllFileList(path);
		List<String> pathList = lr.getFileList();
		
		TFSResourcesFactory.blanceTFSResources(config, pathList);
		
		System.out.println("end!");
	}
	
	
}
