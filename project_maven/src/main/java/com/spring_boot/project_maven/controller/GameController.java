package com.spring_boot.project_maven.controller;

import com.spring_boot.project_maven.dto.GameCreationParams;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {
    @PostMapping("/games")
   String game (@RequestBody GameCreationParams params){

    }
    @GetMapping("/games/{gameId}")
    String getGame(@PathVariable String gameId){

    }
    @GetMapping("/games/{gameId}/moves")
    String getMove(@PathVariable String gameId){

    }
    @PostMapping("/games/{gameId}/moves/{idMove}")
        String postMove (@PathVariable String gameId, @PathVariable String idMove){

    }
}
