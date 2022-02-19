package com.challenge.services.Interface;

import com.challenge.dto.response.PokemonDetallesResponseDto;
import com.challenge.dto.response.PokemonInformacionResponseDto;
import com.challenge.entity.Pokemon;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.stream.Stream;

public interface IPokemon {

    Stream<PokemonInformacionResponseDto> getAllPokemon() throws JsonProcessingException;

    PokemonDetallesResponseDto getInfo(String name) throws JsonProcessingException;
}
