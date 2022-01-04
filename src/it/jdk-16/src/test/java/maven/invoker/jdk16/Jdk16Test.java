package maven.invoker.it.jdk16;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

class Jdk16Test {

    @Test
    void jdk16() {
        assertNotNull("String".stripIndent());
    }

}
