package org.github.jamiebuckley.quinqueapi.controllers;

import org.github.jamiebuckley.quinqueapi.models.Game;
import org.github.jamiebuckley.quinqueapi.repositories.GameRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StreamUtils;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
@Import(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    GameRepository repository;

    @Test
    public void createGame_shouldReturn_newGame() throws Exception {
        var game = new Game();
        game.setId(UUID.randomUUID());

        Mockito.when(repository.save(Mockito.any(Game.class))).thenReturn(game);

        mvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(game.getId().toString())));
    }

    @Test
    public void getGames_shoudlReturn_allGames() throws Exception {
        var games = IntStream.range(0, 5).mapToObj(i -> {
            var game = new Game();
            game.setId(UUID.randomUUID());
            return game;
        }).collect(Collectors.toList());
        Mockito.when(repository.findAll()).thenReturn(games);

        mvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }
}
