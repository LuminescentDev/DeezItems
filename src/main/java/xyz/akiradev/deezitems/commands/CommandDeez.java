package xyz.akiradev.deezitems.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.akiradev.deezitems.manager.LocaleManager;
import xyz.akiradev.deezitems.utils.DeezItem;
import xyz.akiradev.deezitems.DeezItems;
import xyz.akiradev.deezitems.utils.TextUtils;
import xyz.akiradev.pluginutils.utils.HexUtils;
import xyz.akiradev.pluginutils.utils.StringPlaceholders;

import java.util.ArrayList;
import java.util.List;

public class CommandDeez implements CommandExecutor, TabCompleter {
    private final LocaleManager localeManager = DeezItems.getInstance().getManager(LocaleManager.class);
    private DeezItems plugin;

    public CommandDeez(DeezItems plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        LocaleManager localeManager = this.plugin.getManager(LocaleManager.class);
        try{
            if(args.length == 0){
                TextUtils.warnPlayer(sender, localeManager.getLocaleMessage("help"));
                return true;
            }
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
                        TextUtils.warnPlayer(sender, localeManager.getLocaleMessage("no-permission-command"));
                    }
                    break;
                case "reload":
                    if(sender.hasPermission("deezitems.reload")){
                        this.plugin.reload();
                        localeManager.sendMessage(sender, "reload-success");
                    } else {
                        TextUtils.warnPlayer(sender, localeManager.getLocaleMessage("no-permission-command"));
                    }
                    break;
                default:
                    TextUtils.warnPlayer(sender, localeManager.getLocaleMessage("unknown-command"));
                    break;
            }
        }catch (Exception e){
            TextUtils.warnPlayer(sender, localeManager.getLocaleMessage("error-occurred"));
            DeezItems.getInstance().getLogger().warning(e.getMessage());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
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
        if(args.length == 2 && args[0].equalsIgnoreCase("give")){
            completions.addAll(DeezItems.getInstance().getItemNames());
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
                localeManager.sendMessage(player, "item-given", StringPlaceholders.single("item", DeezItems.getDeezItem(args[1]).getName()));
            }else{
                TextUtils.warnPlayer(sender, localeManager.getLocaleMessage("no-item-found"));
            }
        }
    }

    /**
     * Sends list of items to player
     */
    public void listItems(CommandSender sender) {
        ArrayList<String> items = new ArrayList<>();

        for (DeezItem item : DeezItems.getInstance().getItems()) {
            String itemName = item.getRarityColor() + item.getName();
            items.add(itemName);
        }
        TextUtils.multilineReply(sender, "&8&l---------Current Items---------", "&8&l-------------------------------", items);
    }

    /**
     * Sends plugin info to the player
     */
    public void pluginInfo(CommandSender sender) {
        List<String> infoLines = new ArrayList<>();

        infoLines.add("&8&l Author: " + DeezItems.getInstance().getDescription().getAuthors());
        infoLines.add("&8&l Version: " + DeezItems.getInstance().getDescription().getVersion());
        infoLines.add("&8&l Description: " + DeezItems.getInstance().getDescription().getDescription());
        infoLines.add("&8&l Website: https://www.akiradev.xyz");
        infoLines.add("&8&l Discord: https://discord.gg/DRUXNsHKZ5");

        TextUtils.multilineReply(sender, "&8&l---------DeezItems Info---------", "&8&l-------------------------------", infoLines);
    }

    /**
     * Sends help info to the player
     */
    public void helpInfo(CommandSender sender) {
        LocaleManager localeManager = this.plugin.getManager(LocaleManager.class);
        List<String> infoLines = new ArrayList<>();

        infoLines.add(localeManager.getLocaleMessage("help"));
        infoLines.add(localeManager.getLocaleMessage("help-list"));
        infoLines.add(localeManager.getLocaleMessage("help-info"));
        if (sender.hasPermission("deezitems.admin")) {
            infoLines.add(localeManager.getLocaleMessage("help-reload"));
            infoLines.add(localeManager.getLocaleMessage("help-give"));
        }

        TextUtils.multilineReply(sender, "&8&l---------DeezItems Help---------", "&8&l-------------------------------", infoLines);
    }
}
