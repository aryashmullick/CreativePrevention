package creativeprevention.creativeprevention;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;



public final class CreativePrevention extends JavaPlugin implements Listener {

    private static CreativePrevention instance;


    @Override
    public void onEnable() {
        Reload commands = new Reload();
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD +       "-------------------------------------------------------------");
        getServer().getConsoleSender().sendMessage(ChatColor.RED +        "                   CreativePrevention");
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA +       "                  Plugin by"+ ChatColor.BLUE+" AR_9157");
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "                      Status: Enabled");
        getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_PURPLE +"                       Config Loaded");
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD +       "-------------------------------------------------------------");
        getCommand("cp").setExecutor(commands);
        instance = this;
    }

    public static CreativePrevention getInstance(){
        return instance;
    }

    @EventHandler
    public void OnGameModeChange(PlayerGameModeChangeEvent event){
        Player player = event.getPlayer();
        FileConfiguration config = this.getConfig();
        if(event.getNewGameMode() == GameMode.SURVIVAL && !Objects.requireNonNull(config.getList("PreventionWhitelist")).contains(player.getName()) && player.getGameMode()==GameMode.CREATIVE && config.getBoolean("ClearItemsWhenGameModeChange")){
            player.getInventory().clear();
            try{player.sendMessage(config.getString("ClearItemsWhenGameModeChangeMessage"));}
            catch(Exception ignored){player.sendMessage("§c Your items were cleared as you switched from creative to survival.");}
            }
        }


    @EventHandler
    public void CreativePreventionDrop(PlayerDropItemEvent e){
        Player player = e.getPlayer();
        FileConfiguration config = this.getConfig();
        try{
            if(config.getBoolean("PreventionDrop") && !Objects.requireNonNull(config.getList("PreventionWhitelist")).contains(player.getName()) && player.getGameMode()==GameMode.CREATIVE){
                e.setCancelled(true);
                player.sendMessage(ChatColor.RED+config.getString("PreventionDropMessage"));
                }
            }
        catch(Exception ignored){}
    }

    @EventHandler
    public void CreativePreventionPlace(BlockPlaceEvent e){
        FileConfiguration config = this.getConfig();
        Player p = e.getPlayer();
        try{
            Material block = Objects.requireNonNull(e.getBlockPlaced()).getType();
            if (config.getBoolean("PreventionPlace") && p.getGameMode() == GameMode.CREATIVE && !Objects.requireNonNull(config.getList("PreventionWhitelist")).contains(p.getName())) {
                for (Object key : Objects.requireNonNull(config.getList("PreventionBlocks"))) {
                    try {
                        Material m = Material.getMaterial((String) key);
                        if (block == m) {
                            e.setCancelled(true);
                            try{ p.sendMessage(config.getString("PreventionPlaceMessage"));}
                            catch(Exception ignored){ p.sendMessage("\"§c You are not allowed to place this box while in creative!\"");}
                            break;
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
        }catch (Exception ignored){}
    }

    @EventHandler
    public void CreativeProtectionInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        FileConfiguration config = this.getConfig();
        try{
            Material block = Objects.requireNonNull(e.getClickedBlock()).getType();
            if (config.getBoolean("PreventionInteract") && player.getGameMode() == GameMode.CREATIVE && !Objects.requireNonNull(config.getList("PreventionWhitelist")).contains(player.getName())) {
                for (Object key : Objects.requireNonNull(config.getList("PreventionBlocks"))) {
                    try {
                        Material m = Material.getMaterial((String) key);
                        if (block == m) {
                            e.setCancelled(true);
                            try{player.sendMessage(config.getString("PreventionInteractMessage"));}
                            catch(Exception ignored){e.getPlayer().sendMessage("§c You are not allowed to interact with this block while in creative mode!");}
                            break;
                        }
                    }catch (Exception exception) {
                        getServer().getConsoleSender().sendMessage(ChatColor.RED+ "An error occurred while executing ProtectionInteract.");
                    }
                }
            }
        }catch (Exception ignored){}
    }


    @EventHandler
    public void CreativePreventionInteractArmorStand(PlayerInteractAtEntityEvent e){
        FileConfiguration config = this.getConfig();
        if(e.getRightClicked() instanceof ArmorStand && config.getBoolean("PreventionInteract")){
            if(e.getPlayer().getGameMode() == GameMode.CREATIVE && !Objects.requireNonNull(config.getList("PreventionWhitelist")).contains(e.getPlayer().getName())){
                e.setCancelled(true);
                try{e.getPlayer().sendMessage(config.getString("PreventionInteractArmorStandMessage"));}
                catch(Exception ignored){e.getPlayer().sendMessage("§c You are not allowed to interact with Armor stands while in creative mode!");}
            }
        }
    }


    @EventHandler
    public void CreativePreventionDamage(EntityDamageByEntityEvent e){
        FileConfiguration config = this.getConfig();
        Entity entity = e.getDamager();
        if(entity instanceof Player && config.getBoolean("PreventionHit")){
            Player p = (Player) e.getDamager();
            if(p.getGameMode() == GameMode.CREATIVE && !Objects.requireNonNull(config.getList("PreventionWhitelist")).contains(p.getName())) {
                if(config.getBoolean("PreventionHitAllEntities")) {
                    e.setCancelled(true);
                    try {p.sendMessage(config.getString("PreventionHitMessage"));}
                    catch (Exception ignored) {p.sendMessage("§c You are not allowed to hit entities while in creative mode!");}
                }else if(!(config.getBoolean("PreventionHitAllEntities")) && e.getEntity() instanceof Player){
                    e.setCancelled(true);
                    try {p.sendMessage(config.getString("PreventionHitMessage"));}
                    catch (Exception ignored) {p.sendMessage("§c You are not allowed to hit players while in creative mode!");}
                }
            }
        }
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD +       "-------------------------------------------------------------");
        getServer().getConsoleSender().sendMessage(ChatColor.RED +        "                    CreativePrevention");
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA +       "                  Plugin by"+ ChatColor.BLUE+" AR_9157");
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED +   "                     Status: Disabled");
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_PURPLE +"                      Config Loaded");
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD +       "-------------------------------------------------------------");
    }
}
