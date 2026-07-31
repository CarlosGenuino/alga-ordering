package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.valueobject.id.CustomerId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerIdTest {

    @Test
    void deveCriarCustomerIdComUUIDValido() {
        UUID uuid = UUID.randomUUID();
        CustomerId customerId = new CustomerId(uuid);

        assertNotNull(customerId);
        assertEquals(uuid, customerId.value());
        assertEquals(uuid.toString(), customerId.toString());
    }

    @Test
    void deveRejeitarUUIDNulo() {
        assertThrows(NullPointerException.class, () -> new CustomerId((UUID) null));
    }

    @Test
    void deveCriarCustomerIdComConstrutorPadrao() {
        CustomerId customerId = new CustomerId();

        assertNotNull(customerId.value());
        assertEquals(customerId.value().toString(), customerId.toString());
    }

    @Test
    void doisCustomerIdsCriadosComConstrutorPadraoDevemSerDiferentes() {
        CustomerId id1 = new CustomerId();
        CustomerId id2 = new CustomerId();

        assertNotEquals(id1.value(), id2.value());
    }

    @Test
    void deveUtilizarIdGeneratorNoConstrutorPadrao() {
        CustomerId customerId = new CustomerId();
        UUID generated = customerId.value();

        assertNotNull(generated);
        // Apenas valida que o UUID gerado é um UUID válido
        assertDoesNotThrow(() -> UUID.fromString(generated.toString()));
    }
}
