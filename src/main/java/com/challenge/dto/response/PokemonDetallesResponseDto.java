package com.challenge.dto.response;

import com.challenge.entity.*;

import java.util.List;

public interface PokemonDetallesResponseDto {

    String getName();
    String getWeight();

    List<Abilities> getAbilities();
    interface Abilities{
        List<Ability> getAbility();

        interface Ability{
            String getName();
        }
    }

    List<Types> getTypes();
    interface Types{
        List<Type> getType();

        interface Type{
            String getName();
        }
    }

    List<Moves> getMoves();
    interface Moves{
        Move getMove();

        interface Move{
            String getName();
        }
    }

    Description getDescription();
    interface Description{
        String getDescription();
        List<Language> getLanguage();

        interface Language{
            String getName();
        }
    }

}
