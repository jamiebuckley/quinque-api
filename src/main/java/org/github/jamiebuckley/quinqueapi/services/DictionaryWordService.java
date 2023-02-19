package org.github.jamiebuckley.quinqueapi.services;

import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
            lines = new BufferedReader(new InputStreamReader(wordResource.getInputStream(),
                    StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load quinqo words list", e);
        }
    }

    public String getRandomWord() {
        var random = new Random();
        return lines.get(random.nextInt(lines.size()));
    }
}
