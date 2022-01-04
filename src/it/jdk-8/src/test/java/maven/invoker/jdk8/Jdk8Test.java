package maven.invoker.it.jdk8;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

class Jdk8Test {

    @Test
    void jdk8() {
        assertNotNull(LocalDate.now());
    }

}
