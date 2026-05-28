package com.spring_boot.project_maven.sensor;

import java.util.Collection;
import java.util.Locale;

public interface GameCatalog {
    Collection<String> getIdGame(Locale locale);
}