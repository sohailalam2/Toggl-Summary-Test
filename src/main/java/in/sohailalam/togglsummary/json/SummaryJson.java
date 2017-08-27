package in.sohailalam.togglsummary.json;

import java.util.List;

/**
 * The Toggl Summary json.
 */
public class SummaryJson {

    private long total_grand;
    private long total_billable;
    private List<TotalCurrency> total_currencies;
    private List<SummaryDataJson> data;

    /**
     * Instantiates a new Summary json.
     */
    public SummaryJson() {
    }

    /**
     * Instantiates a new Summary json.
     *
     * @param total_grand      the total grand
     * @param total_billable   the total billable
     * @param total_currencies the total currencies
     * @param data             the data
     */
    public SummaryJson(long total_grand, long total_billable, List<TotalCurrency> total_currencies, List<SummaryDataJson> data) {
        this.total_grand = total_grand;
        this.total_billable = total_billable;
        this.total_currencies = total_currencies;
        this.data = data;
    }

    /**
     * Gets total grand.
     *
     * @return the total grand
     */
    public long getTotal_grand() {
        return total_grand;
    }

    /**
     * Sets total grand.
     *
     * @param total_grand the total grand
     */
    public void setTotal_grand(long total_grand) {
        this.total_grand = total_grand;
    }

    /**
     * Gets total billable.
     *
     * @return the total billable
     */
    public long getTotal_billable() {
        return total_billable;
    }

    /**
     * Sets total billable.
     *
     * @param total_billable the total billable
     */
    public void setTotal_billable(long total_billable) {
        this.total_billable = total_billable;
    }

    /**
     * Gets total currencies.
     *
     * @return the total currencies
     */
    public List<TotalCurrency> getTotal_currencies() {
        return total_currencies;
    }

    /**
     * Sets total currencies.
     *
     * @param total_currencies the total currencies
     */
    public void setTotal_currencies(List<TotalCurrency> total_currencies) {
        this.total_currencies = total_currencies;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public List<SummaryDataJson> getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(List<SummaryDataJson> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SummaryJson{" +
                "total_grand=" + total_grand +
                ", total_billable=" + total_billable +
                ", total_currencies=" + total_currencies +
                ", data=" + data +
                '}';
    }
}

