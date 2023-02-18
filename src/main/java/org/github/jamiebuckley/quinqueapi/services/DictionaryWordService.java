package org.github.jamiebuckley.quinqueapi.services;

import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

/**
 * Reads a random word from the embedded Quinqo dictionary and returns it
 */
@Service
@Scope("singleton")
public class DictionaryWordService implements WordService {

    private final List<String> lines;

    public DictionaryWordService() {
        var wordResource = new ClassPathResource("quinqo.txt");
        try {
            var wordFile = wordResource.getFile();
            lines = Files.readAllLines(wordFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load quinqo words list", e);
        }
    }

    public String getRandomWord() {
        var random = new Random();
        return lines.get(random.nextInt(lines.size()));
    }
}
