package com.spring_boot.project_maven;

import com.spring_boot.project_maven.sensor.GameDao;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.stream.Stream;

@Repository

public class JdbcGameDao implements GameDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcGameDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    @Override
    public Stream<Game> findAll() {
        return Stream.empty();

    }

    @Override
    public Optional<Game> findById(String gameId) {
        return Optional.empty();
    }

    @Override
    public Game upsert(Game game) {
        String sql = "INSERT INTO game (id, game_type) VALUES (:id, :gameType)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", game.getId().toString())
                .addValue("gameType", game.getClass().getSimpleName());
        namedParameterJdbcTemplate.update(sql, params);
        return game;
    }

    @Override
    public void delete(String gameId) {
        String sql = "DELETE FROM game WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", gameId);
        namedParameterJdbcTemplate.update(sql, params);
    }
}
