package me.vert3xo.deathswap.commands;

import me.vert3xo.deathswap.DeathSwap;
import me.vert3xo.deathswap.runnable.TpRunnable;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeathSwapStart extends AbstractCommand {
    private DeathSwap plugin = DeathSwap.instance;

    public DeathSwapStart(String command) {
        super(command);
    }

    public DeathSwapStart(String command, String permission) {
        super(command, permission);
    }

    public DeathSwapStart(String command, boolean canConsoleUse) {
        super(command, canConsoleUse);
    }

    public DeathSwapStart(String command, String permission, boolean canConsoleUse) {
        super(command, permission, canConsoleUse);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        TpRunnable game = this.plugin.getGame();

        if (this.plugin.getPlayers().size() < 2) {
            sender.sendMessage(ChatColor.RED + "Not enough players.");
            return false;
        }

        if (!game.isGameRunning()) {
            game.setGameRunning(true);
            game.runTaskTimer(plugin, 24, 24);
            for (Player player : plugin.getPlayers()) {
                player.sendMessage(ChatColor.GREEN + "Game started.");
            }
            return false;
        }
        sender.sendMessage(ChatColor.RED + "Game is already running.");
        return false;
    }
}
