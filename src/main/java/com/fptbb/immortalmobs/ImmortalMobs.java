package com.fptbb.immortalmobs;

import com.fptbb.immortalmobs.commands.MainCommand;
import com.fptbb.immortalmobs.commands.MainCommandTabCompletion;
import com.fptbb.immortalmobs.files.Config;
import java.util.Collections;
import java.util.List;

import com.fptbb.immortalmobs.listeners.EntityDamage;
import com.fptbb.immortalmobs.listeners.EntityDamageByEntity;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ImmortalMobs extends JavaPlugin {
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Config.setup();
        FileConfiguration config = Config.get();
        config.addDefault("Mobs", new String[] { "Fox" });
        config.addDefault("Announce.Public", Boolean.TRUE);
        config.addDefault("Announce.Private", Boolean.TRUE);
        config.addDefault("KillPlayer", Boolean.TRUE);
        config.addDefault("Timeout", 60L);
        config.addDefault("TurnImmortal", Boolean.TRUE);
        config.addDefault("ExemptPlayers", new String[] { "fptbb" });
        config.addDefault("Messages.AnnounceMessage", "%player% tried to kill a peaceful mob, shame on you.");
        config.addDefault("Messages.PrivateAnnounce", "HOW DARE YOU!!!Now you GET WHAT YOU DESERVE!!!");
        config.setInlineComments("ExemptPlayers", List.of("List of players to exclude from the Plugin. By Default, if a player is in creative mode, it will already be excluded."));
        config.setInlineComments("Messages", List.of("Set the messages for any text output by the plugin. REQUIRED. Insert %player% as a placeholder for the player name."));
        config.setInlineComments("TurnImmortal", List.of("Turn the entity into invulnerable."));
        config.setInlineComments("Timeout", List.of("Set the timeout between the private message to the player, if enabled, and the kill command."));
        config.setInlineComments("KillPlayer", List.of("Set if the player then will be killed. REQUIRED"));
        config.setInlineComments("Announce.Private", List.of("Set if the player will receive a message after trying. REQUIRED"));
        config.setInlineComments("Announce.Public", List.of("Set if it will or not Announce the AnnounceMessage to public when a player tries to kill one of the selected Mobs. REQUIRED"));
        config.setInlineComments("Mobs", List.of("Add the mobs here that you want to make immortal. REQUIRED"));
        config.options().setFooter(Collections.singletonList("If you have any idea to make it better, contact me via email lucas@fptbb.com"));
        config.options().setHeader(Collections.singletonList("Immortal Mobs by @fptbb"));
        config.options().copyDefaults(true);
        Config.save();
        getLogger().info("Mob Saver Started by Fp");
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
        getCommand("immortalMobs").setExecutor(new MainCommand());
        getCommand("immortalMobs").setTabCompleter(new MainCommandTabCompletion());
    }
}
