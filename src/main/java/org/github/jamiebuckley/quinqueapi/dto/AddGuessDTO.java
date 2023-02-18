package org.github.jamiebuckley.quinqueapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Represents an attempt to guess the word
 */
public class AddGuessDTO {

    @NotBlank
    @Size(min=5, max=5)
    private String guess;

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }
}
