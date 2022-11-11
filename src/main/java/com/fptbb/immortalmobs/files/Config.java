package com.fptbb.immortalmobs.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Config {
    private static File file;
    private static FileConfiguration customFile;

    //Finds or generates the custom config file

    public static void setup(){
        file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("ImmortalMobs")).getDataFolder(), "immortalMobs.yml");

        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                System.out.println("Cannot create config file.");
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void save(){
        try{
            customFile.save(file);
        }catch (IOException e){
            System.out.println("Couldn't save file");
        }
    }

    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void setDefaults() {
        customFile.addDefault("Mobs", new String[] { "FOX" });
        customFile.addDefault("Announce.Public", Boolean.TRUE);
        customFile.addDefault("Announce.Private", Boolean.TRUE);
        customFile.addDefault("KillPlayer", Boolean.TRUE);
        customFile.addDefault("Timeout", 60L);
        customFile.addDefault("TurnImmortal", Boolean.TRUE);
        customFile.addDefault("ExemptPlayers", new String[] { "fptbb" });
        customFile.addDefault("Messages.AnnounceMessage", "%player% tried to kill a peaceful mob, shame on you.");
        customFile.addDefault("Messages.PrivateAnnounce", "HOW DARE YOU!!!Now you GET WHAT YOU DESERVE!!!");
        customFile.setInlineComments("ExemptPlayers", List.of("List of players to exclude from the Plugin. By Default, if a player is in creative mode, it will already be excluded."));
        customFile.setInlineComments("Messages", List.of("Set the messages for any text output by the plugin. REQUIRED. Insert %player% as a placeholder for the player name."));
        customFile.setInlineComments("TurnImmortal", List.of("Turn the entity into invulnerable."));
        customFile.setInlineComments("Timeout", List.of("Set the timeout between the private message to the player, if enabled, and the kill command."));
        customFile.setInlineComments("KillPlayer", List.of("Set if the player then will be killed. REQUIRED"));
        customFile.setInlineComments("Announce.Private", List.of("Set if the player will receive a message after trying. REQUIRED"));
        customFile.setInlineComments("Announce.Public", List.of("Set if it will or not Announce the AnnounceMessage to public when a player tries to kill one of the selected Mobs. REQUIRED"));
        customFile.setInlineComments("Mobs", List.of("Add the mobs here that you want to make immortal. REQUIRED"));
        customFile.options().setFooter(Collections.singletonList("If you have any idea to make it better, contact me via email lucas@fptbb.com"));
        customFile.options().setHeader(Collections.singletonList("Immortal Mobs by @fptbb"));
        save();
    }
}