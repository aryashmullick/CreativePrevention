<h1>CreativePrevention</h1>
<b>A Minecraft Spigot Plugin which limits the powers of the players in creative mode.<b><br>


Current Version : `1.5` <br>
Minecraft Version: `1.16 and above`<br>

<a href="https://github.com/AR9157/CreativePrevention/blob/main/CreativePrevention-1.jar?raw=true">Click here to download</a><br><br>
  
<a href="https://www.spigotmc.org/resources/creativeprevention.98569/">View this plugin on Spigotmc.org</a><br>

Features->
> Stop players in creative from hitting other entities. <br>
> Stop players in creative from dropping items.<br>
> Stop players in creative from interacting with specific blocks.<br>
> Stop players in creative from placing specific blocks.<br>
> Clear inventory when player changes gamemode from creative to survival.<br>
> `PreventionWhitelist`: Players who will not be affected by the plugin.<br>
> `PreventionBlocks`: Blocks which players cannot interact with or place.<br>

Commands-> <br>
> `/cp help`: Help command for the plugin.<br>
> `/cp reload`: Reload the configuration file of the plugin.<br>
  
  

Config.yml ->
```
# Welcome to CreativePrevention's config
# A plugin made by AR_9157

# Stop a player in creative to drop items.
PreventionDrop: true
# The message which will be displayed when a player in creative mode drops an item.
PreventionDropMessage: "§c You are not allowed to drop items while in creative mode!"

# Stop a player in creative to interact with blocks in the "preventionBlocks" list
PreventionInteract: true

# Blocks that the player cannot interact with if in creative mode.
# Warning: Block names should be in uppercase!
PreventionBlocks:
  - CHEST
  - BARREL
  - FURNACE
  - ENDER_CHEST
  - CHEST_MINECART
  - HOPPER
  - SHULKER_BOX
  - ITEM_FRAME
  - WITHER_SKULL
  - TNT
  - TNT_MINECART
  - FURNACE_MINECART
  - BLAST_FURNACE
  - SMOKER
  - BEDROCK
  - BARRIER

# Players who will not be affected by CreativePrevention.
# CASE SENSITIVE!
# One name should always be present! (Default: AR_9157)
PreventionWhitelist:
  - AR_9157

# This message which will be displayed when a player in creative mode interacts with the blocks in "preventionBlocks".
PreventionInteractMessage: "§c You are not allowed to interact with this block while in creative mode!"
PreventionInteractArmorStand: true
PreventionInteractArmorStandMessage: "§c You are not allowed to interact with Armor stands while in creative mode!"

# Clear items of players when they switch from creative to survival. PreventionWhitelist players won't be affected
ClearItemsWhenGameModeChange: true
# This message will be displayed when a player switches from survival to creative and is not PreventionWhitelisted.
ClearItemsWhenGameModeChangeMessage: "§c Your items were cleared as you switched from creative to survival."

# Stop a player in creative mode from damaging other entities.
PreventionHit: true
# if Enabled, this will stop a player in creative mode from hitting all types of entities. Disable if you want
# the player to be stopped only when hitting another player.
PreventionHitAllEntities: true
# This message which will be displayed when a player in creative mode hits an entity.
PreventionHitMessage: "§c You are not allowed to hit entities while in creative mode!"

# Stops players from placing blocks in PreventionBlocks
PreventionPlace: true
#This message will be displayed when a player in creative mode tries to place a block blacklisted in PreventionBlocks
PreventionPlaceMessage: "§c You are not allowed to place this block while in creative!"
```

