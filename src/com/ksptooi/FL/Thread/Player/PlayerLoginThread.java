package com.ksptooi.FL.Thread.Player;

import org.bukkit.entity.Player;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.FL.PlayerProcess.*;

public class PlayerLoginThread implements Runnable{

	Player pl=null;
	String[] args=null;
	PlayerData_Interface playerDataBLL=null;
	Logger lm=null;
	PlayerEffectManager PEP=null;
	PlayerPasswordProcess PlayerPasswordProcess = null;

	
	public PlayerLoginThread(Player pl,String[] args){
		
		this.args=args;
		this.pl=pl;
		lm=new Logger();
		playerDataBLL=new PlayerDataManager();
		PEP = new PlayerEffectManager();
		PlayerPasswordProcess = new PlayerPasswordProcess();
	}
	
	public void run() {
		
		PlayerEntity PDE = playerDataBLL.getPlayerData(pl);
		
		//已登录则关闭线程
		if (PDE.isLogin()) {		
			pl.sendMessage(ConfigManager.getLanguage().getRepeatLogin());
			return;
		}
		
		
		// 未注册则关闭线程
		if(!PDE.isRegister()){	
			pl.sendMessage(ConfigManager.getLanguage().getNotRegister2());
			return;
		}
		
		
		// 有2名同名玩家在线则全部踢出并关闭线程
		if(FUtil.LGD.isGhostPlayer(pl)){			
			FUtil.LGD.kickPlayer(pl);			
			return;
		}
		
		

		try{
		
			
			// 检查密码正确性
			
			if(PlayerPasswordProcess.isRightPassword(PDE, args[0])){
				
				
				/**登录成功*/
				
				PDE.setLogin(true);
				playerDataBLL.updatePlayerData(PDE);

				pl.sendMessage(ConfigManager.getLanguage().getLoginSuccess());

				//如果玩家登录前是OP 赋予玩家OP权限
				FUtil.LS.loginSuccess_OpSecurityProcess(pl);
				//如果玩家登录前是创造模式 赋予玩家创造
				FUtil.LS.loginSuccess_CreativeSecurityProcess(pl);
				
				// 自定义登录消息
				lm.ShowMessage(pl);				
				
				//为玩家添加粒子效果
				PEP.addLoginedEffect(pl);
				
				//移除玩家的失明效果
				PEP.removePreLoginEffect(pl);
				
				//如果isEnable_LoginSecurity为True 则将玩家传送回最后下线的地方
				if(ConfigManager.getConfig().isEnable_LoginSecurity()==true){
					pl.teleport(playerDataBLL.getPlayerData(pl).getLastQuitLocation());
				}
				
				
				return;
				
			}
			

			
			pl.sendMessage(ConfigManager.getLanguage().getPasswordError());
			return;

		}catch (Exception e) {
			pl.sendMessage(ConfigManager.getLanguage().getNullPassword());
			return;
		}
		
		
	}

}
