package com.spring_boot.project_maven;

import com.spring_boot.project_maven.dto.GameCreationParams;
import com.spring_boot.project_maven.sensor.GameService;
import fr.le_campus_numerique.square_games.engine.*;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameServicesImpl implements GameService {
    Map<String, Game> games = new HashMap<>();
//création partie
public String game(GameCreationParams params) {
    GameFactory factory;
    switch(params.gameType) {
        case "connectfour" -> factory = new ConnectFourGameFactory();
        case "taquin" -> factory = new TaquinGameFactory();
        default -> factory = new TicTacToeGameFactory();
    }
    Game initGame = factory.createGame(params.playerCount, params.boardSize);
    games.put(initGame.getId().toString(), initGame);
    return initGame.getId().toString();
}
    public String getGame(String gameId){
        return games.get(gameId).getId().toString();
    };

    public String getMove(String gameId){
        return games.get(gameId).getRemainingTokens().iterator().next().getAllowedMoves().toString();
    };

    public String postMove(String gameId, String idMove){
        String[] parts = idMove.split(",");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        CellPosition position = new CellPosition(x, y);
        Token t = games.get(gameId).getRemainingTokens().iterator().next();
        try {
            t.moveTo(position);
        } catch (InvalidPositionException e) {
            return "Position invalide : " + e.getMessage();
        }
        return position.toString();
    };
}