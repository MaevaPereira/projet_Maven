package com.spring_boot.project_maven;

import com.spring_boot.project_maven.sensor.GameCatalog;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class GameCatalogImpl  implements GameCatalog {
    public Collection<String> getIdGame(){
        TicTacToeGameFactory factory = new TicTacToeGameFactory();
        return List.of(factory.getGameFactoryId());
    }
}
