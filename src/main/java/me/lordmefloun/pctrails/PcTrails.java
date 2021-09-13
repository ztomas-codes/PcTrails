package me.lordmefloun.pctrails;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class PcTrails extends JavaPlugin {


    public HashMap<UUID, ParticleObject> playerTrail = new HashMap<>();

    @Override
    public void onEnable() {



        saveDefaultConfig();
        ParticleObject.loadParticlesFromConfig(this);

        getCommand("pctrail").setExecutor(new Command(this));
        getServer().getPluginManager().registerEvents(new Events(this), this);



        ParticleRunnable pr = new ParticleRunnable(this);
        pr.start();




    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
