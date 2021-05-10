package core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

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
        Pattern pattern = Pattern.compile("\\d+");
        if (input != null) {
            return Arrays.stream(input).noneMatch(x -> x.length() > 3 || !pattern.matcher(x).matches());
        }
        return false;
    }

    /**
     * Check that each possible number is a valid Greek telephone number
     *
     * @param phoneNumber a Set with all the possible numbers
     * @return String
     */
    public String isValidGreekPhoneNumber(Set<String> phoneNumber) {
        List<String> result = new ArrayList<String>();
        int counter = 0;
        for (String values : phoneNumber){
            counter++;
            if ((values.length() == 10 && (values.startsWith("2") || values.startsWith("69"))) ||
                    (values.length() == 14 && (values.startsWith("00302") || values.startsWith("003069")))) {
                result.add("Interpretation " + counter + ": " + values + " [phone number: VALID]");
            } else {
                result.add("Interpretation " + counter + ": " + values + " [phone number: INVALID]");
            }
        }
        return String.join("\n\n", result);
    }

}
