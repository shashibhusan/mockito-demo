package com.patel.mockito.Lenient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LenientExample {
    @Mock
    List<Object> mockList;

    /*
    Lenient :- If you will use Lenient then Mockito will not follow strict stubbing
           means if there is any unused stubbing is there then it will be ignored.
           otherwise if you will not use Lenient then it will throw error.
           Note:-**
           if we need all the stubs of a mock instance to be lenient then marking it with lenient is redundant,
           For these types of scenarios Mockito Provides MockSettings interface.

           Example:-
                        @Mock(lenient = true)
                        or
                        AppleService appleService = mock(AppleService.class, withSettings().lenient());
     */

    @Test // It will fail without Lenient
    public void givenUnusedStub_whenInvokingGetThenThrowUnnecessaryStubbingException() {
        when(mockList.add("one")).thenReturn(true); // this won't get called
        when(mockList.get(anyInt())).thenReturn("hello");
        assertEquals("List should contain hello", "hello", mockList.get(1));
        
    }

    @Test // It will pass due to lenient
    public void givenLenientdStub_whenInvokingGetThenThrowUnnecessaryStubbingException() {
        lenient().when(mockList.add("one")).thenReturn(true);
        when(mockList.get(anyInt())).thenReturn("hello");
        assertEquals("List should contain hello", "hello", mockList.get(1));
    }
}
