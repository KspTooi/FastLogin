package com.ksptooi.FL.Data.PlayerDataIO;

import java.io.File;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;

public interface PlayerDataIO_Interfrace {
	
	/**
	 * 创建玩家数据文件
	 * @return 创建成功返回true 文件已存在/或失败 返回false
	 */
	public boolean createPlayerData(String playerName);
	
	/**
	 * 从GD文件加载玩家数据文件
	 * @param 玩家名
	 * @return 返回一个PlayerDataEntity
	 */
	public PlayerEntity queryPlayerDataByName(String playerName);
	
	//类似于queryPlayerData(String PlayerName)	
	public PlayerEntity queryPlayerData(Player PlayerEntity);
	
	/**
	 * 更新玩家数据文件到GD
	 * @param playerDataEntity
	 * @return 成功返回true 失败返回false
	 */
	public boolean updatePlayerData(PlayerEntity playerDataEntity);
	
	/**
	 * 获取玩家数据文件位置
	 * @param playerName 玩家名称
	 * @return 返回一个File实体类
	 */
	public File getPlayerDataFile(String playerName);
	
	//类似于getPlayerDataFile(String playerName)
	public File getPlayerDataFile(Player playerEntity);
	
	
}
