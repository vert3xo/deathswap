package me.vert3xo.deathswap.commands;

import me.vert3xo.deathswap.DeathSwap;
import me.vert3xo.deathswap.runnable.TpRunnable;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DeathSwapJoin extends AbstractCommand {
    private DeathSwap plugin = DeathSwap.instance;
    private ArrayList<Player> players = plugin.getPlayers();
    private TpRunnable game = plugin.getGame();

    public DeathSwapJoin(String command) {
        super(command);
    }

    public DeathSwapJoin(String command, String permission) {
        super(command, permission);
    }

    public DeathSwapJoin(String command, boolean canConsoleUse) {
        super(command, canConsoleUse);
    }

    public DeathSwapJoin(String command, String permission, boolean canConsoleUse) {
        super(command, permission, canConsoleUse);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (this.players.contains(p)) {
            p.sendMessage(ChatColor.GREEN + "You already joined.");
            return false;
        }

        if (this.game.isGameRunning()) {
            p.sendMessage(ChatColor.RED + "Cannot join while the game is running.");
            return false;
        }

        this.players.add(p);
        p.sendMessage(ChatColor.GREEN + "You joined the game.");
        return false;
    }
}
