import java.util.EmptyStackException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class RPN extends JFrame {

    private ForthStack stack;
    private final JPanel contentPane;
    private final JTextArea dataDisplayTxtArea;
    private final JButton pushBtn;
    private final JButton popBtn;
    private final JTextArea errorTxtArea;
    private final JTextArea dataEntryTxtArea;
    private final JButton plusBtn;
    private final JButton minusBtn;
    private final JButton multiplyBtn;
    private final JButton divideBtn;
    private final JButton dupBtn;
    private final JButton dup2Btn;
    private final JButton clrBtn;

    public RPN() {
        stack = new ForthStack(4);

        setTitle("RPN Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 486, 319);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        dataEntryTxtArea = new JTextArea();
        dataEntryTxtArea.setBounds(10, 22, 450, 52);
        contentPane.add(dataEntryTxtArea);

        dataDisplayTxtArea = new JTextArea();
        dataDisplayTxtArea.setBounds(10, 106, 450, 38);
        contentPane.add(dataDisplayTxtArea);

        errorTxtArea = new JTextArea();
        errorTxtArea.setBounds(10, 165, 450, 46);
        contentPane.add(errorTxtArea);

        plusBtn = new JButton("+");
        plusBtn.setBounds(10, 222, 63, 23);
        contentPane.add(plusBtn);

        minusBtn = new JButton("-");
        minusBtn.setBounds(77, 222, 63, 23);
        contentPane.add(minusBtn);

        multiplyBtn = new JButton("*");
        multiplyBtn.setBounds(144, 222, 63, 23);
        contentPane.add(multiplyBtn);

        divideBtn = new JButton("/");
        divideBtn.setBounds(211, 222, 63, 23);
        contentPane.add(divideBtn);

        dupBtn = new JButton("dup");
        dupBtn.setBounds(10, 256, 63, 23);
        contentPane.add(dupBtn);

        dup2Btn = new JButton("2dup");
        dup2Btn.setBounds(77, 256, 63, 23);
        contentPane.add(dup2Btn);

        clrBtn = new JButton("clr");
        clrBtn.setBounds(391, 256, 63, 23);
        contentPane.add(clrBtn);

        popBtn = new JButton("pop");
        popBtn.setBounds(144, 256, 63, 23);
        contentPane.add(popBtn);

        pushBtn = new JButton("push");

        pushBtn.setBounds(211, 256, 63, 23);
        contentPane.add(pushBtn);
        dataDisplayTxtArea.setText("");

        JLabel lblNumericDisplay = new JLabel("Numeric display:");
        lblNumericDisplay.setBounds(10, 81, 161, 14);
        contentPane.add(lblNumericDisplay);

        JLabel lblNumericDataEntry = new JLabel("Numeric data entry:");
        lblNumericDataEntry.setBounds(10, 0, 142, 14);
        contentPane.add(lblNumericDataEntry);

        JLabel lblErrorDisplay = new JLabel("Error display:");
        lblErrorDisplay.setBounds(10, 144, 132, 14);
        contentPane.add(lblErrorDisplay);
        addPushBtnListener();
        addPopBtnListener();
        addPlusBtnListener();
        addMinusBtnListener();
        addMultiplyBtnListener();
        addDivideBtnListener();
        addDup2BtnListener();
        addDupBtnListener();
        addClrBtnListener();

        setVisible(true);
    }

    public void addPushBtnListener() {
        pushBtn.addActionListener(e -> {
            try {
                stack.push(getEntryData());
                dataEntryTxtArea.setText("");
                displayData();
            } catch (Exception e1) {
                displayError(e1.toString());
            }
        });
    }

    public void addPopBtnListener() {
        popBtn.addActionListener(e -> {
            try {
                stack.pop();
                displayData();
            } catch (EmptyStackException e1) {
                displayError("The stack is empty");
            } catch (Exception e1) {
                displayError(e1.toString());
            }
        });
    }

    public void addPlusBtnListener() {
        plusBtn.addActionListener(e -> {
            try {
                stack.add();
            } catch (EmptyStackException e1) {
                displayError("The stack is empty");
            } catch (Exception e1) {
                displayError(e1.toString());
            }
            displayData();
        });
    }

    public void addMinusBtnListener() {
        minusBtn.addActionListener(e -> {
            try {
                stack.sub();
            } catch (EmptyStackException e1) {
                displayError("The stack is empty");
            } catch (Exception e1) {
                displayError(e1.toString());
            }
            displayData();
        });
    }

    public void addMultiplyBtnListener() {
        multiplyBtn.addActionListener(e -> {
            try {
                stack.multi();
            } catch (EmptyStackException e1) {
                displayError("The stack is empty");
            } catch (Exception e1) {
                displayError(e1.toString());
            }

            displayData();
        });
    }

    public void addDivideBtnListener() {
        divideBtn.addActionListener(e -> {
            try {
                stack.div();
                displayData();
            } catch (EmptyStackException e1) {
                displayError("The stack is empty");
            } catch (Exception e1) {
                displayError(e1.toString());
            }
        });
    }

    public void addDupBtnListener() {
        dupBtn.addActionListener(e -> {
            try {
                stack.duplicate();
                displayData();
            } catch (EmptyStackException e1) {
                displayError("The stack is empty");
            } catch (Exception e1) {
                displayError(e1.toString());
            }
        });
    }

    public void addDup2BtnListener() {
        dup2Btn.addActionListener(e -> {
            try {
                stack.doubleDuplicate();
                displayData();
            } catch (EmptyStackException e1) {
                displayError("The stack is empty");
            } catch (Exception e1) {
                displayError(e1.toString());
            }
        });
    }

    public void addClrBtnListener() {
        clrBtn.addActionListener(e -> {
            try {
                stack.clear();
                displayError("");
                displayData();
            } catch (EmptyStackException e1) {
                displayError("The stack is empty");
            } catch (Exception e1) {
                displayError(e1.toString());
            }
        });
    }

    protected void displayError(String message) {
        errorTxtArea.setText(message);
        System.out.println(message);
    }

    protected double getEntryData() throws NumberFormatException {
        return Double.parseDouble(dataEntryTxtArea.getText().trim());
    }

    public void displayData() {
        double top;
        try {
            top = stack.peek();
            dataDisplayTxtArea.setText("" + top);
            System.out.println("" + top);
            displayError("");
        } catch (EmptyStackException e) {
            dataDisplayTxtArea.setText("");
            System.out.println();
        }
    }

    public static void main(String... args) {
        new RPN();
    }
}
