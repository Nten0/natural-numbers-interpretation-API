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
    private static final String ONLY_DIGITS_REGEX = "\\d+";
    private static final String GREEK_PHONE_PREFIX = "2";
    private static final String GREEK_MOBILE_PHONE_PREFIX = "69";
    private static final String GREEK_PHONE_PREFIX_WITH_INTERNATIONAL_CODE = "00302";
    private static final String GREEK_MOBILE_PHONE_PREFIX_WITH_INTERNATIONAL_CODE = "003069";

    public ValidationService() {
    }

    /**
     * Check that each number in the input sequence is up to a three digit number
     *
     * @param input array with splitted phone number
     * @return boolean
     */
    public boolean validateNumberSize(String[] input) {
        Pattern pattern = Pattern.compile(ONLY_DIGITS_REGEX);
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
        for (String values : phoneNumber) {
            counter++;
            if ((values.length() == 14 &&
                    (values.startsWith(GREEK_PHONE_PREFIX_WITH_INTERNATIONAL_CODE) || values.startsWith(GREEK_MOBILE_PHONE_PREFIX_WITH_INTERNATIONAL_CODE))) ||
                (values.length() == 10 &&
                    (values.startsWith(GREEK_PHONE_PREFIX) || values.startsWith(GREEK_MOBILE_PHONE_PREFIX)))) {
                result.add("Interpretation " + counter + ": " + values + " [phone number: VALID]");
            } else {
                result.add("Interpretation " + counter + ": " + values + " [phone number: INVALID]");
            }
        }
        return String.join("\n\n", result);
    }
}
