package com.ksptooi.FL.Data.Config;

import java.io.File;

import com.ksptooi.FL.Data.Config.Entity.Config;
import com.ksptooi.FL.Data.Config.Entity.DefaultLocationEntity;
import com.ksptooi.FL.Data.Config.Entity.Language;

public class ConfigManager {

	

	
	public static final File fastLoginConfigFile=new File("plugins/ksptooi/fastlogin/FastLogin.conf");
	
	public static final File fastLoginLocationFile=new File("plugins/ksptooi/fastlogin/Location.conf");

	public static final File fastLoginIPCountFile=new File("plugins/ksptooi/fastlogin/IPCount.conf");
	
	public static final File fastLoginLanguageFile=new File("plugins/ksptooi/fastlogin/LanguageV1.conf");
	
	public static final String fastLoginPlayerDataFolder="plugins/ksptooi/fastlogin/database/";
	
	private static Config config = null;
	
	private static Language language = null;
	
	private static DefaultLocationEntity location = null;
	
	private static ConfigReader cr=new ConfigReader();
	
	private static ConfigWriter cw=new ConfigWriter();
	
	private static ConfigUpdate cu=new ConfigUpdate();
	
	
	public static void readConfig() {
		config = cr.readerConfig();
	}
	
	
	public static void readLanguage() {
		language = cr.readerLanguageConfig();
	}
	
	public static void readLocation() {
		location = cr.readerLocationConfig();
	}
		
	
	public static Config getConfig() {		
		return config;	
	}
	
	
	public static Language getLanguage() {	
		return language;
	}
	
	public static DefaultLocationEntity getLocation() {
		return location;
	}
	
	public static void updateLocation(DefaultLocationEntity dle) {
		cw.updateLocationConfig(dle);
	}
	
	
	
	public static void createConfig() {
		cw.createConfig();
	}
	
	public static void createLanguage() {
		cw.createLanguageConfig();
	}
	
	public static void createLocation() {
		cw.createLocationConfig();
	}
	
	public static void createIPCount() {
		cw.createIPCountConfig();
	}
	
	public static void configUpdate() {
		cu.updateConfig();
	}
	
	
	
}
