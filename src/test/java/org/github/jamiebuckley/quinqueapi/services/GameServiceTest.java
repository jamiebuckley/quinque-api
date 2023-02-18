package org.github.jamiebuckley.quinqueapi.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private WordService wordService;

    @Test
    public void createNewGame_should_returnNewGameWithRandomWord() {
        Mockito.when(wordService.getRandomWord()).thenReturn("RANDOM_WORD");

        var gameService = new GameService(wordService);
        var game = gameService.createNewGame();
        assertThat(game.getWord()).isEqualTo("RANDOM_WORD");
    }
}
