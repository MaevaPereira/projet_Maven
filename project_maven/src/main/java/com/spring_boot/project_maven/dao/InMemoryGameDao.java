package com.spring_boot.project_maven.dao;

import com.spring_boot.project_maven.sensor.GameDao;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class InMemoryGameDao implements GameDao {
    Map<String, Game> games = new HashMap<>();

    @Override
    public Game upsert(Game game) {
        games.put(game.getId().toString(), game);
        return game;
    }
    @Override
    public Optional<Game> findById(String gameId) {
        return Optional.ofNullable(games.get(gameId));
    }

    @Override
    public Stream<Game> findAll() {
        return games.values().stream();
    }

    @Override
    public void delete(String gameId) {
        games.remove(gameId);
    }
}
