package org.github.jamiebuckley.quinqueapi.exceptions;

public class NoMoreGuessesAllowedException extends Exception {

    private String gameId;

    public NoMoreGuessesAllowedException(String gameId) {
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }
}
