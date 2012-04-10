package com.github.Cowmaster.NinjaJump;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NinjaJump extends JavaPlugin 
{	
	public HashMap<UUID, Boolean> fall = new HashMap<UUID, Boolean>();
	public HashMap<UUID, Double> bounceHight = new HashMap<UUID, Double>();

	public boolean enabled = true;
	public int jumpItem = 280;
	public double jumpHeight = 1.5;
	public double jumpSpeed = 1.2;
	
	public void onEnable() 
	{

		FileConfiguration config = getConfig();
		
		jumpItem = config.getInt("NinjaJump.jumpitem", jumpItem);
		config.set("NinjaJump.jumpitem", jumpItem);
		jumpHeight = config.getDouble("NinjaJump.jumpheight", jumpHeight);
		config.set("NinjaJump.jumpheight", jumpHeight);
		jumpSpeed = config.getDouble("NinjaJump.jumpspeed", jumpSpeed);
		config.set("NinjaJump.jumpspeed", jumpSpeed);
		
		saveConfig();

		new NinjaJumpPlayerListener(this);
		new NinjaJumpEntityListener(this);
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(commandLabel.equalsIgnoreCase("nj") && sender instanceof Player) 
		{
			//if(hasPermission((Player) sender, "NinjaJump.set"))
			//{
			try
				{
					enabled = Boolean.parseBoolean(args[0]);
					sender.sendMessage(enabled ? "Ninja Skills Enabled":"Ninja Skills Disabled");
					return true;
				}
				catch (Exception e){}
			//}
		}
		return false;
	}
	
	public void onDisable() { }
	
	/*
	 private boolean hasPermission(Player player, String permission)

	{
		return player.isOp() || player.hasPermission(permission);
	}
		 */
}
