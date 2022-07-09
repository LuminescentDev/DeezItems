package xyz.akiradev.deezitems.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.akiradev.deezitems.utils.DeezItem;
import xyz.akiradev.deezitems.DeezItems;
import xyz.akiradev.deezitems.utils.TextUtils;
import xyz.akiradev.deezitems.utils.ConfigManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandDeez implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try{
            switch (args[0].toLowerCase()){
                case "help":
                    helpInfo(sender);
                    break;
                case "info":
                    pluginInfo(sender);
                    break;
                case "list":
                    listItems(sender);
                    break;
                case "give":
                    if(sender.hasPermission("deezitems.give")){
                        give(sender, args);
                    } else {
                        TextUtils.warnPlayer(sender, "You don't have permission to use this command.");
                    }
                    break;
                case "reload":
                    if(sender.hasPermission("deezitems.reload")){
                        ConfigManager.reloadConfig();
                        TextUtils.sendMessage(sender, "Config reloaded");
                    } else {
                        TextUtils.warnPlayer(sender, "You don't have permission to use this command.");
                    }
                    break;
                default:
                    TextUtils.warnPlayer(sender, "Unknown command. Use /deezitems help for help.");
                    break;
            }
        }catch (Exception e){
            TextUtils.warnPlayer(sender, "Something went wrong");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        String cmd = String.join(" ", args);
        List<String> completions = new ArrayList<>();

        /* suggest commands */
        if(args.length == 1){
            completions.add("help");
            completions.add("info");
            completions.add("list");
            if(sender.hasPermission("deezitems.give")){
                completions.add("give");
            }
            if(sender.hasPermission("deezitems.reload")){
                completions.add("reload");
            }
        }

        /* suggest items */
        if(args.length == 2){
            completions.addAll(DeezItems.getItemNames());
        }



        return completions;
    }

    /**
     * Give an item to a player
     * @param sender The sender of the command
     * @param args The arguments of the command
     */
    public void give(CommandSender sender, String[] args){
        if(!(sender instanceof Player)){
            TextUtils.warnPlayer(sender, "You must be a player to use this command");
        } else {
            Player player = (Player)sender;
            int amount = 1;
            if(args.length > 2){
                amount = Integer.parseInt(args[2]);
            }

            ItemStack item = DeezItems.getDeezItem(args[1]).Generate(amount);
            if(item != null) {
                player.getInventory().addItem(item);
                TextUtils.sendMessage(sender, "Given " +  DeezItems.getDeezItem(args[1]).getName());
            }else{
                TextUtils.warnPlayer(sender, "Item not found");
            }
        }
    }

    /**
     * Sends list of items to player
     * @param sender
     */
    public void listItems(CommandSender sender) {
        List<String> items = new ArrayList();
        Iterator iterator = DeezItems.getItems().iterator();

        while(iterator.hasNext()) {
            DeezItem item = (DeezItem)iterator.next();
            String itemName = item.getRarity().getColor() + item.getName();
            items.add(itemName);
        }
        TextUtils.multilineReply(sender, "&8&l---------Current Items---------", "&8&l-------------------------------", items);
    }

    /**
     * Sends plugin info to the player
     * @param sender
     */
    public void pluginInfo(CommandSender sender) {
        List<String> infoLines = new ArrayList();

        infoLines.add("&8&l Author: " + DeezItems.getInstance().getDescription().getAuthors());
        infoLines.add("&8&l Version: " + DeezItems.getInstance().getDescription().getVersion());
        infoLines.add("&8&l Description: " + DeezItems.getInstance().getDescription().getDescription());
        infoLines.add("&8&l Website: https://www.akiradev.xyz");
        infoLines.add("&8&l Discord: https://discord.gg/DRUXNsHKZ5");

        TextUtils.multilineReply(sender, "&8&l---------DeezItems Info---------", "&8&l-------------------------------", infoLines);
    }

    /**
     * Sends help info to the player
     * @param sender
     */
    public void helpInfo(CommandSender sender) {
        List<String> infoLines = new ArrayList();

        infoLines.add("&8&l /deezitems list - Lists all items");
        infoLines.add("&8&l /deezitems info - Shows plugin info");
        infoLines.add("&8&l /deezitems help - Shows this help info");
        if (sender.hasPermission("deezitems.admin")) {
            infoLines.add("&8&l /deezitems reload - Reloads the plugin");
            infoLines.add("&8&l /deezitems give <player> <item> - Gives a player an item");
        }

        TextUtils.multilineReply(sender, "&8&l---------DeezItems Help---------", "&8&l-------------------------------", infoLines);
    }
}
