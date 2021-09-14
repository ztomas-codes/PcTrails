package me.lordmefloun.pctrails;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;

public class ParticleRunnable extends BukkitRunnable {
    PcTrails plugin;

    public ParticleRunnable(PcTrails plugin){
        this.plugin = plugin;
    }

    @Override
    public void run(){


        for (Map.Entry<UUID, ParticleObject> entry : plugin.playerTrail.entrySet()) {

            Player p = Bukkit.getPlayer(entry.getKey());


            for (int j = 0; j < entry.getValue().getIntensity(); j++) {

                entry.getValue().getParticleType().display(p.getLocation());
            }



            entry.getValue().spawn(p);


        }




    }
}
