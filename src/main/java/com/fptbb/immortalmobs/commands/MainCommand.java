package com.fptbb.immortalmobs.commands;

import com.fptbb.immortalmobs.files.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            if(sender.hasPermission("immortalmobs.config")){
                command(sender, args);
            } else {
                sender.sendMessage("[ImmortalMobs] You don't have permission to do that.");
            }
        } else {
            command(sender, args);
        }
        return true;
    }

    private void command(@NotNull CommandSender sender, @NotNull String[] args) {
        if(args.length > 0 && args[0].equals("reload")){
            sender.sendMessage("[ImmortalMobs] Okidoki, reloading configs.");
            Config.reload();
        } else if(args.length > 0 && args[0].equals("info")) {
            sender.sendMessage("[ImmortalMobs] Hello ( ^_^)／ I'm a simple plugin to help you make some mobs immortal.");
        } else {
            sender.sendMessage("[ImmortalMobs] Hello ( ^_^)／");
        }
    }

}