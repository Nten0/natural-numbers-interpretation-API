package core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Set;

public class PhoneNumberInterpretationService {
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberInterpretationService.class);
    private static final String DOUBLE_ZERO = "00";
    private static final String ZERO = "0";
    private static final String ONE = "1";

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
                if (input[0].endsWith(DOUBLE_ZERO) && (input[1].length() == 2)) {  // eg 300 14 or 800 30
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0] + input[1], possibleAmbiguities);
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0].charAt(0) + input[1], possibleAmbiguities);
                } else if (input[0].endsWith(ZERO) && input[1].length() == 1) { // eg 830 5
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0] + input[1], possibleAmbiguities);
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0].substring(0, 2) + input[1], possibleAmbiguities);
                } else { // eg 835
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0], possibleAmbiguities);
                }
            } else if (input[0].length() == 2) { // starts with a 2-digit number
                if ((input[0].endsWith(ZERO) && !input[0].startsWith(ONE)) && input[1].length() == 1) { // eg 20 3
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0] + input[1], possibleAmbiguities);
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0].charAt(0) + input[1], possibleAmbiguities);
                } else { // eg 23
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0], possibleAmbiguities);
                }
            }
        } else if (input.length > 0) { // 1-digit number eg 7
            possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0], possibleAmbiguities);
        } else { // input.length == 0
            possibleAmbiguities.add(possibleAmbiguity);
            logger.info("A possible phone number is: " + possibleAmbiguity);
        }
    }
}