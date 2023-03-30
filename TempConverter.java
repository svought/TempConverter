import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class TempConverter {
    // Variable Declaration
    private JLabel errorLabel;
    private JComboBox<String> firstComboBox;
    private JComboBox<String>  secondComboBox;
    private JTextField firstTextField;
    private JTextField secondTextField;


    private void prepareGUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        String[] firstChoices = {"Fahrenheit", "Celsius"};
        String[] secondChoices = {"Celsius", "Fahrenheit"};
        DefaultComboBoxModel<String> firstChoiceModel = new DefaultComboBoxModel<>(firstChoices);
        DefaultComboBoxModel<String> secondChoiceModel = new DefaultComboBoxModel<>(secondChoices);

        // Create and set up window for converter
        JFrame frame = new JFrame();
        frame.setSize(screenSize.width/3, screenSize.height/3);
        frame.setLocation(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Temperature Converter");
        frame.setResizable(false);

        // Create Labels
        JLabel headerLabel = new JLabel("Temperature Converter", SwingConstants.CENTER);
        JLabel instructionsLabel = new JLabel("Select the temperatures types you want to convert. Input temp value you want converted and press enter.");
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.black);
        headerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 24));
        headerLabel.setForeground(Color.gray);
        headerLabel.setBounds(SwingConstants.CENTER,SwingConstants.CENTER,900,75);
        instructionsLabel.setBounds(150,90,1000,30);

        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setBounds(125,250,1000,30);
        errorLabel.setForeground(Color.RED);

        // Create Check Boxes
        firstComboBox = new JComboBox<>();
        secondComboBox = new JComboBox<>();

        firstComboBox.setModel(firstChoiceModel);
        secondComboBox.setModel(secondChoiceModel);

        firstComboBox.setBounds(215,150,95,30);
        secondComboBox.setBounds(515,150,95,30);

        // Create Text Fields
        firstTextField = new JTextField();
        secondTextField = new JTextField();

        // Set bounds for text fields
        firstTextField.setBounds(325,150,40,30);
        secondTextField.setBounds(625,150,40,30);

        // Add elements to frame
        frame.add(headerLabel);
        frame.add(instructionsLabel);
        frame.add(firstComboBox);
        frame.add(secondComboBox);
        frame.add(firstTextField);
        frame.add(secondTextField);
        frame.add(errorLabel);
        frame.setLayout(null);
        frame.setVisible(true);

        // Create event listeners for text fields
        firstTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(firstTextField);
            }
        });

        secondTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(secondTextField);
            }
        });
    }

    private void textFieldActionPerformed(JTextField textField) {
        String errorMessage = "Need to enter a number for conversion to work.";
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        float answer;
        errorLabel.setText("");

        // Set dynamic variables
        JTextField otherTextField = textField==firstTextField ? secondTextField:firstTextField;
        JComboBox<String> comboBox = textField==firstTextField ? firstComboBox:secondComboBox;

        if(comboBox.getSelectedItem() == "Fahrenheit" && comboBoxesAreDifferent()) {
            try {
                answer =  (Float.parseFloat(textField.getText()) - 32) * (float) 5 /9;
                otherTextField.setText(String.valueOf(df.format(answer)));
            } catch (Exception e){
                errorLabel.setText(errorMessage);
                otherTextField.setText("");
            }
        } else if(comboBox.getSelectedItem() == "Celsius" && comboBoxesAreDifferent()) {
            try {
                answer =  (Float.parseFloat(textField.getText()) * (float) 9 /5) + 32;
                otherTextField.setText(String.valueOf(df.format(answer)));
            } catch (Exception e){
                errorLabel.setText(errorMessage);
                otherTextField.setText("");
            }
        } else {
            try {
                answer =  Float.parseFloat(textField.getText());
                otherTextField.setText(String.valueOf(df.format(answer)));
            } catch (Exception e){
                errorLabel.setText(errorMessage);
                otherTextField.setText("");
            }
        }
    }

    private boolean comboBoxesAreDifferent() {
        return firstComboBox.getSelectedItem() != secondComboBox.getSelectedItem();
    }

    public static void main(String[] args) {
        TempConverter tempConverter = new TempConverter();
        tempConverter.prepareGUI();
    }
}
