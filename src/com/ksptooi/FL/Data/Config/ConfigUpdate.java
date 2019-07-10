package com.ksptooi.FL.Data.Config;

import java.io.File;

import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.Logger;

public class ConfigUpdate {

	
	Logger logManager=null;

	
	public ConfigUpdate(){
		logManager = new Logger();
	}
	
	
	
	public void updateConfig(){
		
		String FastLoginVersion = FUtil.Version;
			
		
		logManager.logInfo("检查配置版本");
		
		try{
			
			String ConfigVersion = ConfigManager.getConfig().getConfigVersion();
			
			//判断配置文件版本
			if(!ConfigVersion.equals(FastLoginVersion)){
				
				
				File OldConfig=new File("plugins/ksptooi/fastlogin/OldConfig_"+ConfigVersion+".conf");
				
				File Config = ConfigManager.fastLoginConfigFile;
				
				logManager.logWarning("配置文件版本不符");
				
				Config.renameTo(OldConfig);			
				
				logManager.logInfo("更新配重项");
				
				
				ConfigManager.createConfig();
				
				ConfigManager.readConfig();				
				ConfigManager.readLanguage();
				ConfigManager.readLocation();

				
			}
			
			
		}catch(Exception e){
			
					
			logManager.logWarning("配置文件版本不符");
			
			File OldConfig=new File("plugins/ksptooi/fastlogin/OldConfig_FastLogin.conf");
			File Config = ConfigManager.fastLoginConfigFile;
				
			Config.renameTo(OldConfig);
			
			
			logManager.logInfo("更新配重项");
			
			ConfigManager.createConfig();
			
			ConfigManager.readConfig();				
			ConfigManager.readLanguage();
			ConfigManager.readLocation();
				
			
		}
		

		
		
		
	}
	
	
	
	
}
