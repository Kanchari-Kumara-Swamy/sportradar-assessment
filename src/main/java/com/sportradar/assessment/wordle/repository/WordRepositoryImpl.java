package com.sportradar.assessment.wordle.repository;

import com.sportradar.assessment.wordle.domain.WordEntry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WordRepositoryImpl implements WordRepository {

    private final String filePath;

    public WordRepositoryImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<WordEntry> loadWords() {
        try {
            return Files.readAllLines(Paths.get(filePath)).stream()
                    .map(String::trim)
                    .filter(line -> line.contains(","))
                    .map(line -> {
                        String[] parts = line.split(",", 2);
                        return new WordEntry(parts[0].trim(), parts[1].trim());
                    })
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load words from " + filePath, e);
        }
    }
}
