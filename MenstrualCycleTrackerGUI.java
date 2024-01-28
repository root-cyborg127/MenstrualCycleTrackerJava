import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenstrualCycleTrackerGUI extends JFrame {
    private JTextField cycleLengthField;
    private JTextField menstrualLengthField;
    private JTextField dayField;
    private JTextField monthField;
    private JTextField yearField;

    public MenstrualCycleTrackerGUI() {
        setTitle("Menstrual Cycle Tracker Java ~ v5.0");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Creating components
        cycleLengthField = new JTextField();
        menstrualLengthField = new JTextField();
        dayField = new JTextField();
        monthField = new JTextField();
        yearField = new JTextField();

        JButton calculateButton = new JButton("Calculate");
        JTextArea resultArea = new JTextArea();

        // Adding components to panels
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Cycle Length:"));
        inputPanel.add(cycleLengthField);
        inputPanel.add(new JLabel("Menstrual Length:"));
        inputPanel.add(menstrualLengthField);
        inputPanel.add(new JLabel("Day of Last Period:"));
        inputPanel.add(dayField);
        inputPanel.add(new JLabel("Month of Last Period:"));
        inputPanel.add(monthField);
        inputPanel.add(new JLabel("Year of Last Period:"));
        inputPanel.add(yearField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(calculateButton);

        // Adding action listener for the Calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateCycle(resultArea);
            }
        });

        // Adding components to the main frame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setVisible(true);
    }

      private void calculateCycle(JTextArea resultArea) {
        try {
            // Convert input values to integers
            int cycleLength = Integer.parseInt(cycleLengthField.getText());
            int menstrualLength = Integer.parseInt(menstrualLengthField.getText());
            int day = Integer.parseInt(dayField.getText());
            int month = Integer.parseInt(monthField.getText());
            int year = Integer.parseInt(yearField.getText());

            // Perform the calculations (similar to the Python code)
            day = day + cycleLength;
            if (day > 31) {
                month += 1;
                day = day - 31;
        
                // Fix for month incrementation
                if (month > 12) {
                    month = 1;
                    year += 1;
                }
            }

            // Calculate menstrual cycle end
            int mEnds = day + menstrualLength;
            if (mEnds > 31) {
                month += 1;
                mEnds = mEnds - 31;
            }

            // Calculate ovulation start
            int A = day + 10;
            int oPeriod = A;
            if (A > 31) {
                A = A - 31;
                month += 1;
            }

            // Calculate ovulation end
            int oEnd = A + 6;
            if (A > 31) {
                month += 1;
                A = A - 31;
            }

            // Build the formatted result string
            String resultText = "Your next period starts from: " + day + " - " + month + " - " + year + "\n";
            resultText += "Your periods end on: " + mEnds + " - " + month + " - " + year + "\n";
            resultText += "Your ovulation starts on: " + A + " - " + month + " - " + year + "\n";
            resultText += "Your ovulation ends on: " + oEnd + " - " + month + " - " + year + "\n";

            
            resultText += "\n\n\n\nAsk your doctor about any concerns or questions you may have about your menstrual experience for best results.\nFor more information, visit WomensHealth.gov or GirlsHealth.gov for more facts about menstruation.\n";
            resultText +="\n\t\t\tDeveloped by vishwajithshaijukumar";
            resultText +="\n\t\t\t\troot-cyborg127";
            // Display the result in the JTextArea
            resultArea.setText(resultText);

            // Display the result in a formatted pop-up window
            JFrame resultFrame = new JFrame("Results ~ Menstrual Cycle Tracker v5.0");
            resultFrame.setSize(800, 250);
            resultFrame.setLocationRelativeTo(null);

            JTextArea resultTextArea = new JTextArea(resultText);
            resultTextArea.setEditable(false);

            resultFrame.add(new JScrollPane(resultTextArea));
            resultFrame.setVisible(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenstrualCycleTrackerGUI();
            }
        });
    }
}
