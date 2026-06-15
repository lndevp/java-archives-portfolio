package portfolio.games;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * A standalone Swing Guess Who-style game using generated neutral character cards.
 * It avoids external image assets so the project is portable and safe to publish.
 */
public class GuessWhoSwingGame {
    private final List<GuessWhoCharacter> allCharacters = createCharacters();
    private final List<GuessWhoCharacter> remainingCharacters = new ArrayList<>(allCharacters);
    private final GuessWhoCharacter secretCharacter = allCharacters.get(new Random().nextInt(allCharacters.size()));
    private final JPanel gridPanel = new JPanel(new GridLayout(4, 6, 8, 8));
    private final JLabel statusLabel = new JLabel("Ask questions to narrow the board.", SwingConstants.CENTER);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessWhoSwingGame().show());
    }

    private void show() {
        JFrame frame = new JFrame("Guess Who - Java Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(12, 12));
        frame.setMinimumSize(new Dimension(980, 680));

        JComboBox<Question> questionBox = new JComboBox<>(Question.values());
        JButton askButton = new JButton("Ask");
        JButton guessButton = new JButton("Guess Character");

        askButton.addActionListener(event -> askQuestion((Question) questionBox.getSelectedItem()));
        guessButton.addActionListener(event -> makeGuess(frame));

        JPanel controls = new JPanel();
        controls.add(questionBox);
        controls.add(askButton);
        controls.add(guessButton);

        statusLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(controls, BorderLayout.SOUTH);

        refreshGrid();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void askQuestion(Question question) {
        if (question == null) {
            return;
        }
        boolean answer = question.matches(secretCharacter);
        remainingCharacters.removeIf(character -> question.matches(character) != answer);
        statusLabel.setText(question.prompt + " Answer: " + (answer ? "Yes" : "No")
                + ". Remaining: " + remainingCharacters.size());
        refreshGrid();
    }

    private void makeGuess(JFrame frame) {
        String[] names = remainingCharacters.stream().map(character -> character.name).toArray(String[]::new);
        String guess = (String) JOptionPane.showInputDialog(frame, "Choose your guess:", "Guess",
                JOptionPane.PLAIN_MESSAGE, null, names, names.length == 0 ? null : names[0]);
        if (guess == null) {
            return;
        }
        if (secretCharacter.name.equals(guess)) {
            JOptionPane.showMessageDialog(frame, "Correct. The character was " + secretCharacter.name + ".");
        } else {
            JOptionPane.showMessageDialog(frame, "Not quite. The character was " + secretCharacter.name + ".");
        }
    }

    private void refreshGrid() {
        gridPanel.removeAll();
        for (GuessWhoCharacter character : allCharacters) {
            gridPanel.add(createCard(character, remainingCharacters.contains(character)));
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private JPanel createCard(GuessWhoCharacter character, boolean active) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(active ? Color.DARK_GRAY : Color.LIGHT_GRAY, 2));
        card.setBackground(active ? character.cardColor : new Color(220, 220, 220));

        JLabel initials = new JLabel(initials(character.name), SwingConstants.CENTER);
        initials.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        initials.setForeground(active ? Color.WHITE : Color.GRAY);

        JLabel name = new JLabel(character.name, SwingConstants.CENTER);
        name.setOpaque(true);
        name.setBackground(new Color(255, 255, 255, active ? 220 : 160));

        card.add(initials, BorderLayout.CENTER);
        card.add(name, BorderLayout.SOUTH);
        return card;
    }

    private String initials(String name) {
        String[] parts = name.split(" ");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            if (!part.isBlank()) {
                result.append(part.charAt(0));
            }
        }
        return result.toString().toUpperCase(Locale.ROOT);
    }

    private static List<GuessWhoCharacter> createCharacters() {
        List<GuessWhoCharacter> characters = new ArrayList<>();
        String[] names = {
                "Alex Nova", "Blair Stone", "Casey Ray", "Drew Vale", "Emery Fox", "Finley Moon",
                "Gray Quinn", "Harper Lane", "Indigo Park", "Jordan Sky", "Kai River", "Logan Frost",
                "Morgan Jet", "Nico Storm", "Oakley Dawn", "Parker Reed", "Quinn Lake", "Riley Star",
                "Sage Cloud", "Taylor Bright", "Uma West", "Val North", "Winter Green", "Zion Brook"
        };
        for (int i = 0; i < names.length; i++) {
            characters.add(new GuessWhoCharacter(
                    names[i],
                    i % 2 == 0,
                    i % 3 == 0,
                    i % 4 == 0,
                    i % 5 == 0,
                    new Color(Color.HSBtoRGB(i / 24.0f, 0.65f, 0.65f))));
        }
        return characters;
    }

    private record GuessWhoCharacter(String name, boolean hasGlasses, boolean hasHat, boolean hasLongHair,
                                     boolean hasFacialHair, Color cardColor) {
    }

    private enum Question {
        GLASSES("Does your character have glasses?") {
            @Override boolean matches(GuessWhoCharacter character) { return character.hasGlasses; }
        },
        HAT("Does your character have a hat?") {
            @Override boolean matches(GuessWhoCharacter character) { return character.hasHat; }
        },
        LONG_HAIR("Does your character have long hair?") {
            @Override boolean matches(GuessWhoCharacter character) { return character.hasLongHair; }
        },
        FACIAL_HAIR("Does your character have facial hair?") {
            @Override boolean matches(GuessWhoCharacter character) { return character.hasFacialHair; }
        };

        private final String prompt;

        Question(String prompt) {
            this.prompt = prompt;
        }

        abstract boolean matches(GuessWhoCharacter character);

        @Override
        public String toString() {
            return prompt;
        }
    }
}
