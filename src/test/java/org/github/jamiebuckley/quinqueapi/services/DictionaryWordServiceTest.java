package org.github.jamiebuckley.quinqueapi.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DictionaryWordServiceTest {

    @Test
    public void getRandomWord_shouldReturn_randomWord() {
        var wordService = new DictionaryWordService();
        var word = wordService.getRandomWord();
        Assertions.assertThat(word).isIn(Arrays.asList("EXAMPLE_WORD_1", "EXAMPLE_WORD_2", "EXAMPLE_WORD_3"));
    }
}
