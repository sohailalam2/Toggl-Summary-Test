package in.sohailalam.togglsummary.db.schema;

import in.sohailalam.togglsummary.json.SummaryItemJson;

import java.util.Arrays;
import java.util.List;

/**
 * The Summary item database schema.
 */
public class SummaryItemSchema {

    private String title;
    private long time;
    private String currency;
    private String rate;

    /**
     * Summary item schema headers list.
     *
     * @return the list
     */
    public static List<String> summaryItemSchemaHeaders() {
        return Arrays.asList("title", "time", "currency", "rate");
    }

    /**
     * Summary item schema values list.
     *
     * @return the list
     */
    public List<String> summaryItemSchemaValues() {
        return Arrays.asList(this.title, String.valueOf(this.time), this.currency, this.rate);
    }

    /**
     * Instantiates a new Summary item schema.
     */
    public SummaryItemSchema() {
    }

    /**
     * Instantiates a new Summary item schema.
     *
     * @param json the json
     */
    public SummaryItemSchema(SummaryItemJson json) {
        this.title = json.getTitle().getTime_entry();
        this.time = json.getTime();
        this.currency = json.getCur();
        this.rate = json.getRate();
    }

    /**
     * Instantiates a new Summary item schema.
     *
     * @param title    the title
     * @param time     the time
     * @param currency the currency
     * @param rate     the rate
     */
    public SummaryItemSchema(String title, long time, String currency, String rate) {
        this.title = title;
        this.time = time;
        this.currency = currency;
        this.rate = rate;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
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
     * Gets currency.
     *
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets currency.
     *
     * @param currency the currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
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
        return "SummaryItemSchema{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", currency='" + currency + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
