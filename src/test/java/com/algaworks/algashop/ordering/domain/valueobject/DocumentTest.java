package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.exception.ErrorMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    @Test
    void deveCriarDocumentComValorValido() {
        Document document = new Document("12345678900");

        assertNotNull(document);
        assertEquals("12345678900", document.value());
        assertEquals("Document{value='12345678900'}", document.toString());
    }

    @Test
    void deveRejeitarValorNulo() {
        NullPointerException ex = assertThrows(NullPointerException.class, () -> new Document(null));
        assertEquals(ErrorMessages.VALIDATION_ERROR_DOCUMENT_IS_NULL, ex.getMessage());
    }

    @Test
    void toStringDeveRetornarValorFormatado() {
        Document document = new Document("ABC123");
        assertEquals("Document{value='ABC123'}", document.toString());
    }
}
