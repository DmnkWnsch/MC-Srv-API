package net.mcsrvapi.main.api.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class PlayerScoreboard {

    private static final ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

    private final Player player;

    private final Scoreboard scoreboard;
    private Objective objective;

    public PlayerScoreboard(Player player) {
        this.player = player;

        this.scoreboard = scoreboardManager.getNewScoreboard();
    }

    public Player getPlayer() {
        return player;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    

}
