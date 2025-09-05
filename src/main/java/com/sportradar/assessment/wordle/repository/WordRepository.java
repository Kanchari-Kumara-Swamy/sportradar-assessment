package com.sportradar.assessment.wordle.repository;

import com.sportradar.assessment.wordle.domain.WordEntry;

import java.util.List;

public interface WordRepository {

    List<WordEntry> loadWords();
}
