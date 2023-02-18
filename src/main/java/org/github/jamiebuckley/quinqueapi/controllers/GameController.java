package org.github.jamiebuckley.quinqueapi.controllers;

import org.github.jamiebuckley.quinqueapi.dto.GameDTO;
import org.github.jamiebuckley.quinqueapi.mappers.GameMapper;
import org.github.jamiebuckley.quinqueapi.models.Game;
import org.github.jamiebuckley.quinqueapi.repositories.GameRepository;
import org.github.jamiebuckley.quinqueapi.services.GameService;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameRepository gameRepository;
    private final GameService gameService;

    private final GameMapper gameMapper = Mappers.getMapper(GameMapper.class);

    public GameController(GameRepository gameRepository, GameService gameService) {
        this.gameRepository = gameRepository;
        this.gameService = gameService;
    }

    @GetMapping
    public List<GameDTO> getGames() {
        return gameRepository.findAll().stream()
                .map(gameMapper::gameToGameDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public GameDTO createGame() {
        var newGame = gameService.createNewGame();
        newGame = gameRepository.save(newGame);
        return gameMapper.gameToGameDto(newGame);
    }
}
