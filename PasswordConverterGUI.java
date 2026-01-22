// ======================= IMPORTS =======================
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


// ======================= MAIN CLASS =======================
public class PasswordConverterGUI {

    // Constant shift value for the Caesar Cipher
    private static final int SHIFT = 3;

    // ======================= ENCODING METHOD =======================
    // Encodes text using a Caesar Cipher (shift of 3)
    private static String encode(String original) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);

            // Only shift alphabetic characters
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';

                // Shift letter forward by SHIFT positions
                c = (char) ((c - base + SHIFT) % 26 + base);
            }

            result.append(c);
        }

        return result.toString();
    }

    // ======================= DECODING METHOD =======================
    // Decodes text that was encoded using the encode() method
    private static String decode(String encoded) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < encoded.length(); i++) {
            char c = encoded.charAt(i);

            // Only shift alphabetic characters
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';

                // Shift letter backward by SHIFT positions
                c = (char) ((c - base - SHIFT + 26) % 26 + base);
            }

            result.append(c);
        }

        return result.toString();
    }

    // ======================= GUI CREATION =======================
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
                inputField.setText(encode(input));
                outputLabel.setText("Encoded successfully!");
            }
        });

        // Decode button action
        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                outputLabel.setText("Decoded: " + decode(input));
            }
        });

        // Close button action
        closeButton.addActionListener(e -> System.exit(0));

        frame.add(instruction);
        frame.add(inputField);
        frame.add(encodeButton);
        frame.add(decodeButton);
        frame.add(closeButton);
        frame.add(outputLabel);

        frame.setVisible(true);
    }

    // ======================= UNUSED CONSOLE METHODS =======================
    // Leftover from an earlier console-based version

    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to encode/decode (or type 'exit' to quit): ");
        return scanner.nextLine();
    }

    private static void printMenu() {
        System.out.println("1. Encode");
        System.out.println("2. Decode");
        System.out.println("3. Exit");
    }

    // ======================= PROGRAM ENTRY POINT =======================
    public static void main(String[] args) {
        createGUI();
    }
}
