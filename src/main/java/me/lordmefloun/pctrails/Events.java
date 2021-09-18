package me.lordmefloun.pctrails;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

    public PcTrails plugin;

    public Events(PcTrails plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){

        if (plugin.playerTrail.get(e.getPlayer().getUniqueId()) != null) {
            plugin.mysql.updatePlayerTrail(e.getPlayer().getUniqueId(), plugin.playerTrail.get(e.getPlayer().getUniqueId()).getNumber() );
            plugin.playerTrail.remove(e.getPlayer().getUniqueId());
        }
        else{
            plugin.mysql.updatePlayerTrail(e.getPlayer().getUniqueId(), 0);
        }


    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){


        if (plugin.mysql.getPlayerTrail(e.getPlayer().getUniqueId()) != 0) {
            plugin.playerTrail.put(e.getPlayer().getUniqueId(), ParticleObject.particleObjectByNumber(plugin.mysql.getPlayerTrail(e.getPlayer().getUniqueId())));
        }
    }









}
