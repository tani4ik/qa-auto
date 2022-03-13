package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParsingJson {

    @Test
    void jsonTest () {

        String path = "src/test/resources/user.json";
        ObjectMapper mapper = new ObjectMapper();

        try {
            File jsonFile = new File (path);
            User user = mapper.readValue(jsonFile, User.class);
            assertThat(user.name).isEqualTo("John");
            assertThat(user.surname).isEqualTo("Galt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
