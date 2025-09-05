package com.sportradar.assessment.wordle;

import com.sportradar.assessment.wordle.repository.WordRepository;

public class Processor {

    private static final int MAX_ATTEMPTS = 5;

    private final WordRepository repository;
    private final WordEvaluator evaluator;

    public Processor() {
        this.repository = new WordRepositoryImpl("classpath:words.txt");
        this.evaluator = new WordEvaluator();
    }

    public void play(){
        repository.loadWords();
        evaluator.evaluate("apple", "apple");
    }

}
