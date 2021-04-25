package me.vert3xo.deathswap.commands;

import me.vert3xo.deathswap.DeathSwap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractCommand implements CommandExecutor {
    private String command;
    private String permission;
    private boolean canConsoleUse = false;

    public AbstractCommand(String command) {
        this.command = command;
        this.permission = null;
        setExecutor();
    }

    public AbstractCommand(String command, String permission) {
        this.command = command;
        this.permission = permission;
        setExecutor();
    }

    public AbstractCommand(String command, boolean canConsoleUse) {
        this.command = command;
        this.permission = null;
        this.canConsoleUse = canConsoleUse;
        setExecutor();
    }

    public AbstractCommand(String command, String permission, boolean canConsoleUse) {
        this.command = command;
        this.permission = permission;
        this.canConsoleUse = canConsoleUse;
        setExecutor();
    }

    public abstract boolean execute(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!canConsoleUse && !(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players may use this command!");
            return false;
        }

        if (permission != null && !commandSender.hasPermission(permission)) {
            commandSender.sendMessage(ChatColor.RED + "You don't have the permission to use this command!");
            return false;
        }
        return execute(commandSender, args);
    }

    private void setExecutor() {
        DeathSwap.instance.getCommand(command).setExecutor(this);
    }
}
