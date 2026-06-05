package page.pieters.cenv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CenvTest {

    @Test
    void resolvesStandardSystemProperty() {    
        // java.home is set by the JVM on every platform
        Cenv cenv = new Cenv("java.home");
        assertNotNull(cenv.getValue());
        assertFalse(cenv.getValue().isEmpty());
    }

    @Test
    void resolvesStandardEnvironmentVariable() {
        // PATH is present on Linux, macOS, and Windows
        Cenv cenv = new Cenv("PATH");
        assertNotNull(cenv.getValue());
        assertFalse(cenv.getValue().isEmpty());
    }
}
