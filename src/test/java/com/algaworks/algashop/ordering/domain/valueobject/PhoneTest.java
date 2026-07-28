package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.exception.ErrorMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {

    @Test
    void deveCriarPhoneComValorValido() {
        Phone phone = new Phone("+55 21 99999-8888");

        assertNotNull(phone);
        assertEquals("+55 21 99999-8888", phone.value());
        assertEquals("+55 21 99999-8888", phone.toString());
    }

    @Test
    void deveRejeitarValorNulo() {
        NullPointerException ex = assertThrows(NullPointerException.class, () -> new Phone(null));
        assertEquals(ErrorMessages.VALIDATION_ERROR_PHONE_IS_NULL, ex.getMessage());
    }

    @Test
    void deveRejeitarValorEmBranco() {
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> new Phone(""));
        assertEquals(ErrorMessages.VALIDATION_ERROR_PHONE_IS_BLANK, ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> new Phone("   "));
        assertEquals(ErrorMessages.VALIDATION_ERROR_PHONE_IS_BLANK, ex2.getMessage());
    }

    @Test
    void toStringDeveRetornarValorDiretamente() {
        Phone phone = new Phone("123456789");
        assertEquals("123456789", phone.toString());
    }
}
