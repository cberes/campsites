package net.seabears.campsites.api.db;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class MockDatabaseConfig {
    private final List<MockDataLoader> loaders;

    @PersistenceContext
    private EntityManager db;

    public MockDatabaseConfig(final List<MockDataLoader> loaders) {
        this.loaders = loaders;
    }

    @Transactional
    public void load() {
        loaders.forEach(loader -> loader.load(db::persist));
    }
}
