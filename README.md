# Wordle (CLI Edition)

A command-line version of the popular word game Wordle, built with Java & Maven.

## Game Rules

- **Guess a 5-letter word in 5 attempts.**
- **Green:** correct letter & position.
- **Yellow:** correct letter, wrong position (no overcounting).
- **No Color:** letter not in the word.
- You can have a hint revealed on the 5th attempt.

## How to Run

### Prerequisites

- Java 17+
- Maven 3.x

### Run the Game

- `mvn clean package`
- `java -jar target/master-SNAPSHOT.jar`

### Run Tests

- `mvn test`

## Implementation

- Developed in a **Test-Driven Development (TDD)** environment.
- Applied **SOLID principles** for clean architecture.
- Modular design with separate classes for:
    - Word evaluation
    - Feedback rendering
    - Word list repository

## Highlights

- Accurate duplicate letter handling.
- Hints included for user-friendly gameplay.
- Lightweight word list stored in `resources/words.txt`.
- Unit tests for evaluation rules and game flow.
