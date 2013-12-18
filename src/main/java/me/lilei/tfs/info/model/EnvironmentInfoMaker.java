package me.lilei.tfs.info.model;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvironmentInfoMaker {

	private static final Logger logger = LoggerFactory.getLogger(EnvironmentInfoMaker.class);
	private EnvironmentInfo envi = null;
	private Pattern pattern = null;

	private static final String IPADDRESS_PATTERN = 
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	private EnvironmentInfoMaker(){
		
		initEnvironmentInfoMaker();
	}
	
	private void initEnvironmentInfoMaker(){
		envi = new EnvironmentInfo();
		pattern = Pattern.compile(IPADDRESS_PATTERN);
	}
	
	private EnvironmentInfo initEnvInfo(){
		
		List<String> localMachineIPV4List = getLcoalMachineIP();
/*		if(ipV4FormatCheck(strIPV4)){
			envi.setIpV4(strIPV4);
		}*/
		return envi;
	}
	public static EnvironmentInfo getCurrentEnvInfo(){
		EnvironmentInfoMaker envMaker = new EnvironmentInfoMaker();
		return envMaker.initEnvInfo();
	}
	
	private boolean ipV4FormatCheck(String ipV4){
		
		 Matcher matcher = pattern.matcher(ipV4);
		 return matcher.matches();	   
	}
	
	private List<String> getLcoalMachineIP(){
		
		Enumeration<NetworkInterface> niEnum = null;
		try {
			niEnum = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> IPAddressList = new ArrayList<String>(4);
		while(niEnum.hasMoreElements()){
/*			String strIPV4 = niEnum.nextElement();
			if(ipV4FormatCheck(strIPV4)){
				IPAddressList.add(strIPV4);
			}*/
			
		}
		
		if(IPAddressList.size() > 0){
			return IPAddressList;
		}
		return null;
	}
}
