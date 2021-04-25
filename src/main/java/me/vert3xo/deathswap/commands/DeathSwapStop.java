package me.vert3xo.deathswap.commands;

import me.vert3xo.deathswap.DeathSwap;
import me.vert3xo.deathswap.runnable.TpRunnable;
import me.vert3xo.deathswap.utils.GameUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeathSwapStop extends AbstractCommand {
    private DeathSwap plugin = DeathSwap.instance;

    public DeathSwapStop(String command) {
        super(command);
    }

    public DeathSwapStop(String command, String permission) {
        super(command, permission);
    }

    public DeathSwapStop(String command, boolean canConsoleUse) {
        super(command, canConsoleUse);
    }

    public DeathSwapStop(String command, String permission, boolean canConsoleUse) {
        super(command, permission, canConsoleUse);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        TpRunnable game = this.plugin.getGame();

        if (game.isGameRunning()) {
            game.setGameRunning(false);
            game.cancel();
            for (Player player : plugin.getPlayers()) {
                player.sendMessage(ChatColor.GREEN + "Game stopped.");
            }

            // Recreate the TpRunnable object so we can run the game again
            GameUtil.runnableRecreate();

            return false;
        }
        sender.sendMessage(ChatColor.RED + "Game is not running.");
        return false;
    }
}
