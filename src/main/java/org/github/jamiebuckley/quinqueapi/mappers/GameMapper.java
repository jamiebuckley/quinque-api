package org.github.jamiebuckley.quinqueapi.mappers;

import org.github.jamiebuckley.quinqueapi.dto.GameDTO;
import org.github.jamiebuckley.quinqueapi.models.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GameMapper {

    GameDTO gameToGameDto(Game game);
}
