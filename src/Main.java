import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class Main {

    public static class CalculatorGUI extends JFrame implements ActionListener {
        private final JTextField textField;
//        private double num1, num2, result;
         private final Deque<Double> numbers = new ArrayDeque<>();
        private String operator;
        private final List<String> operators = Arrays.asList("/","*","-","+");
        private final List<String> operands = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");


        public CalculatorGUI() {
            setTitle("Calculator");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());
            // tiny text no more
            Font textFieldFont = new Font("Arial",Font.BOLD,95);
            textField = new JTextField(10);
            textField.setEditable(false);
            textField.setFont(textFieldFont);
            Color green = new Color(0,143,17);
            Color black = new Color(0,0,0);
            Color reddish = new Color(185, 22, 22);
            textField.setBackground(black);
            textField.setForeground(green);
            add(textField,BorderLayout.NORTH);

//original calc was too small for me
//            textField = new JTextField(10);
//            textField.setEditable(false);
//            add(textField, BorderLayout.NORTH);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(4, 4));
            // small buttons begone
            Font buttonFonts = new Font("Arial", Font.BOLD,80);

            String[] buttonLabels = {
                    "7", "8", "9", "/",
                    "4", "5", "6", "*",
                    "1", "2", "3", "-",
                    "C", "0", "=", "+"
            };

            for (String label : buttonLabels) {
                JButton button = new JButton(label);
                // COLOR YAY
                button.setBackground(reddish);
                button.addActionListener(this);
                // change button size in the constructor loop
                button.setFont(buttonFonts);
                buttonPanel.add(button);
            }

            add(buttonPanel, BorderLayout.CENTER);
            pack();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            String currentText = textField.getText();
            String equals = "=";
            String clearAction = "C";
            if (operands.contains(command)) {
                textField.setText(currentText + command);
            } else if (operators.contains(command)) {
                numbers.addLast(Double.parseDouble(textField.getText()));
                textField.setText("");
                operator = command;
            } else if (command.equals(equals)) {
                if (!textField.getText().isEmpty() && numbers.size() == 1 && operator != null) {
                    numbers.addLast(Double.parseDouble(textField.getText()));
                    textField.setText((evaluate(numbers.pop(), numbers.pop(), operator)));
                }
            } else if (command.equals(clearAction)) {
                textField.setText("");
                numbers.clear();
            }
        }



            // add conditional check methods here
            // For example, you can check the command and perform corresponding actions.
            // You need to implement this part based on your requirements.
        }
        public static String evaluate(double num1, double num2, String operator){
            switch (operator){
                    case "/":
                        if (num2 != 0){
                            return String.valueOf(num1 / num2);
                        }
                        else {
                            return "ERROR";
                        }
                    case "*":
                        return String.valueOf(num1 * num2);
                    case "-":
                       return String.valueOf(num1 - num2);
                    case "+":
                        return String.valueOf(num1 + num2);
                    default:
                        return "Wow you broke it";

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculator = new CalculatorGUI();
            calculator.setVisible(true);
        });
    }
    }