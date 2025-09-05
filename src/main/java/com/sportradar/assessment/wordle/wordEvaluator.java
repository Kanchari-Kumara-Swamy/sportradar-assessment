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
        boolean[] occupied = new boolean[5];

        // checking for green letter
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == solution.charAt(i)) {
                result.set(i, Color.GREEN);
                occupied[i] = true;
            }
        }

        //checking for yellow letters
        for (int i = 0; i < 5; i++) {
            if (result.get(i) == Color.GRAY) {
                for (int j = 0; j < 5; j++) {
                    if (!occupied[j] && guess.charAt(i) == solutionChars[j]) {
                        result.set(i, Color.YELLOW);
                        occupied[j] = true;
                        break;
                    }
                }
            }
        }
        return result;

    }
}
