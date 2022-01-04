package maven.invoker.it.jdk11;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

class Jdk11Test {

    @Test
    void jdk11() {
        assertFalse("String".isBlank());
    }

}
