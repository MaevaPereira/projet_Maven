package com.spring_boot.project_maven;

import com.spring_boot.project_maven.sensor.GameCatalog;
import com.spring_boot.project_maven.sensor.GamePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Service
public class GameCatalogImpl implements GameCatalog {
    @Autowired
    private List<GamePlugin> plugins;

    public Collection<String> getIdGame(Locale locale) {
        return plugins.stream()
                .map(p -> p.getName(locale))
                .toList();
    }
}