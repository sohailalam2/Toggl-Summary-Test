package in.sohailalam.togglsummary.json;

/**
 * The Toggl Summary item json data.
 */
public class SummaryItemJson {
    private SummaryItemTitleJson title;
    private long time;
    private String cur;
    private long sum;
    private String rate;

    /**
     * Instantiates a new Summary item json.
     */
    public SummaryItemJson() {
    }

    /**
     * Instantiates a new Summary item json.
     *
     * @param title the title
     * @param time  the time
     * @param cur   the cur
     * @param sum   the sum
     * @param rate  the rate
     */
    public SummaryItemJson(SummaryItemTitleJson title, long time, String cur, long sum, String rate) {
        this.title = title;
        this.time = time;
        this.cur = cur;
        this.sum = sum;
        this.rate = rate;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public SummaryItemTitleJson getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(SummaryItemTitleJson title) {
        this.title = title;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public long getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Gets cur.
     *
     * @return the cur
     */
    public String getCur() {
        return cur;
    }

    /**
     * Sets cur.
     *
     * @param cur the cur
     */
    public void setCur(String cur) {
        this.cur = cur;
    }

    /**
     * Gets sum.
     *
     * @return the sum
     */
    public long getSum() {
        return sum;
    }

    /**
     * Sets sum.
     *
     * @param sum the sum
     */
    public void setSum(long sum) {
        this.sum = sum;
    }

    /**
     * Gets rate.
     *
     * @return the rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * Sets rate.
     *
     * @param rate the rate
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "SummaryItemJson{" +
                ", title=" + title +
                ", time=" + time +
                ", cur='" + cur + '\'' +
                ", sum=" + sum +
                ", rate='" + rate + '\'' +
                '}';
    }
}
