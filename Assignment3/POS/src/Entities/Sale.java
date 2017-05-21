package Entities;

import org.joda.time.DateTime;

/**
 * Created by nashm on 20/05/2017.
 */
public class Sale {
    private String id;
    private String customerId;
    private Time timeOfSale;
    private String drinkOrderedId;

    public Sale(String id, String customer, Time timeOfSale, String drinkOrdered) {
        this.id = id;
        this.customerId = customer;
        this.timeOfSale = timeOfSale;
        this.drinkOrderedId = drinkOrdered;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Time getTimeOfSale() {
        return timeOfSale;
    }

    public void setTimeOfSale(Time timeOfSale) {
        this.timeOfSale = timeOfSale;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDrinkOrderedId() {
        return drinkOrderedId;
    }

    public void setDrinkOrderedId(String drinkOrderedId) {
        this.drinkOrderedId = drinkOrderedId;
    }
}
