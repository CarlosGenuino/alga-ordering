package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.exception.ErrorMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void deveCriarEmailValido() {
        Email email = new Email("usuario@dominio.com");
        assertNotNull(email);
        assertEquals("usuario@dominio.com", email.value());
        assertEquals("usuario@dominio.com", email.toString());
    }

    @Test
    void deveRejeitarEmailNulo() {
        NullPointerException ex = assertThrows(NullPointerException.class, () -> new Email(null));
        assertEquals(ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID, ex.getMessage());
    }

    @Test
    void deveRejeitarEmailEmBranco() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Email("   "));
        assertEquals(ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID, ex.getMessage());
    }

    @Test
    void deveRejeitarEmailInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Email("usuario-invalido"));
        assertEquals(ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID, ex.getMessage());
    }

    @Test
    void toStringDeveRetornarValorDiretamente() {
        Email email = new Email("teste@algaworks.com");
        assertEquals("teste@algaworks.com", email.toString());
    }
}
