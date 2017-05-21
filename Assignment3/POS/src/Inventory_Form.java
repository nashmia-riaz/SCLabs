import dao.DatabaseDAO;
import Entities.Employee;
import Entities.Item;
import org.lightcouch.CouchDbClient;
import org.lightcouch.ReplicationResult;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nashm on 20/05/2017.
 */

class SimpleTableDemo implements TableModelListener {

    private Item item;
    private DatabaseDAO appDatabase;
    private CouchDbClient inventoryCafe1;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public SimpleTableDemo(JTable table) {
        table.getModel().addTableModelListener(this);
        item = new Item();
        appDatabase = new DatabaseDAO();
        inventoryCafe1 = new CouchDbClient("inventorycafe1", true, "http", "127.0.0.1", 5984, "nash","1234");

    }

    public void tableChanged(TableModelEvent e) {
        System.out.println("table changed");
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        if(model.getValueAt(row, column) != ""){
            Object data = model.getValueAt(row, column);

            Object ID = model.getValueAt(row, 0);
            Object Name = model.getValueAt(row, 1);
            Object Amount = model.getValueAt(row, 2);
    //        System.out.println(ID);
    //        System.out.println(Name);
    //        System.out.println(Amount);

            this.item.setId(ID.toString());
            this.item.setName(Name.toString());
            this.item.setAmount(Integer.valueOf(Amount.toString()));

            appDatabase.updateItem(this.inventoryCafe1, this.item);
        }

    }

}

class CellRenderer extends DefaultTableCellRenderer {
    Color backgroundColor = getBackground();
    private Component c;

    public int getTargetRow() {
        return targetRow;
    }

    public void setTargetRow(int targetRow) {
        this.targetRow = targetRow;
    }

    private int targetRow;

    public CellRenderer(){
        this.targetRow = -1;
    }
    public CellRenderer(int targetRow) {
        this.targetRow = targetRow;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        System.out.println(this.targetRow);
        if(targetRow == row){
            c.setBackground(Color.BLUE);
            c.setForeground(Color.WHITE);
        }
        else
        {c.setBackground(Color.WHITE);
        c.setForeground(Color.BLACK);}

        return this.c;
    }
}

public class Inventory_Form {
    public JPanel panel1;
    private JList inventoryList;
    public JTextField searchTField;
    private JButton searchButton;
    private JTextField getId;
    private JTextField getName;
    private JTextField getPrice;
    private JButton addButton;
    private JButton deleteButton;
    private JLabel appTitle;
    private JLabel windowTitle;
    private JLabel IDLabel;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JTable inventoryTable;
    private int highlightRow;

    private String[][] loadDatabase(CouchDbClient db, DatabaseDAO dbdao){
        List<Item> items = dbdao.getAllItems(db);

        String itemNames[][] = new String[items.size()][3];
        for (int i = 0; i<items.size(); i++){
            itemNames[i][0]= items.get(i).getId();
            itemNames[i][1]= items.get(i).getName();
            itemNames[i][2]= Integer.toString(items.get(i).getAmount());
        }
        return itemNames;
    }


    Inventory_Form() {

        Font title = new Font("Times new roman", 1, 40);
        appTitle.setFont(title);
        windowTitle.setFont(new Font("Times New Roman", 1, 20));
        System.out.println("Constructor");
        DatabaseDAO appDatabase = new DatabaseDAO();
        CouchDbClient inventoryCafe1 = new CouchDbClient("inventorycafe1", true, "http", "127.0.0.1", 5984, "nash", "1234");
        ReplicationResult  result = inventoryCafe1.replication()
                .source("inventorycafe1")
                .target("https://couchdb-8496a0.smileupps.com/inventorycafe1")
                .continuous(true)
                .trigger();
        System.out.println(result);

        //*************DISPLAY TABLE****************
//        List<Item> items = appDatabase.getAllItems(inventoryCafe1);
//
//        String itemNames[][] = new String[items.size()][3];
//        for (int i = 0; i<items.size(); i++){
//            itemNames[i][0]= items.get(i).getId();
//            itemNames[i][1]= items.get(i).getName();
//            itemNames[i][2]= Integer.toString(items.get(i).getAmount());
//        }
        String itemNames[][] = loadDatabase(inventoryCafe1, appDatabase);
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"ID", "Name", "Amount"};

        model.setDataVector(itemNames, columnNames);
        inventoryTable.setModel(model);
        inventoryTable.setRowSelectionAllowed(true);
        model.fireTableDataChanged();
        SimpleTableDemo d = new SimpleTableDemo(inventoryTable);
        //****************************************************************
        //********************SEARCH TABLE************************
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = searchTField.getText();
                inventoryTable.setDefaultRenderer(Object.class, new CellRenderer(2));
                    for(highlightRow=0; highlightRow<inventoryTable.getRowCount(); highlightRow++){
                        if(name.toString().contains(inventoryTable.getValueAt(highlightRow, 1).toString())){
                            CellRenderer table = new CellRenderer(highlightRow);
                            inventoryTable.setDefaultRenderer(Object.class, table);
                            inventoryTable.repaint();
                            break;
                        }
                    }
            }


        });

        //****************************************************************
        //**********************Add Row***********************************
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = getId.getText();
                String Name = getName.getText();
                int Amount = Integer.valueOf(getPrice.getText());

                Item i = new Item(ID, Name, Amount);
                appDatabase.saveItem(inventoryCafe1, i);

                String[][] items = loadDatabase(inventoryCafe1, appDatabase);
                String[] columnNames = {"ID", "Name", "Amount"};

                inventoryTable.setModel(new DefaultTableModel(items,columnNames));
            }


        });

        //****************************************************************
        //**********************REMOVE ITEM FROM TABLE********************
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = inventoryTable.getSelectedRow();
                String ID = inventoryTable.getValueAt(row, 0).toString();
                String Name = inventoryTable.getValueAt(row, 1).toString();
                int Amount = Integer.valueOf(inventoryTable.getValueAt(row, 2).toString());

                Item i = new Item(ID, Name, Amount);
                appDatabase.deleteItem(inventoryCafe1, i);

                String[][] items = loadDatabase(inventoryCafe1, appDatabase);
                String[] columnNames = {"ID", "Name", "Amount"};

                inventoryTable.setModel(new DefaultTableModel(items,columnNames));
            }


        });
        //****************************************************************


    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Inventory_Form");

        frame.setSize(1600,900);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new Inventory_Form().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
