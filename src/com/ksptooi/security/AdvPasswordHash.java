package com.ksptooi.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ksptooi.FL.Util.FUtil;

public class AdvPasswordHash {

	//根据插件配置文件 自动识别加密算法
	public String autoCompression(String str){
		
		String Hash = FUtil.config.getEnable_passwordHash();
		
		//检测加密算法
		if(Hash.equalsIgnoreCase("false")){
			return str;
		}
		
		//md5
		if(Hash.equalsIgnoreCase("md5")){		
			return md5(str);			
		}
		
		
		return str;	
		
	}
	
	//使用指定的加密算法
	public String Compression(String str,String Hash){
		
		//检测加密算法
		if(Hash.equalsIgnoreCase("false")){
			return str;
		}
		
		//md5
		if(Hash.equalsIgnoreCase("md5")){		
			return md5(str);			
		}
		
		return str;
		
	}
	
	
	
	
	
	//加密算法
	private String md5(String str){
		

        byte[] secretBytes = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            md.update(str.getBytes());
           
            secretBytes = md.digest();
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("算法异常! - MD5");
        }
        
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字

        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        
        return md5code;
         
    }
		
		
		

	
	
	@SuppressWarnings("unused")
	private String sha1(String str){
		return str;
		
	}
	
	
	
	
	
}
