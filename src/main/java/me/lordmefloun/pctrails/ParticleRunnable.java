package me.lordmefloun.pctrails;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;

public class ParticleRunnable {
    PcTrails plugin;

    public ParticleRunnable(PcTrails plugin){
        this.plugin = plugin;
    }

    public void start(){
        new BukkitRunnable(){

            @Override
            public void run(){


                for (Map.Entry<UUID, ParticleObject> entry : plugin.playerTrail.entrySet()) {

                    Player p = Bukkit.getPlayer(entry.getKey());
                    entry.getValue().spawn(p);


                }




            }
        }.runTaskTimer(plugin, 0, 2);
    }
}
