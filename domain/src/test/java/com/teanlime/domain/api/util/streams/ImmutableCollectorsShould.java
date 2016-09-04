package com.teanlime.domain.api.util.streams;

import com.annimon.stream.Stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ImmutableCollectorsShould {

    private final List<String> originalList = Arrays.asList("One", "Two");

    @Test(expected = UnsupportedOperationException.class)
    public void create_immutable_list() {
        // given
        final Stream<String> listStream = givenListStream();

        // when
        listStream.collect(ImmutableCollectors.toList()).add("Three");
    }

    private Stream<String> givenListStream() {
        return Stream.of(originalList);
    }

    @Test
    public void create_a_correct_list() {
        // given
        final Stream<String> listStream = givenListStream();

        // when
        final List<String> list = listStream.collect(ImmutableCollectors.toList());

        // then
        assertThat(list.size(), is(2));
        assertThat(list.get(0), is(equalTo(originalList.get(0))));
        assertThat(list.get(1), is(equalTo(originalList.get(1))));
    }
}