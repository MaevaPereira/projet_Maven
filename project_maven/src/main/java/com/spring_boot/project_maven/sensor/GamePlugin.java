package com.spring_boot.project_maven.sensor;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Locale;

public interface GamePlugin {
    Game createGame();
    String getName(Locale locale);
}
