package com.spring_boot.project_maven.controller;

import com.spring_boot.project_maven.sensor.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

@RestController
public class GameCatalogController {
    @Autowired
    private GameCatalog gameCatalog;
    @GetMapping("/gameCatalog")
    Collection<String> gameCatalog(){
        return gameCatalog.getIdGame();
    }
}
