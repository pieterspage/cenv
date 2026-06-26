package page.pieters.cenv;

import java.util.HashMap;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Cenv {

    private static final Logger LOGGER = Logger.getLogger(Cenv.class.getName());

    EnvType envType;
    String key;
    String value;
    HashMap<String, String> subKeys;

    public Cenv(String key) {
        this.envType = EnvType.NONE;
        this.key = key;
        this.value = getEnv(key);
    }

    public Cenv(EnvType envType, String key) {
        this.envType = envType;
        this.key = key;

        try {
            this.subKeys = JParse.parseFlatJson(getEnv(key));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to to parse flat JSON string", e);
        }
    }

    public String getValue() {
        return value;
    }

    public String getSubKey(String subKey) {
        return subKeys.get(subKey);
    }

    private String getEnv(String key) {

        String value = System.getProperty(key);

        if (value == null || value.isEmpty()) {
            value = System.getenv(key);
        }

        return value;
    }
}
