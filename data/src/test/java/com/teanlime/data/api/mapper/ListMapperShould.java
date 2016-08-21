package com.teanlime.data.api.mapper;

import com.annimon.stream.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListMapperShould {

    private final List<Integer> integersWithNulls = Arrays.asList(null, 2, 4, null, 3);
    private final List<Integer> integers = Arrays.asList(1, 2, 4, 5, 3);
    private final List<Integer> nulls = Arrays.asList(null, null, null);


    private ListMapper<Integer, String> listMapper;

    @Mock
    private Mapper<Integer, String> listItemMapper;

    @Before
    public void setup() {
        givenListItemMapperThatMapsCorrectly();
        givenListMapper();
    }

    private void givenListItemMapperThatMapsCorrectly() {
        when(listItemMapper.map(anyInt())).then(returnCorrectlyMappedString());
    }

    private void givenListMapper() {
        listMapper = new ListMapper<>(listItemMapper);
    }

    private Answer<Optional<String>> returnCorrectlyMappedString() {
        return invocation -> {
            final Integer toBeMapped = (Integer) invocation.getArguments()[0];
            return toBeMapped == null ? Optional.empty() : Optional.of(toBeMapped.toString());
        };
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_list_item_mapper() {
        // when
        new ListMapper<>(null);
    }

    @Test
    public void return_empty_optional_if_mapped_list_is_null() {
        // when
        final Optional<List<String>> mappingResult = listMapper.map(null);

        // then
        assertThat(mappingResult, equalTo(Optional.empty()));
    }

    @Test
    public void return_empty_optional_if_mapped_list_is_empty() {
        // when
        final Optional<List<String>> mappingResult = listMapper.map(new ArrayList<>());

        // then
        assertThat(mappingResult, equalTo(Optional.empty()));
    }

    @Test
    public void map_each_list_item_using_listItemMapper() {
        // when
        listMapper.map(integers);

        // then
        verify(listItemMapper, times(5)).map(anyInt());
    }

    @Test
    public void return_correctly_mapped_list_when_mapped_data_is_correct() {
        // when
        final Optional<List<String>> mappingResult = listMapper.map(integers);

        // then
        assertThat(mappingResult.isPresent(), is(true));
        assertThat(mappingResult.get().size(), is(5));
        assertThat(mappingResult.get().get(3), equalTo("5"));
        assertThat(mappingResult.get().get(4), equalTo("3"));
        assertThat(mappingResult.get(), hasItems("1", "2", "4", "5", "3"));
    }

    @Test
    public void return_correctly_mapped_list_when_mapped_data_contains_null_items() {
        // when
        final Optional<List<String>> mappingResult = listMapper.map(integersWithNulls);

        // then
        assertThat(mappingResult.isPresent(), is(true));
        assertThat(mappingResult.get().size(), is(3));
        assertThat(mappingResult.get().get(2), equalTo("3"));
        assertThat(mappingResult.get(), hasItems("2", "4", "3"));
    }

    @Test
    public void return_empty_optional_when_mapped_data_contains_only_null() {
        // when
        final Optional<List<String>> mappingResult = listMapper.map(nulls);

        // then
        assertThat(mappingResult.isPresent(), is(false));
        assertThat(mappingResult, equalTo(Optional.empty()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void return_immutableList() {
        // when
        final Optional<List<String>> mappingResult = listMapper.map(integersWithNulls);
        mappingResult.get().add("should not be allowed to immutable list");
    }
}