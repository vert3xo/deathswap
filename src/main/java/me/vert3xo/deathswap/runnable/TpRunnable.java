package me.vert3xo.deathswap.runnable;

import me.vert3xo.deathswap.DeathSwap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class TpRunnable extends BukkitRunnable {
    private boolean gameRunning = false;
    private int timer = 500;

    private ArrayList<Player> players = DeathSwap.instance.getPlayers();

    @Override
    public void run() {
        this.timer--;

        if (timer == 60) {
            for (Player p : players) {
                p.sendMessage(ChatColor.RED + "1 minute remaining.");
            }
        }

        if (timer <= 10) {
            for (Player p : players) {
                p.sendMessage(ChatColor.RED + "Swap in " + timer + " seconds.");
            }
        }

        if (timer == 0) {
            Player p1 = players.get(0);
            Player p2 = players.get(1);
            Location p1Loc = p1.getLocation();
            Location p2Loc = p2.getLocation();

            p1.teleport(p2Loc);
            p2.teleport(p1Loc);

            timer = 500;
        }
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }
}
