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
    private static final String EMPTY_STRING = "";

    public PhoneNumberInterpretationService() {
    }

    /**
     * Check recursively for possible ambiguities in number spelling
     *
     * @param input               array with splitted phone number
     * @param possibleAmbiguity   holds a possible ambiguity
     * @param possibleAmbiguities has all the possible ambiguities
     */
    public void possibleAmbiguitiesIdentifier(String[] input, String possibleAmbiguity, Set<String> possibleAmbiguities) {

        if (input.length > 1 && input[0].length() > 1) { // a sequence of more than one numbers
            if (input[0].length() == 3) { // starts with a 3-digit number
                if (input[0].endsWith(DOUBLE_ZERO) && (input[1].length() == 2)) {  // eg 300 14 or 800 30
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0] + input[1], possibleAmbiguities);
                    if (!input[1].endsWith(ZERO)) { // eg 300 14
                        possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0].charAt(0) + input[1], possibleAmbiguities); // eg 314
                    } else { // eg 800 30
                        input[1] = input[0].charAt(0) + input[1];
                        possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity, possibleAmbiguities); //eg 830 - possible to combine with 1-digit number
                    }
                } else if (input[0].endsWith(DOUBLE_ZERO) && input[1].length() == 1) { // eg 400 5
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0] + input[1], possibleAmbiguities); // eg 4005
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0].substring(0, 2) + input[1], possibleAmbiguities); // eg 405
                } else if (input[0].endsWith(ZERO) && input[1].length() == 1) {// eg 830 5
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0] + input[1], possibleAmbiguities); // eg 8305
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0].substring(0, 2) + input[1], possibleAmbiguities); // eg 835
/**/                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0].charAt(0) + DOUBLE_ZERO + input[0].charAt(1) + input[1], possibleAmbiguities); // eg 80035
/**/                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0].charAt(0) + DOUBLE_ZERO + input[0].charAt(1) + ZERO + input[1], possibleAmbiguities); // eg 800305
                } else if (input[0].substring(0, 2).endsWith(ZERO)) { // eg 702 following by any 3-digit number
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0], possibleAmbiguities); // eg 702
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0].replace(ZERO, EMPTY_STRING), possibleAmbiguities); // eg 72
                } else { // eg 835
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0], possibleAmbiguities); // eg 835
/**/                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0].substring(0, 2) + ZERO + input[0].charAt(2), possibleAmbiguities); // eg 8305
/**/                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0].charAt(0) + DOUBLE_ZERO + input[0].substring(1, 3), possibleAmbiguities); // eg 80035
/**/                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0].charAt(0) + DOUBLE_ZERO + input[0].charAt(1) + ZERO + input[0].charAt(2), possibleAmbiguities); // eg 800305
                }
            } else if (input[0].length() == 2) { // starts with a 2-digit number
                if ((input[0].endsWith(ZERO) && !input[0].startsWith(ONE)) && input[1].length() == 1) { // eg 20 3
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0] + input[1], possibleAmbiguities); // eg 203
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 2, input.length), possibleAmbiguity + input[0].charAt(0) + input[1], possibleAmbiguities); // eg 23
                } else if (!input[0].startsWith(ONE) && !input[0].endsWith(ZERO)) {
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0], possibleAmbiguities); // eg 93
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0].charAt(0) + ZERO + input[0].charAt(1), possibleAmbiguities); // eg 90 3
                } else {
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0], possibleAmbiguities); // eg 13
                }
            }
        } else if (input.length == 1 && input[0].length() > 1) { // the last number with 2 or 3 digits
            if (input[0].length() == 3) { // eg 835
                possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0], possibleAmbiguities);
/**/                possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0].substring(0, 2) + ZERO + input[0].charAt(2), possibleAmbiguities); // eg 8305
/**/                possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0].charAt(0) + DOUBLE_ZERO + input[0].substring(1, 3), possibleAmbiguities); // eg 80035
/**/                possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0].charAt(0) + DOUBLE_ZERO + input[0].charAt(1) + ZERO + input[0].charAt(2), possibleAmbiguities); // eg 800305
            } else if (input[0].length() == 2) { // eg 93 or 13
                if (!input[0].startsWith(ONE) && !input[0].endsWith(ZERO)) {
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0], possibleAmbiguities); // eg 93
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0].charAt(0) + ZERO + input[0].charAt(1), possibleAmbiguities); // eg 90 3
                } else {
                    possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0], possibleAmbiguities); // eg 13
                }
            }
        } else if (input.length > 0) { // 1 number eg 7 or 26 or 638
            possibleAmbiguitiesIdentifier(ArrayUtils.subarray(input, 1, input.length), possibleAmbiguity + input[0], possibleAmbiguities);
        } else { // input.length == 0
            possibleAmbiguities.add(possibleAmbiguity);
        }
    }
}