package core.controller;

import core.model.PhoneNumber;
import core.service.PhoneNumberInterpretationService;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.service.ValidationService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

@Path("/number")
public class PhoneNumberInterpretationController {
    private static final String INTERNATIONAL_GREEK_CODE_PREFIX = "0030";
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberInterpretationController.class);
    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String SPACE = " ";


    private String EMPTY_STRING = "";

    public PhoneNumberInterpretationController() {
    }

    @GET
    @Path("/{param}")
    public Response printPossibleAmbiguities(@PathParam("param") String input) {
        PhoneNumber phoneNumber = new PhoneNumber();
        PhoneNumberInterpretationService phoneNumberInterpretationService = new PhoneNumberInterpretationService();
        ValidationService validationService = new ValidationService();

        String result = "Something went wrong!";
        String[] inputNumberArray = input.trim().split(WHITESPACE_REGEX);

        if (validationService.validateNumberSize(inputNumberArray)) {
            logger.info("Input is Valid!");

            logger.info("Input telephone number is: " + String.join(EMPTY_STRING, inputNumberArray));
            phoneNumber.setInputNumber(String.join(EMPTY_STRING, inputNumberArray));

            // remove code prefix (if exists) from the number, in order to avoid extra ambiguities
            String[] inputNumberArrayWithoutCodePrefix = removeCodePrefix(inputNumberArray);
            boolean hasCodePrefix = !stringArraysAreEquals(inputNumberArrayWithoutCodePrefix, inputNumberArray);

            logger.info("Calculating all the possible ambiguities for the telephone number: " + String.join(EMPTY_STRING, inputNumberArray));
            Set<String> possibleAmbiguities = new HashSet();
            phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(inputNumberArrayWithoutCodePrefix, EMPTY_STRING, possibleAmbiguities);
            if (hasCodePrefix) {
                logger.info("Adding the code prefix to the telephone number");
                Set<String> possibleAmbiguitiesWithPrefix = new HashSet();
                for (String possibleAmbiguity : possibleAmbiguities) {
                    possibleAmbiguitiesWithPrefix.add(INTERNATIONAL_GREEK_CODE_PREFIX.concat(possibleAmbiguity));
                }
                phoneNumber.setPossiblePhoneNumbers(possibleAmbiguitiesWithPrefix);
            } else {
                phoneNumber.setPossiblePhoneNumbers(possibleAmbiguities);
            }

            result = "Input telephone number is: " + String.join(EMPTY_STRING, inputNumberArray) + "\n\n";
            result += validationService.isValidGreekPhoneNumber(phoneNumber.getPossiblePhoneNumbers());
        } else {
            logger.warn("Input is Invalid!");
        }

        result = result.replaceAll("(\r\n|\n\n|\n)", "<br/><br/>");

        return Response
                .status(Response.Status.OK)
                .entity(result)
                .build();
    }

    public String[] removeCodePrefix(String[] strArray) {
        logger.info("Checking if the telephone number contains the code prefix");
        String current = EMPTY_STRING;
        int pos = 0;
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i] != SPACE) {
                current = current.concat(strArray[i]);
                if (current.startsWith(INTERNATIONAL_GREEK_CODE_PREFIX)) {
                    pos = i;
                    break;
                }
            }
        }
        if (pos != 0) {
            logger.info("The code prefix exists in the telephone number");
            return ArrayUtils.subarray(strArray, ++pos, strArray.length);
        } else {
            logger.info("The code prefix does not exist in the telephone number");
            return strArray;
        }
    }

    public static boolean stringArraysAreEquals(String[] array1, String[] array2) {
        if (array1.length == array2.length) {
            for (int i = 0; i < array1.length; i++) {
                if (array1[i].equals(array2[i])) {
                    // do nothing
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}