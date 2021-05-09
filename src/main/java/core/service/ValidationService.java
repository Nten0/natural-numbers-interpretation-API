package core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidationService {
    private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);

    public ValidationService() {
    }

    /**
     * Check that each number in the input sequence is up to a three digit number
     *
     * @param input array with splitted phone number
     * @return boolean
     */
    public boolean validateNumberSize(String[] input) {
        for (String x : input) {
            if (x.length() > 3 || !x.matches("\\d+")) {
                return false;
            }
        }
        return true;
    }

    public String isValidGreekPhoneNumber(Set<String> phoneNumber) {
        List<String> result = new ArrayList<String>();;
        phoneNumber.forEach(values -> {
            if ((values.length() == 10 && (values.startsWith("2") || values.startsWith("69"))) ||
                    (values.length() == 14 && (values.startsWith("00302") || values.startsWith("003069")))) {
                result.add(values + " - phone number: VALID");
            } else {
                result.add(values + " - phone number: INVALID");
            }
        });
        return String.join("\n\n", result);
    }

}
