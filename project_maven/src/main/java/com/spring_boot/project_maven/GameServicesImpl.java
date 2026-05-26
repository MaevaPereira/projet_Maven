package com.spring_boot.project_maven;

import com.spring_boot.project_maven.dto.GameCreationParams;
import com.spring_boot.project_maven.sensor.GameService;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameServicesImpl implements GameService {
    Map<String, Game> games = new HashMap<>();
//création partie
    public String game(GameCreationParams params) {
        TicTacToeGameFactory factory = new TicTacToeGameFactory();
        //factory.createGame(params.playerCount, params.boardSize);
        Game initGame = factory.createGame(params.playerCount, params.boardSize);
        games.put(initGame.getId().toString(), initGame );
        return initGame.getId().toString();
    };

    public String getGame(String gameId){
        return games.get(gameId).getId().toString();
    };

    public String getMove(String gameId){
        return games.get(gameId).getRemainingTokens().iterator().next().getAllowedMoves().toString();
    };

    public String postMove(String gameId, String idMove){
        return

    };
}