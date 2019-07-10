package com.ksptooi.FL.Event.PlayerEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
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
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Cache.PlayerDataCache;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Data.Player.Entity.PlayerData;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.Event.FastEvent.PlayerJo1nEvent;
import com.ksptooi.FL.Player.Check.PlayerNameRuleCheck;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.FL.security.PlayerFilter;

/**P.E.H**/
public class PlayerEventHandler implements Listener{

	Logger lm=null;
	PlayerData_Interface PDB=null;
	PlayerNameRuleCheck playerNameProcess=null;
	PlayerFilter PF=null;
	Logger logManager=null;
	PlayerEffectManager PEP=null;
	
	public PlayerEventHandler(){
		lm=new Logger();
		PDB=new PlayerDataManager();
		playerNameProcess=new PlayerNameRuleCheck();
		PF=new PlayerFilter();
		logManager = new Logger();
		PEP = new PlayerEffectManager();
	}
	
	
	/**异步登录 - 开始**/
	@EventHandler(priority = EventPriority.MONITOR)		
	public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event){
		
		String PlayerName=event.getName().toLowerCase();
		
		for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
	
            if(pl.getName().toLowerCase().equalsIgnoreCase(PlayerName)){
                event.setLoginResult(Result.KICK_OTHER);
                event.setKickMessage(ConfigManager.getLanguage().getJoinGameError1());
            }
            
		}
		
	}
		
	//玩家加入事件
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
	
		FastPlayer pl = new FastPlayer(event.getPlayer());
		
		event.setJoinMessage(lm.GenJoinedMessage(pl));
		
		
		//调用FastEvent
		PlayerJo1nEvent eve = new PlayerJo1nEvent(pl);
		FastLogin.getEventManager().runFastEvent(eve);
		
	}
		

	//移动	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		
		
		
		
		//验证是否为realPlayer
		if(!PF.isRealPlayer(event.getPlayer())){
			return;
		}
		
		//判断是否在下落
		if(event.getFrom().getY()>event.getTo().getY()){
			return ;
		}
		
		//判断是否移动了位置
		if(event.getFrom().getX()==event.getTo().getX()){
			return ;
		}
		
		if(event.getFrom().getZ()==event.getTo().getZ()){
			return ;
		}
		
		
		PlayerData PDE = PDB.getPlayerData(event.getPlayer());
		
		
		if(!PDE.isLogin()){
			event.getPlayer().teleport(event.getFrom());
		}
		

		
	}
	
	//说话
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		
		PlayerData PDE = PDB.getPlayerData(event.getPlayer());
		
		if(!PDE.isLogin()){	
			event.setCancelled(true);
		}
		
		
	}
	
	//交互
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		
		//验证是否为realPlayer
		if(!PF.isRealPlayer(event.getPlayer())){
			return;
		}
		
		PlayerData PDE = PDB.getPlayerData(event.getPlayer());
		
		
		if(!PDE.isLogin()){
			event.setCancelled(true);
		}

		
	}
	
	//传送
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event){
		
		//如果玩家名不合法则禁止传送		
		
	}
	
	//命令
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerCommand(PlayerCommandPreprocessEvent event){

		String message = event.getMessage().toLowerCase();
		
		if(message.contains("/l")||message.contains("/login")||message.contains("/register")||message.contains("/reg")) {
			return;
		}	
		
		PlayerData PDE = PDB.getPlayerData(event.getPlayer());
		
		
		if(!PDE.isLogin()){
			event.setCancelled(true);   
		}
		
		
	}
	
	//被攻击(Damage)
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
		
		//验证是否为PlayerEntity
		if(!(event.getEntity() instanceof Player)){
			return;
		}
		
		//验证是否为realPlayer
		if(!PF.isRealPlayer((Player)event.getEntity())){
			return;
		}
		
		
		  
		Player pl=(Player)event.getEntity();
		
		
		
		PlayerData PDE = PDB.getPlayerData(pl);
		
		
		if(!PDE.isLogin()){
			event.setCancelled(true);
		}
	
		
	}
	
	//被伤害
	@EventHandler
	public void onEntityDamage1(EntityDamageByBlockEvent event){
		
				
		//验证是否为PlayerEntity
		if(!(event.getEntity() instanceof Player)){
			return;
		}
		
		//验证是否为realPlayer
		if(!PF.isRealPlayer((Player)event.getEntity())){
			return;
		}
		
		Player pl=(Player)event.getEntity();
		
		
		PlayerData PDE = null;
		
		try{
			
			PDE = PDB.getPlayerData(pl);
			
		}catch(Exception e){
			event.setCancelled(true);
			return;
		}
		
		if(!PDE.isLogin()){
			event.setCancelled(true);
		}
		
		
	}
	/****/
	
	
	//攻击
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		
		//验证是否为Player
		if(!(event.getDamager().getType() == EntityType.PLAYER)){
			return;
		}
		
		//验证是否为realPlayer
		if(!PF.isRealPlayer((Player)event.getDamager())){
			return;
		}

		Player pl=(Player)event.getDamager();
		
		
		
		PlayerData PDE = PDB.getPlayerData(pl);
		
		
		if(!PDE.isLogin()){
			event.setCancelled(true);
		}
		

		
	}
	
	//背包点击
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
			
		Player pl=(Player)event.getWhoClicked();
		

		
		PlayerData PDE = PDB.getPlayerData(pl);
		
		
		if(!PDE.isLogin()){
			pl.closeInventory();
			event.setCancelled(true);
		}
		
		
	}
	
	
	
	
	//丢弃
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event){
			
		//验证是否为realPlayer
		if(!(event.getPlayer().getType() == EntityType.PLAYER)){
			return;
		}
		
		
		PlayerData PDE = PDB.getPlayerData(event.getPlayer());
		
		
		if(!PDE.isLogin()){
			event.setCancelled(true);
		}
		
		
	}
	

	//退出
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {

		FastPlayer pl =new FastPlayer(event.getPlayer());
		
		event.setQuitMessage(lm.GenQuitMessage(pl));
		
		
		if (!playerNameProcess.nameValid(pl)) {
			return;
		}
		
		
		//如果已经登录则保存一下用户的位置
		if(pl.isLogin()){
			pl.saveQuitLocation();
		}
		
		//移除Online列表
		FastLogin.removeOnlinePlayer(event.getPlayer().getName());
		
		//将用户设置为未登录状态
		pl.setLogin(false);
		
		//更新用户GD文件
		pl.save();
		
		//清理缓存
		PlayerDataCache.removePlayerData(event.getPlayer().getName());

	}
	
	
	
	
}
