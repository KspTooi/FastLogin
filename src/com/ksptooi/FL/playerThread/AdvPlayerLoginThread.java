package com.ksptooi.FL.playerThread;

import org.bukkit.entity.Player;
import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.PlayerProcess.PlayerEffectProcess;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.playerData_BLL.PlayerDataBLL_Interface;
import com.ksptooi.playerData_BLL.PlayerDataBLLimpl;
import com.ksptooi.FL.PlayerProcess.*;

public class AdvPlayerLoginThread implements Runnable{

	Player pl=null;
	String[] args=null;
	PlayerDataBLL_Interface playerDataBLL=null;
	LogManager lm=null;
	PlayerEffectProcess PEP=null;
	PlayerPasswordProcess PlayerPasswordProcess = null;

	
	public AdvPlayerLoginThread(Player pl,String[] args){
		
		this.args=args;
		this.pl=pl;
		lm=new LogManager();
		playerDataBLL=new PlayerDataBLLimpl();
		PEP = new PlayerEffectProcess();
		PlayerPasswordProcess = new PlayerPasswordProcess();
	}
	
	public void run() {
		
		PlayerDataEntity PDE = playerDataBLL.getPlayerData(pl);
		
		//已登录则关闭线程
		if (PDE.isLogin()) {		
			pl.sendMessage(FUtil.language.getRepeatLogin());
			return;
		}
		
		
		// 未注册则关闭线程
		if(!PDE.isRegister()){	
			pl.sendMessage(FUtil.language.getNotRegister2());
			return;
		}
		
		
		// 有2名同名玩家在线则全部踢出并关闭线程
		if(FUtil.LGD.isGhostPlayer(pl)){
			
			FUtil.LGD.kickPlayer(pl);
			
			return;
		}
		
		

		try{
		
			
			// 检查密码正确性
			
			if(PlayerPasswordProcess.isRightPassword(PDE, args[1])){
				
				
				/**登录成功*/
				
				PDE.setLogin(true);
				playerDataBLL.updatePlayerData(PDE);

				pl.sendMessage(FUtil.language.getLoginSuccess());

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
				if(FUtil.config.isEnable_LoginSecurity()==true){
					pl.teleport(playerDataBLL.getPlayerData(pl).getLastQuitLocation());
				}
				
				
				return;
				
			}
			

			
			pl.sendMessage(FUtil.language.getPasswordError());
			return;

		}catch (Exception e) {
			pl.sendMessage(FUtil.language.getNullPassword());
			return;
		}
		
		
	}

}
