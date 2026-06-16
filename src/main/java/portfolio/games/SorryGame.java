package portfolio.games;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 * Console Sorry-style board game.
 *
 * This is a cleaned portfolio version of an ICS4U project. The rules are simplified
 * so the program stays playable in a terminal while still demonstrating arrays/lists,
 * classes, user input, random cards, save/load, and turn-based game logic.
 */
public class SorryGame {
    private static final int BOARD_SIZE = 60;
    private static final int HOME_POSITION = BOARD_SIZE;
    private static final int PAWNS_PER_PLAYER = 2;
    private static final Path SAVE_FILE = Path.of("sorry-save.properties");

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final List<Player> players = new ArrayList<>();
    private final List<Card> deck = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public static void main(String[] args) {
        new SorryGame().run();
    }

    private void run() {
        printTitle();
        setupDeck();

        if (Files.exists(SAVE_FILE) && askYesNo("Load saved game?")) {
            try {
                loadGame();
            } catch (IOException ex) {
                System.out.println("Could not load save file. Starting a new game.");
                setupPlayers();
            }
        } else {
            setupPlayers();
        }

        playGameLoop();
    }

    private void setupPlayers() {
        int playerCount = askInt("How many players? (2-4)", 2, 4);
        players.clear();
        for (int i = 0; i < playerCount; i++) {
            System.out.print("Player " + (i + 1) + " name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                name = "Player " + (i + 1);
            }
            players.add(new Player(name, i));
        }
        currentPlayerIndex = 0;
    }

