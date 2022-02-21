package com.challenge.entity.pokemonProperties;

import com.challenge.entity.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonEntity {

    private int count;
    private String next;
    private String previous;
    private List<Pokemon> results;

}
