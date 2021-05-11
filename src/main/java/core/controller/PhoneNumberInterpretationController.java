package core.controller;

import core.model.PhoneNumber;
import core.service.PhoneNumberInterpretationService;
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
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberInterpretationController.class);
    private static final String WHITESPACE_REGEX = "\\s+";

    private String EMPTY_STRING = "";

    public PhoneNumberInterpretationController() {
    }

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String input) {
        PhoneNumber phoneNumber = new PhoneNumber();
        PhoneNumberInterpretationService phoneNumberInterpretationService = new PhoneNumberInterpretationService();
        ValidationService validationService = new ValidationService();

        String result = null;
        String[] inputNumberArray = input.trim().split(WHITESPACE_REGEX);

        if (validationService.validateNumberSize(inputNumberArray)) {
            logger.info("Each number in the input sequence is up to a three digit number!");

            logger.info("Input telephone number is: " + String.join(EMPTY_STRING, inputNumberArray));
            phoneNumber.setInputNumber(String.join(EMPTY_STRING, inputNumberArray));

            logger.info("Calculating all the possible ambiguities for the telephone number: " + String.join(EMPTY_STRING, inputNumberArray));
            Set<String> possibleAmbiguities = new HashSet();
            phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(inputNumberArray, EMPTY_STRING, possibleAmbiguities);
            phoneNumber.setPossiblePhoneNumbers(possibleAmbiguities);

            result = validationService.isValidGreekPhoneNumber(phoneNumber.getPossiblePhoneNumbers());
        } else {
            logger.warn("Input Is Not Invalid");
        }

        return Response
                .status(Response.Status.OK)
                .entity(result)
                .build();
    }
}