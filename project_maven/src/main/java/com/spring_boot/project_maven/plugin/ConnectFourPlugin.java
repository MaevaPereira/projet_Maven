package com.spring_boot.project_maven.plugin;

import com.spring_boot.project_maven.sensor.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ConnectFourPlugin implements GamePlugin {
    @Value("${game.connectfour.default-player-count}")
    private int playerCount;

    @Value("${game.connectfour.default-board-size}")
    private int boardSize;
    public Game createGame() {
        return new ConnectFourGameFactory().createGame(playerCount, boardSize);
    }
    public String getName(Locale locale) {
        return messageSource.getMessage("game.connectfour.name", null, locale);
    }

    public String getGameId() {
        return "connectfour";
    }

    @Autowired
    private MessageSource messageSource;
}
