package me.lordmefloun.pctrails;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

    public PcTrails plugin;

    public Events(PcTrails plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){

        plugin.playerTrail.remove(e.getPlayer().getUniqueId());

    }
}
