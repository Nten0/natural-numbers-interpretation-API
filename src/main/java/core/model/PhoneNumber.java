package core.model;

import java.util.Set;

public class PhoneNumber {

    String inputNumber;
    Set<String> possiblePhoneNumbers;

    public PhoneNumber() {
    }

    public String getInputNumber() {
        return inputNumber;
    }

    public void setInputNumber(String inputNumber) {
        this.inputNumber = inputNumber;
    }

    public Set<String> getPossiblePhoneNumbers() {
        return possiblePhoneNumbers;
    }

    public void setPossiblePhoneNumbers(Set<String> possiblePhoneNumbers) {
        this.possiblePhoneNumbers = possiblePhoneNumbers;
    }
}
