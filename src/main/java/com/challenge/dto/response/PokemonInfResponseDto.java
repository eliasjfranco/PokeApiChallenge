package com.challenge.dto.response;

import com.challenge.entity.pokemonProperties.Abilities;
import com.challenge.entity.pokemonProperties.Sprites;
import com.challenge.entity.pokemonProperties.Types;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class PokemonInfResponseDto {

    int id;
    String name;
    String url;
    int weight;
    Sprites sprites;
    List<Types> types;
    List<Abilities> abilities;


}
