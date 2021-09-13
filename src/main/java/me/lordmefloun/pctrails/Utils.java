package me.lordmefloun.pctrails;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {
    public static void sendMessage(Player p, String message){
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));


    }


    public static void sendConsoleMessage(String message){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void sendSenderMessage(CommandSender sender, String message){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static boolean senderPlayerType(CommandSender sender){

        return (sender instanceof Player);

    }



}
