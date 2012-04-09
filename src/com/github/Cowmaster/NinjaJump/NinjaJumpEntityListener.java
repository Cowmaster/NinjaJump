package com.github.Cowmaster.NinjaJump;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class NinjaJumpEntityListener implements Listener{

		private static NinjaJump plugin;
		
		public NinjaJumpEntityListener(NinjaJump ninjaJump) 
		{
			plugin = ninjaJump;
	        plugin.getServer().getPluginManager().registerEvents(this, plugin);
		}
		
		@EventHandler(priority = EventPriority.LOW)
		public void onEntityDamage(EntityDamageEvent event)
		{
			if (event.getEntity() instanceof Player)
			{
				UUID key = event.getEntity().getUniqueId();
				if(plugin.fall.containsKey(key))
				{
					event.setCancelled(plugin.fall.get(key));
					 System.out.println(key);
				}
			}
		}

	

}
