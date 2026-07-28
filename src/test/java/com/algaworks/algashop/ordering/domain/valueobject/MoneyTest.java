package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void deveCriarMoneyComValorValido() {
        Money money = new Money("10.50");
        assertEquals("10.50", money.toString());
    }

    @Test
    void deveRejeitarValorNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Money((BigDecimal) null));
        assertThrows(NullPointerException.class, () -> new Money((String) null));
    }

    @Test
    void deveRejeitarValorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Money("-5.00"));
    }

    @Test
    void deveAjustarEscalaPara2ComHalfEven() {
        Money money = new Money("10.555");
        assertEquals("10.56", money.toString());
    }

    @Test
    void deveExporConstanteZero() {
        assertEquals("0.00", Money.ZERO.toString());
    }

    @Test
    void deveSomarDoisValoresMonetarios() {
        Money m1 = new Money("10.00");
        Money m2 = new Money("5.50");
        Money resultado = m1.add(m2);
        assertEquals("15.50", resultado.toString());
    }

    @Test
    void deveMultiplicarPorQuantityValida() {
        Money money = new Money("10.00");
        Quantity quantity = new Quantity(3);
        Money resultado = money.multiply(quantity);
        assertEquals("30.00", resultado.toString());
    }

    @Test
    void deveLancarExcecaoAoMultiplicarPorQuantityMenorQue1() {
        Money money = new Money("10.00");
        Quantity quantity = new Quantity(0);
        assertThrows(IllegalArgumentException.class, () -> money.multiply(quantity));
    }

    @Test
    void deveDividirValoresMonetarios() {
        Money m1 = new Money("10.00");
        Money m2 = new Money("2.00");
        Money resultado = m1.divide(m2);
        assertEquals("5.00", resultado.toString());
    }

    @Test
    void deveLancarExcecaoAoDividirPorZero() {
        Money m1 = new Money("10.00");
        Money m2 = Money.ZERO;
        assertThrows(ArithmeticException.class, () -> m1.divide(m2));
    }

    @Test
    void deveCompararValoresMonetarios() {
        Money m1 = new Money("10.00");
        Money m2 = new Money("15.00");
        assertTrue(m1.compareTo(m2) < 0);
        assertTrue(m2.compareTo(m1) > 0);
        assertEquals(0, m1.compareTo(new Money("10.00")));
    }
}
