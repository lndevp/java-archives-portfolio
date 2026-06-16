# Java Archives Portfolio

Polished Java portfolio projects rebuilt from an Eclipse coursework folder. The original work covered arrays, `ArrayList`, sorting, searching, recursion, OOP, inheritance, abstract classes, interfaces, Swing GUI basics, and small game projects.

## Projects

| Area | Files | What it demonstrates |
| --- | --- | --- |
| Algorithms | `portfolio.algorithms` | Linear search, binary search, insertion sort, selection sort, bubble sort, merge sort, quick sort, benchmark counters |
| Recursion | `portfolio.algorithms.RecursionExamples` | Recursive factorial, sum, countdown, and binary search |
| Bank Account OOP | `portfolio.oop.BankAccountDemo` | Encapsulation, validation, deposits, withdrawals, transfers |
| Inheritance / Abstract Classes | `portfolio.oop.InheritanceDemo`, `portfolio.oop.AbstractClassDemo` | Abstract methods, constructors, inheritance, method overriding |
| Marvel Battle | `portfolio.games.MarvelBattleGame` | Interfaces, random outcomes, clean model classes |
| Tic Tac Toe | `portfolio.games.TicTacToeGame` | 2-player console game loop and win detection |
| Guess Who | `portfolio.games.GuessWhoSwingGame` | Java Swing GUI, character filtering, question logic, guessing logic, generated neutral cards |
| Sorry Game | `portfolio.games.SorryGame` | ICS4U console board game archive with cleaned turn logic, card drawing, save/load, and pawn movement |

## Run

Compile everything:

```bash
javac -d out $(find src/main/java -name "*.java")
```

Run examples:

```bash
java -cp out portfolio.algorithms.SortingBenchmark
java -cp out portfolio.algorithms.SearchComparison
java -cp out portfolio.oop.BankAccountDemo
java -cp out portfolio.oop.InheritanceDemo
java -cp out portfolio.games.MarvelBattleGame
java -cp out portfolio.games.TicTacToeGame
java -cp out portfolio.games.GuessWhoSwingGame
java -cp out portfolio.games.SorryGame
```

Run smoke checks:

```bash
javac -cp out -d out $(find src/test/java -name "*.java")
java -cp out portfolio.PortfolioSmokeTest
```

## Privacy / Asset Note

The original Eclipse project included personal photos used as temporary classroom assets. They are intentionally not included in this public portfolio. The Swing Guess Who project uses generated neutral character cards instead. The original Sorry game source is kept in `archive/sorry-game-ics4u/`, while the compileable version is integrated into `portfolio.games`.
