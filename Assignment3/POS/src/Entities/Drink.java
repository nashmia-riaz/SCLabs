package Entities;

/**
 * Created by nashm on 20/05/2017.
 */
public class Drink {
    private String id;
    private String name;
    private int priceInRupees;

    public int getPriceInRupees() {
        return priceInRupees;
    }

    public void setPriceInRupees(int priceInRupees) {
        this.priceInRupees = priceInRupees;
    }


    public Drink(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.priceInRupees = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
