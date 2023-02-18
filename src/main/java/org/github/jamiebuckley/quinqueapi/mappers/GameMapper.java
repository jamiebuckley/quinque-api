package org.github.jamiebuckley.quinqueapi.mappers;

import org.github.jamiebuckley.quinqueapi.dto.GameDTO;
import org.github.jamiebuckley.quinqueapi.dto.PreviousGuessDTO;
import org.github.jamiebuckley.quinqueapi.models.Game;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface GameMapper {

    default GameDTO gameToGameDto(Game game) {
        var gameDTO = new GameDTO();
        gameDTO.setId(game.getId().toString());

        if (game.getSolved()) {
            gameDTO.setWord(game.getWord());
            gameDTO.setSolved(true);
        }

        var previousGuesses = getPreviousGuesses(game);
        gameDTO.setPreviousGuesses(previousGuesses);

        return gameDTO;
    }

    default List<PreviousGuessDTO> getPreviousGuesses(Game game) {

        var previousGuessMapper = Mappers.getMapper(PreviousGuessMapper.class);
        return game.getGuesses().stream()
                .map(guess -> previousGuessMapper.previousGuessToPreviousGuessDTO(game, guess))
                .collect(Collectors.toList());
    }
}
