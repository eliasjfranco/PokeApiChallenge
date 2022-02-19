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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
public class PokemonController {

    @Autowired
    IPokemon service;

    @GetMapping(value = "/pokemons")
    public ResponseEntity<Stream<PokemonInformacionResponseDto>> getPokemons() throws JsonProcessingException {
        return ResponseEntity.ok().body(service.getAllPokemon());
    }

    @GetMapping(value = "/pokemon/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PokemonDetallesResponseDto> getInfo(@RequestParam String name) throws JsonProcessingException {
        return ResponseEntity.ok().body(service.getInfo(name));
    }

}
