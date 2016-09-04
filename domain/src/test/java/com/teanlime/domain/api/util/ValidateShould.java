package com.teanlime.domain.api.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValidateShould {

    private static final String VALIDATE_TEST = "validate_test_string";
    private static final String VALIDATE_TEST_NULL = null;


    @Test(expected = NullPointerException.class)
    public void throw_NPE_for_null_object() {
        // when
        Validate.nonNull(VALIDATE_TEST_NULL);
    }

    @Test
    public void return_non_null_object() {
        // when
        final String returned = Validate.nonNull(VALIDATE_TEST);

        // then
        assertThat(returned, is(VALIDATE_TEST));
    }
}