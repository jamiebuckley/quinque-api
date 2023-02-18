package org.github.jamiebuckley.quinqueapi.dto;
import java.util.List;

/**
 * Describes a guess that has been made previously
 */
public class PreviousGuessDTO {

    /**
     * Whether a letter was correct, wrong or contained in a different place in the word
     */
    public enum LetterCorrectness {
        CORRECT,
        WRONG,
        WRONG_PLACE
    }

    /**
     * An individual letter of the guess, with the correctness of that letter
     */
    public static class LetterGuess {
        private String letter;

        private LetterCorrectness letterCorrectness;

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public PreviousGuessDTO.LetterCorrectness getLetterCorrectness() {
            return letterCorrectness;
        }

        public void setLetterCorrectness(PreviousGuessDTO.LetterCorrectness letterCorrectness) {
            this.letterCorrectness = letterCorrectness;
        }
    }

    /**
     * The word that was guessed
     */
    private String guess;

    /**
     * The letters that made up the guess, and their correctness
     */
    private List<LetterGuess> letters;

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public List<LetterGuess> getLetters() {
        return letters;
    }

    public void setLetters(List<LetterGuess> letters) {
        this.letters = letters;
    }
}
