package com.teanlime.domain.api.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringUtilsShould {

    private static final String BLANK_STRING = "     ";
    private static final String SPACE_STRING = " ";
    private static final String EMPTY_STRING = "";
    private static final String NULL_STRING = null;
    private static final String STRING = "S";

    @Test
    public void return_true_for_null_string_in_isBlank() {
        // when
        assertThat(StringUtils.isBlank(NULL_STRING), is(true));
    }


    @Test
    public void return_true_for_empty_string_in_isBlank() {
        // when
        assertThat(StringUtils.isBlank(EMPTY_STRING), is(true));
    }


    @Test
    public void return_true_for_space_string_in_isBlank() {
        // when
        assertThat(StringUtils.isBlank(SPACE_STRING), is(true));
    }


    @Test
    public void return_true_for_blank_string_in_isBlank() {
        // when
        assertThat(StringUtils.isBlank(BLANK_STRING), is(true));
    }

    @Test
    public void return_false_for_valid_string_in_isBlank() {
        // when
        assertThat(StringUtils.isBlank(STRING), is(false));
    }

    @Test
    public void return_false_for_null_string_in_isNotBlank() {
        // when
        assertThat(StringUtils.isNotBlank(NULL_STRING), is(false));
    }


    @Test
    public void return_false_for_empty_string_in_isNotBlank() {
        // when
        assertThat(StringUtils.isNotBlank(EMPTY_STRING), is(false));
    }


    @Test
    public void return_false_for_space_string_in_isNotBlank() {
        // when
        assertThat(StringUtils.isNotBlank(SPACE_STRING), is(false));
    }


    @Test
    public void return_false_for_blank_string_in_isNotBlank() {
        // when
        assertThat(StringUtils.isNotBlank(BLANK_STRING), is(false));
    }

    @Test
    public void return_true_for_valid_string_in_isNotBlank() {
        // when
        assertThat(StringUtils.isNotBlank(STRING), is(true));
    }
}