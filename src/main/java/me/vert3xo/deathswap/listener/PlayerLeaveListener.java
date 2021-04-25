package me.vert3xo.deathswap.listener;

import me.vert3xo.deathswap.DeathSwap;
import me.vert3xo.deathswap.runnable.TpRunnable;
import me.vert3xo.deathswap.utils.GameUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class PlayerLeaveListener implements Listener {
    private DeathSwap plugin = DeathSwap.instance;
    private ArrayList<Player> players = plugin.getPlayers();

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if (players.contains(p)) {
            players.remove(p);
        }

        if (players.size() < 2) {
            TpRunnable game = this.plugin.getGame();
            if (game.isGameRunning()) {
                game.cancel();
                GameUtil.runnableRecreate();
                for (Player player : players) {
                    player.sendMessage(ChatColor.RED + "Game ended, " + p.getDisplayName() + " disconnected and there is not enough players to continue.");
                }
            }
        }
    }
}
