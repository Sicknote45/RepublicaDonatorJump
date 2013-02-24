package com.republicasmp.dev.pero_jump;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import org.bukkit.plugin.java.JavaPlugin;


public class jump extends JavaPlugin implements Listener
{
	
	
	    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    	if (cmd.getName().equalsIgnoreCase("jumpthere")){
	      Player player = (Player) sender;
	      Block block = player.getTargetBlock(null, 256);
	      if ((block == null) || (block.getType() == Material.AIR)) return false; //Doesn't let you tp to air :P
	      if ((block == null) || (block.getType() == Material.WATER)) return; //Ignores water
    	      if ((block == null) || (block.getType() == Material.LAVA)) return; //Ignores Lava
	      
	      Location dest = block.getLocation();
	      int y = dest.getBlockY();
	      if ((y <= 0) || (y > 256)) return false;
		
	      if (!player.hasPermission("pero.jump")) return false; //Permission Node; For Staff and Donators.
	      
	      	while (block.getType() != Material.AIR) {
	         block = block.getRelative(BlockFace.UP);
	         if (block == null) return false;
	         dest = block.getLocation();
	         if (dest.getBlockY() >= 256) return false;
	       }

	       Location src = player.getLocation();
	       dest.setPitch(src.getPitch());
	       dest.setYaw(src.getYaw());
	       dest.setX(dest.getX());
	       dest.setZ(dest.getZ());

	       player.teleport(dest);
		      player.sendMessage(ChatColor.LIGHT_PURPLE + "Time warp!");
	    return true;
	    	}
	    	return false;
	    }
	

	
	
	
	
  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
  }
  
  
  public class PlayerInteractListener 
    implements Listener
  {
	  
    public PlayerInteractListener()
    {
    }

    @EventHandler(ignoreCancelled=false)
    public void onPlayerInteract(PlayerInteractEvent event)
    {
      Player player = event.getPlayer();
      if (player == null) return;

      if (!player.hasPermission("pero.jump")) return; //Permission Node; For Staff and Donators.

      Action action = event.getAction();
      
      if (action !=Action.LEFT_CLICK_AIR)
    return; //Nothing happens if there is no block :P
    
      
      ItemStack item = event.getPlayer().getItemInHand();
      if ((item == null) || (item.getType() != Material.WATCH)) return; //Hold a clock

      Block block = player.getTargetBlock(null, 256);
      if ((block == null) || (block.getType() == Material.AIR)) return; //Doesn't let you tp to air :P
      if ((block == null) || (block.getType() == Material.WATER)) return; //Ignores water
      if ((block == null) || (block.getType() == Material.LAVA)) return; //Ignores Lava

      Location dest = block.getLocation();
      int y = dest.getBlockY();
      if ((y <= 0) || (y > 256)) return;

      while (block.getType() != Material.AIR) { //Puts you in a "empty" block
        block = block.getRelative(BlockFace.UP); //On top of the selected block
        if (block == null) return;
        dest = block.getLocation();
        if (dest.getBlockY() >= 256) return;
      }

      Location src = player.getLocation();
      dest.setPitch(src.getPitch());
      dest.setYaw(src.getYaw());
      dest.setX(dest.getX());
      dest.setZ(dest.getZ());

      player.teleport(dest);
     
    }
    @EventHandler(ignoreCancelled=false)
    public void onPlayerInteract1(PlayerInteractEvent event)
    {
    	if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
    		Player player = event.getPlayer();
    			
    		 if (!player.hasPermission("pero.jump")) return; //Permission Node; For Staff and Donators.
    		
    		 ItemStack item = event.getPlayer().getItemInHand();
    	      if ((item == null) || (item.getType() != Material.WATCH)) return; //Hold a clock

    	      Block block = player.getTargetBlock(null, 256);
    	      if ((block == null) || (block.getType() == Material.AIR)) return; //Doesn't let you tp to air :P
    	      if ((block == null) || (block.getType() == Material.WATER)) return; //Ignores water
    	      if ((block == null) || (block.getType() == Material.LAVA)) return; //Ignores Lava
    		
    	      Location dest = block.getLocation();
    	      int y = dest.getBlockY();
    	      if ((y <= 0) || (y > 256)) return;
    		
    		 
    		 while (block.getType() != Material.AIR) {
                 block = block.getRelative(BlockFace.UP);
                 if (block == null) return;
                 dest = block.getLocation();
                 if (dest.getBlockY() >= 256) return;
               }

               Location src = player.getLocation();
               dest.setPitch(src.getPitch());
               dest.setYaw(src.getYaw());
               dest.setX(dest.getX());
               dest.setZ(dest.getZ());

               player.teleport(dest);
    		     
    	}
    	
    }


  }
 
}
