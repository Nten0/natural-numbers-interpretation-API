package junit5;

import core.service.PhoneNumberInterpretationService;
import core.service.ValidationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberInterpretationApplicationTest {

    private ValidationService validationService;
    private PhoneNumberInterpretationService phoneNumberInterpretationService;

    // Different test cases
    private static final String VALID_PHONE_NUMBER_1 = "2 10 6 9 30 6 6 4";
    private static final String VALID_PHONE_NUMBER_2 = "2 10 69 30 6 6 4";
    private static final String VALID_PHONE_NUMBER_3 = "0 0 30 69 700 24 1 3 50 2";

    private static final String INVALID_PHONE_NUMBER_1 = "0030 69 700 24 1 3 50 2";
    private static final String INVALID_PHONE_NUMBER_2 = "1 B I7Z rU k 3f";
    private static final String INVALID_PHONE_NUMBER_3 = " ";
    private static final String INVALID_PHONE_NUMBER_4 = "";

    @BeforeEach
    public void setUp() {
        validationService = new ValidationService();
        phoneNumberInterpretationService = new PhoneNumberInterpretationService();
    }

    @Test
    @DisplayName("Test validateNumberSize")
    public void testValidateNumberSize() {

        assertTrue(validationService.validateNumberSize(VALID_PHONE_NUMBER_1.trim().split("\\s+")));
        assertTrue(validationService.validateNumberSize(VALID_PHONE_NUMBER_2.trim().split("\\s+")));
        assertTrue(validationService.validateNumberSize(VALID_PHONE_NUMBER_3.trim().split("\\s+")));

        assertFalse(validationService.validateNumberSize(INVALID_PHONE_NUMBER_1.trim().split("\\s+")));
        assertFalse(validationService.validateNumberSize(INVALID_PHONE_NUMBER_2.trim().split("\\s+")));
        assertFalse(validationService.validateNumberSize(INVALID_PHONE_NUMBER_3.trim().split("\\s+")));
        assertFalse(validationService.validateNumberSize(INVALID_PHONE_NUMBER_4.trim().split("\\s+")));
    }

    @Test
    @DisplayName("Test isValidGreekPhoneNumber")
    public void testValidGreekPhoneNumber() throws URISyntaxException {

        Set<String> test = new HashSet();
        test.add("302558");
        test.add("2106930664");
        test.add("21069306604");
        test.add("00306974092252");

        List<String> result = new ArrayList<String>();
        File file = new File(getClass().getClassLoader().getResource("expected-output.txt").toURI());

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ;
                result.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(String.join("\n", result), validationService.isValidGreekPhoneNumber(test));
    }

    @Test
    @DisplayName("Test possibleAmbiguitiesIdentifier")
    public void testPossibleAmbiguitiesIdentifier() {
        String[] strArray =  "2 3".split(" ");

        Set<String> possibleAmbiguities = new HashSet();
        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(strArray, "", possibleAmbiguities);
        System.out.println(possibleAmbiguities);

//        possibleAmbiguities = new HashSet();
//        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(VALID_PHONE_NUMBER_1.trim().split("\\s+"), "", possibleAmbiguities)
//        assertEquals(2, possibleAmbiguities.size());
//        possibleAmbiguities = new HashSet();
//        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(VALID_PHONE_NUMBER_2.trim().split("\\s+"), "", possibleAmbiguities)
//        assertEquals(4, possibleAmbiguities.size());
//        possibleAmbiguities = new HashSet();
//        phoneNumberInterpretationService.possibleAmbiguitiesIdentifier(VALID_PHONE_NUMBER_3.trim().split("\\s+"), "", possibleAmbiguities)
//        assertEquals(8, possibleAmbiguities.size());
    }
}
