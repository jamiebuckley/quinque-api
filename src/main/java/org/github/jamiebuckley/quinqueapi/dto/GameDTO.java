package org.github.jamiebuckley.quinqueapi.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * A game of Quinqo
 */
public class GameDTO {

    private String id;

    private List<PreviousGuessDTO> previousGuesses = new ArrayList<>();

    private boolean solved;

    private String word;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PreviousGuessDTO> getPreviousGuesses() {
        return previousGuesses;
    }

    public void setPreviousGuesses(List<PreviousGuessDTO> previousGuesses) {
        this.previousGuesses = previousGuesses;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
