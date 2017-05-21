package Entities;

/**
 * Created by nashm on 20/05/2017.
 */
public class Item {
    private String id;
    private String name;
    private int amount;

    public Item(){}
    public Item(String id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
