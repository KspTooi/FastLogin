package us.ktmc.process;

import java.io.File;
import java.io.IOException;
import org.bukkit.entity.Player;
import us.ktmc.util.Language;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;

public class ActionHandler {
	
	
	FastUtil Util=Var.Util;
	
	/**
	 * 玩家登录成功以后进行的一系列操作
	 * @param pl
	 * @throws Exception
	 */
	public void LoginSuccess(Player pl) throws Exception{
		
		Util.setPlayerLogin(pl, true);
		
		pl.sendMessage(Language.LoginSuccessful);
		
		
		Var.Util.ShowMessage(pl);
		
		
		//如果玩家在登录前是OP 将OP给回玩家
		for (int i=0;i<Var.opTables.size();i++) {

			if (pl.getName().equals(Var.opTables.get(i))){		
				pl.setOp(true);
				Var.opTables.remove(i);
			}

		}
			
		
		//登陆保护开启时将玩家传送回最后下线地点
		if (Var.Enable_LoginSecurity) {
			
			if (Var.Location_world.equalsIgnoreCase("empty")){
				return;
			}
			
			if (Util.getPlayerQuitLocation(pl) != null) {
				pl.teleport(Util.getPlayerQuitLocation(pl));
			}
			
			return;
		}

		//登录保护不开启则将玩家传送至预设的默认登陆点
		Util.TelePort_DefaultLoginLocation(pl);
		
		
		
	}
	
	
	/**
	 * 玩家注册成功以后进行的一系列操作
	 * @param PlayerEntity
	 */
	public void RegisterSuccess(Player PlayerEntity,String Passwd){
		
		File PlayerConfig=Var.Util.getPlDataFile(PlayerEntity);
		
		
		/** 更新密码||注册状态||登陆状态 -开始**/
		
		Var.V3.setTargetFile(PlayerConfig);
		
		
		try {
			
			Var.V3.WriteAtKey("password=", Passwd);
			Var.V3.WriteAtKey("register=", "1");
			Var.V3.WriteAtKey("login=", "1");
			
		} catch (IOException e) {

			e.printStackTrace();			
			PlayerEntity.sendMessage(Var.Util.getFileErrorText());
			
		}
		
		//将注册前是OP的玩家重新赋予OP
		for(int i=0;i<Var.opTables.size();i++){
			
			if(PlayerEntity.getName().equals(Var.opTables.get(i))){
				PlayerEntity.setOp(true);
				Var.opTables.remove(Var.opTables.remove(i));
			}
			
		}
		
		PlayerEntity.sendMessage(Language.RegisterSuccessful);
		
		
		//将玩家传送到默认登陆位置
		Var.Util.TelePort_DefaultLoginLocation(PlayerEntity);
		
		
	}

	
	
		
	
}
