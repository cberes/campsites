package net.seabears.campsites.api.serialization;

import net.seabears.campsites.db.domain.Campsite;
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
public class CampsiteJsonTest {
    @Autowired
    private JacksonTester<Campsite> json;

    @Test
    public void serializeJson() throws IOException {
        Campsite obj = new Campsite.Builder()
                .withAreaId(100)
                .withCampgroundId(200)
                .withName("Site A")
                .build();

        final JsonContent<Campsite> content = json.write(obj);
        System.out.println(content.getJson());
        assertThat(content).extractingJsonPathNumberValue("@.areaId").isEqualTo(100);
        assertThat(content).extractingJsonPathNumberValue("@.campgroundId").isEqualTo(200);
    }
}
