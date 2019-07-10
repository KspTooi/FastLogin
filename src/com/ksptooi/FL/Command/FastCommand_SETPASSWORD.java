package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.Language;
import com.ksptooi.FL.Data.Hash.PasswordHash;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Data.Player.Entity.PlayerData;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;

public class FastCommand_SETPASSWORD implements FastCommand{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
	
		PlayerDataManager pdm = DataManager.getPlayerDataManager();
		
		PasswordHash advPasswordHash = DataManager.getAdvPasswordHash();
		
		Language lang = ConfigManager.getLanguage();
		
		
			if(args.length<3){
				sender.sendMessage(lang.getAdminSetPasswordUsage());
				return;
			}
			
			PlayerData pde = pdm.getPlayerData(args[1]);
			
			if(!pde.isRegister()){
				sender.sendMessage(lang.getAdminSetPasswordError1());
				return;
			}
			
			//OP修改的玩家密码 不受插件的长度约束
			pde.setPassword(advPasswordHash.autoCompression(args[2]));
			
			pdm.updatePlayerData(pde);
			
			sender.sendMessage(lang.getAdminSetPasswordSuccess());			
			
			FastPlayer pl = FastLogin.getPlayer(pde.getPlayername());
			
			//如果玩家在线则踢出
			if(pl!=null) {
				
				pl.kickPlayer(lang.getAdminSetPasswordKick());
				
			}		

		
	}

}
