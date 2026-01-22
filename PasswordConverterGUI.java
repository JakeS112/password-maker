import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class PasswordConverterGUI {

    private static final int SHIFT = 3;

    // Main method to run the GUI
    public static void main(String[] args) {
        createGUI();
    }

    // Create GUI
    private static void createGUI() {
        JFrame frame = new JFrame("Password Converter");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1));

        JLabel instruction = new JLabel("Enter text:", SwingConstants.CENTER);
        JTextField inputField = new JTextField();
        JButton encodeButton = new JButton("Encode");
        JButton decodeButton = new JButton("Decode");
        JButton closeButton = new JButton("Close");
        JLabel outputLabel = new JLabel("Result will appear here", SwingConstants.CENTER);

        // Encode button action
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                String encoded = encode(input);
                inputField.setText(encoded);
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

        // Close button action
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add components
        frame.add(instruction);
        frame.add(inputField);
        frame.add(encodeButton);
        frame.add(decodeButton);
        frame.add(closeButton);
        frame.add(outputLabel);

        // Show window
        frame.setVisible(true);
    }

    // Encode Caesar Cipher (shift 3)
    private static String encode(String original) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);

            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base + Math.abs(SHIFT)) % 26 + base);
            }
            result.append(c);
        }

        return SHIFT + "|" + result.toString();
    }

    // Decode back to original
    private static String decode(String encoded) {
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

    // Method to get user input
    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to encode/decode (or type 'exit' to quit): ");
        return scanner.nextLine();
    }

    // Method to print menu options
    private static void printMenu() {
        System.out.println("1. Encode");
        System.out.println("2. Decode");
        System.out.println("3. Exit");
    }
}
