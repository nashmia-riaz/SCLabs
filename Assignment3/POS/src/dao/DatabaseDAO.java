package dao;

import Entities.*;
import org.lightcouch.CouchDbClient;
import org.lightcouch.Document;

import java.util.List;

/**
 * Created by nashm on 20/05/2017.
 */
public class DatabaseDAO {
    public static void saveCustomer(CouchDbClient db, Customer customer){
        db.save(customer);
    }

    public void saveSale(CouchDbClient db, Sale sale){
        db.save(sale);
    }

    public void saveDrink(CouchDbClient db, Drink drink){
        db.save(drink);
    }

    public void saveItem(CouchDbClient db, Item item){
        db.save(item);
    }

    public static void saveEmployee(CouchDbClient db, Employee employee){
        db.save(employee);
    }

    public void saveAdmin(CouchDbClient db, Admin admin){
        db.save(admin);
    }

    public Customer getCustomer(CouchDbClient db, String id){
        List<Customer> customer = db.view("cafe/by_id")
                .key(id)
                .limit(1)
                .includeDocs(true)
                .query(Customer.class);

        return customer.get(0);
    }

    public Drink getDrink(CouchDbClient db, String id){
        List<Drink> drink = db.view("cafe/by_id")
                .key(id)
                .limit(1)
                .includeDocs(true)
                .query(Drink.class);

        return drink.get(0);
    }

    public Sale getSale(CouchDbClient db, String id){
        List<Sale> sale = db.view("cafe/by_id")
                .key(id)
                .limit(1)
                .includeDocs(true)
                .query(Sale.class);

        return sale.get(0);
    }

    public List<Customer> getAllCustomers(CouchDbClient db){
        List<Customer> customers = db.view("cafe/by_id")
                .includeDocs(true)
                .query(Customer.class);

        return customers;
    }

    public List<Sale> getAllSales(CouchDbClient db){
        List<Sale> sales = db.view("cafe/by_id")
                .limit(100)
                .includeDocs(true)
                .query(Sale.class);

        return sales;
    }

    public List<Drink> getAllDrinkss(CouchDbClient db){
        List<Drink> drinks = db.view("cafe/by_id")
                .includeDocs(true)
                .query(Drink.class);

        return drinks;
    }
    public int deleteEmployee(CouchDbClient db, Employee emp) {
        List<Document> d = db.view("cafe/by_id")
                .key(emp.getId())
                .limit(1)
                .includeDocs(true)
                .query(Document.class);

        if (d.size() <= 0) {
            System.out.println("Not found");
            return 0;
        }
        db.remove(d.get(0));
        return 1;

    }
    public int deleteCustomer(CouchDbClient db, Customer c) {
        List<Document> d = db.view("cafe/by_id")
                .key(c.getId())
                .limit(1)
                .includeDocs(true)
                .query(Document.class);

        if (d.size() <= 0) {
            System.out.println("Not found");
            return 0;
        }
        db.remove(d.get(0));
        return 1;

    }

    public List<Employee> getAllEmployees(CouchDbClient db){
        List<Employee> employees = db.view("cafe/by_id")
                .includeDocs(true)
                .query(Employee.class);
        return employees;
    }

    public List<Admin> getAllAdmins(CouchDbClient db){
        List<Admin> admins = db.view("cafe/by_id")
                .includeDocs(true)
                .query(Admin.class);

        return admins;
    }
    public List<Item> getAllItems(CouchDbClient db){
        List<Item> items = db.view("cafe/by_id")
                .includeDocs(true)
                .query(Item.class);

        return items;
    }

    public void deleteItem(CouchDbClient db, Item item){
        List <Document> d=db.view("cafe/by_id")
                .key(item.getId())
                .limit(1)
                .includeDocs(true)
                .query(Document.class);

        if(d.size()<=0){
            System.out.println("Not found");
            return;
        }

        db.remove(d.get(0));
    }

    public void updateItem(CouchDbClient db, Item item){
        List <Document> d=db.view("cafe/by_id")
                .key(item.getId())
                .limit(1)
                .includeDocs(true)
                .query(Document.class);

        if(d.size()<=0){
            System.out.println("Not found");
            return;
        }
        String doc_id = d.get(0).getId();
        System.out.println("DOC ID IS: "+doc_id);
        String query = "field=title&value=new value";
        query = "name="+item.getName()+"&amount="+Integer.toString(item.getAmount());

        String output = db.invokeUpdateHandler("cafe/update1", doc_id, query);
        System.out.println(output);

//        inventoryCafe1.update(item);
    }
}
