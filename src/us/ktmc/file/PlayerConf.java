package us.ktmc.file;

import java.io.File;
import java.io.IOException;
import org.bukkit.entity.Player;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;
import us.ktmc.util_interface.GeneralUtil;

//通过这个类 直接操作玩家数据文件

/**
 * 
 * 部分操作已移至GeneralUtil类
 *
 */

public class PlayerConf {

	FastUtil Util=new GeneralUtil();
				
	
	public File getPlayerFile(String PlayerName){		
		
		return new File("plugins/ksptooi/fastlogin/database/"+PlayerName.toLowerCase()+".gd");
		
	}
	
	
	public boolean isRegister(String pl){
		
		File fl=new File("plugins/ksptooi/fastlogin/database/"+pl.toLowerCase()+".gd");
		
		try {
			
			if(Var.IO.FromKeyInput(fl, "register=", "UTF-8").replace("register=", "").equalsIgnoreCase("1")){
				return true;								
			}
					
		} catch (IOException e) {
			return false;
		}
		return false;	
		
	}
	
	public boolean isRegister(Player pl){
		
		File fl=new File("plugins/ksptooi/fastlogin/database/"+pl.getName().toLowerCase()+".gd");
		
		try {
			
			if(Var.IO.FromKeyInput(fl, "register=", "UTF-8").replace("register=", "").equalsIgnoreCase("1")){
				return true;								
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;	
		
	}
	
	public boolean isPassword(Player PlayerEntity,String Passwd){
		return Util.isPassword(PlayerEntity, Passwd);
	}
	
	public boolean isLogin(Player pl){
		
		File fl=new File("plugins/ksptooi/fastlogin/database/"+pl.getName().toLowerCase()+".gd");
		
		try {
			if(Var.IO.FromKeyInput(fl, "login=", "UTF-8").replace("login=", "").equals("1")){
				return true;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return false;
		
		
	}
	
		
}
