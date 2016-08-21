package com.teanlime.data.api.mapper;

import com.annimon.stream.Optional;
import com.teanlime.domain.api.util.StringUtils;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StringUseCaseExceptionMapperShould {

    private static final String LOCALISED_MESSAGE = "localised_message_test";

    private StringUseCaseExceptionMapper stringUseCaseExceptionMapper;

    @Before
    public void setup() {
        givenStringUseCaseExceptionMapper();
    }

    private void givenStringUseCaseExceptionMapper() {
        stringUseCaseExceptionMapper = new StringUseCaseExceptionMapper();
    }

    @Test
    public void return_empty_optional_if_throwable_is_null() {
        // when
        final Optional<String> message = stringUseCaseExceptionMapper.map(null);

        // then
        assertThat(message, equalTo(Optional.empty()));
    }

    @Test
    public void return_empty_optional_if_throwable_localised_message_is_empty() {
        // given
        final Throwable throwable = givenThrowableWithLocalisedMessage(StringUtils.EMPTY);

        // when
        final Optional<String> message = stringUseCaseExceptionMapper.map(throwable);

        // then
        assertThat(message, equalTo(Optional.empty()));
    }

    private Throwable givenThrowableWithLocalisedMessage(String localisedMessage) {
        final Throwable throwable = mock(Throwable.class);
        when(throwable.getLocalizedMessage()).thenReturn(localisedMessage);
        return throwable;
    }

    @Test
    public void return_localised_message_when_mapping_throwable() {
        // given
        final Throwable throwable = givenThrowableWithLocalisedMessage(LOCALISED_MESSAGE);

        // when
        final Optional<String> message = stringUseCaseExceptionMapper.map(throwable);

        // then
        assertThat(message.isPresent(), is(true));
        assertThat(message.get(), equalTo(LOCALISED_MESSAGE));
    }
}