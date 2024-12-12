package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.entity.OrderEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ValidationForVerifyNoteTest {

    @Test
    void testValidateForVerifyDescription_withNull() {

        OrderEntity orderEntity = mock(OrderEntity.class);

        when(orderEntity.getNote()).thenReturn(null);

        ValidationForVerifyNote validator = new ValidationForVerifyNote();

        validator.validate(orderEntity);

        verify(orderEntity).setNote(null);
    }

    @Test
    void testValidateForVerifyDescription_withEmptyString() {

        OrderEntity orderEntity = mock(OrderEntity.class);

        when(orderEntity.getNote()).thenReturn("");

        ValidationForVerifyNote validator = new ValidationForVerifyNote();

        validator.validate(orderEntity);

        verify(orderEntity).setNote(null);
    }

    @Test
    void testValidateForVerifyDescription_withWhitespace() {

        OrderEntity orderEntity = mock(OrderEntity.class);

        when(orderEntity.getNote()).thenReturn("  ");

        ValidationForVerifyNote validator = new ValidationForVerifyNote();

        validator.validate(orderEntity);

        verify(orderEntity).setNote(null);
    }

    @Test
    void testValidateForVerifyDescription_withNote() {

        OrderEntity orderEntity = mock(OrderEntity.class);

        String textNote= "Заметка";
        when(orderEntity.getNote()).thenReturn(textNote);

        ValidationForVerifyNote validator = new ValidationForVerifyNote();

        validator.validate(orderEntity);

        verify(orderEntity, never()).setNote(any());
        assertEquals(textNote, orderEntity.getNote(), "Заметка не должна измениться.");
    }

}
