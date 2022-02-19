package com.challenge.dto.response;

import com.challenge.entity.Abilities;
import com.challenge.entity.Ability;
import com.challenge.entity.Type;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface PokemonInformacionResponseDto {
    int getId();
    String getName();
    String getUrl();
    int getWeight();
    List<Types> getTypes();
    List<Abilities> getAbilities();

    interface Types{
        List<Type> getType();

        interface Type{
            String getName();
        }
    }

    interface Abilities{
        List<Ability> getAbility();

        interface Ability{
            String getName();
        }
    }


}
