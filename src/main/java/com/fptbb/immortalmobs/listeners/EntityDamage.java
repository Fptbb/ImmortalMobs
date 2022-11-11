package com.fptbb.immortalmobs.listeners;

import com.fptbb.immortalmobs.files.Config;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Objects;

public class EntityDamage implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        try {
            if(Objects.requireNonNull(Config.get().getList("Mobs")).contains(e.getEntity().getName())){
                e.getEntity().setInvulnerable(true);
            }
        } catch(Exception err) {
            Bukkit.getLogger().info("Plugin Captured some error: " + err + " Probably your config is wrong.");
        }
    }
}