package me.lilei.tfs.operate;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;

import me.lilei.tfs.config.model.ConfigResources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalResources {
	
	private static final Logger logger = LoggerFactory.getLogger(LocalResources.class);
	
	//private List<String> pathList = new ArrayList<String>(16);
	private ConfigResources config = null;
	
	private final List<String> fileList = new LinkedList<String>(); 
	
	private FileVisitor<Path> myFileVisitor = new SimpleFileVisitor<Path>() {
        
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attribs) {
            //System.out.println("I'm visiting file "+file+" which has size "+attribs.size());
        	fileList.add(file.toString() + "@" + attribs.size());
            return FileVisitResult.CONTINUE;
        }
    };
	
    public LocalResources(ConfigResources config){
    	this.config = config;
    }
    
    /**
     * 清除全局文件列表中的内容，以备下一次调用
     */
    private void clearUploadFilesList(){
    	fileList.clear();
    }
    
    public List<String> getFileList() {
		return fileList;
	}

	/**
     * 根据路径列表将列表中的所有文件指向的文件添加到文件列表中
     * @param pathList
     */
	public void getAllFileList(List<String> pathList){
		if(pathList == null || 0 == pathList.size()){
			//do nothing
		}else{
			clearUploadFilesList();
			
			long startTime_File = System.nanoTime();   //获取开始时间
			
			if(pathList instanceof RandomAccess){
				for(int i = 0; i < pathList.size();i++){
					getFilePathList(pathList.get(i));
				}
			}else{
				for(String path :pathList){
					getFilePathList(path);
				}
			}
			
			long endTime_File=System.nanoTime(); //获取结束时间  
			String strTimeInfo = "文件检索耗时： " + (endTime_File-startTime_File) + "ns";
	        System.out.println(strTimeInfo);  
	        logger.info(strTimeInfo);
		}
	}
	/**
	 * 根据单个路径将其或其包含的文件提取出来
	 * @param path
	 */
	private void getFilePathList(String path){
		if(null == path || path.isEmpty()){
			//do nothing
		}else{
			Path headDir = Paths.get(path);
			try {
				Files.walkFileTree(headDir, myFileVisitor);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
				logger.error("current data path:" + path);
			}
		}
	}
}
