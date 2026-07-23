package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.exception.CustomerArchivedException;
import com.algaworks.algashop.ordering.domain.exception.DomainException;
import com.algaworks.algashop.ordering.domain.utility.IdGenerator;
import com.algaworks.algashop.ordering.domain.valueobject.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    @Test
    void given_invalidEmail_whenTryCreateCustomer_shouldGenerateException() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()-> {
                    new Customer(
                            new CustomerId(),
                            new FullName("Jonh", "Doe"),
                            new BirthDate(LocalDate.of(1991, 7, 5)),
                            new Email("invalid"),
                            new Phone("478-256-2504"),
                            new Document("255-08-0578"),
                            false,
                            OffsetDateTime.now(),
                            Address.builder()
                                    .street("Bourbon Street")
                                    .number("1437")
                                    .neighborhood("Noth Ville")
                                    .city("York")
                                    .state("South California")
                                    .zipCode(new ZipCode("21350"))
                                    .complement("Apt 1103")
                                    .build()
                    );
                });
    }

    @Test
    void given_invalidEmail_whenTryUpdatedCustomerEmail_shouldGenerateException() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("Jonh", "Doe"),
                new BirthDate(LocalDate.of(1991, 7, 5)),
                new Email("john.doe@gmail.com"),
                new Phone("478-256-2504"),
                new Document("255-08-0578"),
                false,
                OffsetDateTime.now(),
                Address.builder()
                        .street("Bourbon Street")
                        .number("1437")
                        .neighborhood("Noth Ville")
                        .city("York")
                        .state("South California")
                        .zipCode(new ZipCode("21350"))
                        .complement("Apt 1103")
                        .build()
        );

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()-> {
                    customer.changeEmail(new Email("invalid"));
                });
    }

    @Test
    void given_unarchivedCustomer_whenArchive_shouldAnonymize() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("Jonh", "Doe"),
                new BirthDate(LocalDate.of(1991, 7, 5)),
                new Email("john.doe@gmail.com"),
                new Phone("478-256-2504"),
                new Document("255-08-0578"),
                false,
                OffsetDateTime.now(),
                Address.builder()
                        .street("Bourbon Street")
                        .number("1437")
                        .neighborhood("Noth Ville")
                        .city("York")
                        .state("South California")
                        .zipCode(new ZipCode("21350"))
                        .complement("Apt 1103")
                        .build()
        );

        customer.archive();

        Assertions.assertWith(customer,
                c -> assertThat(c.fullName().firstName()).isEqualTo("Anonymous"),
                c -> assertThat(c.email().value()).isNotEqualTo("john.doe@gmail.com"),
                c -> assertThat(c.phone().value()).isEqualTo("000-000-0000"),
                c -> assertThat(c.document().value()).isEqualTo("000-00-0000"),
                c -> assertThat(c.birthDate()).isNull(),
                c -> assertThat(c.isPromotionNotificationsAllowed()).isFalse(),
                c -> assertThat(c.address().number()).isEqualTo("Anonymized"),
                c -> assertThat(c.address().complement()).isNull()
        );

    }

    @Test
    void given_archivedCustomer_whenTryToUpdate_shouldGenerateException() {
        Customer customer = new Customer(
                new CustomerId(),
                "Anonymous",
                null,
                new Email("anonymous@anonymous.com"),
                new Phone("000-000-0000"),
                new Document("000-00-0000"),
                false,
                true,
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                new LoyaltyPoints(10)
        );

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::archive);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(()-> customer.changeEmail(new Email("email@gmail.com")));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(()-> customer.changePhone(new Phone("123-123-1111")));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::enablePromotionNotifications);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::disablePromotionNotifications);
    }

    @Test
    void given_brandNewCustomer_whenAddLoyaltyPoints_shouldSumPoints() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("Jonh", "Doe"),
                new BirthDate(LocalDate.of(1991, 7, 5)),
                new Email("john.doe@gmail.com"),
                new Phone("478-256-2504"),
                new Document("255-08-0578"),
                false,
                OffsetDateTime.now(),
                Address.builder()
                        .street("Bourbon Street")
                        .number("1437")
                        .neighborhood("Noth Ville")
                        .city("York")
                        .state("South California")
                        .zipCode(new ZipCode("21350"))
                        .complement("Apt 1103")
                        .build()
        );

        customer.addLoyaltyPoints(new LoyaltyPoints(10));
        customer.addLoyaltyPoints(new LoyaltyPoints(20));

        Assertions.assertThat(customer.loyaltyPoints().value()).isEqualTo(30);
    }

    @Test
    void given_brandNewCustomer_whenAddInvalidLoyaltyPoints_shouldGenerateException() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("Jonh", "Doe"),
                new BirthDate(LocalDate.of(1991, 7, 5)),
                new Email("john.doe@gmail.com"),
                new Phone("478-256-2504"),
                new Document("255-08-0578"),
                false,
                OffsetDateTime.now(),
                Address.builder()
                        .street("Bourbon Street")
                        .number("1437")
                        .neighborhood("Noth Ville")
                        .city("York")
                        .state("South California")
                        .zipCode(new ZipCode("21350"))
                        .complement("Apt 1103")
                        .build()
        );

        Assertions.assertThatExceptionOfType(DomainException.class)
                .isThrownBy(()-> customer.addLoyaltyPoints(LoyaltyPoints.ZERO));

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()-> customer.addLoyaltyPoints(new LoyaltyPoints(-10)));
    }

    @Test
    void given_nullBirthDate_whenTryCreateCustomer_shouldGenerateException() {
        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(()-> {
                    new Customer(
                            new CustomerId(),
                            new FullName("Jonh", "Doe"),
                            new BirthDate(null),
                            new Email("dev@gmail.com"),
                            new Phone("478-256-2504"),
                            new Document("255-08-0578"),
                            false,
                            OffsetDateTime.now(),
                            Address.builder()
                                    .street("Bourbon Street")
                                    .number("1437")
                                    .neighborhood("Noth Ville")
                                    .city("York")
                                    .state("South California")
                                    .zipCode(new ZipCode("21350"))
                                    .complement("Apt 1103")
                                    .build()
                    );
                });
    }

    @Test
    void given_a_future_BirthDate_whenTryCreateCustomer_shouldGenerateException() {
        Assertions.assertThatExceptionOfType(DomainException.class)
                .isThrownBy(()-> {
                    new Customer(
                            new CustomerId(),
                            new FullName("Jonh", "Doe"),
                            new BirthDate(LocalDate.now().plusYears(1)),
                            new Email("dev@gmail.com"),
                            new Phone("478-256-2504"),
                            new Document("255-08-0578"),
                            false,
                            OffsetDateTime.now(),
                            Address.builder()
                                    .street("Bourbon Street")
                                    .number("1437")
                                    .neighborhood("Noth Ville")
                                    .city("York")
                                    .state("South California")
                                    .zipCode(new ZipCode("21350"))
                                    .complement("Apt 1103")
                                    .build()
                    );
                });
    }
}