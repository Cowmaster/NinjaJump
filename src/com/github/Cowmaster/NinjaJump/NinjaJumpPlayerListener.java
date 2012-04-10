package com.github.Cowmaster.NinjaJump;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;
import org.bukkit.util.Vector;

public class NinjaJumpPlayerListener implements Listener
{
	private static NinjaJump plugin;

	
	public NinjaJumpPlayerListener(NinjaJump instance)
	{
		this.plugin = instance;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event){
		
		Player player  = event.getPlayer();
		UUID key = player.getUniqueId();
		
		//System.out.println(player.getInventory().getItemInHand().getTypeId());
		//System.out.println(player.getWorld().getBlockAt(player.getLocation().subtract(0, 0.5, 0)).getTypeId());
		//System.out.println(player.getWorld().getBlockAt(player.getLocation().subtract(0, 1, 0)).getTypeId());
		//System.out.println(player.getWorld().getBlockAt(player.getLocation().subtract(0, 2, 0)).getTypeId());
		//System.out.println(event.getAction());
		//Jump
		
			if((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && player.getInventory().getItemInHand().getTypeId() == plugin.jumpItem &&  player.getWorld().getBlockAt(player.getLocation().subtract(0, 0.5, 0)).getTypeId() != 0 && plugin.enabled)	
			{			
				//System.out.println("Sent to if loc: " + loc.getBlock().getTypeId() + " loc2: " + loc2.getBlock().getTypeId() + " loc3: " + loc3.getBlock().getTypeId());
				plugin.bounceHight.put(player.getUniqueId(), plugin.jumpHeight);
				Vector dir = player.getLocation().getDirection().multiply(plugin.jumpSpeed);
				dir.setY(plugin.bounceHight.get(key));
				player.setVelocity(dir);
				player.setFallDistance(-50);
				// Ninja Jump!
				//player.sendMessage( "Ninja JUMP! - Standingon:Action:item" + player.getWorld().getBlockAt(player.getLocation().subtract(0, -2, 0)).getTypeId() + event.getAction() + player.getInventory().getItemInHand().getTypeId());
				player.sendMessage( "Ninja JUMP!");
			}
			
	}

}
