package com.miquido.interview.swapi.web;

import com.miquido.interview.swapi.data.model.SwapiCharacter;
import com.miquido.interview.swapi.service.SwapiCharacterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
public class SwapiCharacterController {

    private final SwapiCharacterService characterService;

    @ApiOperation(value = "Import character from SWAPI with given id", notes = "Returns a character as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully imported"),
            @ApiResponse(code = 404, message = "Not found - The character with given id is not present in SWAPI"),
            @ApiResponse(code = 409, message = "Conflict - This character already exists in database")
    })
    @PostMapping("/import/{id}")
    public ResponseEntity<SwapiCharacter> importCharacter(@PathVariable("id") Integer id) {
        SwapiCharacter result = characterService.importCharacter(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @ApiOperation(value = "Get character from database with given id", notes = "Returns a character as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 404, message = "Not found - The character with given id is not present in database")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SwapiCharacter> findCharacter(@PathVariable("id") Integer id) {
        SwapiCharacter result = characterService.getCharacter(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @ApiOperation(value = "Get characters that contains given name from database", notes = "Returns a set of characters containing name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found by given phrase"),
            @ApiResponse(code = 404, message = "Not found - The character with given string is not present in database")
    })
    @GetMapping("/find/{name}")
    public ResponseEntity<List<SwapiCharacter>> findByName(@PathVariable("name") String name){
        List<SwapiCharacter> result = characterService.getCharactersByName(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @ApiOperation(value = "Height validator", notes = "Returns set of characters with height less than given in application properties")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found below height in application properties"),
            @ApiResponse(code = 404, message = "Not found - There are no characters with height less than value in application properties")
    })
    @GetMapping("/getBelowHeight")
    public ResponseEntity<List<SwapiCharacter>> findBelowHeight(){
        List<SwapiCharacter> result = characterService.heightValidator();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
