package com.sportradar.assessment.wordle.service;

import com.sportradar.assessment.wordle.enums.Color;

import java.util.List;

public class FeedbackRender {

    public String render(String guess, List<Color> evaluation) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char ch = Character.toUpperCase(guess.charAt(i));
            switch (evaluation.get(i)) {
                case GREEN -> sb.append("\u001B[32m").append(ch).append("\u001B[0m");
                case YELLOW -> sb.append("\u001B[93m").append(ch).append("\u001B[0m");
                default -> sb.append(ch);
            }
        }
        return sb.toString();
    }
}
