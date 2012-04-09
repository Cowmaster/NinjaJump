package com.github.Cowmaster.NinjaJump;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class NinjaJumpPlayerListener implements Listener
{
	private static NinjaJump plugin;
	
	public NinjaJumpPlayerListener(NinjaJump instance)
	{
		plugin = instance;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerMove(PlayerMoveEvent event)
	{		
		Player player  = event.getPlayer();
		
		UUID key = player.getUniqueId();
		
		Location loc  = new Location(player.getWorld(), event.getTo().getX(), event.getTo().getY() -0.4, event.getTo().getZ());
		Location xloc  = new Location(player.getWorld(), event.getTo().getX() -0.8, event.getTo().getY(), event.getTo().getZ());
		Location zloc  = new Location(player.getWorld(), event.getTo().getX(), event.getTo().getY(), event.getTo().getZ()-0.8);
		Location xloc2  = new Location(player.getWorld(), event.getTo().getX() +0.8, event.getTo().getY(), event.getTo().getZ());
		Location zloc2  = new Location(player.getWorld(), event.getTo().getX(), event.getTo().getY(), event.getTo().getZ()+0.8);
		Location yloc  = new Location(player.getWorld(), event.getTo().getX(), event.getTo().getY() +2, event.getTo().getZ());
		Location loc2  = new Location(player.getWorld(), event.getTo().getX(), event.getTo().getY() -0.7, event.getTo().getZ());
		Location loc3  = new Location(player.getWorld(), event.getTo().getX(), event.getTo().getY() -0.8, event.getTo().getZ());
		Location loc4  = new Location(player.getWorld(), event.getTo().getX(), event.getTo().getY() -2, event.getTo().getZ());
	
//		System.out.println(" xloc2: " + xloc2.getBlock().getTypeId() + " zloc2: " + zloc2.getBlock().getTypeId() + " xloc: " + xloc.getBlock().getTypeId() + " zloc: " + zloc.getBlock().getTypeId() + " loc: " + loc.getBlock().getTypeId() + "loc2: " + loc2.getBlock().getTypeId() + " loc3: " + loc3.getBlock().getTypeId());
		
		//Vertical/Horizontal Acceleration check for open space
			if(yloc.getBlock().getTypeId() == 0 && xloc2.getBlock().getTypeId() == 0 && zloc2.getBlock().getTypeId() == 0 && xloc.getBlock().getTypeId() == 0 && zloc.getBlock().getTypeId() == 0 && loc.getBlock().getTypeId() == 0 && loc2.getBlock().getTypeId() == 0 && loc3.getBlock().getTypeId() != 0 && plugin.enabled)
			{			
				//System.out.println("Sent to if loc: " + loc.getBlock().getTypeId() + " loc2: " + loc2.getBlock().getTypeId() + " loc3: " + loc3.getBlock().getTypeId());
				plugin.bounceHight.put(player.getUniqueId(), plugin.jumpHeight);
				Vector dir = player.getLocation().getDirection().multiply(plugin.jumpSpeed);
				dir.setY(plugin.bounceHight.get(key));
				player.setVelocity(dir);
				player.setFallDistance(0);
				// Ninja Jump!
				//player.sendMessage( "Ninja JUMP! -  xloc2: " + xloc2.getBlock().getTypeId() + " zloc2: " + zloc2.getBlock().getTypeId() + "xloc: " + xloc.getBlock().getTypeId() + " zloc: " + zloc.getBlock().getTypeId() + "loc: " + loc.getBlock().getTypeId() + "loc2: " + loc2.getBlock().getTypeId() + " loc3: " + loc3.getBlock().getTypeId() );
			}
		//Negate Jump Damage just before hitting the ground
			if(loc.getBlock().getTypeId() == 0 && loc4.getBlock().getTypeId() != 0 && plugin.enabled)
			{			
				//player.sendMessage( "Negate Fall Damage - loc: " + loc.getBlock().getTypeId() + "loc4: " + loc2.getBlock().getTypeId() );
				player.setFallDistance(0);
				plugin.bounceHight.put(player.getUniqueId(), 0.0);
			}
	}
}
