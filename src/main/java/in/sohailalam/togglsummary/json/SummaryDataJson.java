package in.sohailalam.togglsummary.json;

import java.util.List;

/**
 * The Toggl Summary Data Json Structure
 */
public class SummaryDataJson {
    private long id;
    private long time;
    private SummaryTitleJson title;
    private List<TotalCurrency> total_currencies;
    private List<SummaryItemJson> items;

    /**
     * Instantiates a new Summary data json.
     */
    public SummaryDataJson() {
    }

    /**
     * Instantiates a new Summary data json.
     *
     * @param id               the id
     * @param time             the time
     * @param title            the title
     * @param total_currencies the total currencies
     * @param items            the items
     */
    public SummaryDataJson(long id, long time, SummaryTitleJson title, List<TotalCurrency> total_currencies, List<SummaryItemJson> items) {
        this.id = id;
        this.time = time;
        this.title = title;
        this.total_currencies = total_currencies;
        this.items = items;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
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
     * Gets title.
     *
     * @return the title
     */
    public SummaryTitleJson getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(SummaryTitleJson title) {
        this.title = title;
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
     * Gets items.
     *
     * @return the items
     */
    public List<SummaryItemJson> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(List<SummaryItemJson> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "SummaryDataJson{" +
                "id=" + id +
                ", time=" + time +
                ", title=" + title +
                ", total_currencies=" + total_currencies +
                ", items=" + items +
                '}';
    }
}
