package me.lilei.tfs.info.model;

import me.lilei.tfs.exception.IPV4FormatErrorException;

/*
 * 问题点
 * 1.google protocol buffer
 * 2.多网卡设备怎办
 */

public class EnvironmentInfo {
	
	//程序所在电脑的IPV4地址
	private String ipV4 = "";

	protected EnvironmentInfo(){
		
	}
	
	public String getIpV4() {
		return ipV4;
	}

	
	public  void setIpV4(String strIPV4) throws IPV4FormatErrorException{
		
		//TODO：这里通过正则表达式验证IP地址是否符合
		//String regex = 
		
		if(false){
		
			throw new IPV4FormatErrorException();
		}
		
		ipV4 = strIPV4;
	}
	
}
