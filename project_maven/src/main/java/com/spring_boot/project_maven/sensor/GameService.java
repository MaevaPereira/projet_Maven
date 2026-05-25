package com.spring_boot.project_maven.sensor;

import com.spring_boot.project_maven.dto.GameCreationParams;

public interface GameService {
    String game(GameCreationParams params);
    String getGame(String gameId);
    String getMove(String gameId);
    String postMove(String gameId, String idMove);
}
