package com.spring_boot.project_maven.plugin;

import com.spring_boot.project_maven.sensor.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InconsistentGameDefinitionException;
import fr.le_campus_numerique.square_games.engine.TokenPosition;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class ConnectFourPlugin implements GamePlugin {
    @Value("${game.connectfour.default-player-count}")
    private int playerCount;

    @Value("${game.connectfour.default-board-size}")
    private int boardSize;
    private final ConnectFourGameFactory connectFourGameFactory = new ConnectFourGameFactory();
    private final MessageSource messageSource;

    public Game createGame() {
        return connectFourGameFactory.createGame(playerCount, boardSize);
    }
    public String getName(Locale locale) {
        return messageSource.getMessage("game.connectfour.name", null, locale);
    }

    public String getGameId() {
        return "connectfour";
    }

    public ConnectFourPlugin(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
