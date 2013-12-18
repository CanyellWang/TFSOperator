package me.lilei.tfs.uploadfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.FileVisitor;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths; 
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.lilei.tfs.info.model.EnvironmentInfo;
import me.lilei.tfs.info.model.EnvironmentInfoMaker;

import com.taobao.common.tfs.DefaultTfsManager;

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
		
/*		//TFS资源获取并执行上传
		TFSResources tfs = new TFSResources(config);
		tfs.uploadResource2TFS(pathList);
		tfs.destoryTFSResource();*/
        
		System.out.println("end!");
	}
	
	
}
