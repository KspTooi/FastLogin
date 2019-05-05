package us.ktmc.player;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;
import us.ktmc.util_interface.GeneralUtil;
import us.ktmc.util.Language;

public class PlayerProcessThread implements Runnable{
	
	
	Player pl=null;
	int LoginTime=0;
	int sendtime=0;
	FastUtil Util=new GeneralUtil();
	
	public PlayerProcessThread(Player pl) {
		this.pl=pl;			
	}
	
	
	public void run(){

		
		if(! Util.isReg(pl)){
			pl.sendMessage(Language.NotRegister);
			
		}else{
			
			pl.sendMessage(Language.Notlogin);
			
		}
		
			
		
		Var.NoDamagePlayer.add(pl.getName());
					 
		while(true){
			
			try {
				
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			sendtime++;
			LoginTime++;
			
			//不在线则关闭线程
			if(!pl.isOnline()){
				Var.NoDamagePlayer.remove(pl.getName());
				break;
			}
			
			//登陆超时则关闭线程
			if(LoginTime > Var.LoginTimeOut){
				
				Bukkit.getScheduler().runTask(Var.MainClass, new Runnable(){
					
					public void run() {
						pl.kickPlayer(Language.LoginTimeOutKick);						
					}		
					
				});			
				
				break;
			}
				
			//已登录则关闭线程
			if (Util.isLogin(pl)) {
				
				Var.NoDamagePlayer.remove(pl.getName());
				break;

			}
			
					
			if(!(sendtime >= Var.MessageInterval)){
				continue;
			}
					
			sendtime=0;	
			
			
			if(! Util.isReg(pl)){
				pl.sendMessage(Language.NotRegister);
				continue;
			}
			
			
			if(!Util.isLogin(pl)){
				pl.sendMessage(Language.Notlogin);
				continue;
			}
			
			break;
			
		}
		
		
		
	}
	

}
