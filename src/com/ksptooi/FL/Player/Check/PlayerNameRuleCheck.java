package com.ksptooi.FL.Player.Check;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;


public class PlayerNameRuleCheck {

	
	/**
	 * 用于判断玩家名称
	 * 
	 * @param Player 玩家实体
	 * @return 如果名称合法返回True 不合法返回False 并踢出玩家
	 */
	public boolean nameValid(FastPlayer Player) {
		
		
		//判断玩家名称长度
		if (Player.getName().length() < ConfigManager.getConfig().getPlayerNameMinLength()) {
			Player.kickPlayer("玩家名过短 最小需要" + ConfigManager.getConfig().getPlayerNameMinLength() + "个字符");
			return false;
		}
		//End

		
		//以自定义正则表达式判断玩家名称 Start
		if(!ConfigManager.getConfig().getRegexMatchForPlayerName().equalsIgnoreCase("*")){
			
			if(!Player.getName().matches(ConfigManager.getConfig().getRegexMatchForPlayerName())){
				Player.kickPlayer("玩家名称不符合要求！");			
				return false; 
			}
			
		}	
		//End
		
		
		
		//以严格模式判断玩家名称 Start
		if (ConfigManager.getConfig().isEnable_UserNameStrictmode()){
			
			String regex = "([A-Z]|[a-z]|[0-9]|-|_){1,}";
	
			if (!Player.getName().matches(regex)) {
				
				Player.kickPlayer("玩家名不符合要求,玩家名只能由A-Z 0-9与下划线组成,不得含有空格与特殊符号!");		
				return false; 
				
			}		
		
		}
		//End
	    	    
		//判断玩家名称是否含有被服务器禁用的字符
		for(String str:ConfigManager.getConfig().getBanName()){
			
			if(Player.getName().toLowerCase().contains(str.toLowerCase())){
				
				Player.kickPlayer("玩家名含有本服务器禁用的关键字:"+str);
				return false;
				
			}
			
		}
		//End
		
		return true;
	}
	
	
}
