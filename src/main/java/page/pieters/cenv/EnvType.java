package page.pieters.cenv;

enum EnvType {
    NONE("None"),
    AWS_SECRET("AWS Secret");

    private String description;

    private EnvType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
