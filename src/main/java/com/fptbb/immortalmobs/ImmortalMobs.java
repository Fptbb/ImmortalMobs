package com.fptbb.immortalmobs;

import com.fptbb.immortalmobs.commands.MainCommand;
import com.fptbb.immortalmobs.commands.MainCommandTabCompletion;
import com.fptbb.immortalmobs.files.Config;
import java.util.Objects;

import com.fptbb.immortalmobs.listeners.EntityDamage;
import com.fptbb.immortalmobs.listeners.EntityDamageByEntity;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ImmortalMobs extends JavaPlugin {
    private static ImmortalMobs plugin;
    public void onEnable() {
        plugin = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Config.setup();
        FileConfiguration config = Config.get();

        config.options().copyDefaults(true);
        Config.setDefaults();
        Config.save();
        getLogger().info("Mob Saver Started by Fp");
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
        Objects.requireNonNull(getCommand("immortalMobs")).setExecutor(new MainCommand());
        Objects.requireNonNull(getCommand("immortalMobs")).setTabCompleter(new MainCommandTabCompletion());
    }

    public static ImmortalMobs getPlugin() {
        return plugin;
    }
}
