package junit5;

import core.service.PhoneNumberInterpretationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberInterpretationServiceTest {

    private static final String whitespaceRegex = "\\s+";

    private String EMPTY_STRING = "";

    private PhoneNumberInterpretationService phoneNumberInterpretationService;

    // Different test cases
    private static final String VALID_PHONE_NUMBER_1 = "2 10 6 9 30 6 6 4";
    private static final String VALID_PHONE_NUMBER_2 = "2 10 69 30 6 6 4";
    private static final String VALID_PHONE_NUMBER_3 = "69 700 24 1 3 50 2";

    @BeforeEach
    public void setUp() {
        phoneNumberInterpretationService = new PhoneNumberInterpretationService();
    }

    @Test
    @DisplayName("Test possibleAmbiguitiesIdentifier")
    public void testPossibleAmbiguitiesIdentifier() {

        Set<String> possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(VALID_PHONE_NUMBER_1.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(2, possibleAmbiguities.size());

        possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(VALID_PHONE_NUMBER_2.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(4, possibleAmbiguities.size());

        possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(VALID_PHONE_NUMBER_3.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(8, possibleAmbiguities.size());
    }
}
