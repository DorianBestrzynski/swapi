package com.miquido.interview.swapi.service;

import com.miquido.interview.swapi.data.model.SwapiCharacter;
import com.miquido.interview.swapi.data.repository.SwapiCharacterRepository;
import com.miquido.interview.swapi.exception.ApiDuplicateCharacterException;
import com.miquido.interview.swapi.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static com.miquido.interview.swapi.exception.ExceptionInfo.*;

@Service
@RequiredArgsConstructor
public class SwapiCharacterServiceImpl implements SwapiCharacterService {

    private final SwapiCharacterRepository characterRepository;

    @Value("${heightValidator}")
    private Integer heightValidator;

    private static final String SWAPI_URL = "https://swapi.dev/api/people/";


    @Override
    public List<SwapiCharacter> getCharactersByName(String name) {
        List<SwapiCharacter> result = characterRepository.findByNameContainingIgnoreCase(name);
        if(result.isEmpty()) throw new ApiRequestException(NO_MATCHING_NAME);
        return result;
    }

    @Override
    public SwapiCharacter importCharacter(Integer id) {
        JSONObject resultObject = getDataFromSwapi(id);
        String name = resultObject.getString("name");
        isCharacterInDatabase(name);
        String height = resultObject.getString("height");
        String mass = resultObject.getString("mass");
        SwapiCharacter swapiCharacter = new SwapiCharacter(name,isNumeric(height),isNumeric(mass));
        characterRepository.save(swapiCharacter);
        return swapiCharacter;
    }

    @Override
    public SwapiCharacter getCharacter(Integer id) {
            return characterRepository.findById(id).orElseThrow(() -> new ApiRequestException(NO_CHARACTER));

    }

    /**
     * Height validator
     * @return list of characters with height less than in application properties
     */
    @Override
    public List<SwapiCharacter> heightValidator() {
        List<SwapiCharacter> result = characterRepository.getSwapiCharactersByHeightLessThan(heightValidator);
        if(result.isEmpty()) throw new ApiRequestException(NO_CHARACTER_HEIGHT + heightValidator);
        return result;
    }



    private Integer isNumeric(String str) {
        try {
            return Integer.parseInt(str);
        } catch(NumberFormatException e){
            return null;
        }
    }

    private JSONObject getDataFromSwapi(Integer id){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(SWAPI_URL + id, String.class);
            return new JSONObject(result);
        }catch(RestClientException ex){
            throw new ApiRequestException(NO_CHARACTER_SWAPI);
        }

    }
    private void isCharacterInDatabase(String name){
        var swapiCharacter = characterRepository.findByName(name);
        if(swapiCharacter != null) throw new ApiDuplicateCharacterException(CHARACTER_ALREADY_PRESENT);
    }
}
