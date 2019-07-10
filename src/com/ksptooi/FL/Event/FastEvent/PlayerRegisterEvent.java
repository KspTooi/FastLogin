package com.ksptooi.FL.Event.FastEvent;

import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Player.Check.PlayerPasswordRuleCheck;
import com.ksptooi.FL.Util.FUtil;

public class PlayerRegisterEvent implements FastEvent{

	FastPlayer pl = null;
	String[] args = null;
	
	public PlayerRegisterEvent(Player pl,String[] args) {
		this.pl = new FastPlayer(pl);
		this.args = args;
	}
	
	
	@Override
	public void run() {
		
		try {

			PlayerPasswordRuleCheck playerPwdProcess = new PlayerPasswordRuleCheck();

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
			pl.reload();

			if (pl.isRegister()) {
				pl.sendMessage(ConfigManager.getLanguage().getRepeatRegister());
				return;
			}

			// 判断ip是否已经达到限额
			if (FUtil.RIC.playerIp_isMaxReg(pl)) {
				pl.sendMessage("注册失败,每个IP最多只能注册" + ConfigManager.getConfig().getMaxRegisterIP() + "个账号,你已超出限额！");
				return;
			}

			// 检查密码长度
			if (!playerPwdProcess.pwdIsValid(pl, ConfirmPasswd)) {

				return;
			}

			// 检查密码与确认密码是否一致
			if (!(Passwd.equals(ConfirmPasswd))) {
				pl.sendMessage(ConfigManager.getLanguage().getConfirmPasswdError());
				return;
			}

			// 注册成功 - 调用事件
			PlayerRegisterSuccessEvent prse = new PlayerRegisterSuccessEvent(pl, ConfirmPasswd);
			FastLogin.getEventManager().runFastEvent(prse);

			
		} catch (Exception e) {
			e.printStackTrace();
			pl.sendMessage("注册发生错误!,请联系管理员.");
		}
		
		
	}
	
	

}
