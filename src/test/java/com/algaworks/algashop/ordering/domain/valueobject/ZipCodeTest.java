package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.exception.ErrorMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZipCodeTest {

    @Test
    void deveCriarZipCodeValido() {
        ZipCode zipCode = new ZipCode("12345-678");

        assertNotNull(zipCode);
        assertEquals("12345-678", zipCode.value());
        assertEquals("12345-678", zipCode.toString());
    }

    @Test
    void deveRejeitarValorNulo() {
        NullPointerException ex = assertThrows(NullPointerException.class, () -> new ZipCode(null));
        assertEquals(ErrorMessages.VALIDATION_ERROR_ZIP_CODE_INVALID, ex.getMessage());
    }

    @Test
    void deveRejeitarValorEmBranco() {
        assertThrows(IllegalArgumentException.class, () -> new ZipCode(""));
        assertThrows(IllegalArgumentException.class, () -> new ZipCode("   "));
    }

    @Test
    void deveRejeitarValorComMenosDe5Caracteres() {
        assertThrows(IllegalArgumentException.class, () -> new ZipCode("1234"));
    }

    @Test
    void toStringDeveRetornarValorDiretamente() {
        ZipCode zipCode = new ZipCode("98765-432");
        assertEquals("98765-432", zipCode.toString());
    }
}
