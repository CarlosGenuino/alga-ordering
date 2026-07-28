package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.exception.ErrorMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FullNameTest {

    @Test
    void deveCriarFullNameComValoresValidos() {
        FullName fullName = new FullName("Carlos", "Silva");

        assertNotNull(fullName);
        assertEquals("Carlos", fullName.firstName());
        assertEquals("Silva", fullName.lastName());
        assertEquals("Carlos Silva", fullName.toString());
    }

    @Test
    void deveRejeitarFirstNameNulo() {
        NullPointerException ex = assertThrows(NullPointerException.class, () -> new FullName(null, "Silva"));
        assertEquals(ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_NULL, ex.getMessage());
    }

    @Test
    void deveRejeitarLastNameNulo() {
        NullPointerException ex = assertThrows(NullPointerException.class, () -> new FullName("Carlos", null));
        assertEquals(ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_NULL, ex.getMessage());
    }

    @Test
    void deveRejeitarFirstNameEmBranco() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new FullName("   ", "Silva"));
        assertEquals(ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_BLANK, ex.getMessage());
    }

    @Test
    void deveRejeitarLastNameEmBranco() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new FullName("Carlos", ""));
        assertEquals(ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_BLANK, ex.getMessage());
    }

    @Test
    void deveRemoverEspacosExtrasDosNomes() {
        FullName fullName = new FullName("  Carlos  ", "  Silva  ");
        assertEquals("Carlos", fullName.firstName());
        assertEquals("Silva", fullName.lastName());
        assertEquals("Carlos Silva", fullName.toString());
    }
}
