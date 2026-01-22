// ======================= IMPORTS =======================
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ======================= MAIN CLASS =======================
public class PasswordConverterGUI {

    // Constant shift value for the Caesar Cipher
    private static final int SHIFT = 3;
    
    // Modern color scheme
    private static final Color PRIMARY_COLOR = new Color(99, 102, 241);
    private static final Color SECONDARY_COLOR = new Color(139, 92, 246);
    private static final Color BACKGROUND_COLOR = new Color(248, 250, 252);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(30, 41, 59);
    private static final Color SUCCESS_COLOR = new Color(34, 197, 94);

    // ======================= ENCODING METHOD =======================
    private static String encode(String original) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base + SHIFT) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }

    // ======================= DECODING METHOD =======================
    private static String decode(String encoded) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < encoded.length(); i++) {
            char c = encoded.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base - SHIFT + 26) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }

    // ======================= STYLED BUTTON CREATION =======================
    private static JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 40));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }

    // ======================= GUI CREATION =======================
    private static void createGUI() {
        JFrame frame = new JFrame("Password Converter");
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(BACKGROUND_COLOR);
        
        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Title
        JLabel title = new JLabel("🔐 Password Converter");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(TEXT_COLOR);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Subtitle
        JLabel subtitle = new JLabel("Caesar Cipher (Shift 3)");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(100, 116, 139));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input card panel
        JPanel inputCard = new JPanel();
        inputCard.setLayout(new BorderLayout(10, 10));
        inputCard.setBackground(CARD_COLOR);
        inputCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        inputCard.setMaximumSize(new Dimension(450, 120));

        JLabel inputLabel = new JLabel("Enter your text:");
        inputLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        inputLabel.setForeground(TEXT_COLOR);

        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        inputField.setPreferredSize(new Dimension(400, 45));

        inputCard.add(inputLabel, BorderLayout.NORTH);
        inputCard.add(inputField, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.setMaximumSize(new Dimension(450, 50));

        JButton encodeButton = createStyledButton("Encode", PRIMARY_COLOR);
        JButton decodeButton = createStyledButton("Decode", SECONDARY_COLOR);

        buttonPanel.add(encodeButton);
        buttonPanel.add(decodeButton);

        // Output card panel
        JPanel outputCard = new JPanel(new BorderLayout(10, 10));
        outputCard.setBackground(CARD_COLOR);
        outputCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        outputCard.setMaximumSize(new Dimension(450, 100));

        JLabel outputTitle = new JLabel("Result:");
        outputTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        outputTitle.setForeground(TEXT_COLOR);

        JLabel outputLabel = new JLabel("Your result will appear here");
        outputLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        outputLabel.setForeground(new Color(100, 116, 139));

        outputCard.add(outputTitle, BorderLayout.NORTH);
        outputCard.add(outputLabel, BorderLayout.CENTER);

        // Close button
        JButton closeButton = createStyledButton("Close", new Color(239, 68, 68));
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Encode button action
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                if (!input.isEmpty()) {
                    String encoded = encode(input);
                    inputField.setText(encoded);
                    outputLabel.setText("✓ Encoded successfully!");
                    outputLabel.setForeground(SUCCESS_COLOR);
                }
            }
        });

        // Decode button action
        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                if (!input.isEmpty()) {
                    String decoded = decode(input);
                    outputLabel.setText(decoded);
                    outputLabel.setForeground(TEXT_COLOR);
                }
            }
        });

        // Close button action
        closeButton.addActionListener(e -> System.exit(0));

        // Add all components with spacing
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(subtitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(inputCard);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(outputCard);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        mainPanel.add(closeButton);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // ======================= PROGRAM ENTRY POINT =======================
    public static void main(String[] args) {
        // Use system look and feel for better integration
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> createGUI());
    }
}
