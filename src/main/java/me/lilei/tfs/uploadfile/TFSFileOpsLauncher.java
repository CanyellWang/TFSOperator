package me.lilei.tfs.uploadfile;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.lilei.tfs.info.model.EnvironmentInfo;
import me.lilei.tfs.info.model.EnvironmentInfoMaker;

public class TFSFileOpsLauncher {

	//keep this statement (ConfigResources class initial )to be the first line .
	//0 以静态方式 加载配置文件
	private static ConfigResources config = new ConfigResources();
	
	private static final Logger logger = LoggerFactory.getLogger(TFSFileOpsLauncher.class);
	
	public static void main(String[] args) {
		
		
		//1.初始化环境信息
		//EnvironmentInfo envInfo = EnvironmentInfoMaker.getCurrentEnvInfo();
		
		//2.启动heartbeat线程，执行heartbeat通信，传递EnvironmentInfo以及当前执行任务信息，同时获取服务器分配的任务信息
		
		//3.执行获取到的任务
		
	
		//本地上传文件位置取得
		List<String> path = new ArrayList<String>(1);
		path.add("/home/jeffrey/TFS-test");
		//path.add("/home/jeffrey/TFS-test/20130614/籍永丰/09");
		LocalResources lr = new LocalResources(config);
		lr.getAllFileList(path);
		List<String> pathList = lr.getFileList();
		
		TFSResourcesFactory.blanceTFSResourcesScheduler(config, pathList);
		
		System.out.println("end!");
	}
	
	
}
