package me.smokplay.explosion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import me.smokplay.explosion.Main;
import sun.security.krb5.Config;


public class TNTEvents implements Listener{
	/*@EventHandler
	public void onI(PlayerInteractEvent e){
		if(e.getItem() == null) return;
		
		if(e.isBlockInHand()){
			if(e.getItem().equals(Material.TNT)){
				
				Location loc = e.getPlayer().getLocation();
				
				Entity tnt = Bukkit.getWorld("World").spawn(loc, TNTPrimed.class);
				
				((TNTPrimed)tnt).setFuseTicks(40);
				
				e.getItem().setAmount(0);
				
			}
		}
	}*/
	

	@EventHandler
	public  void onBlockPlace(BlockPlaceEvent e ){
		
		Player p = e.getPlayer();
		Block b = e.getBlock();
		
		if(e.getBlock().getType() == Material.TNT && p.hasPermission("ae.use")) {
			
			if(Main.getInstance().getEstado() == true){
			/*
			ActionBar actionBar = new ActionBar ("§CBOMBA §7colocada");
	        actionBar.sendToPlayer (p);
	        */
			e.getBlock().getWorld().getBlockAt(e.getBlock().getLocation()).setType(Material.AIR);
			
			TNTPrimed tnt = e.getBlock().getWorld().spawn(e.getBlock().getLocation(), TNTPrimed.class);
			tnt.setFuseTicks(40);
			}else{
				p.sendMessage(Main.getInstance().getConfig().getString("msg_warn").replace("&", "§"));
			}
			
		}else{
			p.sendMessage(Main.getInstance().getConfig().getString("msg_permissions").replace("&", "§"));
		}
	}
	
}
