package com.teanlime.data.api.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ListMapperShould {

    private ListMapper<Integer, String> listMapper;

    @Mock
    private Mapper<Integer, String> listItemMapper;

    @Before
    public void setup() {
        listMapper = new ListMapper<>(listItemMapper);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_list_item_mapper() {
        new ListMapper<>(null);
    }
}