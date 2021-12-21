package creativeprevention.creativeprevention;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;


public class Reload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("cp")){
            try {
                String argOne = String.valueOf(args[0]);
                if (argOne.equalsIgnoreCase("reload")){
                    if(!(sender instanceof Player) || sender.isOp()) {
                        CreativePrevention.getInstance().reloadConfig();
                        sender.sendMessage(ChatColor.GOLD + "[CreativePrevention] " + ChatColor.GREEN + "Config reloaded!");
                        return true;
                    }else{
                        sender.sendMessage(ChatColor.GOLD + "[CreativePrevention] "+ChatColor.RED+"You do not have the required permissions to run this command!");
                        return true;
                    }
                } else if(argOne.equalsIgnoreCase("help")){
                    sender.sendMessage(ChatColor.GOLD + "[CreativePrevention] " + ChatColor.GREEN + "/cp reload - Reloads configuration file");
                    return true;
                } else{
                    sender.sendMessage(ChatColor.GOLD+"[CreativePrevention] "+ChatColor.RED+"Invalid Command. Use "+ChatColor.BLUE+"/cp help");
                    return true;
                }
            }catch(Exception e){
                sender.sendMessage(ChatColor.GOLD+"[CreativePrevention] "+ChatColor.RED+"Invalid Command. Use "+ChatColor.BLUE+"/cp help");
            }
        }

        return true;
    }
}
