package me.vert3xo.deathswap.commands;

import me.vert3xo.deathswap.DeathSwap;
import me.vert3xo.deathswap.runnable.TpRunnable;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DeathSwapLeave extends AbstractCommand {
    private DeathSwap plugin = DeathSwap.instance;
    private ArrayList<Player> players = plugin.getPlayers();
    private TpRunnable game = plugin.getGame();

    public DeathSwapLeave(String command) {
        super(command);
    }

    public DeathSwapLeave(String command, String permission) {
        super(command, permission);
    }

    public DeathSwapLeave(String command, boolean canConsoleUse) {
        super(command, canConsoleUse);
    }

    public DeathSwapLeave(String command, String permission, boolean canConsoleUse) {
        super(command, permission, canConsoleUse);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (!this.players.contains(p)) {
            p.sendMessage(ChatColor.RED + "You are not in the game.");
            return false;
        }

        if (this.game.isGameRunning()) {
            p.sendMessage(ChatColor.RED + "Can not leave while the game is running.");
            return false;
        }

        this.players.remove(p);
        p.sendMessage(ChatColor.RED + "You left the game.");
        return false;
    }
}
