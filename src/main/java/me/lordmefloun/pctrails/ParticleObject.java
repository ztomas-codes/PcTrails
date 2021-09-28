package me.lordmefloun.pctrails;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.ArrayList;

public class ParticleObject {

    public static Configuration config = PcTrails.getPlugin(PcTrails.class).getConfig();
    public static ArrayList<ParticleObject> particleObjectList = new ArrayList<>();
    
    private ParticleEffect particleType;
    private int intensity;
    private PcTrails plugin;
    private int number;
    private double offset;

    public ParticleObject(PcTrails plugin, ParticleEffect particleType, int intensity, int number, double offset){
        this.plugin = plugin;
        this.particleType = particleType;
        this.intensity = intensity;
        this.number = number;
        this.offset = offset;
    }

    public static void loadParticlesFromConfig(PcTrails pl){
        ConfigurationSection configSection = config.getConfigurationSection("Particles");
        if (configSection != null) {
            for (String key : configSection.getKeys(false)) {
                String type = config.getString("Particles." + key + ".type");
                int intensity = config.getInt("Particles." + key + ".intensity");
                double offset = config.getDouble("Particles." + key + ".offset");
                int number = Integer.parseInt( key);
                if (number != 0)
                    particleObjectList.add(new ParticleObject(pl, ParticleEffect.valueOf(type), intensity, number, offset));
                else{
                    System.out.println("Particle 0 cannot be added, please change it to 1 or something else");
                }


            }
        }
    }

    public void spawn(Player p){
        if (intensity == 0){
            getParticleType().display(p.getLocation().add(0, 1, 0));
        }
        else {
            new ParticleBuilder(getParticleType(), p.getLocation().add(0, offset, 0))
                    .setAmount(getIntensity())
                    .display();
        }
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

    public int getIntensity() {
        return intensity;
    }

    public int getNumber() {
        return number;
    }
}
