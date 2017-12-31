package net.seabears.campsites.api.serialization;

import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@JsonTest
public class AreaJsonTest {
    @Autowired
    private JacksonTester<Area> json;

    @Test
    public void serializeJson() throws IOException {
        final Area obj = new Area();
        obj.setCampground(new Campground());
        obj.getCampground().setId(200);

        final JsonContent<Area> content = json.write(obj);
        System.out.println(content.getJson());
        assertThat(content).extractingJsonPathNumberValue("@.campgroundId").isEqualTo(200);
    }
}
