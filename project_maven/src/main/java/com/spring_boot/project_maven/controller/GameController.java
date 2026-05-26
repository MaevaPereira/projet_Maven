package com.spring_boot.project_maven.controller;

import com.spring_boot.project_maven.dto.GameCreationParams;
import com.spring_boot.project_maven.sensor.GameService;

import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/games")
   String game (@RequestBody GameCreationParams params){
        return gameService.game(params);
    }

    @GetMapping("/games/{gameId}")
    String getGame(@PathVariable String gameId){
        return gameService.getGame(gameId);
    }

    @GetMapping("/games/{gameId}/moves")
    String getMove(@PathVariable String gameId){
        return gameService.getMove(gameId);
    }

    @PostMapping("/games/{gameId}/moves/{idMove}")
        String postMove (@PathVariable String gameId, @PathVariable String idMove){
        return gameService.postMove(gameId, idMove);
    }
}

