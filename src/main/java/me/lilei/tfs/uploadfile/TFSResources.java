package me.lilei.tfs.uploadfile;

import java.util.List;
import java.util.RandomAccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.common.tfs.DefaultTfsManager;

public class TFSResources implements Runnable{

	private static final Logger logger = LoggerFactory
			.getLogger(TFSResources.class);

	private ConfigResources config = null;

	private DefaultTfsManager tfsManager = null;
	
	//存储上传资源位置的列表
	private List<String> resourcePathList = null;
	
	public List<String> getResourcePathList() {
		return resourcePathList;
	}

	public void setResourcePathList(List<String> resourcePathList) {
		this.resourcePathList = resourcePathList;
	}

/*	*//**
	 * TFS上传最终启动线程数目
	 * 0为自动配置，将计算上传文件数量获取开启的线程数目
	 *//*
	private int ThreadCount = 1;

	public int getThreadCount() {
		return ThreadCount;
	}

	public void setThreadCount(int threadCount) {
		ThreadCount = threadCount;
	}*/

	// /////////////////////////////////////////////
	private int successCount = 0;
	private int failCount = 0;
	private long totalFileSize = 0;
	private final float timeTime = 1000000000.000000000f;
	private final float storgeTime = 1024.000000f;

	// /////////////////////////////////////////////

	public TFSResources(ConfigResources config) {
		this.config = config;

		initTFSResource();
	}

	private void initTFSResource() {
		tfsManager = new DefaultTfsManager();
		// tfsManager.setNsip("192.168.11.75:8108");
		tfsManager.setMasterIP("192.168.11.75:8108");
		tfsManager.init();
	}
	
	/**
	 * TFS资源一旦不使用请销毁
	 */
	public void destoryTFSResource() {
		
		if(null != tfsManager){
		
			tfsManager.destroy();
		}
	}

	public void uploadResource2TFS(List<String> pathList) {
		
		if(null == pathList || 0 == pathList.size()){
			return;
		}
		long startTime=System.nanoTime();   //获取开始时间  
		try {
			//
			if (pathList instanceof RandomAccess) {
				
				for(int i=0; i < pathList.size(); i++) {
					
					send1Resource2TFS(pathList.get(i));
				}
			} else {
				
				for (String strPath : pathList) {

					send1Resource2TFS(strPath);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			tfsManager.destroy();
		}
		
		long endTime=System.nanoTime(); //获取结束时间  
        System.out.println("文件系统上传TFS耗时： " +(endTime-startTime)/timeTime+"[秒]");  
        System.out.println("成功上传的文件数量： " + successCount +"[个]");  
        System.out.println("上传失败的文件数量： " + failCount + "[个]");  
        System.out.println("成功上传的文件总大小： " + totalFileSize + "[KB]");  
        System.out.println("每秒上传大小： "+ totalFileSize /((endTime - startTime)/timeTime) + "[KB/s]");  
	}
	
	/**
	 * 发送单个文件到TFS
	 * @param strPath
	 */
	private void send1Resource2TFS(String strPath){
		
		String[] fileAttr = strPath.split("@");
		long startTime_WTFS = System.nanoTime();
		double fileSize = Long.parseLong(fileAttr[1]) / storgeTime;
		String tfsSavedFileName = tfsManager.saveFile(fileAttr[0],
				null, null);
		System.out.println(strPath + "写入TFS耗时（单条）： "
				+ (System.nanoTime() - startTime_WTFS) / timeTime
				+ "[秒]" + "SIZE=" + fileSize + "[KB]");
		if (null == tfsSavedFileName) {
			failCount++;
		} else {
			successCount++;
			totalFileSize += fileSize;
		}

		long startTime_WF = System.nanoTime();

		System.out.println("写入文件耗时（单条）： "
				+ (System.nanoTime() - startTime_WF) / timeTime
				+ "[秒]");
	}

	public void run() {
		
		if(null == resourcePathList || 0 == resourcePathList.size()){
			return;
		}
		uploadResource2TFS(resourcePathList);
		destoryTFSResource();
	}
}
