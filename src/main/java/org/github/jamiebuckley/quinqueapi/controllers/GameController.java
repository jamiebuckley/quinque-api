package org.github.jamiebuckley.quinqueapi.controllers;

import org.github.jamiebuckley.quinqueapi.dto.GameDTO;
import org.github.jamiebuckley.quinqueapi.mappers.GameMapper;
import org.github.jamiebuckley.quinqueapi.models.Game;
import org.github.jamiebuckley.quinqueapi.repositories.GameRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameRepository gameRepository;

    private final GameMapper gameMapper = Mappers.getMapper(GameMapper.class);

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public List<GameDTO> getGames() {
        return gameRepository.findAll().stream().map(gameMapper::gameToGameDto).collect(Collectors.toList());
    }

    @PostMapping
    public GameDTO createGame() {
        var game = new Game();
        game = gameRepository.save(game);
        return gameMapper.gameToGameDto(game);
    }
}
