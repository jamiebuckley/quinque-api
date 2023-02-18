package org.github.jamiebuckley.quinqueapi.mappers;

import org.github.jamiebuckley.quinqueapi.dto.PreviousGuessDTO;
import org.github.jamiebuckley.quinqueapi.models.Game;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper
public interface PreviousGuessMapper {

    default PreviousGuessDTO previousGuessToPreviousGuessDTO(Game game, String guess) {
        var word = game.getWord();
        var letterGuesses = new ArrayList<PreviousGuessDTO.LetterGuess>();
        for(var i = 0; i < 5 ; i++) {
            letterGuesses.add(getPreviousLetterGuess(word, guess, i));
        }
        var previousGuessDTO = new PreviousGuessDTO();
        previousGuessDTO.setGuess(guess);
        previousGuessDTO.setLetters(letterGuesses);
        return previousGuessDTO;
    }

    default PreviousGuessDTO.LetterGuess getPreviousLetterGuess(String word, String guess, int index) {
        var letterGuess = new PreviousGuessDTO.LetterGuess();
        letterGuess.setLetter(String.valueOf(guess.charAt(index)));

        if (guess.charAt(index) == word.charAt(index)) {
            letterGuess.setLetterCorrectness(PreviousGuessDTO.LetterCorrectness.CORRECT);
        }
        else if (word.indexOf(guess.charAt(index)) != -1) {
            letterGuess.setLetterCorrectness(PreviousGuessDTO.LetterCorrectness.WRONG_PLACE);
        }
        else {
            letterGuess.setLetterCorrectness(PreviousGuessDTO.LetterCorrectness.WRONG);
        }
        return letterGuess;
    }
}
