package core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class ValidationService {
    private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);

    /**
     * Check that each number in the input sequence is up to a three digit number
     *
     * @param input array with splitted phone number
     * @return boolean
     */
    public boolean validateNumberSize(String[] input) {
        for (String x : input) {
            if(x.length() > 3 || !x.matches("\\d+")){
                return false;
            }
        }
        return true;
    }

    public String isValidGreekPhoneNumber(Set<String> phoneNumber) {

       return "OK";
    }

}
