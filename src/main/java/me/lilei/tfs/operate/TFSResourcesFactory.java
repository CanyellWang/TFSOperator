package me.lilei.tfs.operate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.lilei.tfs.config.model.ConfigResources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TFSResourcesFactory {

	private static final Logger logger = LoggerFactory
			.getLogger(TFSResourcesFactory.class);
	
	//线程启动阈值
	private static int iThreadCount = 4;
	
	public static void blanceTFSResourcesScheduler(ConfigResources config,List<String> pathList){
		
		int size = 0;
		
		if(null == pathList || 0 == (size = pathList.size())){
			//do nothing
		}else{
			
			int diffCount = size / iThreadCount;
			final CountDownLatch latch = new CountDownLatch(iThreadCount);
			final ExecutorService exec = Executors.newCachedThreadPool();
			
			for(int i = 0; i < iThreadCount; i++ ){
				TFSResources tfsr = new TFSResources(config);
				List<String> subList = null;
				if((iThreadCount - 1) == i){
					
					subList = new ArrayList<String>(size - (i) * diffCount);
					subList.addAll(pathList.subList((i) * diffCount, size));
					TFSResourcesFactory.clearTFSManager(exec,latch,tfsr);
				}else{
					
					subList = new ArrayList<String>(diffCount);
					subList.addAll(pathList.subList((i)*diffCount, (i+1)*diffCount));
				}
				tfsr.setResourcePathList(subList);
				tfsr.setLatch(latch);
				exec.execute(tfsr);
			}

			//exec.shutdown();
		}
	}
	
	private static void clearTFSManager(final ExecutorService exec,final CountDownLatch latch,final TFSResources tfsr){
		//启动清理线程
		exec.execute(new Runnable(){

			public void run() {
				
				try {
					latch.await();
					tfsr.destoryTFSResource();
					logger.info("所有任务线程均已完成，销毁TFS资源管理器完成。");
					exec.shutdownNow();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
		});
	}
}
