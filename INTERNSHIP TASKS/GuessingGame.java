import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessingGame extends JFrame {
    private int lowerBound;
    private int upperBound;
    private int randomNumber;
    private int attempts;
    private int score;
    
    private JTextField guessField;
    private JButton submitButton;
    private JLabel resultLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;

    public GuessingGame(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.attempts = 0;
        this.score = 0;

        setTitle("Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        guessField = new JTextField();
        submitButton = new JButton("Submit");
        resultLabel = new JLabel("");
        attemptsLabel = new JLabel("");
        scoreLabel = new JLabel("");

        add(new JLabel("Enter your guess:"));
        add(guessField);
        add(submitButton);
        add(resultLabel);
        add(attemptsLabel);
        add(scoreLabel);

        generateRandomNumber();

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;
            if (guess == randomNumber) {
                resultLabel.setText("Congratulations! You've guessed the number.");
                score++;
                generateRandomNumber();
                attempts = 0;
            } else if (guess < randomNumber) {
                resultLabel.setText("Too low. Try again.");
            } else {
                resultLabel.setText("Too high. Try again.");
            }
            guessField.setText("");
            attemptsLabel.setText("Attempts: " + attempts);
            scoreLabel.setText("Score: " + score);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame(1, 100);
        game.setVisible(true);
    }
}
