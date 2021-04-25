package me.vert3xo.deathswap.listener;

import me.vert3xo.deathswap.DeathSwap;
import me.vert3xo.deathswap.runnable.TpRunnable;
import me.vert3xo.deathswap.utils.GameUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;

public class PlayerDeathListener implements Listener {
    DeathSwap plugin = DeathSwap.instance;
    ArrayList<Player> players = plugin.getPlayers();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        TpRunnable game = plugin.getGame();
        Player p = event.getEntity();

        if (game.isGameRunning()) {
            if (players.contains(p)) {
                players.remove(p);
                p.sendMessage(ChatColor.RED + "You lost!");
            }

            if (players.size() <= 1) {
                game.setGameRunning(false);
                game.cancel();
                GameUtil.runnableRecreate();
                players.get(0).sendMessage(ChatColor.GREEN + "You won!");
            }
        }
    }
}
