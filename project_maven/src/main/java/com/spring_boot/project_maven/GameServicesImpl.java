package com.spring_boot.project_maven;

import com.spring_boot.project_maven.dto.GameCreationParams;
import com.spring_boot.project_maven.sensor.GamePlugin;
import com.spring_boot.project_maven.sensor.GameService;
import fr.le_campus_numerique.square_games.engine.*;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameServicesImpl implements GameService {
    Map<String, Game> games = new HashMap<>();
//création partie
@Autowired
private List<GamePlugin> plugins;
public String game(GameCreationParams params) {
    GamePlugin plugin;
    switch(params.gameType) {
        case "connectfour" -> plugin = new ConnectFourGameFactory();
        case "taquin" -> plugin = new TaquinGameFactory();
        default -> plugin = new TicTacToeGameFactory();
    }
    Game initGame = plugin.createGame(params.playerCount, params.boardSize);
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