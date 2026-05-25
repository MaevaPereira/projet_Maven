package com.spring_boot.project_maven;

import com.spring_boot.project_maven.dto.GameCreationParams;
import com.spring_boot.project_maven.sensor.GameService;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameServicesImpl implements GameService {
    Map<String, Game> games = new HashMap<>();
    public String game(GameCreationParams params) {
        TicTacToeGameFactory factory = new TicTacToeGameFactory();
        factory.createGame(params.playerCount, params.boardSize);
    };
    public String getGame(String gameId){

    };
    public String getMove(String gameId){

    };
    public String postMove(String gameId, String idMove){

    };
}