package org.github.jamiebuckley.quinqueapi.services;

import org.github.jamiebuckley.quinqueapi.models.Game;
import org.springframework.stereotype.Service;

/**
 * Handles creation of and state updates to games
 */
@Service
public class GameService {

    private final WordService wordService;

    public GameService(WordService wordService) {
        this.wordService = wordService;
    }

    public Game createNewGame() {
        var game = new Game();
        game.setWord(wordService.getRandomWord());
        return game;
    }

    public Game addGuess(Game game, String guess) {
        game.getGuesses().add(guess.toUpperCase());
        if (guess.equalsIgnoreCase(game.getWord())) {
            game.setSolved(true);
        }
        return game;
    }

    public boolean canGuess(Game game) {
        if (!game.getSolved()) return false;
        return game.getGuesses().size() < 5;
    }
}
