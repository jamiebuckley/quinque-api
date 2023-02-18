package org.github.jamiebuckley.quinqueapi.mappers;

import org.assertj.core.api.HamcrestCondition;
import org.github.jamiebuckley.quinqueapi.dto.PreviousGuessDTO;
import org.github.jamiebuckley.quinqueapi.models.Game;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class PreviousGuessMapperTest {

    @Test
    public void previousGuessToPreviousGuessDTO_shouldIdentifyCorrectGuess() {
        var game = new Game();
        game.setWord("MANIC");

        var previousGuessMapper = Mappers.getMapper(PreviousGuessMapper.class);
        PreviousGuessDTO previousGuessDTO = previousGuessMapper.previousGuessToPreviousGuessDTO(game, "MANIC");
        var letters = previousGuessDTO.getLetters();

        assertThat(letters, Matchers.everyItem(
            hasProperty("letterCorrectness", is(PreviousGuessDTO.LetterCorrectness.CORRECT))
        ));
    }

    @Test
    public void previousGuessToPreviousGuessDTO_shouldIdentifyIncorrectGuess() {
        var game = new Game();
        game.setWord("MANIC");

        var previousGuessMapper = Mappers.getMapper(PreviousGuessMapper.class);
        PreviousGuessDTO previousGuessDTO = previousGuessMapper.previousGuessToPreviousGuessDTO(game, "ZZZZZ");
        var letters = previousGuessDTO.getLetters();

        assertThat(letters, Matchers.everyItem(
                hasProperty("letterCorrectness", is(PreviousGuessDTO.LetterCorrectness.WRONG))
        ));
    }

    @Test
    public void previousGuessToPreviousGuessDTO_shouldIdentifyLettersInWrongPlace() {
        var game = new Game();
        game.setWord("MANIC");

        var previousGuessMapper = Mappers.getMapper(PreviousGuessMapper.class);
        PreviousGuessDTO previousGuessDTO = previousGuessMapper.previousGuessToPreviousGuessDTO(game, "AZZZZ");
        var letters = previousGuessDTO.getLetters();
        assertThat(letters.get(0).getLetterCorrectness(), is(PreviousGuessDTO.LetterCorrectness.WRONG_PLACE));
    }
}
