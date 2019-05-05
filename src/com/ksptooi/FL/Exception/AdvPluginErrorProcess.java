package com.ksptooi.FL.Exception;

import org.bukkit.Bukkit;

import com.ksptooi.FL.Util.LogManager;

public class AdvPluginErrorProcess {

	LogManager lm=null;
	
	
	public AdvPluginErrorProcess(){
		lm = new LogManager();
		
	}
	
	
	public void ErrorOfGeneralDataCoreNoFound(){
		
		
		lm.writerError("发生错误!");
		lm.writerError("没有找到前置插件-GeneralDataCoreV5");
		Bukkit.shutdown();
	}
	
	
	public void ErrorOfGeneralDataCoreVersionInvaild(){
		
		lm.writerError("发生错误!");
		lm.writerError("前置插件GeneralDataCore的版本不匹配!");
		Bukkit.shutdown();
		
	}
	
	
}
