package net.seabears.campsites.api.serialization;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.seabears.campsites.db.domain.*;

public class DatabaseModule extends SimpleModule {
    private static final String NAME = DatabaseModule.class.getName();

    public DatabaseModule() {
        super(NAME, new Version(1, 0, 0, null, null, null));
        setMixInAnnotation(Area.class, AreaMixin.class);
        setMixInAnnotation(Campground.class, CampgroundMixin.class);
        setMixInAnnotation(Campsite.class, CampsiteMixin.class);
        setMixInAnnotation(Customer.class, CustomerMixin.class);
    }
}
