package me.smokplay.explosion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin{
	private static Main plugin;
	private YamlConfiguration c;
	private boolean estate ;
	FileConfiguration config;
    File cfile;

public void onEnable(){
	  
		plugin = this;
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new TNTEvents(), this);
	    config = getConfig();
	    config.options().copyDefaults(true);
	    saveConfig();
	    cfile = new File(getDataFolder(), "config.yml");
	    c = new YamlConfiguration();
	    try {
			c.load(cfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    ConsoleCommandSender lccs = Bukkit.getServer().getConsoleSender();
    lccs.sendMessage("§7[]=======================[§3§lAutomatic§d§lExplosion§7]===================[]");
    lccs.sendMessage("                      §bStatus: §2ON");
    lccs.sendMessage("                      §aBY: §2SmokPlay");
    lccs.sendMessage("§7[]====================================================[]");
		
}

public void onDisable(){
  saveConfig();
  
  ConsoleCommandSender lccs = Bukkit.getServer().getConsoleSender();
  lccs.sendMessage("§7[]=======================[§3§lAutomatic§d§lExplosion§7]===================[]");
  lccs.sendMessage("                      §bStatus: §cOFF         ");
  lccs.sendMessage("                      §aBY: §2SmokPlay");
  lccs.sendMessage("§7[]====================================================[]");
}

public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
	  Player p = (Player) sender;
	  
	  if (commandLabel.equalsIgnoreCase("ae") && p.hasPermission("ae.admin")) {
    if (p.hasPermission("ae.admin")){
      if (args.length == 0){
      	p.sendMessage("§7[]======[§3§lAutomatic§d§lExplosion§7]======[]");
      	p.sendMessage("§a /ae enable - activate AutomaticExplosion");
      	p.sendMessage("§a /ae disable - desactive AutomaticExplosion");
      	p.sendMessage("§a /ae reload - reload config");
      	p.sendMessage(" §7[]==========================[]");
      }
      else if (args.length == 1){
        if (args[0].equalsIgnoreCase("enable")){
          p.sendMessage(getConfig().getString("msg_enable").replace("&", "§"));
          /*config.set("active", true);
          saveCustomYml(config, cfile);*/
          estate = true;
          
        }
        else if (args[0].equalsIgnoreCase("disable"))
        {
          p.sendMessage(getConfig().getString("msg_disable").replace("&", "§"));
          /*config.set("active", false);
          saveCustomYml(config, cfile);*/
          estate = false;
        }
        if(args[0].equalsIgnoreCase("reload")){
        	reloadFiles(cfile, config);
        	p.sendMessage(getConfig().getString("msg_reload").replace("&", "§"));
        }
        }
       
      
    }
    }
	return false;
	}

public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile){
	  try{
		  ymlConfig.save(ymlFile);
	  }catch(IOException e){
		  e.printStackTrace();
	  }
}

public void reloadFiles(File file, FileConfiguration configuration){
	  try{
		  configuration.load(file);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
}
public String getMessage(){
	return config.getString("message");
}

public static Main getInstance(){
	return plugin;
}

public FileConfiguration  Config(){
	return c;
}

public boolean getEstado(){
	return estate;	
}

}