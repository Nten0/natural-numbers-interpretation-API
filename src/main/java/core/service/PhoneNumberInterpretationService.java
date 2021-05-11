package core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Set;

public class PhoneNumberInterpretationService {
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberInterpretationService.class);

    public PhoneNumberInterpretationService() {
    }

    /**
     * Check recursively for possible ambiguities in number spelling
     *
     * @param input               array with splitted phone number
     * @param possibleAmbiguity   holds a possible ambiguity
     * @param possibleAmbiguities has all the possible ambiguities
     * @return a Set with all the possible numbers
     */
    public void possibleAmbiguitiesIdentifier(String[] input, String possibleAmbiguity, Set<String> possibleAmbiguities) {

        if (input.length > 1 && input[0].length() > 1) { // a sequence of more than one numbers
            if (input[0].length() == 3) { // starts with a 3-digit number
                if (input[0].endsWith("00") && (input[1].length() == 2)) { // eg 300 14 or 800 30

                } else if (input[0].endsWith("0") && input[1].length() == 1) { // eg 830 5

                } else { // eg 835

                }
            } else if (input[0].length() == 2) { // starts with a 2-digit number
                if ((input[0].endsWith("0") && !input[0].startsWith("1")) && input[1].length() == 1) { // eg 20 3

                } else { // eg 23

                }
            } else { // starts with a 1-digit number eg 7

            }
        } else if (input.length > 0) {
            possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0], possibleAmbiguities);
        } else {
            possibleAmbiguities.add(possibleAmbiguity);
        }
    }
}