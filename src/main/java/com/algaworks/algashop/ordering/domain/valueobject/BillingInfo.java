package com.algaworks.algashop.ordering.domain.valueobject;

import lombok.Builder;
import java.util.Objects;

@Builder
public record BillingInfo(
        FullName fullName,
        Document document,
        Phone phone,
        Address address
) {

    public BillingInfo {
        Objects.requireNonNull(fullName, "FullName é obrigatório.");
        Objects.requireNonNull(document, "Document é obrigatório.");
        Objects.requireNonNull(phone, "Phone é obrigatório.");
        Objects.requireNonNull(address, "Address é obrigatório.");
    }
}
