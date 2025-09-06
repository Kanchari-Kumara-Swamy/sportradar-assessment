package com.sportradar.assessment.wordle;

import com.sportradar.assessment.wordle.domain.WordEntry;
import com.sportradar.assessment.wordle.repository.WordRepository;
import com.sportradar.assessment.wordle.repository.WordRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordRepositoryTest {

    private List<WordEntry> entries;

    @BeforeEach
    public void loadWords(){
        WordRepositoryImpl repo = new WordRepositoryImpl("src/main/resources/words.txt");
        entries = repo.loadWords();
    }

    @Test
    void repositoryLoadsWordEntriesCorrectly() throws IOException {

        assertEquals(7, entries.size());

        assertEquals("water", entries.get(0).word());
        assertEquals("the thing that keeps you hydrated", entries.get(0).hint());

        assertEquals("otter", entries.get(1).word());
        assertEquals("some people keep it as a pet", entries.get(1).hint());

        assertEquals("pizza", entries.get(3).word());
        assertEquals("could be your favourite dish", entries.get(3).hint());
    }

    @Test
    void repositoryIgnoresInvalidLines() throws IOException {

        Path tempFile = Files.createTempFile("temp", ".txt");
        Files.writeString(tempFile, """
                water, the thing that keeps you hydrated
                invalidlinewithoutcomma
                pizza, could be your favourite dish
                """);

        WordRepository repo = new WordRepositoryImpl(tempFile.toString());
        List<WordEntry> entries = repo.loadWords();

        assertEquals(2, entries.size()); // only valid lines
        assertEquals("water", entries.get(0).word());
        assertEquals("pizza", entries.get(1).word());

        Files.deleteIfExists(tempFile);
    }
}
