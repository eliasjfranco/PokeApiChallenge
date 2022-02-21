package com.challenge.dto.response;

import com.challenge.entity.Abilities;
import com.challenge.entity.Sprites;
import com.challenge.entity.Types;
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
