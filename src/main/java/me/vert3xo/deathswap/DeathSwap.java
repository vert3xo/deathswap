package me.vert3xo.deathswap;

import me.vert3xo.deathswap.commands.DeathSwapJoin;
import me.vert3xo.deathswap.commands.DeathSwapLeave;
import me.vert3xo.deathswap.commands.DeathSwapStart;
import me.vert3xo.deathswap.commands.DeathSwapStop;
import me.vert3xo.deathswap.listener.PlayerDeathListener;
import me.vert3xo.deathswap.listener.PlayerLeaveListener;
import me.vert3xo.deathswap.runnable.TpRunnable;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class DeathSwap extends JavaPlugin {
    public static DeathSwap instance;

    private final ConsoleCommandSender LOGGER = getServer().getConsoleSender();
    private final String NAME = "Death Swap";
    private final ArrayList<Player> players = new ArrayList<>();
    private TpRunnable game;

    @Override
    public void onEnable() {
        instance = this;
        this.game = new TpRunnable();

        this.registerEvents();
        this.registerCommands();
        LOGGER.sendMessage(ChatColor.GREEN + NAME + " enabled.");
    }

    @Override
    public void onDisable() {
        this.players.clear();

        LOGGER.sendMessage(ChatColor.RED + NAME + " disabled.");
    }

    private void registerEvents() {
        final PluginManager MANAGER = getServer().getPluginManager();

        MANAGER.registerEvents(new PlayerLeaveListener(), this);
        MANAGER.registerEvents(new PlayerDeathListener(), this);
    }

    private void registerCommands() {
        new DeathSwapJoin("deathswapjoin");
        new DeathSwapLeave("deathswapleave");
        new DeathSwapStart("deathswapstart", true);
        new DeathSwapStop("deathswapstop", true);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public TpRunnable getGame() {
        return game;
    }

    public void setGame(TpRunnable game) {
        this.game = game;
    }
}
