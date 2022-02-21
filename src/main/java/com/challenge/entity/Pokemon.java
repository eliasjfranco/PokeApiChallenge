package com.challenge.entity;


import com.challenge.entity.pokemonProperties.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Pokemon implements Serializable {

    private int id;
    private String name;
    private String url;
    private int weight;

    private List<Abilities> abilities;
    private Sprites sprites;
    private List<Types> types;
    private List<Moves> moves;
    private Description description;

}
