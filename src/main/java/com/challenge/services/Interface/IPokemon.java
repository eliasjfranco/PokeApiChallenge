package com.challenge.services.Interface;

import com.challenge.dto.response.PokemonDetallesResponseDto;
import com.challenge.dto.response.PokemonInfResponseDto;
import com.challenge.exception.PokemonNotFoundException;

import java.io.IOException;
import java.util.List;

public interface IPokemon {


    List<PokemonInfResponseDto> getAllPokemon() throws IOException;

    PokemonDetallesResponseDto getInfo(String name) throws IOException, PokemonNotFoundException;
}
