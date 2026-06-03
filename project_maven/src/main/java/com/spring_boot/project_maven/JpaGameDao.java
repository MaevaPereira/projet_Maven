package com.spring_boot.project_maven;

import com.spring_boot.project_maven.entity.GameEntity;
import com.spring_boot.project_maven.entity.GameTokenEntity;
import com.spring_boot.project_maven.repository.GameEntityRepository;
import com.spring_boot.project_maven.sensor.GameDao;
import com.spring_boot.project_maven.sensor.GamePlugin;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Primary
@Repository
public class JpaGameDao implements GameDao {

    private final GameEntityRepository gameEntityRepository;
    private final List<GamePlugin> plugins;

    private GameEntity toEntity(Game game) {
        GameEntity entity = new GameEntity();
        entity.id = game.getId().toString();
        entity.factoryId = game.getFactoryId();
        entity.tokens = Stream.concat(
                game.getRemainingTokens().stream().map(token -> {
                    GameTokenEntity t = new GameTokenEntity();
                    t.name = token.getName();
                    t.ownerId = token.getOwnerId().orElseThrow().toString();
                    t.removed = false;
                    CellPosition pos = token.getPosition();
                    if (pos != null) {
                        t.x = pos.x();
                        t.y = pos.y();
                    }
                    return t;
                }),
                game.getRemovedTokens().stream().map(token -> {
                    GameTokenEntity t = new GameTokenEntity();
                    t.name = token.getName();
                    t.ownerId = token.getOwnerId().orElseThrow().toString();
                    t.removed = true;
                    return t;
                })
        ).toList();
        return entity;
    }

    private Game toGame(GameEntity entity) {
        return plugins.stream()
                .filter(p -> p.getGameId().equals(entity.factoryId))
                .findFirst()
                .orElseThrow()
                .createGame();
    }

    @Override
    public Optional<Game> findById(String gameId) {
        return gameEntityRepository.findById(gameId).map(this::toGame);
    }

    @Override
    public Stream<Game> findAll() {
        return gameEntityRepository.findAll().stream().map(this::toGame);
    }

    @Override
    public Game upsert(Game game) {
        gameEntityRepository.save(toEntity(game));
        return game;
    }

    @Override
    public void delete(String gameId) {
        gameEntityRepository.deleteById(gameId);
    }

    public JpaGameDao(GameEntityRepository gameEntityRepository, List<GamePlugin> plugins) {
        this.gameEntityRepository = gameEntityRepository;
        this.plugins = plugins;


    }


}
