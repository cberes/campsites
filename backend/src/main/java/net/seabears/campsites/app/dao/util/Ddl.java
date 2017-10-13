package net.seabears.campsites.app.dao.util;

import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.io.DatabaseIO;
import org.apache.ddlutils.model.Database;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public final class Ddl {
    private Ddl() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static void update(final DataSource dataSource, final boolean alterDb) {
        final Database model = getDatabase();
        final Platform platform = PlatformFactory.createNewPlatformInstance(dataSource);

        if (alterDb) {
            platform.alterTables(model, false);
        } else {
            platform.createTables(model, true, false);
        }
    }

    static Database getDatabase() {
        final InputStream stream = Ddl.class.getResourceAsStream("/database.xml");
        final Reader reader = new InputStreamReader(stream);
        return new DatabaseIO().read(reader);
    }
}
