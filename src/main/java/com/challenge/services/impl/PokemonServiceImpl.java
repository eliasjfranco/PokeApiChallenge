package com.challenge.services.impl;

import com.challenge.dto.response.PokemonDetallesResponseDto;
import com.challenge.dto.response.PokemonInfResponseDto;
import com.challenge.entity.*;
import com.challenge.entity.pokemonProperties.Description;
import com.challenge.entity.pokemonProperties.Descriptions;
import com.challenge.entity.pokemonProperties.PokemonEntity;
import com.challenge.exception.PokemonNotFoundException;
import com.challenge.services.Interface.IPokemon;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements IPokemon {

    private String URL_API = "https://pokeapi.co/api/v2/pokemon/?limit=21";
    private String URL_DESCRIPTION = "https://pokeapi.co/api/v2/characteristic/";

    @Autowired
    ObjectMapper mapper;
    @Autowired
    ProjectionFactory projectionFactory;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    MessageSource messageSource;

    @Override
    public List<PokemonInfResponseDto> getAllPokemon() throws IOException {
        List<Pokemon> pokemon = getPokemones();
        List<PokemonInfResponseDto> dto = new ArrayList<>(); //pokemon.stream().map(p -> modelMapper.map(p, PokemonInfResponseDto.class)).collect(Collectors.toList());
        return dto = pokemon.stream().map(p -> modelMapper.map(p, PokemonInfResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public PokemonDetallesResponseDto getInfo(String name) throws IOException, PokemonNotFoundException {
        List<Pokemon> pokemons = getPokemones();
        Pokemon pokemon = new Pokemon();
        if(pokemons.stream().filter(p -> p.getName().equals(name)).findFirst().isPresent()){
            pokemon = pokemons.stream().filter(p -> p.getName().equals(name)).findAny().get();
            String url = URL_DESCRIPTION + pokemon.getId();
            pokemon.setDescription(getDescription(url));
        }else
            throw new PokemonNotFoundException(messageSource.getMessage("pokemon.error.not.found",null, Locale.getDefault()));
        return projectionFactory.createProjection(PokemonDetallesResponseDto.class, pokemon);
    }

    public List<Pokemon> getPokemones() throws IOException {
        String json = obtenerJson(URL_API);
        List<PokemonEntity> i = mapper.readValue(json, new TypeReference<List<PokemonEntity>>() {});
        List<Pokemon> pokemon = new ArrayList<>();
        i.stream().forEach(p -> {
            p.getResults().forEach(r -> {
                try {
                    pokemon.add(getInformacionPokemons(r.getUrl()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });

        return pokemon;
    }

    public Pokemon getInformacionPokemons(String urlPokemon) throws IOException {
        String json = obtenerJson(urlPokemon);
        Pokemon pokemon = mapper.readValue(json, Pokemon.class);
        pokemon.setUrl(urlPokemon);
        return pokemon;
    }

    public Description getDescription(String urlDescription) throws IOException {
        String json = obtenerJson(urlDescription);
        Descriptions des = mapper.readValue(json, Descriptions.class);
        Description description = new Description();
        description = des.getDescriptions().stream().filter(d -> d.getLanguage().getName().equals("es")).findAny().get();
        System.out.println(description.getDescription());
        return description;
    }

    public String obtenerJson(String url){
        RestTemplate rest = new RestTemplate();
        return rest.getForObject(url, String.class);

    }
}
