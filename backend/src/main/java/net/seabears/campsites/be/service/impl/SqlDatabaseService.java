package net.seabears.campsites.be.service.impl;

import net.seabears.campsites.be.service.DatabaseService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
public class SqlDatabaseService implements DatabaseService {
    @PersistenceContext
    private EntityManager db;

    @Transactional
    @Override
    public void update(final boolean alterDb) {
        if (alterDb) {
            throw new UnsupportedOperationException("not implemented: database update");
        } else {
            createDatabase();
        }
    }

    private void createDatabase() {
        final InputStream stream = SqlDatabaseService.class.getResourceAsStream("/database.sql");
        final String sql = toString(stream);
        db.createNativeQuery(sql).executeUpdate();
    }

    private static String toString(final InputStream stream) {
        // \A means "beginning of the input boundary," apparently
        final Scanner s = new Scanner(stream, StandardCharsets.UTF_8.name()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
