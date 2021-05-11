package junit5;

import core.controller.PhoneNumberInterpretationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberInterpretationControllerTest {

    private static final String whitespaceRegex = "\\s+";

    private String EMPTY_STRING = "";

    private PhoneNumberInterpretationController phoneNumberInterpretationController;

    // Different test cases
    private static final String VALID_PHONE_NUMBER_1 = "2 10 6 9 30 6 6 4";
    private static final String VALID_PHONE_NUMBER_2 = "2 10 69 30 6 6 4";
    private static final String VALID_PHONE_NUMBER_3 = "69 700 24 1 3 50 2";
    private static final String VALID_PHONE_NUMBER_3_WITHOUT_PREFIX = "69 700 24 1 3 50 2";

    @BeforeEach
    public void setUp() {
        phoneNumberInterpretationController = new PhoneNumberInterpretationController();
    }

    @Test
    @DisplayName("Test removeCodePrefix")
    public void testRemoveCodePrefix() {

        String withoutPrefix = String.join(EMPTY_STRING, VALID_PHONE_NUMBER_3_WITHOUT_PREFIX.trim().split(whitespaceRegex));
        String withPrefix = String.join(EMPTY_STRING, phoneNumberInterpretationController.removeCodePrefix(VALID_PHONE_NUMBER_3.trim().split(whitespaceRegex)));
        assertEquals(withoutPrefix, withPrefix);
    }

    @Test
    @DisplayName("Test stringArraysAreEquals")
    public void testStringArraysAreEquals() {

        String str1 = String.join(EMPTY_STRING, VALID_PHONE_NUMBER_1.trim().split(whitespaceRegex));
        String str2 = String.join(EMPTY_STRING, VALID_PHONE_NUMBER_2.trim().split(whitespaceRegex));
        String str3 = String.join(EMPTY_STRING, VALID_PHONE_NUMBER_3.trim().split(whitespaceRegex));

        assertEquals(str1, str1);
        assertNotEquals(str2, str3);
    }
}
