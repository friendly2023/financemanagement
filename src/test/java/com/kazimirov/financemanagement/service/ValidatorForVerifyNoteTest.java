package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.entity.OrderEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ValidatorForVerifyNoteTest {

    @Test
    void testValidateForVerifyDescription_withNull() {

        OrderEntity orderEntity = mock(OrderEntity.class);

        when(orderEntity.getNote()).thenReturn(null);

        ValidatorForVerifyNote validator = new ValidatorForVerifyNote();

        validator.validate(orderEntity);

        verify(orderEntity).setNote(null);
    }

    @Test
    void testValidateForVerifyDescription_withEmptyString() {

        OrderEntity orderEntity = mock(OrderEntity.class);

        when(orderEntity.getNote()).thenReturn("");

        ValidatorForVerifyNote validator = new ValidatorForVerifyNote();

        validator.validate(orderEntity);

        verify(orderEntity).setNote(null);
    }

    @Test
    void testValidateForVerifyDescription_withWhitespace() {

        OrderEntity orderEntity = mock(OrderEntity.class);

        when(orderEntity.getNote()).thenReturn("  ");

        ValidatorForVerifyNote validator = new ValidatorForVerifyNote();

        validator.validate(orderEntity);

        verify(orderEntity).setNote(null);
    }

    @Test
    void testValidateForVerifyDescription_withNote() {

        OrderEntity orderEntity = mock(OrderEntity.class);

        String textNote= "Заметка";
        when(orderEntity.getNote()).thenReturn(textNote);

        ValidatorForVerifyNote validator = new ValidatorForVerifyNote();

        validator.validate(orderEntity);

        verify(orderEntity, never()).setNote(any());
        assertEquals(textNote, orderEntity.getNote(), "Заметка не должна измениться.");
    }

}
