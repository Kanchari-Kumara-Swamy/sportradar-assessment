package com.sportradar.assessment.wordle;

import com.sportradar.assessment.wordle.domain.WordEntry;
import com.sportradar.assessment.wordle.repository.WordRepository;
import com.sportradar.assessment.wordle.repository.WordRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordRepositoryTest {

    @Test
    void repositoryLoadsWordEntriesCorrectly() throws IOException {

        WordRepositoryImpl repo = new WordRepositoryImpl("words.txt");
        List<WordEntry> entries = repo.loadWords();

        assertEquals(7, entries.size());

        assertEquals("water", entries.get(0).word());
        assertEquals("the thing that keeps you hydrated", entries.get(0).hint());

        assertEquals("otter", entries.get(1).word());
        assertEquals("some people keep it as a pet", entries.get(1).hint());

        assertEquals("pizza", entries.get(3).word());
        assertEquals("could be your favourite dish", entries.get(3).hint());
    }

    @Test
    void repositoryIgnoresInvalidLines() throws IOException, InterruptedException {

        File tempFile = new File("test.txt");
        WordRepository repo = new WordRepositoryImpl(tempFile.getName());
        List<WordEntry> entries = repo.loadWords();

        assertEquals(2, entries.size()); // only valid lines
        assertEquals("water", entries.get(0).word());
        assertEquals("pizza", entries.get(1).word());

    }
}
