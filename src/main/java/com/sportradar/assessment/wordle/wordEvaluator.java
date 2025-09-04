package com.sportradar.assessment.wordle;

import com.sportradar.assessment.wordle.enums.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class wordEvaluator {

    public List<Color> evaluate(String guess, String solution) {
        guess = guess.toLowerCase();
        solution = solution.toLowerCase();

        List<Color> result = new ArrayList<>(Collections.nCopies(5, Color.GRAY));
        char[] solutionChars = solution.toCharArray();

        // checking for green letter
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == solution.charAt(i)) {
                result.set(i, Color.GREEN);
            }
        }

        //checking for yellow letters
        for (int i = 0; i < 5; i++) {
            if (result.get(i) == Color.GRAY) {
                for (int j = 0; j < 5; j++) {
                    if (guess.charAt(i) == solutionChars[j]) {
                        result.set(i, Color.YELLOW);
                        break;
                    }
                }
            }
        }
        return result;

    }
}
