package com.ksptooi.FL.Event.FastEvent;

import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.PlayerProcess.PlayerPasswordProcess;
import com.ksptooi.FL.Util.FUtil;

public class PlayerRegisterEvent implements LittleEvent{

	Player pl = null;
	String[] args = null;
	
	public PlayerRegisterEvent(Player pl,String[] args) {
		this.pl = pl;
		this.args = args;
	}
	
	
	@Override
	public void run() {
		
		try {

			
			PlayerDataManager playerDataManager = DataManager.getPlayerDataManager();
			PlayerPasswordProcess playerPwdProcess = new PlayerPasswordProcess();

			String Passwd = null;
			String ConfirmPasswd = null;

			// 检查注册参数是否合法
			if (args.length < 2) {
				pl.sendMessage(ConfigManager.getLanguage().getNoConfirmPasswd());
				return;
			}

			Passwd = args[0];
			ConfirmPasswd = args[1];

			// 判断是否已注册
			PlayerEntity PDE = playerDataManager.getPlayerData(pl);

			if (PDE.isRegister()) {
				pl.sendMessage(ConfigManager.getLanguage().getRepeatRegister());
				return;
			}

			// 判断ip是否已经达到限额
			if (FUtil.RIC.playerIp_isMaxReg(pl)) {
				pl.sendMessage("注册失败,每个IP最多只能注册" + ConfigManager.getConfig().getMaxRegisterIP() + "个账号,你已超出限额！");
				return;
			}

			// 检查密码长度
			if (!playerPwdProcess.passWordLengthIsAccess(pl, ConfirmPasswd)) {

				return;
			}

			// 检查密码与确认密码是否一致
			if (!(Passwd.equals(ConfirmPasswd))) {
				pl.sendMessage(ConfigManager.getLanguage().getConfirmPasswdError());
				return;
			}

			// 注册成功 - 调用事件
			PlayerRegisterSuccessEvent prse = new PlayerRegisterSuccessEvent(pl, ConfirmPasswd);
			FastLogin.getEventManager().runEvent(prse);

			
		} catch (Exception e) {
			e.printStackTrace();
			pl.sendMessage("注册发生错误!,请联系管理员.");
		}
		
		
	}
	
	

}
