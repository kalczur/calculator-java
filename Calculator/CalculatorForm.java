package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class CalculatorForm {
    private JPanel panelMain;
    private JPanel panelKeypad;
    private JButton buttonMC;
    private JButton buttonMR;
    private JButton buttonMS;
    private JButton buttonMplus;
    private JButton buttonMminus;
    private JButton buttonBackspace;
    private JButton buttonCE;
    private JButton buttonC;
    private JButton buttonPlusMinus;
    private JButton buttonSqrt;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button1;
    private JButton button2;
    private JButton buttonComma;
    private JButton button0;
    private JButton buttonPlus;
    private JButton buttonEqual;
    private JButton buttonMinus;
    private JButton buttonMultiplication;
    private JButton buttonDivision;
    private JButton buttonPercent;
    private JButton button3;
    private JButton button1_x;
    private JLabel labelMain;
    private JLabel labelAdditional;

    private static DecimalFormat df = new DecimalFormat("0.00");
    private String operation = "";

    private String formatToString(float x) {
        String str = df.format(x).replace( ',', '.');

        System.out.println(str);
        if (str.charAt(str.length() - 1) == '0') {
            if (str.charAt(str.length() - 2) == '0')
                return str.substring(0, str.length() - 3);

            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    private void makeOperation() {
        Float y = Float.parseFloat(labelMain.getText());
        Float x;
        if (labelAdditional.getText().equals(""))
            return;
        x = Float.parseFloat(labelAdditional.getText());

        if (operation.equals("+"))
            labelMain.setText(formatToString(x+y));
        else if (operation.equals("-"))
            labelMain.setText(formatToString(x-y));
        else if (operation.equals("*"))
            labelMain.setText(formatToString(x*y));
        else if (operation.equals("/")) {
            if (y == 0f) {
                labelMain.setText("NaN");
                labelAdditional.setText("");
            }
            else labelMain.setText(formatToString(x/y));
        }
        else return;

        operation = "";
        labelAdditional.setText("");
    }

    public String deleteLastChar(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        if (str.length() == 0)
            return "0";
        return str;
    }

    public CalculatorForm() {

        // ---- Numbers and comma
        ActionListener numberListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (labelMain.getText().equals("0") && !e.getActionCommand().equals(".") || labelMain.getText().equals("NaN")) labelMain.setText("");
                if (labelMain.getText().equals(".") && e.getActionCommand().equals(".")) return;
                labelMain.setText(labelMain.getText() + e.getActionCommand());

                // nie wiem dlaczego nie dziala :/
                if (labelMain.getText().length() > 7)
                    panelMain.setSize(new Dimension(panelMain.getWidth() + 35, panelMain.getHeight()));
            }
        };
        button9.addActionListener(numberListener);
        button8.addActionListener(numberListener);
        button7.addActionListener(numberListener);
        button6.addActionListener(numberListener);
        button5.addActionListener(numberListener);
        button4.addActionListener(numberListener);
        button3.addActionListener(numberListener);
        button2.addActionListener(numberListener);
        button1.addActionListener(numberListener);
        button0.addActionListener(numberListener);
        buttonComma.addActionListener(numberListener);

        // ---- Operations +, -, *, /
        ActionListener operationListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!operation.equals("")) {
                    makeOperation();
                    return;
                }

                labelAdditional.setText(labelMain.getText());
                labelMain.setText("0");
                operation = e.getActionCommand();
            }
        };

        buttonMinus.addActionListener(operationListener);
        buttonPlus.addActionListener(operationListener);
        buttonDivision.addActionListener(operationListener);
        buttonMultiplication.addActionListener(operationListener);

        // ---- Equals button
        buttonEqual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeOperation();
            }
        });

        // ---- C button
        buttonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation="";
                labelMain.setText("0");
                labelAdditional.setText("");
            }
        });

        // ---- CE button
        buttonCE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelMain.setText("0");
            }
        });

        // ---- Backspace
        buttonBackspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelMain.setText(deleteLastChar(labelMain.getText()));
            }
        });
    }

    public static void main(String[] args) {

        // Dzialaja tylko
        // przyciski numeryczne, przecinek, +, -, *, /, =
        // CE, C, Backspace

        JFrame frame = new JFrame("CalculatorForm");
        frame.setContentPane(new CalculatorForm().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100, 100);
        frame.setTitle("prawie win calc");
        frame.setMinimumSize( new Dimension(370,590) );
        frame.setVisible(true);
        frame.setResizable(true);
        frame.pack();
    }
}
