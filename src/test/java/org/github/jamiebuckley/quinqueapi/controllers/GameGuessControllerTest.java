package org.github.jamiebuckley.quinqueapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.github.jamiebuckley.quinqueapi.dto.AddGuessDTO;
import org.github.jamiebuckley.quinqueapi.models.Game;
import org.github.jamiebuckley.quinqueapi.repositories.GameRepository;
import org.github.jamiebuckley.quinqueapi.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameGuessController.class)
@Import(GameGuessController.class)
public class GameGuessControllerTest {

    private final String exampleUUID = "ca14ee78-751c-400b-8a44-86d7271e59a8";

    @Autowired
    private MockMvc mvc;

    @MockBean
    GameRepository repository;

    @MockBean
    GameService gameService;

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        game.setWord("WORD1");
        game.setGuesses(Arrays.asList("WORD2", "WORD3"));
        game.setId(UUID.fromString(exampleUUID));
    }

    @Test
    public void getGuesses_shouldReturn_allGuessesForGame_whenGameExists() throws Exception {
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(game));

        mvc.perform(get("/games/%s/guesses".formatted(exampleUUID)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getGuesses_shouldReturn_404_whenGameNotExists() throws Exception {
        mvc.perform(get("/games/%s/guesses".formatted(exampleUUID)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addGuess_shouldReturn_400_whenNoGuessesAllowed() throws Exception {
        var guessDto = new AddGuessDTO();
        guessDto.setGuess("WORD1");

        Mockito.when(gameService.canGuess(any())).thenReturn(false);
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(game));

        mvc.perform(post("/games/%s/guesses".formatted(exampleUUID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(guessDto)))
                .andExpect(status().isBadRequest());
    }
}
