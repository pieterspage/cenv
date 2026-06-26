package page.pieters.cenv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    void envTypeParsesSubKeysFromFlatJson() {
        System.setProperty("cenv.test.json", "{\"username\":\"admin\",\"password\":\"secret\"}");
        try {
            Cenv cenv = new Cenv(EnvType.AWS_SECRET, "cenv.test.json");
            assertEquals("admin", cenv.getSubKey("username"));
            assertEquals("secret", cenv.getSubKey("password"));
        } finally {
            System.clearProperty("cenv.test.json");
        }
    }

    @Test
    void envTypeStoresCorrectEnvType() {
        System.setProperty("cenv.test.type", "{\"key\":\"value\"}");
        try {
            Cenv cenv = new Cenv(EnvType.AWS_SECRET, "cenv.test.type");
            assertEquals(EnvType.AWS_SECRET, cenv.envType);
        } finally {
            System.clearProperty("cenv.test.type");
        }
    }

    @Test
    void envTypeWithMissingKeyProducesEmptySubKeys() {
        Cenv cenv = new Cenv(EnvType.AWS_SECRET, "cenv.test.nonexistent.key");
        assertNull(cenv.getSubKey("anything"));
    }

    @Test
    void envTypeParsesSubKeysFromNonFlatJson() {
        System.setProperty("cenv.test.nested", "{\"username\":\"admin\",\"address\":{\"city\":\"NYC\"}}");
        try {
            Cenv cenv = new Cenv(EnvType.AWS_SECRET, "cenv.test.nested");
            // Flat keys preceding the nested object are still parsed correctly
            assertEquals("admin", cenv.getSubKey("username"));
            // The nested object value is not supported; the "address" key is absent
            assertNull(cenv.getSubKey("address"));
        } finally {
            System.clearProperty("cenv.test.nested");
        }
    }
}
