package com.ksptooi.FL.Util;

import java.util.ArrayList;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.security.LoginSecurity;
import com.ksptooi.FL.security.RegsterIPCount;

/**
 *
 * 此Class作为全局变量使用！
 *  
 * @author KspTooi
 *
 */
public class FUtil {
		
	//全局变量
	
//	public static final File fastLoginConfigFile=new File("F:\\1217/MineCraft Server/1.7.10[PT]/plugins/ksptooi/fastlogin/fastlogin.conf");
	
	public static LoginSecurity LS=new LoginSecurity();
	
	public static RegsterIPCount RIC=new RegsterIPCount();
	
	
	//全局容器 初始化
	public static ArrayList<String> NoDamagePlayer=new ArrayList<String>();
	
	
	//全局工具 初始化
	public static FastLogin MainClass=null;
	
	
}