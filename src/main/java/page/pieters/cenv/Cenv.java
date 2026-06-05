package page.pieters.cenv;

public class Cenv {

    String key;
    String value;

    public Cenv(String key) {

        this.key = key;
        this.value = getEnv(key);
    }

    public String getValue() {
        return value;
    }

    private String getEnv(String key) {

        String value = System.getProperty(key);

        if (value == null || value.isEmpty()) {
            
            value = System.getenv(key);
        }

        return value;
    }
}
