package portfolio.games;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/** Small Swing window demo replacing the original launch-page experiment. */
public class SimpleWindowDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleWindowDemo::createWindow);
    }

    private static void createWindow() {
        JFrame frame = new JFrame("Window Demo");
        JButton button = new JButton("Open Message");
        button.addActionListener(event -> showMessageWindow());
        frame.add(button, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 220);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showMessageWindow() {
        JFrame frame = new JFrame("Message");
        frame.add(new JLabel("Hello from a second Swing window.", SwingConstants.CENTER));
        frame.setSize(360, 180);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
