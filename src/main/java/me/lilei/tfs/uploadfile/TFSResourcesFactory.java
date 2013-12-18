package me.lilei.tfs.uploadfile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TFSResourcesFactory {

	private static final Logger logger = LoggerFactory
			.getLogger(TFSResourcesFactory.class);
	
	private static int iThreadCount = 4;
	
	public static void blanceTFSResources(ConfigResources config,List<String> pathList){
		
		int size = 0;
		
		if(null == pathList || 0 == (size = pathList.size())){
			//do nothing
		}else{
			
			int diffCount = size / iThreadCount;
			
			ExecutorService exec = Executors.newCachedThreadPool();
			for(int i = 0; i <iThreadCount;i++){
				TFSResources tfsr = new TFSResources(config);
				List<String> subList = null;
				if((iThreadCount-1) == i){
					
					subList = new ArrayList<String>(size - (i)*diffCount);
					subList.addAll(pathList.subList((i)*diffCount, size - 1));
				}else{
					
					subList = new ArrayList<String>(diffCount);
					subList.addAll(pathList.subList((i)*diffCount, (i+1)*diffCount - 1));
				}
				tfsr.setResourcePathList(subList);
				exec.execute(tfsr);
			}
			exec.shutdown();
		}
	}
}
