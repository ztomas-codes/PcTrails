package me.lordmefloun.pctrails;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.ArrayList;

public class ParticleObject {


    public static Configuration config = PcTrails.getPlugin(PcTrails.class).getConfig();

    private ParticleEffect particleType;
    private double intensity;
    public PcTrails plugin;
    private Integer number;

    public static ArrayList<ParticleObject> particleObjectList = new ArrayList<>();



    public ParticleObject(PcTrails plugin, ParticleEffect particleType, double intensity, Integer number){


        this.plugin = plugin;
        this.particleType = particleType;
        this.intensity = intensity;
        this.number = number;


    }


    public static void loadParticlesFromConfig(PcTrails pl){

        ConfigurationSection configSection = config.getConfigurationSection("Particles");
        if (configSection != null) {
            for (String key : configSection.getKeys(false)) {

                String type = config.getString("Particles." + key + ".type");
                Double intensity = config.getDouble("Particles." + key + ".intensity");
                Integer number = config.getInt("Particles." + key + ".number");


                particleObjectList.add(new ParticleObject( pl, ParticleEffect.valueOf(type),  intensity, number));



            }
        }


    }

    public void spawn(Player p){


        getParticleType().display(p.getLocation().add(0, 1, 0));
    }


    public static ParticleObject particleObjectByNumber(Integer num){
        for (ParticleObject po : particleObjectList){
            if (po.getNumber() == num){
                return po;
            }
        }
        return null;
    }



    public ParticleEffect getParticleType() {
        return particleType;
    }


    public double getIntensity() {
        return intensity;
    }

    public Integer getNumber() {
        return number;
    }
}
