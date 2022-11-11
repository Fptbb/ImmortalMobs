package com.fptbb.immortalmobs.listeners;

import com.fptbb.immortalmobs.files.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class EntityDamageByEntity implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e){
        try {
            FileConfiguration config = Config.get();
            if(Objects.requireNonNull(config.getList("Mobs")).contains(e.getEntity().getName())){
                if(e.getDamager() instanceof Player p) {
                    if(!Objects.requireNonNull(config.getList("ExemptPlayers")).contains(p.getName()) && !p.getGameMode().equals(GameMode.CREATIVE)){
                        if(config.getBoolean("TurnImmortal")){
                            e.setDamage(0);
                            (e.getEntity()).setInvulnerable(true);
                        }
                        if(config.getBoolean("Announce.Private")){
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("Messages.PrivateAnnounce")).replace("%player%", p.getName() )));
                        }
                        Plugin plugin = Bukkit.getPluginManager().getPlugin("ImmortalMobs");
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            if(config.getBoolean("Announce.Public")){
                                for(Player ps : Bukkit.getOnlinePlayers()) {
                                    ps.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("Messages.AnnounceMessage")).replace("%player%", p.getName() )));
                                }
                            }
                            if(config.getBoolean("KillPlayer")){
                                p.setHealth(0D);
                            }
                        }, config.getLong("Timeout"));

                    }
                } else {
                    e.getEntity().setInvulnerable(false);
                }
            }
        } catch(Exception err) {
            Bukkit.getLogger().info("Plugin Captured some error: " + err.toString() + " Probably your config is wrong.");
        }
    }
}
