package com.ksptooi.FL.playerEvent;

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
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.PlayerProcess.PlayerEffectProcess;
import com.ksptooi.FL.PlayerProcess.PlayerLocationProcess;
import com.ksptooi.FL.PlayerProcess.PlayerNameProcess;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.FL.playerThread.AdvPlayerLoginThread;
import com.ksptooi.FL.playerThread.AdvPlayerRegThread;
import com.ksptooi.FL.playerThread.PlayerLoginMessageSendThread;
import com.ksptooi.FL.security.PlayerFilter;

/**P.E.H**/
public class PlayerEventHandler implements Listener{

	Logger lm=null;
	PlayerData_Interface PDB=null;
	PlayerLocationProcess playerLocationProcess=null;
	PlayerNameProcess playerNameProcess=null;
	PlayerFilter PF=null;
	Logger logManager=null;
	PlayerEffectProcess PEP=null;
	
	public PlayerEventHandler(){
		lm=new Logger();
		PDB=new PlayerDataManager();
		playerLocationProcess=new PlayerLocationProcess();
		playerNameProcess=new PlayerNameProcess();
		PF=new PlayerFilter();
		logManager = new Logger();
		PEP = new PlayerEffectProcess();
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
	
		Player pl = event.getPlayer();
		PlayerEntity PDE=null;
		
		
		//验证是否为realPlayer
		if(!PF.isRealPlayer(pl)){
			return;
		}
		

		
		//清除玩家数据缓存
		PlayerDataCache.removePDE(pl.getName());
		
			
		
		//初始化玩家属性
		PDB.createPlayerData(pl.getName());
		
		PDE=PDB.getPlayerData(pl.getName());
		
		PDE.setLogin(false);
		
		PDB.updatePlayerData(PDE);
		
		event.setJoinMessage(lm.GenJoinedMessage(pl));
		
		playerLocationProcess.TelePort_DefaultLoginLocation(pl);
		
		
		//OP安全检测
		FUtil.LS.joinServer_OpSecurityProcess(pl);
		//创造安全检测
		FUtil.LS.joinServer_CreativeSecurityProcess(pl);
		
		
		//玩家名称检测
		if(! playerNameProcess.playerNameIsAccess(pl)){
			return;
		}
		
		//为玩家添加失明效果
		PEP.addPreLoginEffect(pl);
		
		//添加Online列表
		FastLogin.addOnlinePlayer(pl.getName(), pl);
		
		
		//全部通过则开启一个玩家登录监测线程
		new Thread(new PlayerLoginMessageSendThread(pl)).start();

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
		
		
		PlayerEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		
		if(!PDE.isLogin()){
			event.getPlayer().teleport(event.getFrom());
		}
		

		
	}
	
	//说话
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		
		PlayerEntity PDE = PDB.getPlayerData(event.getPlayer());
		
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
		
		PlayerEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		
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

		
		String[] Commands=null;

		//命令预处理
		try{
			Commands = event.getMessage().split(" ");

			for(int i=0;i<Commands.length;i++){
				Commands[i]=Commands[i].replace(" ", "");
			}
			
		}catch(Exception e){
		}
		
		Bukkit.getLogger().setFilter(null);
		
		//登录
		if(Commands[0].equalsIgnoreCase("/login")|Commands[0].equalsIgnoreCase("/l")){
			
			logManager.logInfo("玩家:"+event.getPlayer().getName()+"正在尝试登录");
			
			new Thread(new AdvPlayerLoginThread(event.getPlayer(),Commands)).start();
			
			
			event.setCancelled(true);
			return;
		}
		
		//注册
		if(Commands[0].equalsIgnoreCase("/register")|Commands[0].equalsIgnoreCase("/reg")){
			
			logManager.logInfo("玩家:"+event.getPlayer().getName()+"正在尝试注册");
			
			new Thread(new AdvPlayerRegThread(event.getPlayer(),Commands)).start();
			
			
			event.setCancelled(true);
			return;
		}
		
		
		PlayerEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		
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
		
		
		
		PlayerEntity PDE = PDB.getPlayerData(pl);
		
		
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
		
		
		PlayerEntity PDE = null;
		
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
		
		
		
		PlayerEntity PDE = PDB.getPlayerData(pl);
		
		
		if(!PDE.isLogin()){
			event.setCancelled(true);
		}
		

		
	}
	
	//背包点击
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
			
		Player pl=(Player)event.getWhoClicked();
		

		
		PlayerEntity PDE = PDB.getPlayerData(pl);
		
		
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
		
		
		PlayerEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		
		if(!PDE.isLogin()){
			event.setCancelled(true);
		}
		
		
	}
	

	//退出
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {

		event.setQuitMessage(lm.GenQuitMessage(event.getPlayer()));
		
		
		if (!playerNameProcess.playerNameIsAccess(event.getPlayer())) {
			return;
		}

		
		PlayerEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		//如果已经登录则保存一下用户的位置
		if(PDE.isLogin()){
			PDE.setLastQuitLocation(event.getPlayer().getLocation());
		}
		
		//移除Online列表
		FastLogin.removeOnlinePlayer(event.getPlayer().getName());
		
		//将用户设置为未登录状态
		PDE.setLogin(false);
		
		//更新用户GD文件
		PDB.updatePlayerData(PDE);
		
		//清理缓存
		PlayerDataCache.removePDE(event.getPlayer().getName());

	}
	
	
	
	
}
