package me.vert3xo.deathswap.utils;

import me.vert3xo.deathswap.DeathSwap;
import me.vert3xo.deathswap.runnable.TpRunnable;

public class GameUtil {
    public static void runnableRecreate() {
        DeathSwap.instance.setGame(new TpRunnable());
    }
}
