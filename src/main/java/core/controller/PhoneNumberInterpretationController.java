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

@Path("/number")
public class PhoneNumberInterpretationController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberInterpretationController.class);

    public PhoneNumberInterpretationController() {
    }

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String input) {
        PhoneNumber phoneNumber = new PhoneNumber();
        PhoneNumberInterpretationService phoneNumberInterpretationService = new PhoneNumberInterpretationService();
        ValidationService validationService = new ValidationService();

        String result = null;
        String[] inputNumberArray = input.trim().split("\\s+");

        if (validationService.validateNumberSize(inputNumberArray)) {
            logger.info("Each number in the input sequence is up to a three digit number!");

            phoneNumber.setInputNumber(String.join("", inputNumberArray));
            phoneNumber.setPossiblePhoneNumbers(phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(inputNumberArray));

            result = validationService.isValidGreekPhoneNumber(phoneNumber.getPossiblePhoneNumbers());

        } else {
            logger.warn("Input Is Not Invalid");
        }

        return Response
                .status(Response.Status.OK)
                .entity(phoneNumber.getInputNumber())
                .build();
    }
}