package com.challenge.controller;

import com.challenge.dto.response.PokemonDetallesResponseDto;
import com.challenge.dto.response.PokemonInformacionResponseDto;
import com.challenge.entity.Pokemon;
import com.challenge.services.Interface.IPokemon;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@CrossOrigin(origins = "D:/Elias/Escritorio/Challenge%20JamerSoft%20Front-End/index.html")
@RestController
public class PokemonController {

    @Autowired
    private IPokemon service;

    @GetMapping(value = "/pokemons")
    public ResponseEntity<Stream<PokemonInformacionResponseDto>> getPokemons() throws JsonProcessingException {
        return ResponseEntity.ok().body(service.getAllPokemon());
    }

    @GetMapping(value = "/pokemon/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PokemonDetallesResponseDto> getInfo(@PathVariable String name) throws JsonProcessingException {
        return ResponseEntity.ok().body(service.getInfo(name));
    }

}
