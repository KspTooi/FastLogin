package com.ksptooi.FL.PluginConf;

import java.io.File;

import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.gdc.FileAPI.IOController_V5;

public class ConfigUpdate {

	
	LogManager logManager=null;
	IOController_V5 v5=null; 
	ConfigWriter PCCW=null;
	ConfigReader PCCR=null;
	
	public ConfigUpdate(){
		PCCW = new ConfigWriter();
		logManager = new LogManager();
		v5=new IOController_V5();
		PCCR = new ConfigReader();
	}
	
	
	
	
	public void checkAndUpdateOfConfig(){
		
		String FastLoginVersion = FUtil.Version;
		
		String ConfigVersion = FUtil.config.getVersion();
		
		
		logManager.writerInfo("检查配置版本");
		
		
		try{
			
			//判断配置文件版本
			if(!ConfigVersion.equals(FastLoginVersion)){
				
				
				File OldConfig=new File("plugins/ksptooi/fastlogin/OldConfig_"+ConfigVersion+".conf");
				
				File Config = FUtil.fastLoginConfigFile;
				
				logManager.writerWarning("配置文件版本不符");
				
				
				Config.renameTo(OldConfig);				
				
				logManager.writerInfo("更新配重项");
				
				PCCW.createConfig();
				
				FUtil.config=PCCR.readerConfig();
				FUtil.defaultLocationEntity=PCCR.readerLocationConfig();
				FUtil.language=PCCR.readerLanguageConfig();
				
			}
			
			
		}catch(Exception e){
			
			
			
			logManager.writerWarning("配置文件版本不符");
			
			File OldConfig=new File("plugins/ksptooi/fastlogin/OldConfig_FastLogin.conf");
			File Config = FUtil.fastLoginConfigFile;
				
			Config.renameTo(OldConfig);
			
			
			logManager.writerInfo("更新配重项");
			
			PCCW.createConfig();
			
			FUtil.config=PCCR.readerConfig();
			FUtil.defaultLocationEntity=PCCR.readerLocationConfig();
			FUtil.language=PCCR.readerLanguageConfig();
			
			
			
		}
		

		
		
		
	}
	
	
	
	
}
