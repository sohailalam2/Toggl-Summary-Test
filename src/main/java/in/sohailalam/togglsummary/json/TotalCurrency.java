package in.sohailalam.togglsummary.json;

/**
 * The Toggl Total currency.
 */
public class TotalCurrency {
    private String currency;
    private Double amount;

    /**
     * Instantiates a new Total currency.
     */
    public TotalCurrency() {
    }

    /**
     * Instantiates a new Total currency.
     *
     * @param currency the currency
     * @param amount   the amount
     */
    public TotalCurrency(String currency, Double amount) {
        this.currency = currency;
        this.amount = amount;
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
     * Gets amount.
     *
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TotalCurrency{" +
                "currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }
}
