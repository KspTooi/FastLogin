package us.ktmc.player;

import org.bukkit.entity.Player;
import us.ktmc.Exception.PasswdRulesException;
import us.ktmc.util.Language;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;

public class PlayerRegThread implements Runnable{

	
	Player PlayerEntity=null;	
	String[] args=null;	

	
	public PlayerRegThread(Player PlayerEntity,String[] args){
		
		this.PlayerEntity=PlayerEntity;		
		this.args=args;			

		
	}
	
	public void run() {
		
			
		try{		
			
			FastUtil Util=Var.Util;
			String Passwd=null;
			String ConfirmPasswd=null;
			
			
			//检查注册参数是否合法
			if(args.length < 2){
				PlayerEntity.sendMessage(Language.NoConfirmPasswd);
				return ;
			}
			
			Passwd=args[0];
			ConfirmPasswd=args[1];


			//判断是否已注册
			
			
			if(Var.isMysql){
				
				if(Var.SQL.SQL_isReg(PlayerEntity)){		
					PlayerEntity.sendMessage(Language.RepeatRegister);
					return;
				}
				
				
			}else{
				
				if(Util.isReg(PlayerEntity)){		
					PlayerEntity.sendMessage(Language.RepeatRegister);
					return;
				}	
				
			}
			
			
			
			
			//判断ip是否已经达到限额
			if(Var.PIP.isMaxRegIP(PlayerEntity)){
				PlayerEntity.sendMessage("每个IP最多只能注册"+Var.MaxRegisterIP+"个账号,你已超出限额！");
				return ;
			}
			
			//检查密码长度
			try {
				
				Util.PasswdIsAvailable(Passwd);
				
			} catch (PasswdRulesException e) {
				
				if(e.isTooLong()){
					PlayerEntity.sendMessage(Language.PassMax);
					return;
				}
				
				if(e.isTooShort()){			
					PlayerEntity.sendMessage(Language.PassMin);
					return;				
				}
			}
			
			//检查密码与确认密码是否一致
			if(!(Passwd.equals(ConfirmPasswd))){
				PlayerEntity.sendMessage(Language.ConfirmPasswdError);
				return ;
			}
			
			
			/**
			 * 1.检查注册参数是否合法
			 * 2.判断是否已经注册
			 * 3.判断IP是否达到限额
			 * 4.检查密码长度
			 * 5.检查密码与确认密码是否一致
			 * 6.调用注册结果处理
			 */
			
			
			//调用注册结果处理
			Var.AH.RegisterSuccess(PlayerEntity,Passwd);
			
	
			
			return;
			
			
			
		} catch (Exception e){
			
			PlayerEntity.sendMessage(Language.NullPassword);
			return;
			
		}
		
		
		
		
	}

	
	
}
