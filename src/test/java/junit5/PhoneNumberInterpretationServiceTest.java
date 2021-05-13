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
    private static final String VALID_PHONE_NUMBER_1 = "2 10 6 9 30 6 6 4"; // 1 * 1 * 1 * 1 * 2 * 1 * 1 = 2
    private static final String VALID_PHONE_NUMBER_2 = "2 10 69 30 6 6 4"; // 1 * 1 * 2 * 2 * 1 * 1 = 4
    private static final String VALID_PHONE_NUMBER_3 = "69 700 24 1 3 50 2"; // 2 * 2 * 2 * 1 * 1 * 2 = 16

    private static final String COMBINATIONS_COUNTER_1 = "69 700 24"; // 2 * 2 * 2 = 8
    private static final String COMBINATIONS_COUNTER_2 = "69 710 24"; // 2 * 2 * 2 = 8
    private static final String COMBINATIONS_COUNTER_3 = "695 760 24 3"; // 4 * 2 * 2 * 1 = 16
    private static final String COMBINATIONS_COUNTER_4 = "6 90 10 706 24 3"; // 1 * 1 * 1 * 2 * 2 * 1 = 4
    private static final String COMBINATIONS_COUNTER_5 = "69 766 17 243"; // 2 * 4 * 1 * 4 = 32
    private static final String COMBINATIONS_COUNTER_6 = "695 766 50 3"; // 4 * 4 * 2 = 32
    private static final String COMBINATIONS_COUNTER_7 = "69 100 50 3"; // 2 * 4 = 8
    private static final String COMBINATIONS_COUNTER_8 = "000 1 00"; // 1 * 1 * 1 = 1
    private static final String COMBINATIONS_COUNTER_9 = "00 506"; // 1 * 2 = 2
    private static final String COMBINATIONS_COUNTER_10 = "00 560 0"; // 1 * 2 * 1 = 2
    private static final String COMBINATIONS_COUNTER_11 = "69 766 17 213"; // 2 * 4 * 1 * 2 = 16

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
        assertEquals(16, possibleAmbiguities.size());

        possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(COMBINATIONS_COUNTER_1.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(8, possibleAmbiguities.size());

        possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(COMBINATIONS_COUNTER_2.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(8, possibleAmbiguities.size());

        possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(COMBINATIONS_COUNTER_3.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(16, possibleAmbiguities.size());

        possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(COMBINATIONS_COUNTER_4.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(4, possibleAmbiguities.size());

        possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(COMBINATIONS_COUNTER_5.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(32, possibleAmbiguities.size());

        possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(COMBINATIONS_COUNTER_6.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(32, possibleAmbiguities.size());

        possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(COMBINATIONS_COUNTER_7.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(8, possibleAmbiguities.size());

        possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(COMBINATIONS_COUNTER_8.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(1, possibleAmbiguities.size());

        possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(COMBINATIONS_COUNTER_9.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(2, possibleAmbiguities.size());

        possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(COMBINATIONS_COUNTER_10.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(2, possibleAmbiguities.size());

        possibleAmbiguities = new HashSet<String>();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(COMBINATIONS_COUNTER_11.trim().split(whitespaceRegex), EMPTY_STRING, possibleAmbiguities);
        System.out.println(possibleAmbiguities);
        assertEquals(16, possibleAmbiguities.size());
    }
}
