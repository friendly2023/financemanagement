package com.kazimirov.financemanagement.service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ValidatorUrlTest {

    @Test
    void testValidatorURI_withNull() {

        assertNull(ValidatorUrl.validatorURI(null));
    }

    @Test
    void testValidatorURI_withEmptyString() {

        assertNull(ValidatorUrl.validatorURI(""));
    }

    @Test
    void testValidatorURI_withWhitespace() {

        assertNull(ValidatorUrl.validatorURI("   "));
    }

    @Test
    void testValidatorURI_withValidUrl() {

        String validUrl = "https://example.com";
        assertEquals(validUrl, ValidatorUrl.validatorURI(validUrl));
    }

    @Test
    void testValidatorURI_withInvalidUrl() {

        String invalidUrl = "invalid-url";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ValidatorUrl.validatorURI(invalidUrl);
        });
        assertEquals("Invalid URL: invalid-url", exception.getMessage());
    }
}
