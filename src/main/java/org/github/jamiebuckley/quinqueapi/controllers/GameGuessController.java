package org.github.jamiebuckley.quinqueapi.controllers;

import jakarta.validation.Valid;
import org.github.jamiebuckley.quinqueapi.dto.AddGuessDTO;
import org.github.jamiebuckley.quinqueapi.dto.APIError;
import org.github.jamiebuckley.quinqueapi.dto.PreviousGuessDTO;
import org.github.jamiebuckley.quinqueapi.exceptions.NoMoreGuessesAllowedException;
import org.github.jamiebuckley.quinqueapi.mappers.GameMapper;
import org.github.jamiebuckley.quinqueapi.mappers.PreviousGuessMapper;
import org.github.jamiebuckley.quinqueapi.repositories.GameRepository;
import org.github.jamiebuckley.quinqueapi.services.GameService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/games/{gameId}/guesses")
public class GameGuessController {

    private final GameRepository gameRepository;
    private final GameService gameService;

    private final GameMapper gameMapper = Mappers.getMapper(GameMapper.class);

    private final PreviousGuessMapper previousGuessMapper = Mappers.getMapper(PreviousGuessMapper.class);

    public GameGuessController(GameRepository gameRepository, GameService gameService) {
        this.gameRepository = gameRepository;
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<PreviousGuessDTO>> getGuesses(@PathVariable("gameId") UUID gameId) {
        return gameRepository.findById(gameId)
                .map(game -> ResponseEntity.ok(gameMapper.getPreviousGuesses(game)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PreviousGuessDTO> addGuess(@PathVariable("gameId") UUID gameId, @Valid @RequestBody AddGuessDTO addGuessDTO) throws NoMoreGuessesAllowedException {
        var gameOptional = gameRepository.findById(gameId);
        if(gameOptional.isEmpty()) return ResponseEntity.notFound().build();

        var game = gameOptional.get();
        if (!gameService.canGuess(game)) {
            throw new NoMoreGuessesAllowedException(gameId.toString());
        }

        game = gameService.addGuess(gameOptional.get(), addGuessDTO.getGuess());
        game = gameRepository.save(game);

        return ResponseEntity.ok(previousGuessMapper.previousGuessToPreviousGuessDTO(game, addGuessDTO.getGuess()));
    }
}
