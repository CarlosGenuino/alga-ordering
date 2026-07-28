package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BillingInfoTest {

    @Test
    void deveCriarBillingInfoComTodosOsCamposValidos() {
        BillingInfo info = BillingInfo.builder()
                .fullName(new FullName("Carlos", "Silva"))
                .document(new Document("12345678900"))
                .phone(new Phone("+5521999998888"))
                .address(Address.builder()
                        .street("Bourbon Street")
                        .number("1134")
                        .neighborhood("North Ville")
                        .city("York")
                        .state("South California")
                        .zipCode(new ZipCode("12345"))
                        .complement("Apt. 114")
                        .build())
                .build();

        assertNotNull(info);
        assertEquals("Carlos Silva", info.fullName().toString());
    }

    @Test
    void deveRejeitarFullNameNulo() {
        assertThrows(NullPointerException.class, () -> BillingInfo.builder()
                .document(new Document("12345678900"))
                .phone(new Phone("+5521999998888"))
                .address(Address.builder()
                        .street("Bourbon Street")
                        .number("1134")
                        .neighborhood("North Ville")
                        .city("York")
                        .state("South California")
                        .zipCode(new ZipCode("12345"))
                        .complement("Apt. 114")
                        .build())
                .build());
    }

    @Test
    void deveRejeitarDocumentNulo() {
        assertThrows(NullPointerException.class, () -> BillingInfo.builder()
                .fullName(new FullName("Carlos", "Silva"))
                .phone(new Phone("+5521999998888"))
                .address(Address.builder()
                        .street("Bourbon Street")
                        .number("1134")
                        .neighborhood("North Ville")
                        .city("York")
                        .state("South California")
                        .zipCode(new ZipCode("12345"))
                        .complement("Apt. 114")
                        .build())
                .build());
    }

    @Test
    void deveRejeitarPhoneNulo() {
        assertThrows(NullPointerException.class, () -> BillingInfo.builder()
                .fullName(new FullName("Carlos", "Silva"))
                .document(new Document("12345678900"))
                .address(Address.builder()
                        .street("Bourbon Street")
                        .number("1134")
                        .neighborhood("North Ville")
                        .city("York")
                        .state("South California")
                        .zipCode(new ZipCode("12345"))
                        .complement("Apt. 114")
                        .build())
                .build());
    }

    @Test
    void deveRejeitarAddressNulo() {
        assertThrows(NullPointerException.class, () -> BillingInfo.builder()
                .fullName(new FullName("Carlos", "Silva"))
                .document(new Document("12345678900"))
                .phone(new Phone("+5521999998888"))
                .build());
    }
}
