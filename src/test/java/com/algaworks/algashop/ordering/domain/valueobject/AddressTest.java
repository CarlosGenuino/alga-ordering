package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void deveCriarAddressComTodosOsCamposValidos() {
        ZipCode zipCode = new ZipCode("12345-678");
        Address address = new Address(
                "Rua das Flores",
                "123",
                "Apto 45",
                "Centro",
                "Rio de Janeiro",
                "RJ",
                zipCode
        );

        assertNotNull(address);
        assertEquals("Rua das Flores", address.street());
        assertEquals("123", address.number());
        assertEquals("Apto 45", address.complement());
        assertEquals("Centro", address.neighborhood());
        assertEquals("Rio de Janeiro", address.city());
        assertEquals("RJ", address.state());
        assertEquals(zipCode, address.zipCode());
    }

    @Test
    void deveRejeitarStreetEmBranco() {
        ZipCode zipCode = new ZipCode("12345-678");
        assertThrows(IllegalArgumentException.class, () -> new Address(
                "   ",
                "123",
                "Apto 45",
                "Centro",
                "Rio de Janeiro",
                "RJ",
                zipCode
        ));
    }

    @Test
    void deveRejeitarNumberEmBranco() {
        ZipCode zipCode = new ZipCode("12345-678");
        assertThrows(IllegalArgumentException.class, () -> new Address(
                "Rua das Flores",
                "",
                "Apto 45",
                "Centro",
                "Rio de Janeiro",
                "RJ",
                zipCode
        ));
    }

    @Test
    void deveRejeitarNeighborhoodEmBranco() {
        ZipCode zipCode = new ZipCode("12345-678");
        assertThrows(IllegalArgumentException.class, () -> new Address(
                "Rua das Flores",
                "123",
                "Apto 45",
                "   ",
                "Rio de Janeiro",
                "RJ",
                zipCode
        ));
    }

    @Test
    void deveRejeitarCityEmBranco() {
        ZipCode zipCode = new ZipCode("12345-678");
        assertThrows(IllegalArgumentException.class, () -> new Address(
                "Rua das Flores",
                "123",
                "Apto 45",
                "Centro",
                "",
                "RJ",
                zipCode
        ));
    }

    @Test
    void deveRejeitarStateEmBranco() {
        ZipCode zipCode = new ZipCode("12345-678");
        assertThrows(IllegalArgumentException.class, () -> new Address(
                "Rua das Flores",
                "123",
                "Apto 45",
                "Centro",
                "Rio de Janeiro",
                "   ",
                zipCode
        ));
    }
}
