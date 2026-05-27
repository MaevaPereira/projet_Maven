package com.spring_boot.project_maven.plugin;

import com.spring_boot.project_maven.sensor.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TaquinPlugin implements GamePlugin {
        @Value("${game.taquin.default-player-count}")
        private int playerCount;

        @Value("${game.taquin.default-board-size}")
        private int boardSize;
        public Game createGame() {
            return new TaquinGameFactory().createGame(playerCount, boardSize);
        }
        public String getName(Locale locale) {
            return "Taquin";
        }
    }