    private void playGameLoop() {
        while (true) {
            Player player = players.get(currentPlayerIndex);
            printBoardSummary();
            System.out.println("\n" + player.name + "'s turn.");
            System.out.println("Press Enter to draw a card, type save, or type quit.");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("save")) {
                saveGameSafely();
                continue;
            }
            if (command.equals("quit")) {
                saveGameSafely();
                System.out.println("Game saved. Goodbye.");
                return;
            }

            Card card = drawCard();
            System.out.println("Card: " + card.description);
            takeTurn(player, card);

            if (player.hasWon()) {
                printBoardSummary();
                System.out.println("\n" + player.name + " wins!");
                deleteSaveFile();
                return;
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    private void takeTurn(Player player, Card card) {
        if (card.type == CardType.SORRY) {
            playSorryCard(player);
            return;
        }

        List<Pawn> movablePawns = player.movablePawns(card);
        if (movablePawns.isEmpty()) {
            System.out.println("No legal move for this card.");
            return;
        }

        Pawn pawn = choosePawn(movablePawns);
        int oldPosition = pawn.position;
        pawn.move(card.steps);
        bumpOpponentAt(pawn.position, player);
        System.out.println("Moved pawn " + pawn.label + " from " + formatPosition(oldPosition) + " to " + formatPosition(pawn.position) + ".");
    }

    private void playSorryCard(Player player) {
        Pawn startPawn = player.firstPawnInStart();
        if (startPawn == null) {
            System.out.println("No pawn in start, so the Sorry card cannot be used.");
            return;
        }

        List<Pawn> targets = opponentPawnsOnBoard(player);
        if (targets.isEmpty()) {
            System.out.println("No opponent pawn is on the board.");
            return;
        }

        Pawn target = choosePawn(targets);
        startPawn.position = target.position;
        target.sendToStart();
        System.out.println(player.name + " bumped " + target.owner.name + "'s pawn " + target.label + ".");
    }

    private Pawn choosePawn(List<Pawn> options) {
        for (int i = 0; i < options.size(); i++) {
            Pawn pawn = options.get(i);
            System.out.println((i + 1) + ". " + pawn.owner.name + " pawn " + pawn.label + " at " + formatPosition(pawn.position));
        }
        int choice = askInt("Choose a pawn", 1, options.size());
        return options.get(choice - 1);
    }

    private void bumpOpponentAt(int position, Player movingPlayer) {
        if (position < 0 || position >= HOME_POSITION) {
            return;
        }
        for (Player player : players) {
            if (player == movingPlayer) {
                continue;
            }
            for (Pawn pawn : player.pawns) {
                if (pawn.position == position) {
                    pawn.sendToStart();
                    System.out.println("Bumped " + player.name + "'s pawn " + pawn.label + " back to start.");
                }
            }
        }
    }

    private List<Pawn> opponentPawnsOnBoard(Player currentPlayer) {
        List<Pawn> targets = new ArrayList<>();
        for (Player player : players) {
            if (player == currentPlayer) {
                continue;
            }
            for (Pawn pawn : player.pawns) {
                if (pawn.isOnBoard()) {
                    targets.add(pawn);
                }
            }
        }
        return targets;
    }

    private void setupDeck() {
        deck.clear();
        deck.add(new Card("1: move one space forward or leave start", 1, CardType.MOVE));
        deck.add(new Card("2: move two spaces forward or leave start", 2, CardType.MOVE));
        deck.add(new Card("3: move three spaces forward", 3, CardType.MOVE));
        deck.add(new Card("4: move four spaces backward", -4, CardType.MOVE));
        deck.add(new Card("5: move five spaces forward", 5, CardType.MOVE));
        deck.add(new Card("7: move seven spaces forward", 7, CardType.MOVE));
        deck.add(new Card("8: move eight spaces forward", 8, CardType.MOVE));
        deck.add(new Card("10: move ten spaces forward", 10, CardType.MOVE));
        deck.add(new Card("11: move eleven spaces forward", 11, CardType.MOVE));
        deck.add(new Card("12: move twelve spaces forward", 12, CardType.MOVE));
        deck.add(new Card("Sorry: move from start and bump an opponent", 0, CardType.SORRY));
    }

    private Card drawCard() {
        return deck.get(random.nextInt(deck.size()));
    }

    private void printBoardSummary() {
        System.out.println("\nBoard summary");
        System.out.println("-------------");
        for (Player player : players) {
            System.out.print(player.name + ": ");
            for (Pawn pawn : player.pawns) {
                System.out.print(pawn.label + "=" + formatPosition(pawn.position) + " ");
            }
            System.out.println();
        }
    }

    private String formatPosition(int position) {
        if (position < 0) {
            return "START";
        }
        if (position >= HOME_POSITION) {
            return "HOME";
        }
        return String.valueOf(position);
    }

    private int askInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Re-prompt below.
            }
            System.out.println("Enter a number from " + min + " to " + max + ".");
        }
    }

    private boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            }
            if (input.equals("n") || input.equals("no")) {
                return false;
            }
        }
    }

    private void saveGameSafely() {
        try {
            saveGame();
            System.out.println("Saved to " + SAVE_FILE + ".");
        } catch (IOException ex) {
            System.out.println("Save failed: " + ex.getMessage());
        }
    }

    private void saveGame() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("currentPlayerIndex", String.valueOf(currentPlayerIndex));
        properties.setProperty("playerCount", String.valueOf(players.size()));

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            properties.setProperty("player." + i + ".name", player.name);
            for (int j = 0; j < player.pawns.size(); j++) {
                properties.setProperty("player." + i + ".pawn." + j, String.valueOf(player.pawns.get(j).position));
            }
        }

        try (OutputStream out = Files.newOutputStream(SAVE_FILE)) {
            properties.store(out, "SorryGame save file");
        }
    }

    private void loadGame() throws IOException {
        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(SAVE_FILE)) {
            properties.load(in);
        }

        players.clear();
        int playerCount = Integer.parseInt(properties.getProperty("playerCount"));
        for (int i = 0; i < playerCount; i++) {
            String name = properties.getProperty("player." + i + ".name", "Player " + (i + 1));
            Player player = new Player(name, i);
            for (int j = 0; j < PAWNS_PER_PLAYER; j++) {
                player.pawns.get(j).position = Integer.parseInt(properties.getProperty("player." + i + ".pawn." + j, "-1"));
            }
            players.add(player);
        }
        currentPlayerIndex = Integer.parseInt(properties.getProperty("currentPlayerIndex", "0"));
        currentPlayerIndex = Math.floorMod(currentPlayerIndex, players.size());
    }

    private void deleteSaveFile() {
        try {
            Files.deleteIfExists(SAVE_FILE);
        } catch (IOException ignored) {
            // The game can finish even if cleanup fails.
        }
    }

    private void printTitle() {
        System.out.println("============================");
        System.out.println("      Sorry-Style Game");
        System.out.println("============================");
    }

    private enum CardType {
        MOVE,
        SORRY
    }

    private static final class Card {
        private final String description;
        private final int steps;
        private final CardType type;

        private Card(String description, int steps, CardType type) {
            this.description = description;
            this.steps = steps;
            this.type = type;
        }
    }

    private static final class Player {
        private final String name;
        private final List<Pawn> pawns = new ArrayList<>();

        private Player(String name, int playerIndex) {
            this.name = name;
            for (int i = 0; i < PAWNS_PER_PLAYER; i++) {
                pawns.add(new Pawn(this, (char) ('A' + i)));
            }
        }

        private List<Pawn> movablePawns(Card card) {
            List<Pawn> result = new ArrayList<>();
            for (Pawn pawn : pawns) {
                if (pawn.canMove(card.steps)) {
                    result.add(pawn);
                }
            }
            return result;
        }

        private Pawn firstPawnInStart() {
            for (Pawn pawn : pawns) {
                if (pawn.isInStart()) {
                    return pawn;
                }
            }
            return null;
        }

        private boolean hasWon() {
            for (Pawn pawn : pawns) {
                if (!pawn.isHome()) {
                    return false;
                }
            }
            return true;
        }
    }

    private static final class Pawn {
        private final Player owner;
        private final char label;
        private int position = -1;

        private Pawn(Player owner, char label) {
            this.owner = owner;
            this.label = label;
        }

        private boolean isInStart() {
            return position < 0;
        }

        private boolean isOnBoard() {
            return position >= 0 && position < HOME_POSITION;
        }

        private boolean isHome() {
            return position >= HOME_POSITION;
        }

        private boolean canMove(int steps) {
            if (isHome()) {
                return false;
            }
            if (isInStart()) {
                return steps == 1 || steps == 2;
            }
            return position + steps >= 0;
        }

        private void move(int steps) {
            if (isInStart()) {
                position = 0;
            } else {
                position += steps;
            }
            if (position >= HOME_POSITION) {
                position = HOME_POSITION;
            }
        }

        private void sendToStart() {
            position = -1;
        }
    }
}
