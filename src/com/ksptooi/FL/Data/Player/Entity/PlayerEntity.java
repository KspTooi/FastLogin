package com.ksptooi.FL.Data.Player.Entity;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PlayerEntity {

	private String playername=null;
	
	private String password=null;
	
	private String register=null;
	
	private String login=null;
	
	private String loc_world=null;
	
	private double loc_x=0.0;
	
	private double loc_y=0.0;
	
	private double loc_z=0.0;
	
	private double loc_pitch=0.0;
	
	private double loc_yaw=0.0;
	
	private Location lastQuitLocation=null;

	
	//Íæ¼Ò·½·¨
	
	//ÅÐ¶ÏÊÇ·ñ×¢²á	
	public boolean isRegister(){
		
		if(this.register.equals("1")){
			return true;
		}
		
		return false;
		
	}
	
	//ÅÐ¶ÏÊÇ·ñµÇÂ¼
	public boolean isLogin(){
		
		if(this.login.equals("1")){
			return true;
		}
		return false;
	}
	
	//ÉèÖÃµÇÂ¼×´Ì¬
	public void setLogin(boolean login) {
		
		
		if(login==true){
			this.setLogin("1");
			return;
		}
		
		this.setLogin("0");
	}
	
	//ÉèÖÃ×¢²á×´Ì¬
	public void setRegister(boolean register) {
		
		
		if(register==true){
			this.setRegister("1");
			return;
		}
		
		this.setRegister("0");
	}
	
	
	public String getPlayername() {
//		return playername;
		return playername.toLowerCase();
	}

	public void setPlayername(String playername) {
//		this.playername = playername;
		this.playername = playername.toLowerCase();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLoc_world() {
		return loc_world;
	}

	public void setLoc_world(String loc_world) {
		this.loc_world = loc_world;
	}

	public double getLoc_x() {
		return loc_x;
	}

	public void setLoc_x(double loc_x) {
		this.loc_x = loc_x;
	}

	public double getLoc_y() {
		return loc_y;
	}

	public void setLoc_y(double loc_y) {
		this.loc_y = loc_y;
	}

	public double getLoc_z() {
		return loc_z;
	}

	public void setLoc_z(double loc_z) {
		this.loc_z = loc_z;
	}

	public double getLoc_pitch() {
		return loc_pitch;
	}

	public void setLoc_pitch(double loc_pitch) {
		this.loc_pitch = loc_pitch;
	}

	public double getLoc_yaw() {
		return loc_yaw;
	}

	public void setLoc_yaw(double loc_yaw) {
		this.loc_yaw = loc_yaw;
	}

	public Location getLastQuitLocation() {
		
		//³õÊ¼»¯Location
		if( ! loc_world.equalsIgnoreCase("empty")){
			
			lastQuitLocation=new Location(Bukkit.getWorld(loc_world), loc_x, loc_y, loc_z, (float)loc_pitch, (float)loc_yaw);
			
		}
		
		return lastQuitLocation;
	}

	public void setLastQuitLocation(Location lastQuitLocation) {
		
		this.loc_world=lastQuitLocation.getWorld().getName();
		this.loc_x=lastQuitLocation.getX();
		this.loc_y=lastQuitLocation.getY();
		this.loc_z=lastQuitLocation.getZ();
		this.loc_pitch=lastQuitLocation.getPitch();
		this.loc_yaw=lastQuitLocation.getYaw();
		
		
		//³õÊ¼»¯Location
		if( ! loc_world.equalsIgnoreCase("empty")){
			
			lastQuitLocation=new Location(Bukkit.getWorld(loc_world), loc_x, loc_y, loc_z, (float)loc_pitch, (float)loc_yaw);
			
		}
		
		this.lastQuitLocation = lastQuitLocation;
	}
	
	
}
