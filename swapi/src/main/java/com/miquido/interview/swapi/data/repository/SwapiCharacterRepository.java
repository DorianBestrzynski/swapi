package com.miquido.interview.swapi.data.repository;
import com.miquido.interview.swapi.data.model.SwapiCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SwapiCharacterRepository extends JpaRepository<SwapiCharacter,Integer> {

    List<SwapiCharacter> findByNameContainingIgnoreCase(String name);

    List<SwapiCharacter> getSwapiCharactersByHeightLessThan(Integer height);

    SwapiCharacter findByName(String name);

}
