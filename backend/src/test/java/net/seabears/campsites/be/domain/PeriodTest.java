package net.seabears.campsites.be.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class PeriodTest {
    @Test
    public void test() {
        final Period period = new Period();
        assertNull(period.getStart());
    }
}
