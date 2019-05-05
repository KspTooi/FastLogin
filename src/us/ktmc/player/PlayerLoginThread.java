package us.ktmc.player;

import java.io.File;
import org.bukkit.entity.Player;
import us.ktmc.util.Language;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;




public class PlayerLoginThread implements Runnable{

	Player pl=null;
	String[] args=null;
	File PlConf=null;
	FastUtil Util=Var.Util;
	
	public PlayerLoginThread(Player pl,String[] args){
		
		this.args=args;
		this.pl=pl;
		PlConf=Util.getPlDataFile(pl);
	}
	
	public void run() {
		
		//已登录则关闭线程
		if (Util.isLogin(pl)) {		
			pl.sendMessage(Language.RepeatLogin);
			return;
			
		}

		// 未注册则关闭线程
		if (!Util.isReg(pl)) {		
			pl.sendMessage(Language.NotRegister2);
			return;
			
		}
		
		// 有2名同名玩家在线则全部踢出并关闭线程
		if(Var.FS.isShadowGhost(pl)){
			
			Var.FS.KickShadowGhost(pl);
			return;
		}
		
		//END - 登录检查结束
		
		
		try {

			//密码正确
			if (Util.isPassword(pl, args[0])) {
				
				//调用登录结果处理
				Var.AH.LoginSuccess(pl);				
				
				return;

			}

			pl.sendMessage(Language.PasswordError);
			return;

		} catch (Exception e) {
			pl.sendMessage(Language.NullPassword);
			return;
		}
		
		
	}

}
