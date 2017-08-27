package in.sohailalam.togglsummary.json;

/**
 * The Toggl Summary item title json data.
 */
public class SummaryItemTitleJson {
    private String time_entry;

    /**
     * Instantiates a new Summary item title json.
     */
    public SummaryItemTitleJson() {
    }

    /**
     * Instantiates a new Summary item title json.
     *
     * @param time_entry the time entry
     */
    public SummaryItemTitleJson(String time_entry) {
        this.time_entry = time_entry;
    }

    /**
     * Gets time entry.
     *
     * @return the time entry
     */
    public String getTime_entry() {
        return time_entry;
    }

    /**
     * Sets time entry.
     *
     * @param time_entry the time entry
     */
    public void setTime_entry(String time_entry) {
        this.time_entry = time_entry;
    }

    @Override
    public String toString() {
        return "SummaryItemTitleJson{" +
                "time_entry='" + time_entry + '\'' +
                '}';
    }
}
