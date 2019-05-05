package us.ktmc.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import us.ktmc.util.Language;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;
import us.ktmc.util_interface.GeneralUtil;

/**P.E.P**/
public class PlayerEventHandler implements Listener{

	FastUtil Util=new GeneralUtil();
	
	/**异步登录 - 开始**/
	@EventHandler(priority = EventPriority.MONITOR)		
	public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event){
		
		String PlayerName=event.getName().toLowerCase();
		
		for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
	
            if(pl.getName().toLowerCase().equalsIgnoreCase(PlayerName)){
                event.setLoginResult(Result.KICK_OTHER);
                event.setKickMessage(Language.JoinGameError1);
            }                                   
		}
		
	}
		
	//登录
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
	
		try {
			
			
			Var.PIP.isMaxRegIP(event.getPlayer());
			
			Player pl = event.getPlayer();
				
			//初始化玩家属性
			if(Var.isMysql){
				
				Var.SQL.SQL_CreatePlayerData(pl);
				Var.SQL.SQL_setPlayerLogin(pl, false);
				
			}else{
				
				Util.CreatePlayerData(pl);
				Util.setPlayerLogin(pl, false);
			}
				
			
			Util.TelePort_DefaultLoginLocation(pl);

			event.setJoinMessage(Var.Util.GenJoinedMessage(pl));
			
			
			if(Var.Enable_OPSecurity){
				
				if (pl.isOp()) {
					Var.opTables.add(pl.getName());
					pl.setOp(false);
				}
				
			}
			

			if (!Util.PlayerNameProcess(pl)) {
				return;
			}
			
			new Thread(new PlayerProcessThread(pl)).start();

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(Util.getFileErrorText());

		}

	}
		
	
	//移动	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		
		//开始两步验证
		if(!(event.getPlayer().getType() == EntityType.PLAYER)){
			return;
		}
		
		
		//End
		
		
		//开始数据校验
		if(Var.isMysql){
			
			
			if(!Var.SQL.SQL_isLogin(event.getPlayer())){
				
				if(event.getFrom().getY()>event.getTo().getY()){
					return ;
				}
				
			
				event.getPlayer().teleport(event.getFrom());
			
			}
			
			
		}else{
			
			
			if(!Util.isLogin(event.getPlayer())){
				
				if(event.getFrom().getY()>event.getTo().getY()){
					return ;
				}
				
			
				event.getPlayer().teleport(event.getFrom());
			
			}
			
			
		}

		
	}
	
	//说话
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		
		if(Var.isMysql){
			if(Var.SQL.SQL_isLogin(event.getPlayer())){
				return ;
			}		
		}else{
			if(Var.PlConf.isLogin(event.getPlayer())){
				return ;
			}	
		}
		
		

							
		
		event.setCancelled(true);
		
	}
	
	//交互
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		
		if(Var.isMysql){
			if(Var.SQL.SQL_isLogin(event.getPlayer())){
				return ;
			}	
		}else{
			if(Var.PlConf.isLogin(event.getPlayer())){
				return ;
			}	
		}

		event.setCancelled(true);
		
	}
	
	//传送
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event){
		
		if(!Util.PlayerNameProcess(event.getPlayer())){
			return;
		}	
		
			
		
		
	}
	//命令
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent event){
		

		if(event.getMessage().toLowerCase().contains("/login")|event.getMessage().toLowerCase().contains("/register")|event.getMessage().toLowerCase().contains("/l")|event.getMessage().toLowerCase().contains("/reg")){
			return;
		}
		
		
		if(Var.isMysql){
			if(Var.SQL.SQL_isLogin(event.getPlayer())){
				return ;
			}	
		}else{
			if(Var.PlConf.isLogin(event.getPlayer())){
				return ;
			}	
		}
		
		event.setCancelled(true);
		
	}
	
	//被伤害
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
		
		if(!(event.getEntity() instanceof Player)){
			return;
		}
		  
		Player pl=(Player)event.getEntity();
		
		
		if(Var.isMysql){
			if(Var.SQL.SQL_isLogin(pl)){
				return ;
			}	
		}else{
			if(Var.PlConf.isLogin(pl)){
				return ;
			}	
		}	
	
		event.setCancelled(true);
		
	}
	
	
	
	//攻击
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		
		
		if(!(event.getDamager().getType() == EntityType.PLAYER)){
			return;
		}

		Player pl=(Player)event.getDamager();
		
		
		if(Var.isMysql){
			if(Var.SQL.SQL_isLogin(pl)){
				return ;
			}	
		}else{
			if(Var.PlConf.isLogin(pl)){
				return ;
			}	
		}
	
		event.setCancelled(true);
		
	}
	
	//背包点击
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
			
		
		if(Var.isMysql){
			if(Var.SQL.SQL_isLogin((Player)event.getWhoClicked())){
				return ;
			}
		}else{
			if(Util.isLogin((Player)event.getWhoClicked())){
				return ;
			}
		}
		
	
		
		event.setCancelled(true);
		
	}
	
	
	
	//丢弃
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event){
			
		
		
		if(Var.isMysql){
			if(Var.SQL.SQL_isLogin(event.getPlayer())){
				return ;
			}	
		}else{
			if(Var.PlConf.isLogin(event.getPlayer())){
				return ;
			}	
		}
	
		event.setCancelled(true);
		
	}
	

	//退出
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {

		event.setQuitMessage(Util.GenQuitMessage(event.getPlayer()));
		
		
		if (!Util.PlayerNameProcess(event.getPlayer())) {
			return;
		}

		
		if (Util.isLogin(event.getPlayer())) {

			Util.setPlayerQuitLocation(event.getPlayer());

		}

		Util.setPlayerLogin(event.getPlayer(), false);
		

	}
	
	
	
	
}
