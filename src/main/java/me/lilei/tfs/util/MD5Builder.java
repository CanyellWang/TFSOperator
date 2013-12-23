package me.lilei.tfs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.DigestInputStream;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Builder {

	private static final Logger logger = LoggerFactory
			.getLogger(MD5Builder.class);

/*	MessageDigestAdapter adapter = new MessageDigestAdapter("MD5");
	String md5str = adapter.digest(TARGET);*/
	
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6','7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	protected static MessageDigest messageDigest = null;
	static {
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			logger.error(nsaex.getMessage());
			System.err.println(MD5Builder.class.getName()+"初始化失败，MessageDigest不支持MD5!");
			nsaex.printStackTrace();
		}
	}


	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}
	
	/**
	 * 将16位byte[] 转换为32位String
	 * 
	 * @param buffer
	 * @return
	 */
	private String toHex(byte buffer[]) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 15, 16));
		}

		return sb.toString();
	}
	
	public static class MD5File{
		
		public static String md5(File f) {
			FileInputStream fis = null;
		
				fis = new FileInputStream(f);
				byte[] buffer = new byte[8192];
				int length;
				while ((length = fis.read(buffer)) != -1) {
					messageDigest.update(buffer, 0, length);
				}

				return new String(Hex.encodeHex(messageDigest.digest()));

		}
		
		public static String getFileMD5String(File file) throws IOException {
			FileInputStream in = new FileInputStream(file);
			FileChannel ch = in.getChannel();
			
			//700000000 bytes are about 670M
			int maxSize=700000000;
			
			long startPosition=0L;
			long step=file.length()/maxSize;
			
			if(step == 0){
				MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,file.length());
				messageDigest.update(byteBuffer);
				return bufferToHex(messageDigest.digest());
			}
			
			for(int i=0;i<step;i++){
				MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, startPosition,maxSize);
				messageDigest.update(byteBuffer);
				startPosition+=maxSize;
			}
			
			if(startPosition==file.length()){
				return bufferToHex(messageDigest.digest());
			}

			MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, startPosition,file.length()-startPosition);
			messageDigest.update(byteBuffer);
			
				
			return bufferToHex(messageDigest.digest());
		}
	}
	
	public static class MD5String{
		
		public static String md5(String target) {
			return DigestUtils.md5Hex(target);
		}
		
		
		public static String getMD5String(String s) {
			return getMD5String(s.getBytes());
		}

		public static String getMD5String(byte[] bytes) {
			messageDigest.update(bytes);
			return bufferToHex(messageDigest.digest());
		}
	}
}
