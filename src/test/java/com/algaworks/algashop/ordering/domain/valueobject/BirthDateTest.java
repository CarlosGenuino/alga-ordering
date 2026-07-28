package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST;
import static org.junit.jupiter.api.Assertions.*;

class BirthDateTest {

    @Test
    void deveCriarBirthDateComDataValidaNoPassado() {
        LocalDate data = LocalDate.of(1990, 5, 20);
        BirthDate birthDate = new BirthDate(data);

        assertNotNull(birthDate);
        assertEquals(data, birthDate.value());
    }

    @Test
    void deveRejeitarDataNula() {
        assertThrows(NullPointerException.class, () -> new BirthDate(null));
    }

    @Test
    void deveRejeitarDataNoFuturo() {
        LocalDate futura = LocalDate.now().plusDays(1);
        DomainException ex = assertThrows(DomainException.class, () -> new BirthDate(futura));
        assertEquals(VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST, ex.getMessage());
    }

    @Test
    void deveCalcularIdadeCorretamente() {
        LocalDate data = LocalDate.now().minusYears(30);
        BirthDate birthDate = new BirthDate(data);

        assertEquals(30, birthDate.age());
    }

    @Test
    void toStringDeveRetornarDataComoTexto() {
        LocalDate data = LocalDate.of(2000, 1, 1);
        BirthDate birthDate = new BirthDate(data);

        assertEquals("2000-01-01", birthDate.toString());
    }
}
