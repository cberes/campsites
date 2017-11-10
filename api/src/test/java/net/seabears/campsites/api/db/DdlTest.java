package net.seabears.campsites.api.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DdlTest {
    @Test
    public void smokeTest() {
        assertNotNull(Ddl.getDatabase());
    }
}
