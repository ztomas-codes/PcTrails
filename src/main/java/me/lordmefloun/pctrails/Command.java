package me.lordmefloun.pctrails;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {


    PcTrails plugin;

    public Command(PcTrails plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {



        if (args.length == 3){

            if (args[0].equalsIgnoreCase("set")){

                if (Bukkit.getPlayer(args[1]) != null){

                    if(ParticleObject.particleObjectByNumber(Integer.parseInt( args[2])) != null){


                        Player p = Bukkit.getPlayer(args[1]);

                        plugin.playerTrail.put(p.getUniqueId(), ParticleObject.particleObjectByNumber(Integer.parseInt( args[2])));

                        Utils.sendSenderMessage(sender, "&aÚspešně přiřazen trail");


                    } else Utils.sendSenderMessage(sender, "&cParticle nenalezen");


                } else Utils.sendSenderMessage(sender, "&cHrac nenalezen");

            } else Utils.sendSenderMessage(sender, "&cSpatne pouziti prikazu &l/pctrail set (hrac) (cislo trailu)");



        } else Utils.sendSenderMessage(sender, "&cSpatne pouziti prikazu &l/pctrail set (hrac) (cislo trailu)");


        return false;
    }
}
