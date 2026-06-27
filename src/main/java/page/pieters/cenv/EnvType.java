package page.pieters.cenv;

public enum EnvType {
    DEFAULT("Default"),
    AWS_SECRET("AWS Secret");

    private final String description;

    private EnvType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
