package com.challenge.controller;

import com.challenge.dto.response.PokemonDetallesResponseDto;
import com.challenge.dto.response.PokemonInfResponseDto;
import com.challenge.exception.PokemonNotFoundException;
import com.challenge.services.Interface.IPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
public class PokemonController {

    @Autowired
    private IPokemon service;

    @GetMapping(value = "/pokemons", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PokemonInfResponseDto>> getPokemons() throws IOException {
        return ResponseEntity.ok().body(service.getAllPokemon());
    }

    @GetMapping(value = "/pokemon/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInfo(@PathVariable String name) throws IOException {
        try{
            return ResponseEntity.ok().body(service.getInfo(name));
        }catch (PokemonNotFoundException p){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(p.getMessage());
        }
    }

}
