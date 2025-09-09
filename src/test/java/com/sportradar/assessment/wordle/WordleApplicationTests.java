package com.sportradar.assessment.wordle;

import com.sportradar.assessment.wordle.domain.WordEntry;
import com.sportradar.assessment.wordle.repository.WordRepository;
import com.sportradar.assessment.wordle.service.FeedbackRender;
import com.sportradar.assessment.wordle.service.WordEvaluator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WordleApplicationTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Mock
    private WordRepository repo;

    @Mock
    private WordEvaluator evaluator;

    @Mock
    private FeedbackRender feedbackRender;

    @InjectMocks
    private Processor processor;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

	@Test
	void testFirstTryWin() {
        when(repo.loadWords()).thenReturn(List.of(new WordEntry("apple", "fruit")));
        System.setIn(new ByteArrayInputStream("Y\napple\n".getBytes()));
        processor.play();

        String output = outContent.toString();
        assertTrue(output.contains("Welcome to Wordle"));
        assertTrue(output.contains("\\(^_^)/  Bravo! You win!"));
	}

    @Test
    void testLoseAfterFiveAttempts() {
        when(repo.loadWords()).thenReturn(List.of(new WordEntry("apple", "fruit")));

        String simulatedInput = "y\nwrong\nwrong\nwrong\nwrong\ny\nwrong\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        processor.play();

        String output = outContent.toString();
        assertTrue(output.contains("Hint: \"fruit\""));
        assertTrue(output.contains("you loose :-("));
    }

}
