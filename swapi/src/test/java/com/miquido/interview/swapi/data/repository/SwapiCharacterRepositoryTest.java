package com.miquido.interview.swapi.data.repository;

import com.miquido.interview.swapi.data.model.SwapiCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SwapiCharacterRepositoryTest {

    @Autowired
    private SwapiCharacterRepository underTest;

    private SwapiCharacter firstCharacter;

    private SwapiCharacter secondCharacter;

    private SwapiCharacter thirdCharacter;

    @BeforeEach
    void createTestData(){
        firstCharacter = new SwapiCharacter("Yoda", 100,100);
        secondCharacter =  new SwapiCharacter("yoad", 111,100);
        thirdCharacter = new SwapiCharacter("test", null, null);
        underTest.save(firstCharacter);
        underTest.save(secondCharacter);
        underTest.save(thirdCharacter);
    }
    @Test
    void itShouldFindCharactersByPartOfName() {
        String name = "yO";
        List<SwapiCharacter> actualResult = underTest.findByNameContainingIgnoreCase(name);
        List<SwapiCharacter> expectedResult  = new ArrayList<>(){
            {
            add(firstCharacter);
            add(secondCharacter);
            }
        };

        assertEquals(expectedResult,actualResult);

    }


    @Test
    void shouldFindCharactersWithHeightLessThan() {
        int heightValidator = 110;
        List<SwapiCharacter> actualResult = underTest.getSwapiCharactersByHeightLessThan(heightValidator);
        List<SwapiCharacter> expectedResult  = new ArrayList<>(){
            {
                add(firstCharacter);
            }
            };
        assertEquals(expectedResult,actualResult);


    }

    @Test
    void shouldFindCharacterByName() {
        String name = "test";
        SwapiCharacter expectedResult = thirdCharacter;
        SwapiCharacter actualResult = underTest.findByName(name);
        assertEquals(expectedResult,actualResult);

    }
}