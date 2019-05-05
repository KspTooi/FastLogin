package com.ksptooi.debug;

import com.ksptooi.FL.PluginConf.ConfigReader;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.playerData_BLL.PlayerDataBLL_Interface;
import com.ksptooi.playerData_BLL.PlayerDataBLLimpl;
import com.ksptooi.playerData_BLL.PlayerSqlDataBLL;

public class debug {

	public static  void main(String rk[]){
		
		ConfigReader FLCR=new ConfigReader();
		
		FUtil.config=FLCR.readerConfig();
		
		FUtil.playerSqlDataBLL=new PlayerSqlDataBLL();
		
		
		PlayerDataBLL_Interface PDBI=new PlayerDataBLLimpl();
		
		PDBI.createPlayerData("KspTooi");
		
		
		
	}
	
}
