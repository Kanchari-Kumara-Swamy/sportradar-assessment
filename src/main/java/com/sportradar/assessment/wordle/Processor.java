package com.sportradar.assessment.wordle;

import com.sportradar.assessment.wordle.domain.WordEntry;
import com.sportradar.assessment.wordle.enums.Color;
import com.sportradar.assessment.wordle.repository.WordRepository;
import com.sportradar.assessment.wordle.repository.WordRepositoryImpl;
import com.sportradar.assessment.wordle.service.FeedbackRender;
import com.sportradar.assessment.wordle.service.WordEvaluator;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

@AllArgsConstructor
public class Processor {

    private static final int MAX_ATTEMPTS = 5;

    private final WordRepository repository;
    private final WordEvaluator evaluator;
    private final FeedbackRender feedback;

    public Processor(String filepath) {
        this.repository = new WordRepositoryImpl(filepath);
        this.evaluator = new WordEvaluator();
        this.feedback = new FeedbackRender();
    }

    //testing
    public Processor(WordRepository wordRepository) {
        this.repository = wordRepository;
        this.evaluator = new WordEvaluator();
        this.feedback = new FeedbackRender();
    }




    public void play() {
        List<WordEntry> wordEntries = repository.loadWords();
        if (wordEntries.isEmpty()) {
            System.out.println("word list is empty to load");
        }

        Scanner scanner = new Scanner(System.in);
        WordEntry entry = wordEntries.get(new Random().nextInt(wordEntries.size()));

        //introducing rules
        gameRules();
        System.out.println("Are you ready? y/n :");

        //gets user consent
        if (!consent(scanner)) return;

        for (int i = MAX_ATTEMPTS; i > 0; i--) {
            System.out.printf("You have %d ❤️, Guess the word : \n", i);

            if (i == 1) {
                System.out.println("Do you need a hint?, y/n:");
                if (consent(scanner)) System.out.println("Hint: \"" + entry.hint() + "\"");
            }
            String guess = scanner.next().trim().toLowerCase();

            if (guess.length() < MAX_ATTEMPTS) {
                System.out.println("Please enter exactly 5 letters.");
                i++;
                continue;
            }
            List<Color> evaluate = evaluator.evaluate(guess, entry.word());
            String renderWord = feedback.render(guess, evaluate);

            System.out.println(renderWord);

            if (guess.equals(entry.word())) {
                System.out.println("\\(^_^)/  Bravo! You win!");
                return;
            } else if (i ==1) {
                System.out.println("you loose :-(");
            }
        }

    }

    private boolean consent(Scanner scanner) {
        Character consent = scanner.next().toLowerCase().trim().charAt(0);
        return consent.equals('y');
    }

    private void gameRules() {

        try {
            System.out.println("Welcome to Wordle (CLI Edition)!");

            Thread.sleep(2000);
            System.out.println("How to play:");

            Thread.sleep(2000);
            System.out.println("You need to guess a 5-letter word in " + MAX_ATTEMPTS + " tries.");

            Thread.sleep(2000);
            System.out.println("Green = correct letter & position, Yellow = correct letter but wrong place.");

            Thread.sleep(2000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
