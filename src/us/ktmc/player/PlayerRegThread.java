package us.ktmc.player;

import java.io.File;
import org.bukkit.entity.Player;
import us.ktmc.util.Language;
import us.ktmc.util.Var;


public class PlayerRegThread implements Runnable{

	
	Player PlayerEntity=null;	
	String[] args=null;	
	File PlayerConfig=null;
	
	public PlayerRegThread(Player PlayerEntity,String[] args){
		
		this.PlayerEntity=PlayerEntity;		
		this.args=args;			
		PlayerConfig=Var.Util.getPlDataFile(PlayerEntity); 
		
	}
	
	
	public void run() {
				
		try{				
			
			if(args.length < 2){
				PlayerEntity.sendMessage(Language.NoConfirmPasswd);
				return ;
			}
			
			
			//¿ªÊ¼×¢²á
			Var.Util.PlayerRegister(PlayerEntity, args[0], args[1]);	

			return ;
			
		} catch (Exception e){
			
			PlayerEntity.sendMessage(Language.NullPassword);
			return ;
			
		}
		
		
		
		
	}

	
	
}
