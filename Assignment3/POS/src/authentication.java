//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import Entities.*;
import dao.DatabaseDAO;
import org.jfree.ui.RefineryUtilities;
import org.lightcouch.CouchDbClient;
import org.lightcouch.DesignDocument;
import org.lightcouch.ReplicationResult;
import org.lightcouch.Response;
import BOs.SalesReporting.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class authentication extends JFrame{
    private int sale;
    DatabaseDAO appDatabase = new DatabaseDAO();
    public static void main(String[] args) {
        new authentication();

    }

    public authentication()
    {
        DesignDocument designDoc;
        sale = 41;
        CouchDbClient salesCafe1 = new CouchDbClient("salescafe1", true, "http", "127.0.0.1", 5984, "nash","1234");
        CouchDbClient customerCafe1 = new CouchDbClient("customerscafe1", true, "http", "127.0.0.1", 5984, "nash","1234");
        CouchDbClient employeeCafe1 = new CouchDbClient("employeecafe1", true, "http", "127.0.0.1", 5984, "nash","1234");
        CouchDbClient adminCafe1 = new CouchDbClient("admincafe1", true, "http", "127.0.0.1", 5984, "nash","1234");
        CouchDbClient drinksCafe1 = new CouchDbClient("drinkscafe1", true, "http", "127.0.0.1", 5984, "nash","1234");
        CouchDbClient inventoryCafe1 = new CouchDbClient("inventorycafe1", true, "http", "127.0.0.1", 5984, "nash","1234");

        Response response;

        designDoc = adminCafe1.design().getFromDesk("cafe");
        response = adminCafe1.design().synchronizeWithDb(designDoc);
        //  System.out.println(response);

        designDoc = employeeCafe1.design().getFromDesk("cafe");
        response = employeeCafe1.design().synchronizeWithDb(designDoc);
        //  System.out.println(response);

        designDoc = customerCafe1.design().getFromDesk("cafe");
        response = customerCafe1.design().synchronizeWithDb(designDoc);
        //  System.out.println(response);

        designDoc = salesCafe1.design().getFromDesk("cafe");
        response = salesCafe1.design().synchronizeWithDb(designDoc);
        //  System.out.println(response);

        designDoc = inventoryCafe1.design().getFromDesk("cafe");
        response = inventoryCafe1.design().synchronizeWithDb(designDoc);
        //  System.out.println(response);

        designDoc = drinksCafe1.design().getFromDesk("cafe");
        response = drinksCafe1.design().synchronizeWithDb(designDoc);
        //  System.out.println(response);


        //*****code for replicating the databases to central database*****
        ReplicationResult result;
        result = employeeCafe1.replication()
                .source("employeecafe1")
                .target("https://couchdb-8496a0.smileupps.com/employeescafe1")
                .continuous(true)
                .trigger();
        //     System.out.println(result);

        result = adminCafe1.replication()
                .source("admincafe1")
                .target("https://couchdb-8496a0.smileupps.com/admincafe1")
                .continuous(true)
                .trigger();

        result = customerCafe1.replication()
                .source("customercafe1")
                .target("https://couchdb-8496a0.smileupps.com/customercafe1")
                .continuous(true)
                .trigger();

        result = inventoryCafe1.replication()
                .source("inventorycafe1")
                .target("https://couchdb-8496a0.smileupps.com/inventorycafe1")
                .continuous(true)
                .trigger();
        //     System.out.println(result);

        result = drinksCafe1.replication()
                .source("drinkscafe1")
                .target("https://couchdb-8496a0.smileupps.com/drinkscafe1")
                .continuous(true)
                .trigger();

        result = salesCafe1.replication()
                .source("salescafe1")
                .target("https://couchdb-8496a0.smileupps.com/salescafe1")
                .continuous(true)
                .trigger();
        //    System.out.println(result);

        JFrame guiFrame = new JFrame();
        JFrame adminframe = new JFrame();
        JFrame manageframe = new JFrame();
        JFrame employeeframe = new JFrame();
        JFrame orderframe = new JFrame();

        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        manageframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orderframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Point of sale system");
        guiFrame.setSize(600,600);
        adminframe.setSize(600,600);
        manageframe.setSize(600,600);
        employeeframe.setSize(600,600);
        orderframe.setSize(600,600);

        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        adminframe.setLocationRelativeTo(null);
        manageframe.setLocationRelativeTo(null);
        employeeframe.setLocationRelativeTo(null);
        orderframe.setLocationRelativeTo(null);

        JPanel headerpanel = new JPanel();
        JPanel btnpanel= new JPanel();
        JPanel userpanel= new JPanel();
        JPanel passpanel= new JPanel();
        JPanel exit = new JPanel();
        JPanel statuspanel = new JPanel();

        JLabel header = new JLabel("Welcome!");
        JLabel loginas = new JLabel("Login as: ");
        JLabel or = new JLabel("OR ");

        JButton adminbtn = new JButton( "Administrator");
        JButton empbtn = new JButton( "Employee");
        JButton exitbtn = new JButton("Exit.");

        JButton viewgraphbtn = new JButton( "View Charts");
        JButton manageempbtn = new JButton("Manage Employee");
        JButton viewlist1 = new JButton();
        JButton search1 = new JButton();
        JButton delete1 = new JButton();
        JButton add1 = new JButton();

        JButton viewlist2 = new JButton();
        JButton search2 = new JButton();
        JButton delete2 = new JButton();
        JButton add2 = new JButton();

        JButton manageinvetory = new JButton("Manage Inventory");
        JButton managecustomer =  new JButton ("Manage Customer");
        JButton placeorder = new JButton("Place Order");

        JLabel usernamelabel = new JLabel("Username:");
        JTextField usernamefield = new JTextField(20);
        JLabel passlabel = new JLabel("Password:");
        JLabel statuslabel = new JLabel("Enter Username and Password.");
        JTextField passfield = new JTextField(20);
        header.setForeground(Color.blue);
        headerpanel.add(header);
        statuspanel.add(statuslabel);
        userpanel.add(usernamelabel);
        userpanel.add(usernamefield);
        passpanel.add(passlabel);
        passpanel.add(passfield);
        btnpanel.add(loginas);
        btnpanel.add(adminbtn);
        btnpanel.add(empbtn);
        exit.add(or);
        exit.add(exitbtn);

        JLabel ordertitle = new JLabel("Place an Order");
        JLabel customeridLabel = new JLabel("Customer id:");
        JLabel customernameLabel = new JLabel("Customer Name:");
        JLabel itemidlabel = new JLabel("Itemid:");
        JLabel Quantitylabel = new JLabel("Quantity:");

        JButton finalizeorder = new JButton("Place Order");
        JTextField userid = new JTextField(20);
        JTextField itemid = new JTextField(20);
        JTextField user_name = new JTextField(20);
        JTextField quant = new JTextField(3);

        JPanel orderhead = new JPanel();
        JPanel customeridpanel = new JPanel();
        JPanel customernamepanel = new JPanel();
        JPanel itempanel = new JPanel();
        JPanel finalize = new JPanel();
        JPanel pricetotal = new JPanel();

        orderhead.add(ordertitle);
        customeridpanel.add(customeridLabel);
        customeridpanel.add(userid);
        customernamepanel.add(customernameLabel);
        customernamepanel.add(user_name);
        itempanel.add(itemidlabel);
        itempanel.add(itemid);
        itempanel.add(Quantitylabel);
        itempanel.add(quant);
        finalize.add(finalizeorder);


        JLabel tpricelabel = new JLabel("Total Price:");
        JLabel tprice = new JLabel("");

        pricetotal.add(tpricelabel);
        pricetotal.add(tprice);



        adminbtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                java.util.List<Admin> admins = appDatabase.getAllAdmins(adminCafe1);
                for(int i =0; i<admins.size(); i++) {
                    String un1 = admins.get(i).getUsername();
                    String un2 = usernamefield.getText();
                    String p1 = admins.get(i).getPassword();
                    String p2 = passfield.getText();
                    if (un1.equals(un2) && p1.equals(p2)) {
                        System.out.println("User is Authenticated");
                        statuslabel.setText("Authenticated");
                        guiFrame.setVisible(false);
                        header.setText("Welcome Admin: "+un1);
                        adminframe.setLayout(new GridLayout(0,1));
                        adminframe.add(header);
                        adminframe.add(viewgraphbtn);
                        adminframe.add(manageempbtn);
                        adminframe.setVisible(true);
                    }
                    else {
                        System.out.println("User is NOT Authenticated");
                        statuslabel.setText("Wrong Input. Please Enter Correct Username and Password");
                        statuslabel.setForeground(Color.red);
                    }
                }
            }
        });
        empbtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("User is Authenticated");
                java.util.List<Employee> emps = appDatabase.getAllEmployees(employeeCafe1);
                System.out.println(emps.size());
                for(int i =0; i<emps.size(); i++) {

                    String un1 = emps.get(i).getUsername();
                    String un2 = usernamefield.getText();
                    String p1 = emps.get(i).getPassword();
                    String p2 = passfield.getText();
                     if (un1.equals(un2) && p1.equals(p2)) {
                        System.out.println("User is Authenticated");
                        statuslabel.setText("Authenticated");
                        guiFrame.setVisible(false);
                        employeeframe.setLayout(new GridLayout(0,1));
                        employeeframe.add(managecustomer);
                        employeeframe.add(manageinvetory);
                        employeeframe.add(placeorder);
                        employeeframe.setVisible(true);
                        break;
                    }
                    else {
                        System.out.println("User is NOT Authenticated");
                        statuslabel.setText("Wrong Input. Please Enter Correct Username and Password");
                        statuslabel.setForeground(Color.red);
                    }
                }
            }
        });
        manageinvetory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiFrame.setSize(1600,900);
                guiFrame.setContentPane(new Inventory_Form().panel1);
                guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                guiFrame.pack();
                guiFrame.setVisible(true);

            }
        });
        manageempbtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                adminframe.setVisible(false);
                manageframe.setLayout(new GridLayout(0,1));
                viewlist1.setText("View Employee list");
                add1.setText("Add Employee");
                delete1.setText("Delete Employee");
                search1.setText("Search Employee");
                JPanel headpanel = new JPanel();

                headpanel.add(viewlist1);
                headpanel.add(search1);
                headpanel.add(add1);
                headpanel.add(delete1);
                manageframe.add(headpanel);
                manageframe.setVisible(true);
            }
        });
        managecustomer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                employeeframe.setVisible(false);
                manageframe.setLayout(new GridLayout(0,1));
                viewlist2.setText("View Customer list");
                add2.setText("Add Customer");
                delete2.setText("Delete Customer");
                search2.setText("Search Customer");
                JPanel headpanel = new JPanel();

                headpanel.add(viewlist2);
                headpanel.add(search2);
                headpanel.add(add2);
                headpanel.add(delete2);
                manageframe.add(headpanel);
                manageframe.setVisible(true);
            }
        });

        viewgraphbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                WeeklySalesCount weeklySalesCount = new WeeklySalesCount("Comparison", "Number of sales on each day of the week");
                weeklySalesCount.pack();
                weeklySalesCount.setVisible(true);
                TimeOfDaySales chart = new TimeOfDaySales(
                        "Sales Vs Time" ,
                        "Number of Items Sold vs Time of day");
                chart.pack();
                RefineryUtilities.centerFrameOnScreen( chart );
                chart.setVisible(true);
            }
        });
        placeorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //guiFrame.setVisible(false);
                orderframe.setLayout(new GridLayout(0,1));


                //orderframe.add(xyzbutton);

                orderframe.setVisible(true);

            }
        });

        viewlist1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                java.util.List<Employee> emps = appDatabase.getAllEmployees(employeeCafe1);


                String emp[][] = new String[emps.size()][3];

                for (int i = 0; i < emps.size(); i++) {
                    emp[i][0] = emps.get(i).getId();
                    emp[i][1] = emps.get(i).getName();
                    emp[i][2] = emps.get(i).getSSN();
                }

                String column[]={"ID","NAME","SSN"};
                JTable jt=new JTable(emp,column);
                jt.setBounds(30,40,200,300);
                JScrollPane sp=new JScrollPane(jt);
                manageframe.add(sp);
                manageframe.setVisible(true);

            }
        });

        add1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                JTextField j1 = new JTextField(" ");
                JTextField j2 = new JTextField(" ");
                JTextField j3 = new JTextField(" ");
                JTextField j4 = new JTextField(" ");
                JTextField j5 = new JTextField(" ");
                JButton submit = new JButton("Submit");
                manageframe.add(new JLabel("ID: "));
                manageframe.add(j1);
                manageframe.add(new JLabel("Name: "));
                manageframe.add(j2);
                manageframe.add(new JLabel("SSN: "));
                manageframe.add(j3);
                manageframe.add(new JLabel("UserName: "));
                manageframe.add(j4);
                manageframe.add(new JLabel("Password: "));
                manageframe.add(j5);
                manageframe.add(submit);
                manageframe.setVisible(true);
                submit.addActionListener(event1 -> {
                    Employee e1 = new Employee(j1.getText(), j2.getText(), j3.getText(), j4.getText(), j5.getText());
                    DatabaseDAO.saveEmployee(employeeCafe1, e1);
                    manageframe.setVisible(false);
                    j1.setText("");j2.setText("");j3.setText("");j4.setText("");j5.setText("");

                    guiFrame.setVisible(true);
                });
            }
        });
        search1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JPanel p1 = new JPanel();
                JTextField j1 = new JTextField(20);
                JButton submit = new JButton("Search");
                p1.add(new JLabel("ID: "));
                p1.add(j1);
                p1.add(submit);
                manageframe.add(p1);
                manageframe.setVisible(true);
                submit.addActionListener(event1 -> {
                    int notfound = 0;
                    java.util.List<Employee> emps = appDatabase.getAllEmployees(employeeCafe1);
                    for (int i = 0; i < emps.size(); i++) {
                        if(emps.get(i).getId().equals(j1.getText())) {
                            manageframe.add(new JLabel("Name: "+emps.get(i).getName()));
                            manageframe.add(new JLabel("SSN: "+emps.get(i).getSSN()));
                            manageframe.add(new JLabel("USERNAME: "+emps.get(i).getUsername()));
                            notfound = 1;
                            break;
                        }
                    }
                    if(notfound==0)
                        manageframe.add(new JLabel("Not Found"));
                    manageframe.setVisible(true);
                });
            }
        });
        delete1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JPanel p1 = new JPanel();
                JTextField j1 = new JTextField(20);
                JButton submit = new JButton("Delete");
                p1.add(new JLabel("Enter ID: "));
                p1.add(j1);
                p1.add(submit);
                manageframe.add(p1);
                manageframe.setVisible(true);
                submit.addActionListener(event1 -> {
                    int notfound = 0;
                    java.util.List<Employee> emps = appDatabase.getAllEmployees(employeeCafe1);
                    Employee e = new Employee(j1.getText().toString(), "", "", "","");
                    notfound = appDatabase.deleteEmployee(employeeCafe1, e);
                    if(notfound==1) {
//                            System.out.println(emps.get(i).getId());
                            //appDatabase.deleteEmployee(employeeCafe1,emps.get(i));
                            manageframe.add(new JLabel("Deleted"));
//                            notfound = 1;
//                            break;
                        }

                    if(notfound==0)
                        manageframe.add(new JLabel("Not Found"));
                    manageframe.setVisible(true);
                });
            }
        });


        viewlist2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                java.util.List<Customer> emps = appDatabase.getAllCustomers(customerCafe1);


                String emp[][] = new String[emps.size()][4];

                for (int i = 0; i < emps.size(); i++) {
                    emp[i][0] = emps.get(i).getId();
                    emp[i][1] = emps.get(i).getName();
                    emp[i][2] = Integer.toString(emps.get(i).getAge());
                    emp[i][3] = emps.get(i).getGender();
                }

                String column[]={"ID","NAME","Age","Gender"};
                JTable jt=new JTable(emp,column);
                jt.setBounds(30,40,200,300);
                JScrollPane sp=new JScrollPane(jt);
                manageframe.add(sp);
                manageframe.setVisible(true);

            }
        });

        add2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                JTextField j1 = new JTextField(" ");
                JTextField j2 = new JTextField(" ");
                JTextField j3 = new JTextField(" ");
                JTextField j4 = new JTextField(" ");

                JButton submit = new JButton("Submit");
                manageframe.add(new JLabel("ID: "));
                manageframe.add(j1);
                manageframe.add(new JLabel("Name: "));
                manageframe.add(j2);
                manageframe.add(new JLabel("Gender: "));
                manageframe.add(j3);
                manageframe.add(new JLabel("Age: "));
                manageframe.add(j4);
                manageframe.add(submit);
                manageframe.setVisible(true);
                submit.addActionListener(event1 -> {
                    Customer c1 = new Customer(j1.getText(), j2.getText(), j3.getText(), Integer.parseInt(j4.getText()));
                    DatabaseDAO.saveCustomer(customerCafe1, c1);
                    manageframe.setVisible(false);
                    j1.setText("");j2.setText("");j3.setText("");j4.setText("");

                    guiFrame.setVisible(true);
                });
            }
        });
        search2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JPanel p1 = new JPanel();
                JTextField j1 = new JTextField(20);
                JButton submit = new JButton("Search");
                p1.add(new JLabel("ID: "));
                p1.add(j1);
                p1.add(submit);
                manageframe.add(p1);
                manageframe.setVisible(true);
                submit.addActionListener(event1 -> {
                    int notfound = 0;
                    java.util.List<Customer> emps = appDatabase.getAllCustomers(customerCafe1);
                    for (int i = 0; i < emps.size(); i++) {
                        if(emps.get(i).getId().equals(j1.getText())) {
                            manageframe.add(new JLabel("Name: "+emps.get(i).getName()));
                            manageframe.add(new JLabel("ID: "+emps.get(i).getId()));
                            manageframe.add(new JLabel("Gender: "+emps.get(i).getGender()));
                            notfound = 1;
                            break;
                        }
                    }
                    if(notfound==0)
                        manageframe.add(new JLabel("Not Found"));
                    manageframe.setVisible(true);
                });
            }
        });
        delete2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JPanel p1 = new JPanel();
                JTextField j1 = new JTextField(20);
                JButton submit = new JButton("Delete");
                p1.add(new JLabel("Enter ID: "));
                p1.add(j1);
                p1.add(submit);
                manageframe.add(p1);
                manageframe.setVisible(true);
                submit.addActionListener(event1 -> {
                    int notfound = 0;
                    Customer c = new Customer(j1.getText().toString(), "", "", 0);
                    notfound = appDatabase.deleteCustomer(customerCafe1, c);

                    if(notfound ==1){
                        manageframe.add(new JLabel("Deleted"));
                    }
                    if(notfound==0)
                        manageframe.add(new JLabel("Not Found"));
                    manageframe.setVisible(true);
                });
            }
        });

        exitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                guiFrame.setVisible(false);
            }
        });

        placeorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                employeeframe.setVisible(false);
                orderframe.setLayout(new GridLayout(0,1));

                orderframe.add(orderhead);
                orderframe.add(customeridpanel);
                orderframe.add(customernamepanel);
                orderframe.add(itempanel);
                orderframe.add(finalize);
                orderframe.add(pricetotal);
                pricetotal.setVisible(false);
                orderframe.setVisible(true);

            }
        });
        finalizeorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String u = user_name.getText();
                String id = userid.getText();
                String ident = itemid.getText();
                String saleID = "S0"+Integer.toString(sale);
                sale++;
                Sale s = new Sale (saleID, id.toString(), new Time(), ident.toString());
                appDatabase.saveSale(salesCafe1, s);

                Drink d = appDatabase.getDrink(drinksCafe1, ident.toString());
                int price = d.getPriceInRupees();
                tprice.setText(Integer.toString(price));
                pricetotal.setVisible(true);



            }
        });

        //END
        guiFrame.setLayout(new GridLayout(0,1));
        guiFrame.add(headerpanel);
        guiFrame.add(statuspanel);
        guiFrame.add(userpanel);
        guiFrame.add(passpanel);
        guiFrame.add(btnpanel);
        guiFrame.add(exit);

        guiFrame.setVisible(true);
    }

}
