package com.ksptooi.FL.Data.Config.Entity;

import org.bukkit.Location;

public class DefaultLocationEntity {

	private Location LoginLocation=new Location(null, 0, 0, 0);
	private String Location_world="empty";
	private double Location_x=0;
	private double Location_y=0;
	private double Location_z=0;
	private float Location_pitch=0F;
	private float Location_yaw=0F;
	
	
	public void removeLoc(){
		
		Location_world = "empty";
		Location_x = 0;
		Location_y = 0;
		Location_z = 0;
		Location_pitch = 0;
		Location_yaw = 0;
		
	}
	
	
	public Location getLoginLocation() {
		return LoginLocation;
	}
	public void setLoginLocation(Location loginLocation) {
		
		this.Location_world=loginLocation.getWorld().getName();
		this.Location_x=loginLocation.getX();
		this.Location_y=loginLocation.getY();
		this.Location_z=loginLocation.getZ();
		this.Location_pitch=loginLocation.getPitch();
		this.Location_yaw=loginLocation.getYaw();
		
		this.LoginLocation = loginLocation;
	}
	public String getLocation_world() {
		return Location_world;
	}
	public void setLocation_world(String location_world) {
		Location_world = location_world;
	}
	public double getLocation_x() {
		return Location_x;
	}
	public void setLocation_x(double location_x) {
		Location_x = location_x;
	}
	public double getLocation_y() {
		return Location_y;
	}
	public void setLocation_y(double location_y) {
		Location_y = location_y;
	}
	public double getLocation_z() {
		return Location_z;
	}
	public void setLocation_z(double location_z) {
		Location_z = location_z;
	}
	public float getLocation_pitch() {
		return Location_pitch;
	}
	public void setLocation_pitch(float location_pitch) {
		Location_pitch = location_pitch;
	}
	public float getLocation_yaw() {
		return Location_yaw;
	}
	public void setLocation_yaw(float location_yaw) {
		Location_yaw = location_yaw;
	}
	

	
}
