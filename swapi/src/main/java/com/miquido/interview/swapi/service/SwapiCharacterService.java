package com.miquido.interview.swapi.service;

import com.miquido.interview.swapi.data.model.SwapiCharacter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SwapiCharacterService {

    List<SwapiCharacter> getCharactersByName(String name);

    SwapiCharacter importCharacter(Integer id);

    SwapiCharacter getCharacter(Integer id);

    List<SwapiCharacter> heightValidator();
}
