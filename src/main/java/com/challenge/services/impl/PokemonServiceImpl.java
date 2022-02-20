package com.challenge.services.impl;

import com.challenge.dto.response.PokemonDetallesResponseDto;
import com.challenge.dto.response.PokemonInformacionResponseDto;
import com.challenge.entity.*;
import com.challenge.services.Interface.IPokemon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PokemonServiceImpl implements IPokemon {

    private final static String URL_API = "https://pokeapi.co/api/v2/pokemon/?limit=20";
    private final static String URL_DESCRIPTION = "https://pokeapi.co/api/v2/characteristic/";

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ProjectionFactory projectionFactory;

    @Override
    public Stream<PokemonInformacionResponseDto> getAllPokemon() throws JsonProcessingException {
        List<Pokemon> pokemon = getPokemones();
        return pokemon.stream().map(p -> projectionFactory.createProjection(PokemonInformacionResponseDto.class, p));

    }

    @Override
    public PokemonDetallesResponseDto getInfo(String name) throws JsonProcessingException {
        List<Pokemon> pokemons = getPokemones();
        Pokemon pokemon = new Pokemon();
        if(pokemons.stream().filter(p -> p.getName().equals(name)).findFirst().isPresent()){
            pokemon = pokemons.stream().filter(p -> p.getName().equals(name)).findAny().get();
            String url = URL_DESCRIPTION + pokemon.getId();
            pokemon.setDescription(getDescription(url));
        }
        return projectionFactory.createProjection(PokemonDetallesResponseDto.class, pokemon);
    }

    public List<Pokemon> getPokemones() throws JsonProcessingException {
        String json = obtenerJson(URL_API);
        List<PokemonEntity> i = mapper.readValue(json, new TypeReference<List<PokemonEntity>>() {});
        List<Pokemon> pokemon = new ArrayList<>();
        i.stream().forEach(p -> {
            p.getResults().forEach(r -> {
                try {
                    pokemon.add(getInformacionPokemons(r.getUrl()));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        });

        return pokemon;
    }

    public Pokemon getInformacionPokemons(String urlPokemon) throws JsonProcessingException {
        String json = obtenerJson(urlPokemon);
        Pokemon pokemon = mapper.readValue(json, Pokemon.class);
        pokemon.setUrl(urlPokemon);
        return pokemon;
    }

    public Description getDescription(String urlDescription) throws JsonProcessingException {
        String json = obtenerJson(urlDescription);
        Descriptions des = mapper.readValue(json, Descriptions.class);
        Description description = new Description();
        /*for(Description x : des){
            description = x.getLanguage().stream().filter(f -> f.getName().equals("es")).findAny().get();

        }*/
        description = des.getDescriptions().stream().filter(d -> d.getLanguage().getName().equals("es")).findAny().get();
        System.out.println(description.getDescription());
        return description;
    }

    public String obtenerJson(String url){
        RestTemplate rest = new RestTemplate();
        return rest.getForObject(url, String.class);
    }
}
