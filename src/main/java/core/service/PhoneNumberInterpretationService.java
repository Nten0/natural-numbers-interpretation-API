package core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class PhoneNumberInterpretationService {
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberInterpretationService.class);

    public PhoneNumberInterpretationService() {
    }

    /**
     * Check for possible ambiguities in number spelling
     *
     * @param input array with splitted phone number
     * @return a Set with all the possible numbers
     */
    public Set<String> possibleAmbiguitiesIdentifier(String[] input) {

        if (input.length > 1) { // a sequence of more than one numbers
            if (input[0].length() == 3) { // starts with a 3-digit number
                if (input[0].endsWith("00")) {
                    if (input[1].length() == 2) {
                        if (input[1].startsWith("1") || !input[1].endsWith("0")) { // eg 300 14 or 800 32
                            //keep it as is
                            //combine the two numbers eg 300 14 -> 314
                        } else {
                            if (input[2].length() == 1) { // eg 400 50 2
                                //keep it as is
                                //find the combinations | 450 2 | 400 52 | 452
                            }
                        }
                    } else { // eg 200 5
                        //keep it as is
                        //combine the two numbers eg 200 5 -> 205
                    }
                } else if (input[0].endsWith("0")) {
                    if (input[1].length() == 1) { // eg 450 2
                        //keep it as is
                        //combine the two numbers eg 540 2 -> 452
                    }
                } else {
                    // keep the number
                }
            } else if (input[0].length() == 2) {
                // implement the above logic
            } else {
                // keep the number
            }
        } else {

        }

        return null;
    }
}