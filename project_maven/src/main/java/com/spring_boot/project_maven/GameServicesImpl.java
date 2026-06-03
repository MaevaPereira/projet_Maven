package com.spring_boot.project_maven;

import com.spring_boot.project_maven.dto.GameCreationParams;
import com.spring_boot.project_maven.sensor.GameDao;
import com.spring_boot.project_maven.sensor.GamePlugin;
import com.spring_boot.project_maven.sensor.GameService;
import fr.le_campus_numerique.square_games.engine.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameServicesImpl implements GameService {

    private List<GamePlugin> plugins;
    private final GameDao gameDao;

        public GameServicesImpl(GameDao gameDao, List<GamePlugin> plugins) {
            this.gameDao = gameDao;
            this.plugins = plugins;
        }

    public String game(GameCreationParams params) {
        GamePlugin plugin = plugins.stream()
                .filter(p -> p.getGameId().equals(params.gameType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("jeu inconnu : " + params.gameType));
        Game initGame = plugin.createGame();
        gameDao.upsert(initGame);
        return initGame.getId().toString();
    }

    public String getGame(String gameId){
        return gameDao.findById(gameId).orElseThrow().getId().toString();
    }

    public String getMove(String gameId){
        return gameDao.findById(gameId).orElseThrow().getRemainingTokens().iterator().next().getAllowedMoves().toString();
    }

    public String postMove(String gameId, String idMove){
        String[] parts = idMove.split(",");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        CellPosition position = new CellPosition(x, y);
        Game game = gameDao.findById(gameId).orElseThrow();
        Token t = game.getRemainingTokens().iterator().next();

        try {
            t.moveTo(position);
        } catch (InvalidPositionException e) {
            return "Position invalide : " + e.getMessage();
        }
        return position.toString();
    }
}