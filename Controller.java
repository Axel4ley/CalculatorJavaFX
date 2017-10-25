package CalculatorJavaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField display;

    boolean lastButtonWasDigit;
    private String displayNumber = "0";
    private String operator = "";
    private double number1 = 0;
    private Model model = new Model();

    public double getNumber() {
        return Double.parseDouble(getDisplayNumber());
    }

    public void setNumber (double number) {
        setDisplayNumber(String.valueOf(number));
    }

    public String getDisplayNumber() {
        return display.getText();
    }

    public void setDisplayNumber(String displayNumber) {
        display.setText(displayNumber);
    }

    public void buttonClearClick(ActionEvent actionEvent) {
        setDisplayNumber("0");
        lastButtonWasDigit = false;
    }

    public void buttonNegateClick(ActionEvent actionEvent) {
        double newNumber = getNumber() * -1;
        setNumber(newNumber);
    }
    public void buttonSqrtClick (ActionEvent actionEvent) {
        double newNumber = Math.sqrt(getNumber());
        setNumber(newNumber);
    }

    public void buttonDigitClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        String digit = button.getText();
        if (lastButtonWasDigit) {
            setDisplayNumber(getDisplayNumber() + digit);
        } else {
            setDisplayNumber(digit);
        }
        lastButtonWasDigit = true;
    }

    public void buttonBackSpace(ActionEvent actionEvent) {
        String str = getDisplayNumber();
        setDisplayNumber(str.substring(0, str.length()-1));
    }

    public void buttonCommaClick(ActionEvent actionEvent) {
        if (!getDisplayNumber().contains(".") && !getDisplayNumber().contains(".")) {
            setDisplayNumber(getDisplayNumber() + ".");
        }
        lastButtonWasDigit = true;
    }

    @FXML
    public void processOperators(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();

        if(!value.equals("=")) {
            if(!operator.isEmpty())
                return;

            operator = value;
            number1 = Double.parseDouble(getDisplayNumber());
            if (value.equals("+")) {setDisplayNumber("+");}
            else if (value.equals("-")) {setDisplayNumber("-");}
            else if (value.equals("*")) {setDisplayNumber("*");}
            else if (value.equals("/")) {setDisplayNumber("/");}
            lastButtonWasDigit = false;
        } else {
            if(operator.isEmpty())
                return;

            double number2 = Double.parseDouble(getDisplayNumber());
            double output = model.calculate(number1, number2, operator);
            setDisplayNumber(String.valueOf(output));
            operator = "";
            lastButtonWasDigit = false;
        }
    }
}
