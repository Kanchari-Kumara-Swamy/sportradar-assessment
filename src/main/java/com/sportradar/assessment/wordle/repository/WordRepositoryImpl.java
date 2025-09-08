package com.sportradar.assessment.wordle.repository;

import com.sportradar.assessment.wordle.domain.WordEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class WordRepositoryImpl implements WordRepository {

    private final String filePath;

    public WordRepositoryImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<WordEntry> loadWords() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects
                .requireNonNull(getClass()
                        .getClassLoader()
                        .getResourceAsStream(filePath))))) {
            return br.lines()
                    .map(String::trim)
                    .filter(line -> line.contains(","))
                    .map(line -> {
                        String[] parts = line.split(",", 2);
                        return new WordEntry(parts[0].trim(), parts[1].trim());
                    })
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
