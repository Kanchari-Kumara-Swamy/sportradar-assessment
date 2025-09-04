package com.sportradar.assessment.wordle;


import com.sportradar.assessment.wordle.enums.Color;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class wordEvaluatorTest {
    
    @Test
    void allCorrect() {
        wordEvaluator evaluator = new wordEvaluator();
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
        wordEvaluator evaluator = new wordEvaluator();
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
        wordEvaluator evaluator = new wordEvaluator();
        List<Color> result = evaluator.evaluate("poppy", "apple");
        assertEquals(Color.GREEN, result.get(0)); // first P
        assertEquals(Color.GRAY, result.get(1));  // second P
    }
}

