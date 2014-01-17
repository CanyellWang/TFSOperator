package me.lilei.tfs.operate;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.lilei.tfs.config.model.ConfigResources;
import me.lilei.tfs.config.model.ConfigResourcesBuilder;
import me.lilei.tfs.info.model.EnvironmentInfo;
import me.lilei.tfs.info.model.EnvironmentInfoMaker;

public class TFSFileOpsLauncher {

	//keep this statement (ConfigResources class initial )to be the first line .
	//0 以静态方式 加载配置文件
	private static ConfigResources config = ConfigResourcesBuilder.build(); 
	
	
	private static final Logger logger = LoggerFactory.getLogger(TFSFileOpsLauncher.class);
	
	public static void main(String[] args) {
	
		//TODO:1.初始化环境信息
		//EnvironmentInfo envInfo = EnvironmentInfoMaker.getCurrentEnvInfo();
		
		//TODO:2.启动heartbeat线程，执行heartbeat通信，传递EnvironmentInfo以及当前执行任务信息，同时获取服务器分配的任务信息
		
		//TODO:3.执行获取到的任务
		
	
		//TODO:使用线程去启动LocalRes去扫描文件，设定一个阈值，或者是综合硬件性能计算出来一个阈值。向LIST添加文件到达这个阈值之后，为防止继续添加LIST将内存搞死，就挂起或是暂停扫描
		//TODO：将达到阈值的LIST添加到队列（或是调用均衡方法，将LIST拆分成DS线程多个，然后放进队列），工作线程（<=DSCount）将队列内的LIST取出开始工作
		//TODO:将完成传输的文件首先通过通用方法写到数据库中（其实是写本地同时写数据库，以防止数据库失败时候，程序跟着失败，但必须保证本地日志能够继续工作，远程写失败之后可以通过读取本地日志继续完成上传）
		//TODO:写数据库的应该也丢到一个队列里，达到一个阈值之后dao才朝数据库中写，但是要注意阈值设定，放置这个队列和上边的队列一起把内存搞死
		//TODO:工作线程全部完成时候通知loaclRes线程继续扫描，并重复以上步骤
		
		//本地上传文件位置取得
		//List<String> path = new ArrayList<String>(1);
		//path.add("/home/jeffrey/TFS-test");
		//path.add("/home/jeffrey/TFS-test/20130614/籍永丰/09");
		//LocalResources lr = new LocalResources(config);
		//lr.getAllFileList(path);
		//List<String> pathList = lr.getFileList();
		
		//TFSResourcesFactory.blanceTFSResourcesScheduler(config, pathList);
		
		System.out.println("end!");
	}
	
	
}
