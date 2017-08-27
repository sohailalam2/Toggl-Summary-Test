package in.sohailalam.togglsummary.json;

/**
 * The Toggl Summary title json.
 */
public class SummaryTitleJson {
    private String project;
    private String color;
    private String hex_color;

    /**
     * Instantiates a new Summary title json.
     */
    public SummaryTitleJson() {
    }

    /**
     * Instantiates a new Summary title json.
     *
     * @param project   the project
     * @param color     the color
     * @param hex_color the hex color
     */
    public SummaryTitleJson(String project, String color, String hex_color) {
        this.project = project;
        this.color = color;
        this.hex_color = hex_color;
    }

    /**
     * Gets project.
     *
     * @return the project
     */
    public String getProject() {
        return project;
    }

    /**
     * Sets project.
     *
     * @param project the project
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Gets hex color.
     *
     * @return the hex color
     */
    public String getHex_color() {
        return hex_color;
    }

    /**
     * Sets hex color.
     *
     * @param hex_color the hex color
     */
    public void setHex_color(String hex_color) {
        this.hex_color = hex_color;
    }

    @Override
    public String toString() {
        return "SummaryTitleJson{" +
                "project='" + project + '\'' +
                ", color='" + color + '\'' +
                ", hex_color='" + hex_color + '\'' +
                '}';
    }
}
