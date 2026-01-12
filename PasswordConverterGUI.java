import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordConverterGUI {

    private static final int SHIFT = 3;

    // Encode using Caesar Cipher (shift 3)
    public static String encode(String og) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < og.length(); i++) {
            char c = og.charAt(i);

            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base + SHIFT) % 26 + base);
            }

            result.append(c);
        }

        return SHIFT + "|" + result.toString();
    }

    // Decode back to original
    public static String decode(String encoded) {
        String[] parts = encoded.split("\\|");

        if (parts.length != 2) {
            return "Invalid encoded format!";
        }

        String text = parts[1];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base - SHIFT + 26) % 26 + base);
            }

            result.append(c);
        }

        return result.toString();
    }

    public static void main(String[] args) {

        // Create window
        JFrame frame = new JFrame("Password Converter");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 1));

        // Components
        JLabel instruction = new JLabel("Enter text:", SwingConstants.CENTER);
        JTextField inputField = new JTextField();
        JButton encodeButton = new JButton("Encode");
        JButton decodeButton = new JButton("Decode");
        JLabel outputLabel = new JLabel("Result will appear here", SwingConstants.CENTER);

        // Encode button action (FIXED)
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                String encoded = encode(input);
                inputField.setText(encoded);   // ✅ FIX
                outputLabel.setText("Encoded successfully!");
            }
        });

        // Decode button action
        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                String decoded = decode(input);
                outputLabel.setText("Decoded: " + decoded);
            }
        });

        // Add components
        frame.add(instruction);
        frame.add(inputField);
        frame.add(encodeButton);
        frame.add(decodeButton);
        frame.add(outputLabel);

        // Show window
        frame.setVisible(true);
    }
}
