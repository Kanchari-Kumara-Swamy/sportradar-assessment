package com.sportradar.assessment.wordle;


import com.sportradar.assessment.wordle.enums.Color;
import com.sportradar.assessment.wordle.service.WordEvaluator;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WordEvaluatorTest {
    
    @Test
    void allCorrect() {
        WordEvaluator evaluator = new WordEvaluator();
        List<Color> result = evaluator.evaluate("water", "water");
        assertEquals(List.of(
                Color.GREEN,
                Color.GREEN,
                Color.GREEN,
                Color.GREEN,
                Color.GREEN
        ), result);
    }

    @Test
    void duplicateLetterRule() {
        WordEvaluator evaluator = new WordEvaluator();
        List<Color> result = evaluator.evaluate("otter", "water");
        // Expect O=gray, T=gray, T=green, E=green, R=green
        assertEquals(List.of(
                Color.GRAY,
                Color.GRAY,
                Color.GREEN,
                Color.GREEN,
                Color.GREEN
        ), result);
    }

    @Test
    void yellowNotOvercounted() {
        WordEvaluator evaluator = new WordEvaluator();
        List<Color> result = evaluator.evaluate("poppy", "apple");
        assertEquals(Color.YELLOW, result.get(0)); // first P
        assertEquals(Color.GREEN, result.get(2)); // second P
        assertEquals(Color.GRAY, result.get(3));  // third P, coz its excess
    }
}

