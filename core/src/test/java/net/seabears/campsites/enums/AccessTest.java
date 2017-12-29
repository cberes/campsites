package net.seabears.campsites.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccessTest {
    @Test
    public void test() {
        assertEquals(0, Access.UNKNOWN.ordinal());
    }
}
